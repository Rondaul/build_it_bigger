 package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.jokeAndroidLibrary.JokeActivity;

public class MainActivityFragment extends Fragment implements JokeAsyncTask.AsyncListener {

    private ProgressBar progressBar;
    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button tellJokeButton = view.findViewById(R.id.tellJokeButton);
        progressBar = view.findViewById(R.id.progressbar);

        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JokeAsyncTask(MainActivityFragment.this).execute();
            }
        });
    }

    @Override
    public void changeProgressBarStatus(boolean isChanged) {
        if(isChanged){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void startJokeActivity(String result) {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(getString(R.string.key_joke_pass), result);
        startActivity(intent);
    }
}
