package com.example.registerationapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    EntryDataBase db;
    private ConstraintLayout parent;
    private RadioGroup genderradiogrp;
    private EditText Editname, Editemail, Editcontact, EditId;
    private Button btnsubmit;
    private String name, email, contact, gender = "male", temp;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();

        EditId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ArrayList<Integer> ids= (ArrayList<Integer>) db.entryDao().getAllId();
                int id2=0;
                try{
                    id2=Integer.parseInt(EditId.getText().toString());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }

                if(ids.contains(id2)){
                    Entry entry=db.entryDao().getEntryById(id2);
                    if(entry!=null){
                        Editname.setText(entry.getName());
                        Editemail.setText(entry.getEmail());
                        Editcontact.setText(entry.getPhonenumber());
                        switch (entry.getGender()){
                            case "male":
                                ((RadioButton) genderradiogrp.findViewById(R.id.male)).setChecked(true);
                                break;
                            case "female":
                                ((RadioButton) genderradiogrp.findViewById(R.id.female)).setChecked(true);
                                break;
                            default:
                                ((RadioButton) genderradiogrp.findViewById(R.id.others)).setChecked(true);
                                break;
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        genderradiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        gender = "male";
                        break;
                    case R.id.female:
                        gender = "female";
                        break;
                    case R.id.others:
                        gender="others";
                        break;
                    default:
                        gender = "others";
                        break;
                }
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Editname.getText().toString();
                email = Editemail.getText().toString();
                contact = Editcontact.getText().toString();

                temp = EditId.getText().toString();
                if (!"".equals(temp)) {
                    id = Integer.parseInt(temp);
                }

                if (check()) {
                    db.entryDao().updateEntry(id, name, gender, email, contact);
                    Toast.makeText(UpdateActivity.this, "Updated Successfully...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, AllEntriesActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(parent, "Please enter all valid details...", Snackbar.LENGTH_INDEFINITE).setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
            }
        });
    }

    private boolean check() {
        String[] emailarr = email.split("@");
        String[] emailarr2=email.split(".");
        if (name.equals("") || email.equals("") || contact.equals("")) {
            return false;
        }
        if (emailarr.length == 1 || emailarr2.length==1) {
            Toast.makeText(this, "Please enter valid email id", Toast.LENGTH_SHORT).show();
            return false;
        }
        Character[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        List<Character> Arr = Arrays.asList(arr);
        for (Character ch : contact.toCharArray()) {
            if (!Arr.contains(ch)) {
                Toast.makeText(this, "Please enter valid Contact", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void init() {
        parent = findViewById(R.id.parent);
        genderradiogrp = findViewById(R.id.radiogrp);
        Editname = findViewById(R.id.name);
        Editemail = findViewById(R.id.email);
        Editcontact = findViewById(R.id.contact);
        btnsubmit = findViewById(R.id.btnsubmit);
        EditId = findViewById(R.id.id);
        db = EntryDataBase.getInstance(this);
    }
}