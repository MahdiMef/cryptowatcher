package com.example.cryptowatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainApi extends AppCompatActivity {
    OkHttpClient client;
    Request request;
    TextView t;
    private DrawerLayout dl;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_api);
        gettenlast();
        //  t = findViewById(R.id.t);

        Button btn = findViewById(R.id.btn_menu);
        dl = findViewById(R.id.activity_main);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.homepage:
                        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent2);
                        Toast.makeText(MainApi.this, "yo entered in Home page  ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.account:
                        Intent intent = new Intent(getApplicationContext(), MainApi.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "yo entered in  private api ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Toast.makeText(getApplicationContext(), "My Cart", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer((int) Gravity.LEFT);
            }
        });


    }

    //----------------------
    String apiKey = "4023ac38-3684-4208-8b78-7050fd6c4b58";
    String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

    private void gettenlast() {

        client = new OkHttpClient();
        request = new Request.Builder().url(uri)
                .addHeader("Accept", "application/json")
                .addHeader("X-CMC_PRO_API_KEY", apiKey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("apiFailed", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String body = response.body().string();
                Log.i("api_get", body);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Stuff that updates the UI
                        // t.setText(body);
                    }
                });

            }
        });

    }

//------------------

}
