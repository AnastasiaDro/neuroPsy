package com.drogunova.neuroapps.neuropsy.room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public interface TenWordsTrialDao {

    //взять всё из таблички проб 10 слов
    @Query("SELECT * FROM table_tenWords")
    Single<List<TenWordsTrial>> getAll();

    //выбрать по id
    @Query("SELECT * FROM table_tenWords WHERE id = :id")
    Single<List<TenWordsTrial>> getAllById(int id);

    //выбрать по имени пользователя
    @Query("SELECT * FROM table_tenWords WHERE userName = :userName")
    Single<List<TenWordsTrial>> getTrialsByUsername(String userName);

    @Insert
    Single <Long> insert(TenWordsTrial twTrial);

    @Insert
    Single<List<Long>> insertTrialList(List<TenWordsTrial> trials);

    @Delete
    Single<Integer> delete(TenWordsTrial twTrial);

    @Update
    Single<Integer> update(TenWordsTrial twTrial);

}
