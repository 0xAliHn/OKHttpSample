package com.android.okhttpsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ali on 4/10/17.
 */

public class AsynchronousGet {
    private static final String TAG = "AsynchronousGet: ";

    public static void executeAsynchronousGet() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
                // manage failure !
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    // manage error
                    Log.e(TAG, "Unexpected code " + response);
                    return;
                }

                Headers responseHeaders = response.headers();
                // show response headers
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                // show body content
                Log.i(TAG, response.body().string());

            }
        });
    }
}
