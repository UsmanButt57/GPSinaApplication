package com.example.usman.gpsinaandroidapplication;

import android.app.ProgressDialog;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class signUpScreenActivity extends AppCompatActivity {


    EditText email, password, confirmPassword, mobileNumber;
    Button signUpButton;

    /*Post data - FORM INPUT*/
    Map<String, String> jsonParams = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        email = (EditText)findViewById(R.id.signUpEmail);
        password = (EditText)findViewById(R.id.signUpPassword);
        confirmPassword = (EditText)findViewById(R.id.signUpConfirmPassword);
        mobileNumber = (EditText)findViewById(R.id.signUpMobileNo);
        signUpButton = (Button)findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                // Intent nextScreen = new Intent(getApplicationContext(), loginScreenActivity.class);
                // startActivity(nextScreen);

                checkUserExist();
            }
        });

    }


    protected void checkUserExist() {

        final ProgressDialog pDialog = new ProgressDialog(signUpScreenActivity.this);

        pDialog.setMessage("Please wait !");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
//        pDialog.dismiss();

        String formattedDate = currentDate();


        Log.d("Date", formattedDate);

        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        String mobileNumber = this.mobileNumber.getText().toString();

        String opt = String.valueOf(Math.floor(1000 + Math.random() * 9000));
        Log.d("OPT", opt);


        jsonParams.put("email",email);
        jsonParams.put("password",password);
        jsonParams.put("username",email);
        jsonParams.put("createdby",password);
        jsonParams.put("createddate", formattedDate);
        jsonParams.put("OTP",opt);
        jsonParams.put("IsMobileVerify","false");
        jsonParams.put("Type","Owner");
        jsonParams.put("MAxSpeed","0");
        jsonParams.put("phone", mobileNumber);
        jsonParams.put("mobile", mobileNumber);
        jsonParams.put("country", "my");


        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Sessions.SignUpCheck_URL ,

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
                            Toast.makeText(signUpScreenActivity.this, "Successfully check User", Toast.LENGTH_SHORT).show();
                            userRegister();

                        }else{

                            Log.d("TAG_F:- if Fail", flag.toString());
                            pDialog.dismiss();
                            Toast.makeText(signUpScreenActivity.this, "Already user Sign Up", Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(signUpScreenActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        //   Handle Error
                        Log.d("VolleyERR", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json; charset=utf-8");
                //headers.put("RiderId", "Rider_d1838e77954343a185200f85cf8af91d");
//                headers.put("id", String.valueOf(id));
                // THIS IS HEADER
                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(signUpScreenActivity.this);
        queue.add(postRequest);


    }


    protected  void userRegister(){

        final ProgressDialog pDialog1 = new ProgressDialog(signUpScreenActivity.this);

        pDialog1.setMessage("Please wait !");
        pDialog1.setIndeterminate(false);
        pDialog1.setCancelable(false);
        pDialog1.show();

    //    pDialog.dismiss();

        jsonParams.put("PrivacyPolicy", "1");

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Sessions.SignUpRegister_URL ,

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
                            pDialog1.dismiss();
                            Toast.makeText(signUpScreenActivity.this, "Successfully register User Successfully", Toast.LENGTH_SHORT).show();
                            userLogin();

                        }else{

                            Log.d("TAG_F:- if Fail", flag.toString());
                            pDialog1.dismiss();
                            Toast.makeText(signUpScreenActivity.this, "Not Register Successfully", Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(signUpScreenActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        //   Handle Error
                        Log.d("VolleyERR", error.toString());
                        pDialog1.dismiss();
                    }
                }) {

        };
        RequestQueue queue = Volley.newRequestQueue(signUpScreenActivity.this);
        queue.add(postRequest);

    }


    protected void userLogin() {
        final ProgressDialog pDialog2 = new ProgressDialog(signUpScreenActivity.this);
        pDialog2.setMessage("Please wait !");
        pDialog2.setIndeterminate(false);
        pDialog2.setCancelable(false);
        pDialog2.show();
//        pDialog.dismiss();

        /*Post data - FORM INPUT*/
        Map<String, String> jsonParams1 = new HashMap<String, String>();
        jsonParams1.put("","");


        String userName = email.getText().toString();
        String Password = password.getText().toString();

        /*jsonParams.put("email",email);
        jsonParams.put("password",password);
        jsonParams.put("type","Owner");*/
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, Sessions.LOGIN_URL +
                "/?username="+userName+"&password=" + Password + "&type=Owner",

                new JSONObject(jsonParams1),
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
                            pDialog2.dismiss();
                            Toast.makeText(signUpScreenActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();


                        }else{

                            Log.d("TAG_F:- if Fail", flag.toString());
                            pDialog2.dismiss();
                            Toast.makeText(signUpScreenActivity.this, "invalid Email/Password", Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(signUpScreenActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        //   Handle Error
                        Log.d("VolleyERR", error.toString());
                        pDialog2.dismiss();
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
        RequestQueue queue = Volley.newRequestQueue(signUpScreenActivity.this);
        queue.add(postRequest);

    }

    protected String currentDate(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }

}