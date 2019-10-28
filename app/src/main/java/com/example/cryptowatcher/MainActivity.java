package com.example.cryptowatcher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.adapter_main;
import model.coinModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.droidsonroids.gif.GifImageView;

import static com.example.cryptowatcher.R.id;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences my_shared;
    public static final String SHARED_NAME = "my_shared";
    public String NUMBER_ROWS = "NUMBER_ROWS";
    private DrawerLayout dl;
    private NavigationView nv;
    OkHttpClient client;
    Request request;
    private List<coinModel> coinModels = new ArrayList<>();
    RecyclerView rc_main;
    public CardView cardView;
    public ImageView img_setting;
    public ConstraintLayout constraintLayout_prog;
    public ConstraintLayout constraintLayout_main;
    public GifImageView gif;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        my_shared = getSharedPreferences(SHARED_NAME, MODE_PRIVATE);
        //SharedPreferences.Editor pref_editor=my_shared.edit();
        // pref_editor.putInt(NUMBER_ROWS,10);
        // pref_editor.apply();
        isNetworkConnected();
        if (isNetworkConnected()) {
        } else {
            Toast.makeText(getApplicationContext(), "NO intrnet. check wifi and data", Toast.LENGTH_LONG).show();

        }

        dl = findViewById(id.activity_main);
        Button btn = findViewById(id.btn_menu);
        final ConstraintLayout heder = findViewById(id.constraintLayout_header);
        cardView = findViewById(R.id.card);
        img_setting = findViewById(id.img_setting);
        rc_main = findViewById(id.rv_main_page);
        constraintLayout_prog = findViewById(id.constraintLayout_prog);
        constraintLayout_main = findViewById(id.constraintLayout_main);
        gif = findViewById(id.gif);

        gettenlast(5);

        nv = findViewById(id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.homepage:
                        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(), "yo entered in  Home page ", Toast.LENGTH_SHORT).show();
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

        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
//
//                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                txt_number = findViewById(R.id.txt_number);
//
//                alertDialog.setTitle("setting");
//                alertDialog.setMessage("set the rows");
//                alertDialog.setView(inflater.inflate(R.layout.setting_layout, null));
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                String st = txt_number.getText().toString();
//                               // number=Integer.parseInt(st);
//                                Toast.makeText(MainActivity.this, st+" "+number, Toast.LENGTH_SHORT).show();
//                               // dialog.dismiss();
//                               // gettenlast(1);
//
//                            }
//                        });
//                alertDialog.show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("با استفاده از shared prefrens کامل شود ");
                alert.setTitle("رفتن به یه اکتیویتی برای ست کردن تنظیمات");


                alert.setPositiveButton("save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        Intent i_setting = new Intent(getApplicationContext(), setting.class);
                        startActivity(i_setting);

                    }
                });

//                alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        // what ever you want to do with No option.
//                    }
//                });
                alert.show();
            }
        });
//        switch_dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//
//                    rc_main.setBackgroundColor(Color.parseColor("#000000"));
//                    heder.setBackgroundColor(getResources().getColor(color.header_color));
//                    switch_dark.setTextColor(Color.parseColor("#ffffff"));
//                    setTheme(style.darkthem);
//                    gettenlast(5);
//
//                } else {
//
//                    rc_main.setBackgroundColor(getResources().getColor(color.colorAccent));
//                    heder.setBackgroundColor(getResources().getColor(color.colorAccent_light));
//                    switch_dark.setTextColor(Color.parseColor("#000000"));
//                    setTheme(style.AppTheme);
//                    gettenlast(5);
//
//                }
//            }
//        });


        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNetworkConnected();
                if (isNetworkConnected()) {
//                  Toast.makeText(getApplicationContext(), "IE ok", Toast.LENGTH_SHORT).show();
                    gettenlast(5);
                    Toast.makeText(getApplicationContext(), "reloding...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "NO intrnet. check wifi and data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //------------------------------------------------------
    private void gettenlast(int index) {
        gif.setVisibility(View.VISIBLE);
        // constraintLayout_prog.setVisibility(View.VISIBLE);
        //constraintLayout_main.setVisibility(View.GONE);

        int number = my_shared.getInt("NUMBER_ROWS", 15);
        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?limit=" + number, index))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("apiFailed", e.getMessage().toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();
                Gson gson = new Gson();
                final List<coinModel> newitem = gson.fromJson(body, new TypeToken<List<coinModel>>() {
                }.getType());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        // Stuff that updates the

                        LinearLayoutManager linearLayoutManagerPayam = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        adapter_main adapter_main = new adapter_main(getApplicationContext(), newitem);
                        rc_main.setAdapter(adapter_main);
                        rc_main.setLayoutManager(linearLayoutManagerPayam);
                        rc_main.setItemAnimator(new DefaultItemAnimator());


                //Log.i("api_get", body);

//تایمر گیف نشون دادن

//                        new CountDownTimer(2000, 1000) {
//                            public void onTick(long millisUntilFinished) {
//                                // You don't need to use this.
//
//                            }
//                            public void onFinish() {
//                                // Put the code to stop the GIF here.
//                               // gif.setVisibility(View.GONE);
//                            }
//                        }.start();
                    }
                });
            }


        });

        Log.i("log____get", request.toString());


    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
