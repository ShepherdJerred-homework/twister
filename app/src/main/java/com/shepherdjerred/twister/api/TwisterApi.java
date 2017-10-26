package com.shepherdjerred.twister.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.shepherdjerred.twister.object.Twist;
import com.shepherdjerred.twister.object.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwisterApi {

    private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    private RequestQueue requestQueue;

    public TwisterApi(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    // https://developer.android.com/training/volley/request.html
    public List<Twist> getTwists() {
        final List<Twist> twists = new ArrayList<>();

        String url = "http://jsonstub.com/twist/";

        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int twistId = jsonObject.getInt("id");
                                String twistUsername = jsonObject.getString("username");
                                String twistMessage = jsonObject.getString("message");
                                String twistTimestampString = jsonObject.getString("timestamp");
                                Date twistTimestamp = JSON_DATE_FORMAT.parse(twistTimestampString);
                                Twist twist = new Twist(twistId, twistUsername, twistMessage, twistTimestamp);
                                twists.add(twist);
                            } catch (JSONException | ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("JsonStub-User-Key", "edbc267a-f880-4dec-8dec-727cccc27e5d");
                params.put("JsonStub-Project-Key", "40e26003-fc1b-40f3-9ae4-bfab71e6d186");

                return params;
            }
        };

        requestQueue.add(request);
        return twists;
    }

    public User getUser(String username) {
        return null;
    }
}
