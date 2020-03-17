package com.esmt.controller.esmtacad.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.esmt.controller.esmtacad.R;
import com.esmt.controller.esmtacad.adaptateurs.RecyclerViewAdapter;
import com.esmt.controller.esmtacad.models.UserActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private final String URL_USERS = "https://jsonplaceholder.typicode.com/users";
    private final String URL_POTHOS = "https://jsonplaceholder.typicode.com/photos";

    private ReferenceQueue referenceQueue ;
    private List<UserActivity>listUserActivity;
    private RecyclerView recyclerView ;
    private RequestQueue requestQueue;
    private RecyclerViewAdapter rvAdapter ;
    private ImageView imageView ;


    public LoginActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        listUserActivity = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewid);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //jsonrequestForImages();
        jsonrequestForUsers();


    }


    private void jsonrequestForUsers(){

        JsonArrayRequest  request = new JsonArrayRequest(URL_USERS, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(final JSONArray response){


                for (int i = 0 ; i < response.length() ; i ++){
                    try {
                        int tmp = i ;
                        JSONObject jsonObject = response.getJSONObject(i);
                        UserActivity userActivity = new UserActivity();
                        userActivity.setId(jsonObject.getInt("id"));
                        userActivity.setName(jsonObject.getString("name"));
                        userActivity.setUsername(jsonObject.getString("username"));
                        userActivity.setEmail(jsonObject.getString("email"));
                        userActivity.setPhone(jsonObject.getString("phone"));
                        userActivity.setWebsite(jsonObject.getString("website"));
                        listUserActivity.add(userActivity);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                setuprecyclerview(listUserActivity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(request);



    }


    private void jsonrequestForImages(){

        JsonArrayRequest  request = new JsonArrayRequest(URL_POTHOS, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(final JSONArray response) {


                for (int i = 0 ; i < 10 ; i ++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        UserActivity rvdata = new UserActivity();
                        rvdata.setImg(jsonObject.getString("thumbnailUrl"));
                        listUserActivity.add(rvdata);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
                setuprecyclerview(listUserActivity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<UserActivity> listeUserActivity){

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,listUserActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }

}
