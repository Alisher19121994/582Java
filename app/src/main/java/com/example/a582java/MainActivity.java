package com.example.a582java;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewFeeds;
    RecyclerView recyclerViewStories;
    ImageView pickUpImage;
    TextView textViewPost;
    ArrayList<Uri> uri = new ArrayList<>();
    FeedsAdapter feedsAdapter;
    int Read_Permission = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        pickImages();
    }

    private void pickImages() {
        recyclerViewFeeds = findViewById(R.id.feeds_recyclerView);
        pickUpImage = findViewById(R.id.pickImages);
        textViewPost = findViewById(R.id.posts_Number);

        feedsAdapter = new FeedsAdapter(uri);
        recyclerViewFeeds.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewFeeds.setAdapter(feedsAdapter);
        allowToGallery();
    }

    private void allowToGallery() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);
        }
        pickUpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("images/*");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    uri.add(data.getClipData().getItemAt(i).getUri());
                }
                feedsAdapter.notifyDataSetChanged();
                textViewPost.setText("Post(" + uri.size() + ")");
            } else if (data.getData() != null) {
                String imageURL = data.getData().getPath();
                uri.add(Uri.parse(imageURL));
            }
        }
    }





    private void initViews() {

        recyclerViewStories = findViewById(R.id.story_recyclerView);
        recyclerViewStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        getStoriesAdapter(dataOfStories());
    }
    private void getStoriesAdapter(ArrayList<Story> stories) {
        StoryAdapter storiesAdapter = new StoryAdapter(this, stories);
        recyclerViewStories.setAdapter(storiesAdapter);
    }
    private ArrayList<Story> dataOfStories() {
        ArrayList<Story> list = new ArrayList();
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        list.add(new Story(R.drawable.rasm, "Alisher"));
        return list;
    }
}