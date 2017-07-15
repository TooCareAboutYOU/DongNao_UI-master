package com.dongnao.ui.master.drawprocess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "CustomTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.i(TAG, "TestActivity------onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "TestActivity------onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "TestActivity------onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "TestActivity------onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "TestActivity------onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "TestActivity------onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "TestActivity------onRestart: ");
    }
}
