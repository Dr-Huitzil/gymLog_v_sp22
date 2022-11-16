package com.daclink.gymlog_v_sp22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;
import com.daclink.gymlog_v_sp22.DB.GymLogDAO;
import com.daclink.gymlog_v_sp22.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mMainDisplay;

    EditText mExercise;
    EditText mWeight;
    EditText mReps;

    Button mSubmit;

    GymLogDAO mGymLogDAO;
    List<GymLog> mGymLogList;
    ActivityMainBinding binding;

    private int mUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mMainDisplay = binding.mainGymLogDisplay;
        mExercise = binding.mainExerciseEditText;
        mWeight = binding.mainWeightEditText;
        mReps = binding.mainRepsEditText;

        mSubmit = binding.mainSubmitButton;

        mMainDisplay.setMovementMethod(new ScrollingMovementMethod());

        mGymLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .gymLogDAO();

        mSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                submitGymLog();
            }
        });

        refreshDisplay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item2:
                Toast.makeText(this, "Logout selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshDisplay(){
        mGymLogList = mGymLogDAO.getGymLogs();
        if(!mGymLogList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(GymLog gymLog : mGymLogList){
                sb.append(gymLog.toString());
            }
            mMainDisplay.setText(sb.toString());
        }else{
            mMainDisplay.setText("No logs yet, time to hit the Gym!");
        }
    }

    private void submitGymLog(){
        String exercise = mExercise.getText().toString();
        double weight = Double.parseDouble(mWeight.getText().toString());
        int reps = Integer.parseInt(mReps.getText().toString());

        GymLog log = new GymLog(exercise, weight, reps, mUserId);
        mGymLogDAO.insert(log);
        refreshDisplay();
    }

    private void logoutUser(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage(R.string.logout);

        alertBuilder.setPositiveButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        clearUserFromPref();
                    }
                });
        alertBuilder.setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                });

        alertBuilder.create().show();
    }

    private void clearUserFromPref(){
        Toast.makeText(this, "clear users not yet implemented", Toast.LENGTH_SHORT).show();
    }
}