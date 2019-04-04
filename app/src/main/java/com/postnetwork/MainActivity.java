package com.postnetwork;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    JsonPlaceholderApi jsonPlaceholderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.textViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
//     getPosts();
//        getComments();
//        createPost();
        patchPost();
    }

    private void createPost() {
        Post post = new Post(23, "New Title", "New Text");
        Call<Post> call = jsonPlaceholderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    return;
                }

                Post postResponse = response.body();

                if (postResponse != null) {
                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    content += "ID: " + postResponse.getId() + "\n";
                    content += "User ID: " + postResponse.getUserId() + "\n";
                    content += "Title: " + postResponse.getTitle() + "\n";
                    content += "Text: " + postResponse.getText() + "\n\n";

                    textViewResult.setText(content);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {

        Call<List<Post>> call = jsonPlaceholderApi.getPosts(new Integer[]{25}, "null");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    return;
                }

                List<Post> posts = response.body();

                if (posts != null) {
                    for (Post post : posts) {
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "User ID: " + post.getUserId() + "\n";
                        content += "Title: " + post.getTitle() + "\n";
                        content += "Text: " + post.getText() + "\n\n";

                        textViewResult.append(content);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceholderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    return;
                }

                List<Comment> comments = response.body();

                if (comments != null) {
                    for (Comment comment : comments) {
                        String content = "";
                        content += "ID: " + comment.getId() + "\n";
                        content += "Post ID: " + comment.getPostId() + "\n";
                        content += "Name: " + comment.getName() + "\n";
                        content += "Email: " + comment.getEmail() + "\n";
                        content += "Text: " + comment.getText() + "\n\n";

                        textViewResult.append(content);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Comment>> call, @NonNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void patchPost() {
        Post post = new Post(12, null, "New Text");

        Call<Post> call = jsonPlaceholderApi.patchPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    return;
                }

                Post postResponse = response.body();
                if (postResponse != null) {
                    String content = "";
                    content += "Code: " + response.code() + "\n";

                    content += "ID: " + postResponse.getId() + "\n";

                    content += "User ID: " + postResponse.getUserId() + "\n";
                    content += "Title: " + postResponse.getTitle() + "\n";
                    content += "Text: " + postResponse.getText() + "\n\n";

                    textViewResult.setText(content);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}

