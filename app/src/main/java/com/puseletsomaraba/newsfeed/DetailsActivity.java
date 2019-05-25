package com.puseletsomaraba.newsfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String url = bundle.getString("url");
            Toast.makeText(this, url, Toast.LENGTH_LONG)
                    .show();

        }


        webView = findViewById(R.id.webView);
    }
}
