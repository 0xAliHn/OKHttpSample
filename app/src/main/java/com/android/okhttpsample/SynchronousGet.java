package com.android.okhttpsample;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ali on 4/10/17.
 */

public class SynchronousGet{
    private static final String TAG = "SynchronousGet";

public static void executeSynchronousGet() {
    final OkHttpClient client = new OkHttpClient();

    final Request request = new Request.Builder()
            .url("http://www.vogella.com/index.html")
            .build();

    //Need to be called from background thread otherwise network on main thread exception will be occurred
    new Thread(new Runnable() {
        @Override
        public void run() {
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!response.isSuccessful()) {
                // manage error
                Log.e(TAG, "Unexpected code " + response);
            }

            // show body content
            try {
                Log.i(TAG, response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }).start();


}
}
