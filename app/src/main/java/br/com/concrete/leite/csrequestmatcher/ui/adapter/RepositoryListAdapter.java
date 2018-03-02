package br.com.concrete.leite.csrequestmatcher.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.concrete.leite.csrequestmatcher.R;
import br.com.concrete.leite.csrequestmatcher.model.Repository;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;



public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.ViewHolder> {

    private List<Repository> repositories;
    private OnItemClickListener onItemClickListener;

    public RepositoryListAdapter() {
        this.repositories = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void updateRepositories(List<Repository> repositories){
        this.repositories = repositories;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Repository repository = repositories.get(position);

        setViews(repository, holder);
    }

    private void setViews(Repository repository, ViewHolder holder){
        loadImage(repository.getOwner().getAvatarUrl(), holder.ivUserProfile);
        holder.tvTitle.setText(repository.getFullName());
        holder.tvDescription.setText(repository.getDescription());
        holder.tvStars.setText(String.valueOf(repository.getStars()));
        holder.tvForks.setText(String.valueOf(repository.getForks()));
        holder.tvIssues.setText(String.valueOf(repository.getIssues()));
    }

    private void loadImage(String url, ImageView ivUserProfile) {
        Picasso.with(ivUserProfile.getContext())
                .load(url)
                .placeholder(R.drawable.downloading_placeholder)
                .error(R.drawable.downloading_error)
                .resizeDimen(R.dimen.profile_pic, R.dimen.profile_pic)
                .transform(new CropCircleTransformation())
                .into(ivUserProfile);
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivUserProfile;
        public TextView tvTitle;
        public TextView tvDescription;
        public TextView tvStars;
        public TextView tvForks;
        public TextView tvIssues;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            ivUserProfile = itemView.findViewById(R.id.ivUserProfile);
            ivUserProfile.setOnClickListener(this);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvText);
            tvStars = itemView.findViewById(R.id.tvStars);
            tvForks = itemView.findViewById(R.id.tvMergedAt);
            tvIssues = itemView.findViewById(R.id.tvIssues);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ivUserProfile:
                    onItemClickListener.onUserAvatarClick(getAdapterPosition());
                    break;
                default:
                    onItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onUserAvatarClick(int position);
    }
}