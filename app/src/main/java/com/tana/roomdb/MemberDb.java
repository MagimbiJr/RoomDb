package com.tana.roomdb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Member.class}, version = 1)
public abstract class MemberDb extends RoomDatabase {
    private static MemberDb INSTANCE;
    public abstract MemberDao memberDao();

    public static synchronized MemberDb getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MemberDb.class,
                    "member_database")
                    .addCallback(dbCallback)
                    .fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new initialInsertAsync(INSTANCE).execute();
        }
    };

    private static class initialInsertAsync extends AsyncTask<Void, Void, Void> {
        private MemberDao memberDao;

        public initialInsertAsync(MemberDb db) {
            memberDao = db.memberDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            memberDao.insert(new Member("Atanas", "Charle", "10/10/1992"));
            memberDao.insert(new Member("Jeremiah", "Keneth", "10/1/2000"));
            memberDao.insert(new Member("Samwel", "Charles", "21/3/1990"));
            return null;
        }
    }
}
