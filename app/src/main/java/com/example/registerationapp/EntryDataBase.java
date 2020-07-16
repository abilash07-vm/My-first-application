package com.example.registerationapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Entry.class}, version = 1)
public abstract class EntryDataBase extends RoomDatabase {
    public abstract EntryDao entryDao();
    private static EntryDataBase instance;
    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new Populateinitialdata(instance).execute();
        }
    };

    public static synchronized EntryDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, EntryDataBase.class, "Entry.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }


//
//    private static class Populateinitialdata extends AsyncTask<Void, Void, Void> {
//        private EntryDao entryDao;
//
//        public Populateinitialdata(EntryDataBase db) {
//            this.entryDao = db.entryDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            entryDao.insertaEntry(new Entry("abilash", "male", "abil17ec004@rmkcet.ac.in", "6380868006"));
//            return null;
//        }
//    }

}

