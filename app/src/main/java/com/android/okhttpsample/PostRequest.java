package com.android.okhttpsample;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ali on 4/10/17.
 */

public class PostRequest {
    private static final String TAG = "PostRequest";

    public static void executePostRequest() {
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String url = "http://www.ssaurel.com/tmp/jsonService";
        String json = "{'mode' : 'test'}"; // Json Content ...

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // manage failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Manage response
                Log.i(TAG, response.body().string());
            }
        });
    }
}
