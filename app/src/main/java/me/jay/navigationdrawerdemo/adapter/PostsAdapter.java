package me.jay.navigationdrawerdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import me.jay.navigationdrawerdemo.R;
import me.jay.navigationdrawerdemo.model.Posts;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private Context context;
    private List<Posts> postsList;

    public PostsAdapter(Context context, List<Posts> posts) {
        this.context = context;
        this.postsList = posts;
    }



    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layout,parent,false);

        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {

        holder.id.setText(Integer.toString(postsList.get(position).getId()));
        holder.userId.setText(Integer.toString(postsList.get(position).getUserId()));
        holder.title.setText(postsList.get(position).getTitle());
        holder.body.setText(postsList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView userId;
        TextView title;
        TextView body;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);

             userId= itemView.findViewById(R.id.tvUserId);
             id = itemView.findViewById(R.id.tvId);
             title = itemView.findViewById(R.id.tvTitle);
             body = itemView.findViewById(R.id.tvBody);

        }
    }

}
