package com.tana.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Member member);
    @Query("SELECT * FROM member_table ORDER BY id ASC")
    LiveData<List<Member>> getAllMember();

}
