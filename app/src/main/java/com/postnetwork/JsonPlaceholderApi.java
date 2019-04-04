package com.postnetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceholderApi {

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId,
                              @Query("_sort") String sort);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);
}
