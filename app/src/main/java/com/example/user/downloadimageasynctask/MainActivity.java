package com.example.user.downloadimageasynctask;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "https://1.bp.blogspot.com/-qF0u6HWsww4/WG1KVseQ1GI/AAAAAAAAA4g/CGMEBQlstv4HPOidyAHJo9SVFFZVer3YgCLcB/s1600/AsyncTask.png";

    private TextView mTextView;
    private Button mDownloadButton;
    private ProgressBar mProgressBar;
    private ImageView mImageView;

    public DownloadImage downloadImageAsync = new DownloadImage();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    public void init(){
        mDownloadButton = findViewById(R.id.download_image_button);
        mImageView = findViewById(R.id.image_view);
        mTextView = findViewById(R.id.download_status_text_view);
        mProgressBar = findViewById(R.id.downloading_progress_bar);

        final String filesDir = getFilesDir().getAbsolutePath();


        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImageAsync.execute(IMAGE_URL, filesDir);
            }
        });

        downloadImageAsync.setmCallbacks(new DownloadImage.DownloadAsyncTaskCallbacks() {
            @Override
            public void onPreExecute() {
                mTextView.setText(getString(R.string.download_status_started));
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPostExecute(String s) {
                mTextView.setText(getString(R.string.download_status_finished));
                mProgressBar.setVisibility(View.GONE);
                mImageView.setImageDrawable(Drawable.createFromPath(s));
                mDownloadButton.setEnabled(false);
            }

            @Override
            public void onProgressUpdate(Integer i) {
                mProgressBar.setProgress(i);
            }
        });


    }
}
