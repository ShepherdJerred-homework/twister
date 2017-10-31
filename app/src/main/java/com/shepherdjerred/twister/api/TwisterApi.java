package com.shepherdjerred.twister.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import java.util.Map;

public class TwisterApi {

    private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    private RequestQueue requestQueue;

    public TwisterApi(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    // https://developer.android.com/training/volley/request.html
    public void getTwists(final onTwistLoad onTwistLoad) {
        String url = "http://jsonstub.com/twist/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ArrayList<Twist> twists = new ArrayList<>();
                        try {
                            JSONArray twistJsonArray = jsonObject.getJSONArray("twists");
                            for (int i = 0; i < twistJsonArray.length(); i++) {
                                JSONObject twistJsonObject = twistJsonArray.getJSONObject(i);
                                int twistId = twistJsonObject.getInt("id");
                                String twistUsername = twistJsonObject.getString("username");
                                String twistMessage = twistJsonObject.getString("message");
                                String twistTimestampString = twistJsonObject.getString("timestamp");
                                Date twistTimestamp = JSON_DATE_FORMAT.parse(twistTimestampString);
                                Twist twist = new Twist(twistId, twistUsername, twistMessage, twistTimestamp);
                                twists.add(twist);
                            }
                            onTwistLoad.run(twists);
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", error.toString());
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
    }

    public interface onTwistLoad {
        void run(ArrayList<Twist> twists);
    }

    public void getUser(String username, final onUserLoad onUserLoad) {
        String url = "http://jsonstub.com/user/" + username;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String username = jsonObject.getString("username");
                            String about = jsonObject.getString("about");
                            User user = new User(username, about);
                            onUserLoad.onSuccess(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage;
                if (error.networkResponse == null) {
                    errorMessage = "No network connection";
                } else {
                    int statusCode = error.networkResponse.statusCode;
                    if (statusCode == 400) {
                        errorMessage = "This user doesn't exist";
                    } else {
                        errorMessage = String.valueOf(error.networkResponse.statusCode);
                    }
                }
                onUserLoad.onError(errorMessage);
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
    }

    public interface onUserLoad {
        void onSuccess(User user);

        void onError(String error);
    }
}
