package me.jay.navigationdrawerdemo.model;

import java.util.List;

public class PostsResponse {
    List<Posts> posts;

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
}
