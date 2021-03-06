package com.myapp.jamesnguyen.bluereminder.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM items")
    List<ItemEntity> getAll();

    @Query("SELECT * FROM items WHERE date_in_millisecond >= :from AND date_in_millisecond<=:to ORDER BY date_in_millisecond ASC")
    List<ItemEntity> getItemsBetweenDates(long from, long to);

    @Query("SELECT * FROM items WHERE priority=:priority")
    List<ItemEntity> getItemsByPriority(int priority);

    @Query("SELECT COUNT(*) FROM items")
    int size();

    @Insert
    void insert(ItemEntity... newItems);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ItemEntity... newItems);

    @Delete
    void delete(ItemEntity... items);

    @Query("DELETE FROM items")
    void deleteAll();

}
