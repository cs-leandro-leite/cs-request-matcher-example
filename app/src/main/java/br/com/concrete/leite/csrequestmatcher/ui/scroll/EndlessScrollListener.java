package br.com.concrete.leite.csrequestmatcher.ui.scroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private final int VISIBLE_THRESHOLD = 5;

    private OnEndlessLoadListener listener;
    private int previousTotal;
    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItem;
    private boolean loading;

    public EndlessScrollListener(OnEndlessLoadListener listener) {
        this.listener = listener;
        setup();
    }

    private void setup() {
        previousTotal = 0;
        loading = true;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        updateVisibleItems(recyclerView);
        decideIfCanStopLoading();
        decideIfCanLoadMoreItems();
    }

    private void updateVisibleItems(RecyclerView recyclerView) {
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = recyclerView.getLayoutManager().getItemCount();
        firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }

    private void decideIfCanStopLoading() {
        if (shouldStopLoading()) {
            loading = false;
            previousTotal = totalItemCount;
        }
    }

    private boolean shouldStopLoading() {
        return loading && totalItemCount > previousTotal;
    }

    private void decideIfCanLoadMoreItems() {
        if (shouldLoadMoreItems()) {
            loading = true;
            listener.endlessLoadMoreItems();
        }
    }

    private boolean shouldLoadMoreItems(){
        return !loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + VISIBLE_THRESHOLD);
    }

    public void reset(){
        setup();
    }

    public interface OnEndlessLoadListener{
        void endlessLoadMoreItems();
    }
}
