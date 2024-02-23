package com.example.workmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button runner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runner = findViewById(R.id.runner);

        //Data
        Data data = new Data.Builder()
                .putInt("max_limit",100000).build();

        //Constraints
         Constraints constraint = new Constraints
                .Builder()
                .setRequiresCharging(true)
                .build();

        //Making use of worker:
        WorkRequest w = new OneTimeWorkRequest
                .Builder(MyWorker.class)
//                .setConstraints(constraint)
                .setInputData(data)
                .build();

        //Enqueue the request with WorkManager
        runner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(getApplicationContext()).enqueue(w);
            }
        });

        //Monitoring the status of work manager
        WorkManager.getInstance(getApplicationContext())
                .getWorkInfoByIdLiveData(w.getId())
                .observe(this,
                        new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                if(workInfo != null){
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Status: "+workInfo.getState().name(),
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                                if(workInfo.getState().isFinished()){
                                    Data d = workInfo.getOutputData();
                                    Toast.makeText(MainActivity.this,
                                            ""+d.getString("msg")+"MSG From worker!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }
}