package com.example.registerationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    private EditText deleteId;
    private Button btndelete;
    private RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        deleteId = findViewById(R.id.editText);
        btndelete = findViewById(R.id.btndelete);
        parent=findViewById(R.id.parent);

        final EntryDataBase db = EntryDataBase.getInstance(this);
        final List<Integer> allid = db.entryDao().getAllId();

        btndelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temp = deleteId.getText().toString();
                int id = 0;
                if (!"".equals(temp)) {
                    id = Integer.parseInt(temp);
                }
                final int finalId = id;
                if(finalId==0){
                    Toast.makeText(DeleteActivity.this, "please enter integer value....", Toast.LENGTH_SHORT).show();
                }else if (allid.contains(finalId)) {
                    Entry entry = new Entry();
                    entry.setId(finalId);
                    db.entryDao().deleteaEntry(entry);
                    Toast.makeText(DeleteActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DeleteActivity.this, AllEntriesActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(parent, "Please enter all valid details...", Snackbar.LENGTH_INDEFINITE).setAction("Refer", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(DeleteActivity.this, AllEntriesActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}