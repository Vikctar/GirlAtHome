package com.girlathome.utilities;

import android.app.Activity;
import android.util.Log;

import com.girlathome.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by steve on 4/23/17.
 */

public class BackgroundNetworkRequest {
    String response;

    public String makeRequest(Activity activity, String url) {
        final int DEFAULT_TIMEOUT = 20 * 10000;
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(DEFAULT_TIMEOUT);
        RequestParams params = new RequestParams();
        client.get(activity.getResources().getString(R.string.base_url) + url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                Log.d("backgrnd_req", "started");
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Log.d("backgrnd_req", s);
                System.out.print(s);
                response = s;

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
//                displayRetry();
                response = "error";
            }


        });
        return response;
    }
}
