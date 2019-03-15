package com.app.telugunewspaper.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.telugunewspaper.R;

public class VideoFragment extends Fragment {

    private String videoid,videofullpath;
    private WebView youtubePlayerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_video, container, false);
        Bundle bundle = this.getArguments();
        if(bundle!=null)
            videoid=bundle.getString("videoid");
        videofullpath="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+"ybl3SM5uO2Q"+"\" frameborder=\"0\" allowfullscreen></iframe>";
        youtubePlayerView=(WebView)view.findViewById(R.id.youtubePlayerView);
        Log.e("onCreateView","onCreateView");

        youtubePlayerView.getSettings().setJavaScriptEnabled(true);
        youtubePlayerView.setWebChromeClient(new WebChromeClient() {
        });
        youtubePlayerView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//               webViews.add(view);
                Log.e("url",""+url);

            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        youtubePlayerView.loadData( videofullpath, "text/html" , "utf-8" );
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("on pause","on pause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","onDestroy");
    }
}
