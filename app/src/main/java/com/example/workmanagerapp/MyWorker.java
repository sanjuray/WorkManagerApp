package com.example.workmanagerapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //Getting Data from InputData
        Data data = getInputData();
        int countingLimit = data.getInt("max_limit",0);
//        Log.v("Eroor","Chck"+countingLimit);
        for(int i=0;i<countingLimit;i++) Log.v("TUGLAQ",i+"");
        //Sending Data and Done Notification
        Data dataSending = new Data.Builder()
                .putString("msg","Task Done Successfully").build();

        return Result.success(dataSending);
    }
}
