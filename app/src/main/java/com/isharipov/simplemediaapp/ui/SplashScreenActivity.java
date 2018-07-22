package com.isharipov.simplemediaapp.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.isharipov.simplemediaapp.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_PROGRESS = "KEY_CURRENT_PROGRESS";
    public static final int TIME_IN_SPLASH = 50;
    public static final int TIME_THREAD_WAIT = 50;
    public static final int TIME_INCR_COUNTER = TIME_IN_SPLASH / TIME_THREAD_WAIT;
    public static final String TAG = SplashScreenActivity.class.getSimpleName();
    public int mCurrentProgress;
    private SplashProgressTask mTask;

    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.splash_tv_msg)
    TextView tv;
    @BindString(R.string.splash_tv_msg)
    String splashTvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        bar.setMax(TIME_IN_SPLASH);
        if(savedInstanceState != null) {
            mCurrentProgress = savedInstanceState.getInt(KEY_CURRENT_PROGRESS);
        }
        mTask = new SplashProgressTask(bar, tv);
        mTask.execute("Hello");
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(KEY_CURRENT_PROGRESS, mCurrentProgress); // save the current value
        cancelTask(); // cancel current task
        super.onSaveInstanceState(bundle);
    }

    @Override
    protected void onDestroy() {
        cancelTask(); // cancel task
        super.onDestroy();
    }

    /**
     * Method cancels the current running task if not already cancelled
     */
    private void cancelTask() {
        if(mTask != null && !mTask.isCancelled()) {
            mTask.cancel(true);
        }
        mTask = null;
    }

    /**
     * Method to launch main activity
     */
    private void launchMainActivity() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    class SplashProgressTask extends AsyncTask<String, Integer, Integer> {
        private ProgressBar mBar;
        private TextView mTvMsg;
        private String mMessage;

        public SplashProgressTask(ProgressBar bar, TextView tv) {
            mBar = bar;
            mTvMsg = tv;
            mMessage = splashTvMsg;
            Log.d(TAG, "SplashProgressTask()"
                    + " -:- mMessage ==> " + mMessage);
        }

        /**
         * start background work here
         */
        @Override
        protected Integer doInBackground(String... args) {
            String url = args[0];
            performTask();
            return 100; // As of now, This has no effect on the task. So return any integer value
        }

        /**
         * Method executes after task completes. now launch main activity
         */
        @Override
        protected void onPostExecute(Integer result) {
            launchMainActivity();
            super.onPostExecute(result);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Progress update. Update UI here
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mBar.setProgress(values[0]);
            Log.d(TAG, "onProgressUpdate()"
                    + " -:- mCurrentProgress ==> " + mCurrentProgress
                    + " -:- mMessage ==> " + mMessage);
            mTvMsg.setText(String.format(mMessage, TIME_IN_SPLASH - mCurrentProgress));
        }

        /**
         * This method does simple calculation as of now. The complicated work like downloading db etc can be implemented here
         */
        private void performTask() {
            // wait for sometime and increment counter each time
            for(int i = mCurrentProgress; i < TIME_IN_SPLASH; i = i + TIME_INCR_COUNTER) {
                if(!isCancelled()) {
                    mCurrentProgress = i;
                    publishProgress(i);
                    try {
                        Thread.sleep(TIME_THREAD_WAIT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
