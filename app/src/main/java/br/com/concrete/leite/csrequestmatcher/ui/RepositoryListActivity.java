package br.com.concrete.leite.csrequestmatcher.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import br.com.concrete.leite.csrequestmatcher.R;
import br.com.concrete.leite.csrequestmatcher.model.Repository;
import br.com.concrete.leite.csrequestmatcher.model.ResponseData;
import br.com.concrete.leite.csrequestmatcher.ui.adapter.RepositoryListAdapter;
import br.com.concrete.leite.csrequestmatcher.ui.app.App;
import br.com.concrete.leite.csrequestmatcher.ui.divider.ListDivider;
import br.com.concrete.leite.csrequestmatcher.ui.scroll.EndlessScrollListener;
import br.com.concrete.leite.csrequestmatcher.viewmodel.RepositoryListViewModel;

@EActivity(R.layout.activity_repository_list)
public class RepositoryListActivity extends AppCompatActivity implements EndlessScrollListener.OnEndlessLoadListener, SwipeRefreshLayout.OnRefreshListener {

    @ViewById(R.id.appBar) protected Toolbar appBar;
    @ViewById(R.id.refreshContainer) protected SwipeRefreshLayout swipeRefresh;
    @ViewById(R.id.rvRepositoryList) protected RecyclerView repositoriesRecyclerView;

    @Inject ViewModelProvider.Factory viewModelFactory;

    private RepositoryListViewModel repositoryListViewModel;
    private RepositoryListAdapter repositoriesAdapter;
    private EndlessScrollListener endlessScrollListener;

    @AfterViews
    protected void setup(){
        setupInjection();
        setupViewModel();
        setupViews();
        observe();

        getRepositories(); //FIXME: Lifecycle
//        onSetupDone();
    }

    private void setupInjection(){
        ((App) getApplication()).getRepositoryListComponent().inject(this);
    }

    private void setupViewModel() {
        repositoryListViewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryListViewModel.class);
    }

    private void setupViews(){
        setupAppBar();
        setupRefreshContainer();
        setupRepositoriesRecyclerViewAdapter();
        setupRepositoriesRecyclerView();
    }

    private void setupAppBar() {
        appBar.setTitle(R.string.app_name);
        setSupportActionBar(appBar);
    }

    private void setupRefreshContainer() {
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent)
        );
    }

    private void setupRepositoriesRecyclerViewAdapter(){
        repositoriesAdapter = new RepositoryListAdapter();
//        repositoriesAdapter.setOnItemClickListener(this);
    }

    private void setupRepositoriesRecyclerView() {
        endlessScrollListener = new EndlessScrollListener(this);
        repositoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        repositoriesRecyclerView.setAdapter(repositoriesAdapter);
        repositoriesRecyclerView.addItemDecoration(
                new ListDivider(ContextCompat.getDrawable(this, R.drawable.divider_item_list)));
        repositoriesRecyclerView.addOnScrollListener(endlessScrollListener);
    }

    private void observe() {

        repositoryListViewModel.repositories.observe(this, new Observer<ResponseData<List<Repository>>>() {
            @Override
            public void onChanged(@Nullable ResponseData<List<Repository>> listResponseData) {
                switch (listResponseData.status){
                    case SUCCESS:
                        repositoriesAdapter.updateRepositories(listResponseData.data);
                        swipeRefresh.setRefreshing(false);
                        break;
                    case ERROR:
                        Toast.makeText(RepositoryListActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                        swipeRefresh.setRefreshing(false);
                        break;
                }
            }
        });
    }

    private void getRepositories(){
        getApplication();
        swipeRefresh.setRefreshing(true);
        repositoryListViewModel.getRepositoriesAtCurrentPage();
    }

    @Override
    public void endlessLoadMoreItems() {
        repositoryListViewModel.incrementPage();
        getRepositories();
    }

    @Override
    public void onRefresh() {
        resetRepositories();
        getRepositories();
    }

    private void resetRepositories() {
        repositoryListViewModel.resetPage();
        repositoryListViewModel.clearRepositories();
        repositoriesAdapter.notifyDataSetChanged();
        endlessScrollListener.reset();
    }
}
