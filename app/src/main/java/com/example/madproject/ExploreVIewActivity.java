package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ExploreVIewActivity extends AppCompatActivity {
    ImageView image;
    ImageButton like, comment, share;
    TextView likeText, user, timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_view);

        image = findViewById(R.id.postImage);
        like = findViewById(R.id.likeButton);
        comment = findViewById(R.id.commentButton);
        share = findViewById(R.id.shareButton);
        likeText = findViewById(R.id.likesText);
        user = findViewById(R.id.username);
        timeStamp = findViewById(R.id.timeStamp);

    }
}