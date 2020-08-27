package com.drogunova.neuroapps.neuropsy.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "table_tenWords")
public class TenWordsTrial {

    @PrimaryKey(autoGenerate = true)
    public int id;

    //имя пользователя
    public String userName;

    //вариант пробы: прямая или отсроченная
    //1 - прямая
    //2 - отсроченная
    public int trialVariant;

    //количество запомненных слов
    public int memoNum;
}
