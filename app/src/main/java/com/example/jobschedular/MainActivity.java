package com.example.jobschedular;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG= "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void scheduleJob(View view){
        ComponentName componentName=new ComponentName(this,ExampleJobService.class);
        JobInfo info=new JobInfo.Builder(123,componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode=scheduler.schedule(info);
        if(resultCode==JobScheduler.RESULT_SUCCESS){
            Log.d(TAG,"Job Scheduled");
        }else{
            Log.d(TAG,"Job Scheduling Failed");
        }
    }
    public void cancelJob(View view){

        JobScheduler jobScheduler=(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(123);
        Log.d(TAG, "Job Cancelled");

    }
}
