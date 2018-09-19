package com.dongnao.ui.master;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.dongnao.ui.master.fragments.FourFragment;
import com.dongnao.ui.master.fragments.OneFragment;
import com.dongnao.ui.master.fragments.ThreeFragment;
import com.dongnao.ui.master.fragments.TwoFragment;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager sManager;
    public static FragmentTransaction sTransaction;
    private FragmentLifecycleCallback mLifecycleCallback;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sManager = getSupportFragmentManager();
        loadOne();
        mLifecycleCallback = new FragmentLifecycleCallback();
        sManager.registerFragmentLifecycleCallbacks(mLifecycleCallback, false);
        Log.i("MainActivity", "sManager.getBackStackEntryCount():" + sManager.getBackStackEntryCount());
    }

    private static void loadOne() {
        sTransaction = sManager.beginTransaction();
        sTransaction.add(R.id.cl_Container, new OneFragment()).commit();
    }

    public static void loadTwo() {
        sTransaction = sManager.beginTransaction();
        sTransaction.replace(R.id.cl_Container, new TwoFragment()).addToBackStack(TwoFragment.class.getSimpleName()).commit();

    }

    public static void loadThree() {
        sTransaction = sManager.beginTransaction();
        sTransaction.replace(R.id.cl_Container, new ThreeFragment()).addToBackStack(ThreeFragment.class.getSimpleName()).commit();

    }

    public static void loadFour() {
        sTransaction = sManager.beginTransaction();
        sTransaction.replace(R.id.cl_Container, new FourFragment()).addToBackStack(FourFragment.class.getSimpleName()).commit();

    }

    private class FragmentLifecycleCallback extends FragmentManager.FragmentLifecycleCallbacks {

        private static final String TAG = "FragmentLifecycleCallba";

        public FragmentLifecycleCallback() {
            super();
        }

        @Override
        public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
            super.onFragmentPreAttached(fm, f, context);
            Log.i(TAG, "onFragmentPreAttached: " + f.toString());
            Log.i("MainActivity", "onFragmentPreAttached增加:" + sManager.getBackStackEntryCount());

        }

        @Override
        public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
            super.onFragmentAttached(fm, f, context);
            Log.i(TAG, "onFragmentAttached: " + f.toString());
        }

        @Override
        public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentPreCreated(fm, f, savedInstanceState);
            Log.i(TAG, "onFragmentPreCreated: " + f.toString());
        }

        @Override
        public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentCreated(fm, f, savedInstanceState);
            Log.i(TAG, "onFragmentCreated: " + f.toString());
        }

        @Override
        public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentActivityCreated(fm, f, savedInstanceState);
            Log.i(TAG, "onFragmentActivityCreated: " + f.toString());
        }

        @Override
        public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            Log.i(TAG, "onFragmentViewCreated: " + f.toString());
        }

        @Override
        public void onFragmentStarted(FragmentManager fm, Fragment f) {
            super.onFragmentStarted(fm, f);
            Log.i(TAG, "onFragmentStarted: " + f.toString());
        }

        @Override
        public void onFragmentResumed(FragmentManager fm, Fragment f) {
            super.onFragmentResumed(fm, f);
            Log.i(TAG, "onFragmentResumed: " + f.toString());
        }

        @Override
        public void onFragmentPaused(FragmentManager fm, Fragment f) {
            super.onFragmentPaused(fm, f);
            Log.i(TAG, "onFragmentPaused: " + f.toString());
        }

        @Override
        public void onFragmentStopped(FragmentManager fm, Fragment f) {
            super.onFragmentStopped(fm, f);
            Log.i(TAG, "onFragmentStopped: " + f.toString());
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
            super.onFragmentSaveInstanceState(fm, f, outState);
            Log.i(TAG, "onFragmentSaveInstanceState: " + f.toString());
        }

        @Override
        public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
            super.onFragmentViewDestroyed(fm, f);
            Log.i(TAG, "onFragmentViewDestroyed: " + f.toString());
        }

        @Override
        public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
            super.onFragmentDestroyed(fm, f);
            Log.i(TAG, "onFragmentDestroyed: " + f.toString());
            Log.i("MainActivity", "onFragmentPreAttached减少:" + sManager.getBackStackEntryCount());

        }

        @Override
        public void onFragmentDetached(FragmentManager fm, Fragment f) {
            super.onFragmentDetached(fm, f);
            Log.i(TAG, "onFragmentDetached: ");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sManager.unregisterFragmentLifecycleCallbacks(mLifecycleCallback);
    }

    long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (sManager.getBackStackEntryCount() ==0) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 20000) {
                    firstTime = secondTime;
                    Toast.makeText(this, "双击退出", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    finish();
                    System.exit(0);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
