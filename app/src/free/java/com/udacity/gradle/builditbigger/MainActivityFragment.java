package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.jokeAndroidLibrary.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeAsyncTask.AsyncListener {

    private static final String EXTRA_JOKE = "extra_joke";
    private ProgressBar mProgressBar;
    private Button mShowJokeButton;

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

        MobileAds.initialize(getContext(), getString(R.string.ad_id));
        initAddMob(view);
        mProgressBar = view.findViewById(R.id.progressbar);

        mShowJokeButton = view.findViewById(R.id.tellJokeButton);
        mShowJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JokeAsyncTask(MainActivityFragment.this).execute();
            }
        });
    }

    private void initAddMob(@NonNull View view) {
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void changeProgressBarStatus(boolean isChanged) {
        if (isChanged) {
            mShowJokeButton.setEnabled(false);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mShowJokeButton.setEnabled(true);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void startJokeActivity(String result) {
        if (TextUtils.isEmpty(result)) {
            Toast.makeText(getContext(), getString(R.string.error_text), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getActivity(), JokeActivity.class);
            intent.putExtra(EXTRA_JOKE, result);
            startActivity(intent);
        }
    }
}
