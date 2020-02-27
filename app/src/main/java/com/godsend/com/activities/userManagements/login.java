package com.godsend.com.activities.userManagements;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.godsend.com.R;
import com.godsend.com.api.apiClass;
import com.godsend.com.sharedPreference.sharedPreferenceClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class login extends AppCompatActivity
{

    //Variables for UI Elements
    private TextView signUp;
    private Button login;
    private EditText userID;
    private EditText password;
    private CheckBox rememberMe;

    //Variables for Permission
    private static final int INITIAL_REQUEST = 1337;
    private String[] Permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    android.Manifest.permission.CAMERA,
                                    android.Manifest.permission.READ_PHONE_STATE};

    //Intent Values to Pass
    private String name = "";
    private String createdOn = "";
    private String address = "";
    private String email = "";
    private String mobile = "";

    //Progress Dialog Box
    private Dialog progressDialog;

    //Shared Preference for Credential Storage
    SharedPreferences credentialStorage;
    private static final String PreferenceName = sharedPreferenceClass.credentialSharedPreferenceName;
    private static final String key_mobile = sharedPreferenceClass.credentialSharedPreferenceKeyMobile;
    private static final String key_password = sharedPreferenceClass.credentialSharedPreferenceKeyPassword;

    //Shared Preference for User Info Storage
    SharedPreferences userInfoStorage;
    private static final String userInfoPreferenceName = sharedPreferenceClass.userInfoSharedPreferenceName;
    private static final String userInfo_key_name = sharedPreferenceClass.userInfoSharedPreferenceKeyName;
    private static final String userInfo_key_createdOn = sharedPreferenceClass.userInfoSharedPreferenceKeyCreatedOn;
    private static final String userInfo_key_address = sharedPreferenceClass.userInfoSharedPreferenceKeyAddress;
    private static final String userInfo_key_email = sharedPreferenceClass.userInfoSharedPreferenceKeyEmail;
    private static final String userInfo_key_mobile = sharedPreferenceClass.userInfoSharedPreferenceKeyMobile;

    //API
    private String signUpAPI = apiClass.baseURL + apiClass.loginAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Showing Action Bar
        Objects.requireNonNull(getSupportActionBar()).show();

        //Dialog Initializer
        progressDialog = new Dialog(login.this);

        //UI Elements
        signUp = (TextView) findViewById(R.id.signUp);
        login = (Button) findViewById(R.id.login);
        userID = (EditText) findViewById(R.id.userID);
        password = (EditText) findViewById(R.id.password);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);

        //Getting Shared Preference Data
        getCredentials();

        //On Click Listener
        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToSignUp();
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!validateUserName() | !validatePassword())
                {
                    return;
                }

                showProgressDialog();
                httpLoginRequest();
            }
        });

        checkingPermission();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    //Checking Permission
    private void checkingPermission()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(!canWriteExternalStorage() || !canAccessFineLocation() || !canReadExternalStorage()
               || !canAccessPhoneState() || !canAccessCamera())
            {
                requestPermissions(Permissions, INITIAL_REQUEST);
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean canAccessFineLocation()
    {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean canWriteExternalStorage()
    {
        return(hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean canReadExternalStorage()
    {
        return(hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean canAccessPhoneState()
    {
        return(hasPermission(Manifest.permission.READ_PHONE_STATE));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean canAccessCamera()
    {
        return(hasPermission(Manifest.permission.CAMERA));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean hasPermission(String perm)
    {
        return(PackageManager.PERMISSION_GRANTED == checkSelfPermission(perm));
    }

    //Go To SignUP
    private void goToSignUp()
    {
        Intent signUp = new Intent(login.this, userRegistration.class);
        startActivity(signUp);
        finish();
    }

    //Go To Home Page
    private void goToHomePage()
    {
        Intent homePage = new Intent(login.this, homepage.class);
        startActivity(homePage);
        finish();
    }

    //Show Progress Dialog
    private void showProgressDialog()
    {
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.dialog_progress);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();
    }

    //Validating Our Text Inputs
    private Boolean validateUserName()
    {
        if(String.valueOf(userID.getText()).isEmpty())
        {
            userID.setError(getResources().getString(R.string.valid_user_id));
            return false;
        }

        if(userID.getText().toString().trim().length() == 0)
        {
            userID.setError(getResources().getString(R.string.valid_user_id));
            return false;
        }

        else
        {
            userID.setError(null);
            return true;
        }
    }

    private Boolean validatePassword()
    {
        if(String.valueOf(password.getText()).trim().isEmpty())
        {
            password.setError(getResources().getString(R.string.no_empty_password));
            return false;
        }

        if(password.getText().toString().trim().length() == 0)
        {
            password.setError(getResources().getString(R.string.no_empty_password));
            return false;
        }

        else
        {
            password.setError(null);
            return true;
        }
    }

    //Shared Preference Data
    public void saveCredentials()
    {
        credentialStorage = getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = credentialStorage.edit();
        editor.putString(key_mobile, userID.getText().toString());
        editor.putString(key_password, password.getText().toString());
        editor.apply();
    }

    public void saveUserInfo()
    {
        userInfoStorage = getSharedPreferences(userInfoPreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorUserInfo = userInfoStorage.edit();
        editorUserInfo.clear();
        editorUserInfo.apply();
        editorUserInfo.putString(userInfo_key_name, name);
        editorUserInfo.putString(userInfo_key_email, email);
        editorUserInfo.putString(userInfo_key_mobile, mobile);
        editorUserInfo.putString(userInfo_key_address, address);
        editorUserInfo.putString(userInfo_key_createdOn, createdOn);
        editorUserInfo.apply();
    }

    public void getCredentials()
    {
        credentialStorage = getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        userID.setText(credentialStorage.getString(key_mobile,""));
        password.setText(credentialStorage.getString(key_password,""));
    }

    public void deleteCredentials()
    {
        credentialStorage = getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = credentialStorage.edit();
        editor.clear();
        editor.apply();
    }

    private void httpLoginRequest()
    {
        StringRequest loginRequest = new StringRequest(Request.Method.POST, signUpAPI,
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
                            JSONArray dataArray = server_response.getJSONArray("data");
                            JSONObject data = dataArray.getJSONObject(0);

                            name = data.getString("fname");
                            email = data.getString("email");
                            address = data.getString("address");
                            createdOn = data.getString("created_at");
                            mobile = data.getString("phone");

                            if(Response.equals("success"))
                            {
                                progressDialog.dismiss();
                                saveUserInfo();

                                if(rememberMe.isChecked())
                                {
                                    saveCredentials();
                                }

                                else
                                {
                                    deleteCredentials();
                                }
                                goToHomePage();
                            }

                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(login.this, message,Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e)
                        {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Toast.makeText(login.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                            Log.d("JSON_DATA",String.valueOf(e));
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(login.this,String.valueOf(error),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ph", userID.getText().toString());
                MyData.put("pass", password.getText().toString());
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
    }
}