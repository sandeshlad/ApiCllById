package me.jay.navigationdrawerdemo.rest;

import java.util.List;
import me.jay.navigationdrawerdemo.model.Posts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("posts")
    Call<List<Posts>> getAllPosts();

    @GET("posts/{id}")
    Call<Posts> getPostById(@Path("id") int id);



}
