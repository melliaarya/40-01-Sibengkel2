package com.example.sibengkel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.sibengkel.controllers.BookController;
import com.example.sibengkel.models.BookModels;
import com.example.sibengkel.utils.Database;
import com.example.sibengkel.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    TextView txt_profile_name;
    Button button_booking_list;
    Button btn_setting;

    SharedPreferences sharedpreferences;
    Intent intent;

    private DatabaseHelper db;
    private List<BookModels> listBookings = new ArrayList<BookModels>();
    private RecyclerView recyclerView;
    private BookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateCoreApp();

        txt_profile_name = (TextView) findViewById(R.id.txt_profile_name);
        txt_profile_name.setText(getIntent().getExtras().get("name").toString());

        button_booking_list = (Button) findViewById(R.id.button_booking_list);
        button_booking_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, BookingList.class);
                intent.putExtra("email", getIntent().getExtras().get("email").toString());

                startActivity(intent);
            }
        });

        btn_setting = (Button)findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(this);

//        Log.d("book",String.valueOf(values));


    }
    //update
    public void bookNow(View view){
        intent = new Intent(MainActivity.this, ScheduleActivity.class);
        intent.putExtra("email", getIntent().getExtras().get("email").toString());
        startActivity(intent);
    }

//
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putBoolean(LoginActivity.session_status, false);
//        editor.putString(LoginActivity.TAG_ID, null);
//        editor.putString(LoginActivity.TAG_EMAIL, null);
//        editor.putString(LoginActivity.TAG_NAME, null);
//        editor.putString(LoginActivity.TAG_PHONE, null);
//        editor.putString(LoginActivity.TAG_ADDRESS, null);
//        editor.commit();
//


    public void pembelian(View view){
        startActivity(new Intent(this, PembelianActivity.class));
    }
    private void initiateCoreApp() {
        Database database = new DatabaseHelper(this);
        BookController.setDatabase(database);
    }

}