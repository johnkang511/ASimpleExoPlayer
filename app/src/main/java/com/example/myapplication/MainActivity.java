package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    private PlayerView mPlayerView;
    private MediaSource mVideoSource;
    private DataSource.Factory mDataSourceFactory;

    private SimpleExoPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerView = findViewById(R.id.playerview);

        mPlayer = ExoPlayerFactory.newSimpleInstance(this);
        mPlayerView.setPlayer(mPlayer);

        mDataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "yourApplicationName"));
        mPlayerView.hideController();

        mVideoSource = new ProgressiveMediaSource.Factory(mDataSourceFactory)
                .createMediaSource(Uri.parse("http://techslides.com/demos/sample-videos/small.mp4"));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Prepare the player with the source.
        mPlayer.prepare(mVideoSource);

        mPlayer.setPlayWhenReady(true);
    }
}
