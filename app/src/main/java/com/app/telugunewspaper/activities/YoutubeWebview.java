package com.app.telugunewspaper.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.app.telugunewspaper.R;

public class YoutubeWebview extends AppCompatActivity {

    public static final int USER_MOBILE = 0;
    public static final int USER_DESKTOP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_webview);
        final WebView video = (WebView) findViewById(R.id.webView);
        video.getSettings().setJavaScriptEnabled(true);
        video.setWebChromeClient(new WebChromeClient() {
        } );
        video.loadData("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/duAEyEKbpoM\" frameborder=\"0\" allowfullscreen></iframe>", "text/html" , "utf-8" );
    }

    public String getHTML(String videoId) {
        String html =
                "<iframe class=\"youtube-player\" "
                        + "style=\"border: 0; width: 100%; height: 95%;"
                        + "padding:0px; margin:0px\" "
                        + "id=\"ytplayer\" type=\"text/html\" "
                        + "src=\"http://www.youtube.com/embed/" + videoId
                        + "?fs=0\" frameborder=\"0\" " + "allowfullscreen autobuffer "
                        + "controls onclick=\"this.play()\">\n" + "</iframe>\n";

        return html;
    }
}
