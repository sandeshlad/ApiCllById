package me.jay.navigationdrawerdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import me.jay.navigationdrawerdemo.R;
import me.jay.navigationdrawerdemo.adapter.PostsAdapter;
import me.jay.navigationdrawerdemo.model.Posts;
import me.jay.navigationdrawerdemo.rest.ApiClient;
import me.jay.navigationdrawerdemo.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostByIdFragment extends Fragment {

    TextInputEditText edtUid;
    MaterialButton btnGetInfo;
    private RecyclerView myRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_by_id_fragment_layout,container,false);
        edtUid = view.findViewById(R.id.edtUid);
        btnGetInfo = view.findViewById(R.id.btnGetInfo);
        myRecyclerView = view.findViewById(R.id.posts_by_id_recycler_view);

        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtUid.getText() != null){

                    if (!edtUid.getText().toString().isEmpty()){
                        int id = Integer.parseInt(String.valueOf(edtUid.getText()));
                        showUserById(id);
                    }else {
                        edtUid.setError("Please enter id");
                        edtUid.requestFocus();
                    }
                }

            }
        });

        return view;
    }

    private void showUserById(int id){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Posts> call = apiService.getPostById(id);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.code() == 200){
                    if (response.body() != null){
                        Posts posts = response.body();
                        List<Posts> postsList = new ArrayList<>();
                        postsList.add(posts);

                        loadDataList(postsList);
                    }
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
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
