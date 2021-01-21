package me.jay.navigationdrawerdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import me.jay.navigationdrawerdemo.R;
import me.jay.navigationdrawerdemo.adapter.PostsAdapter;
import me.jay.navigationdrawerdemo.model.Posts;
import me.jay.navigationdrawerdemo.rest.ApiClient;
import me.jay.navigationdrawerdemo.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPostsFragment extends Fragment {

    RecyclerView myRecyclerView;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_posts_fragment_layout,container,false);
        myRecyclerView = view.findViewById(R.id.posts_recycler_view);
        progressBar = view.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        showAllPosts();
        return view;
    }

    private void showAllPosts(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Posts>> call = apiService.getAllPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.code() == 200){

                    if (response.body() != null){

                        loadDataList(response.body());

                        if (progressBar.getVisibility() == View.VISIBLE){
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                if (progressBar.getVisibility() == View.VISIBLE){
                    progressBar.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataList(List<Posts> posts) {

        PostsAdapter myAdapter = new PostsAdapter(getActivity(), posts);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(myAdapter);

    }
}
