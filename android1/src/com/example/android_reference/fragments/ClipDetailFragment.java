package com.example.android_reference.fragments;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.example.android_reference.Config;
import com.example.android_reference.R;
import com.example.android_reference.models.Clip;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClipDetailFragment extends Fragment implements YouTubePlayer.OnInitializedListener {
    private Clip _clip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clip_detail, container, false);

        _clip = getArguments().getParcelable("clip");

        FragmentManager fragmentManager = getFragmentManager();
        /* the error occurs when you run twice
        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(Config.YOUTUBE_DEVELOPER_KEY, this);
            */
        // http://stackoverflow.com/questions/19848142/how-to-load-youtubeplayer-using-youtubeplayerfragment-inside-another-fragment
        YouTubePlayerFragment youTubePlayerFragment = YouTubePlayerFragment.newInstance();
        youTubePlayerFragment.initialize(Config.YOUTUBE_DEVELOPER_KEY, this);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.youtube_fragment, youTubePlayerFragment);
        fragmentTransaction.commit();
        return v;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
            boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(_clip.youtube);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
            YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            //errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }
        else {
            //String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
            //Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

}
