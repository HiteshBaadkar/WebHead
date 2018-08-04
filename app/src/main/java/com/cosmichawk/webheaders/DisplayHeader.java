package com.cosmichawk.webheaders;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisplayHeader extends AppCompatActivity {

    ListView listViewHeaders;
    private Button btnRetry;
    TextView textViewurl;
    String url,key,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);



        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");

        listViewHeaders = (ListView) findViewById(R.id.listViewheaders);
        textViewurl = (TextView) findViewById(R.id.textView1);
        textViewurl.setText(url);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {

            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            Map<String, List<String>> map = conn.getHeaderFields();



            for (Map.Entry<String, List<String>> entry : map.entrySet()) {


                key= entry.getKey();
                StringBuilder sb = new StringBuilder();
                for (String s : entry.getValue())
                {
                    sb.append(s);
                    sb.append("\t");
                }

                value=sb.toString();

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Header", key);
                hashMap.put("Content", value);
                arrayList.add(hashMap);

                }
                String[] from ={"Header","Content"};
                int[] to = {R.id.textkey, R.id.textvalue};
                CustomAdapter simpleAdapter = new CustomAdapter(this, arrayList, R.layout.list_view_items, from, to);
                listViewHeaders.setAdapter(simpleAdapter);

            String server = conn.getHeaderField("Server");

            if (server == null) {
                Toast.makeText(DisplayHeader.this, "Key 'Server' is not found!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DisplayHeader.this, "Server - " + server,
                        Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(DisplayHeader.this, "Done",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }



        btnRetry = (Button) findViewById(R.id.btn_retry);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

    }});
}}
