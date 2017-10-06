package com.example.usman.gpsinaandroidapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Usman on 9/25/17.
 */

public class loginScreenActivity extends AppCompatActivity {

    Button loginButton;
    EditText userName, Password;
    Intent nextScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        userName = (EditText) findViewById(R.id.loginEmail);
        Password = (EditText) findViewById(R.id.loginPassword);

        loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
               // Intent nextScreen = new Intent(getApplicationContext(), loginScreenActivity.class);
               // startActivity(nextScreen);

                userLogin();
            }
        });

    }

    protected void userLogin() {
        final ProgressDialog pDialog = new ProgressDialog(loginScreenActivity.this);
        pDialog.setMessage("Please wait !");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
//        pDialog.dismiss();

        /*Post data - FORM INPUT*/
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("","");

        String userName = this.userName.getText().toString();
        String Password = this.Password.getText().toString();

        /*jsonParams.put("email",email);
        jsonParams.put("password",password);
        jsonParams.put("type","Owner");*/

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, Sessions.LOGIN_URL +
                "/?username="+userName+"&password=" + Password + "&type=Owner",

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {

                        Log.d("TAG_F", response.toString());

                        Boolean flag = null;
                        try {
                            flag = response.getBoolean("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(flag == true){

                            Log.d("TAG_F:- if success", flag.toString());
                            pDialog.dismiss();
                            Toast.makeText(loginScreenActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                            nextScreen = new Intent(getApplicationContext(), fragmentMainActivity.class);
                            startActivity(nextScreen);


                        }else{

                            Log.d("TAG_F:- if Fail", flag.toString());
                            pDialog.dismiss();
                            Toast.makeText(loginScreenActivity.this, "invalid Email/Password", Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(loginScreenActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        //   Handle Error
                        Log.d("VolleyERR", error.toString());
                        pDialog.dismiss();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
               // headers.put("Content-Type", "application/json; charset=utf-8");
               // headers.put("RiderId", "Rider_d1838e77954343a185200f85cf8af91d");
//                headers.put("id", String.valueOf(id));
                // THIS IS HEADER
                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(loginScreenActivity.this);
        queue.add(postRequest);

    }

}