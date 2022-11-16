package com.daclink.gymlog_v_sp22.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.daclink.gymlog_v_sp22.GymLog;
import com.daclink.gymlog_v_sp22.User;

@TypeConverters({DateTypeConverter.class})
@Database (entities = {GymLog.class, User.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "GymLog.DB";
    public static final String GYMLOG_TABLE = "gym_log";
    public static final String USER_TABLE = "user_table";

    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract GymLogDAO gymLogDAO();

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}
