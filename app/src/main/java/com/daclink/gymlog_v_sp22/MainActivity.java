package com.daclink.gymlog_v_sp22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        GymLog log = new GymLog(exercise, weight, reps);
        mGymLogDAO.insert(log);
        refreshDisplay();
    }
}