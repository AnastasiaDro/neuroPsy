package com.drogunova.neuroapps.neuropsy.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TenWordsTrial.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TenWordsTrialDao tenWordsTrialDao();
}
