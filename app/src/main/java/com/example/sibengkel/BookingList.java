package com.example.sibengkel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import com.example.sibengkel.controllers.BookController;
import com.example.sibengkel.models.BookModels;
import com.example.sibengkel.utils.Database;
import com.example.sibengkel.utils.DatabaseContents;
import com.example.sibengkel.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;
import static com.example.sibengkel.utils.DatabaseContents.TABLE_BOOK;

public class BookingList extends AppCompatActivity {
//bookinglist

    SharedPreferences sharedpreferences;
    Intent intent;

    private DatabaseHelper db;
    private SQLiteDatabase dbsql;
    private List<BookModels> listBookings = new ArrayList<BookModels>();
    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        initiateCoreApp();

        db = new DatabaseHelper(this);



        recyclerView = (RecyclerView) findViewById(R.id.rv_bookings);
        adapter = new BookingAdapter(this, db.allBookings(getIntent().getExtras().get("email").toString()));

        email = (getIntent().getExtras().get("email").toString());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
//database booking list
        //fungsi hapus
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                listBookings.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                intent = new Intent(BookingList.this, MainActivity.class);
                startActivity(intent);
            }
        });
        helper.attachToRecyclerView(recyclerView);

    }
    //connect to database
    private void initiateCoreApp() {
        Database database = new DatabaseHelper(this);
        BookController.setDatabase(database);
    }
}
