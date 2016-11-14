package itheima.okhttp3utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by wschun on 2016/10/7.
 */

public class ItHeiMaHttp {

    private final OkHttpClient okHttpClient;
    private Map<String, String> params = null;
    private Map<String, String> heads = null;

    private Gson gson;
    private final Handler handler;

    private ItHeiMaHttp() {
        okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
        okHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        gson = new Gson();
        handler = new Handler(Looper.myLooper());
        this.params = new HashMap<String, String>();
        this.heads=new HashMap<String, String>();
    }


    public Map<String, String> getParams() {
        return params;
    }


    public ItHeiMaHttp addParam(String key, String value) {
        this.params.put(new String(key), value);
        return this;
    }

    public Map<String, String> getHeads() {
        return heads;
    }


    public ItHeiMaHttp addHeads(String key, String value) {
        this.heads.put(new String(key), value);
        return this;
    }



    public static ItHeiMaHttp httpManager;

    public static ItHeiMaHttp getInstance() {
        if (httpManager == null) {
            synchronized (ItHeiMaHttp.class) {
                httpManager = new ItHeiMaHttp();
            }
        }
        return httpManager;
    }

    public void get(String url, WSCallBack bcb) {
        Request request = buildRequest(url, RequestType.GET);
        doRequest(request, bcb);
    }

    public void post(String url, WSCallBack bcb) {
        Request request = buildRequest(url, RequestType.POST);
        doRequest(request, bcb);
    }



    private Request buildRequest(String url, RequestType type) {
        Request.Builder builder = new Request.Builder();
        if (type == RequestType.GET) {
            url = getParamWithString(url);
            builder.get();
        } else if (type == RequestType.POST) {
            RequestBody requestBody = getFormatData(params);
            builder.post(requestBody);
        }
        builder.url(url);
        addAllHeads(builder);
        return builder.build();
    }

    private void addAllHeads(Request.Builder builder) {
        if (heads.size()>0){
            for (Map.Entry<String, String> entry : heads.entrySet()) {
                 builder.addHeader(entry.getKey(),entry.getValue());
            }
        }
    }


    public String getParamWithString(String url) {
        if (params == null || params.size() < 1)
            return url;
        StringBuilder sb = new StringBuilder();
        if (url.indexOf("http://") == 0
                || url.indexOf("https://") == 0) {
            sb.append(url + "?");
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue())
                    .append("&");
        }

        return sb.toString().substring(0, (sb.toString().length() - 1));
    }

    private RequestBody getFormatData(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() > 0)
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        return builder.build();
    }

    enum RequestType {
        GET,
        POST
    }

    private void doRequest(Request request, final WSCallBack baseCallBack) {

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFaile(baseCallBack, call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    sendSuccess(json, call, baseCallBack);
                } else {
                    sendFaile(baseCallBack, call, null);
                }
            }
        });

    }

    private void sendFaile(final WSCallBack bcb, final Call call, Exception e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                bcb.onFailure(call, null);
            }
        });
    }

    private void sendSuccess(final String json, final Call call, final WSCallBack bcb) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (bcb.type == String.class) {
                    bcb.onSuccess(json);
                } else {
                    try {
                        Object object = gson.fromJson(json, bcb.type);
                        bcb.onSuccess(object);
                    } catch (JsonParseException e) {
                        sendFaile(bcb, call, e);
                    }
                }
            }
        });
    }


}
