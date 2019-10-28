package com.example.cryptowatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.cryptowatcher.MainActivity.SHARED_NAME;

public class setting extends AppCompatActivity {

    private SharedPreferences my_shared;
    public EditText txt_number_row;
    public Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        txt_number_row = findViewById(R.id.txt_number_row);
        btn_save = findViewById(R.id.save_btn_setting);

        my_shared = getSharedPreferences(SHARED_NAME, MODE_PRIVATE);

        final int i = my_shared.getInt("NUMBER_ROWS", 15);
        Toast.makeText(this, i + "تعداد سطر :  ", Toast.LENGTH_SHORT).show();
        txt_number_row.setText(String.valueOf(i));




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor Editor = my_shared.edit();
                int number = Integer.parseInt(txt_number_row.getText().toString());
//                Toast.makeText(setting.this, number, Toast.LENGTH_SHORT).show();
                Editor.putInt("NUMBER_ROWS", number);
                Editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


}
