package com.example.myapplication

import android.os.Bundle
import android.view.View
import com.brightcove.player.appcompat.BrightcovePlayerActivity
import com.brightcove.player.edge.Catalog
import com.brightcove.player.edge.VideoListener
import com.brightcove.player.model.Video
import com.brightcove.player.view.BrightcoveExoPlayerVideoView

class MyActivity : BrightcovePlayerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // When extending the BrightcovePlayer, we must assign the BrightcoveVideoView before
        // entering the superclass. This allows for some stock video player lifecycle
        // management.  Establish the video object and use it's event emitter to get important
        // notifications and to control logging.
        setContentView(R.layout.default_activity_main)
        baseVideoView =
            findViewById<View>(R.id.brightcove_video_view) as BrightcoveExoPlayerVideoView
        //initMediaController(baseVideoView)
        super.onCreate(savedInstanceState)
        val eventEmitter = baseVideoView.eventEmitter
        val account = getString(R.string.sdk_demo_account)
        val catalog =
            Catalog.Builder(eventEmitter, account).setBaseURL(Catalog.DEFAULT_EDGE_BASE_URL)
                .setPolicy(getString(R.string.sdk_demo_policy)).build()
        catalog.findVideoByID(getString(R.string.sdk_demo_videoId), object : VideoListener() {
            // Add the video found to the queue with add().
            // Start playback of the video with start().
            override fun onVideo(video: Video) {
                baseVideoView.add(video)
                baseVideoView.start()
            }
        })
    }
}