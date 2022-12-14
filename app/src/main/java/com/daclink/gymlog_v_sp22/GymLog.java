package com.daclink.gymlog_v_sp22;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;

import java.util.Date;

@Entity(tableName = AppDataBase.GYMLOG_TABLE)
public class GymLog {

    @PrimaryKey(autoGenerate = true)
    private int mLogId;

    private String mExercise;
    private double mWeight;
    private int mReps;

    private Date mDate;

    private int mUserId;

    public GymLog(String exercise, double weight, int reps, int userId) {
        mExercise = exercise;
        mWeight = weight;
        mReps = reps;

        mDate = new Date();

        mUserId = userId;
    }

    public String getExercise() {
        return mExercise;
    }

    public void setExercise(String exercise) {
        mExercise = exercise;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double weight) {
        mWeight = weight;
    }

    public int getReps() {
        return mReps;
    }

    public void setReps(int reps) {
        mReps = reps;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public String toString() {
        return "GymLog{" +
                "mLogId=" + mLogId +
                "mExercise='" + mExercise + '\'' +
                ", mWeight=" + mWeight +
                ", mReps=" + mReps +
                ", mDate=" + mDate +
                '}';
    }
}
