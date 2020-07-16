package com.example.registerationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AllEntriesActivity extends AppCompatActivity {

    //    TextView textView;
    EntryDataBase db;
    private RecyclerView recyclerView;
    private RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_entries);

//        textView=findViewById(R.id.txtdummy);
        db = EntryDataBase.getInstance(this);
        parent = findViewById(R.id.parent);
        List<Entry> allEntries = db.entryDao().getAllEntry();
        List<String> allEntriesArray = new ArrayList<>();
        if (allEntries.isEmpty()) {
            Toast.makeText(this, "null in the database", Toast.LENGTH_SHORT).show();
            Snackbar.make(parent, "The database is empty...Please add to view", Snackbar.LENGTH_INDEFINITE).setAction("Add", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AllEntriesActivity.this, AddEntryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }).show();
        } else {

//            String text="";
            for (Entry entry : allEntries) {
                allEntriesArray.add("Id : " + entry.getId() + "\nName : " + entry.getName() + "\nGender : " + entry.getGender() + "\nEmail : " + entry.getEmail() + "\nContact : " + entry.getPhonenumber());
            }

//            if(text==null)
//                textView.setText("null");
//            else
//            textView.setText(text);
            EntriesAdaptor adaptor = new EntriesAdaptor(this);
            recyclerView = findViewById(R.id.recyview);
            adaptor.setEntries(allEntriesArray);
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllEntriesActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}