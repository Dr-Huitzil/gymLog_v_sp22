package com.daclink.gymlog_v_sp22.DB;

import androidx.room.TypeConverter;

import java.sql.Date;

public class DateTypeConverter {
    @TypeConverter
    public Long convertDateToLong(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public Date convertLongToLocalDate(long epoch){
        return new Date(epoch);
    }
}
