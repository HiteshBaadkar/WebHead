package com.cosmichawk.webheaders;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText inputurl;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
         inputurl= (EditText) findViewById(R.id.url);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String url = inputurl.getText().toString();


                        if (TextUtils.isEmpty(url)) {
                            Toast.makeText(getApplicationContext(), "Enter url!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            Intent intent=(new Intent(MainActivity.this, DisplayHeader.class));
                            intent.putExtra("url",url);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                            inputurl.setText("");
                            }

                    }
                }, 1000);


            }
        });

    }
}