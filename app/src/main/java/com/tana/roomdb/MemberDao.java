package com.tana.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Member member);

    @Update
    void update(Member member);

    @Delete
    void delete(Member member);

    @Query("DELETE  FROM member_table")
    void deleteAll();

    @Query("SELECT * FROM member_table ORDER BY id ASC")
    LiveData<List<Member>> getAllMember();

}
