package com.example.android.jokeAndroidLibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String EXTRA_JOKE = "extra_joke";
    private TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mJokeTextView = findViewById(R.id.tv_joke);
        extractDataFromBundle();
    }

    private void extractDataFromBundle() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_JOKE)) {
            Bundle data = intent.getExtras();
            if (data != null) {
                String joke = data.getString(EXTRA_JOKE);
                mJokeTextView.setText(joke);
            }
        }
    }
}
