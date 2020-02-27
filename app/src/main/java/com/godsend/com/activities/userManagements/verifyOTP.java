package com.godsend.com.activities.userManagements;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.godsend.com.R;
import com.godsend.com.api.apiClass;
import com.goodiebag.pinview.Pinview;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class verifyOTP extends AppCompatActivity
{
    //UI Element Variable
    private String MOBILE_NUMBER = "";
    private String OTP = "";
    private Pinview otpVerify;
    private Button otpSubmit;

    //Progress Dialog Box
    private Dialog progressDialog;

    //API
    private String otpAPI = apiClass.baseURL + apiClass.otpVerificationAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        //Dialog Initializer
        progressDialog = new Dialog(verifyOTP.this);

        //Fetching Data From Intent
        Intent i = getIntent();
        MOBILE_NUMBER = i.getStringExtra("MobileNumber");

        //UI Elements
        otpVerify = (Pinview) findViewById(R.id.otpVerify);
        otpSubmit = (Button) findViewById(R.id.otpSubmit);

        //On Click Listener
        otpSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showProgressDialog();
                httpOTPRequest();
            }
        });

        //Pin View Event Listener
        otpVerify.setPinViewEventListener(new Pinview.PinViewEventListener()
        {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser)
            {
                OTP = pinview.getValue();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToSignUp();
    }

    //Show Progress Dialog
    private void showProgressDialog()
    {
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.dialog_progress);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();
    }

    //Activity Switcher Functions
    private void goToLogin()
    {
        Intent login = new Intent(verifyOTP.this, com.godsend.com.activities.userManagements.login.class);
        startActivity(login);
        finish();
    }

    private void goToSignUp()
    {
        Intent signUp = new Intent(verifyOTP.this, com.godsend.com.activities.userManagements.userRegistration.class);
        startActivity(signUp);
        finish();
    }

    private void httpOTPRequest()
    {
        StringRequest otpVerificationRequest = new StringRequest(Request.Method.POST, otpAPI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject server_response = new JSONObject(response);
                            String message = server_response.getString("msg");
                            String Response = server_response.getString("status");

                            if(Response.equals("success"))
                            {
                                progressDialog.dismiss();
                                goToLogin();
                            }

                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(verifyOTP.this, message,Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e)
                        {
                            progressDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(verifyOTP.this,String.valueOf(error),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("otp", OTP);
                MyData.put("ph", MOBILE_NUMBER);
                return MyData;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(otpVerificationRequest);
    }
}
