package com.godsend.com.activities.userManagements;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class userRegistration extends AppCompatActivity
{

    //Variables for UI Elements
    private EditText date_of_birth;
    private EditText name;
    private EditText email;
    private EditText mobileNumber;
    private EditText password;
    private EditText confirmPassword;
    private Button register;

    //Progress Dialog Box
    private Dialog progressDialog;

    //API
    private String signUpAPI = apiClass.baseURL + apiClass.registrationAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        //Showing Action Bar
        Objects.requireNonNull(getSupportActionBar()).show();

        //Dialog Initializer
        progressDialog = new Dialog(userRegistration.this);

        //UI Elements
        date_of_birth = (EditText) findViewById(R.id.date_of_birth);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        register = (Button) findViewById(R.id.register);

        //On Click Listener
        date_of_birth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pickDate();
            }
        });

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!validateName() | !validateEmail() | !validateMobileNumber() | !validateDateOfBirth() | !validatePassword() | !validatePasswordRe())
                {
                    return;
                }

                showProgressDialog();

                httpRegistrationRequest();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToLogin();
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
        Intent login = new Intent(userRegistration.this, com.godsend.com.activities.userManagements.login.class);
        startActivity(login);
        finish();
    }

    private void goToOTPVerification()
    {
        Intent verifyOtp = new Intent(userRegistration.this, com.godsend.com.activities.userManagements.verifyOTP.class);
        verifyOtp.putExtra("MobileNumber", mobileNumber.getText().toString().trim());
        startActivity(verifyOtp);
        finish();
    }

    //Date Picker Function
    private void pickDate()
    {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(userRegistration.this, new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday)
            {
                selectedmonth = selectedmonth + 1;
                String Date = selectedday + "/" + selectedmonth + "/" + selectedyear;
                date_of_birth.setText(Date);
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    //Validating Text Fields
    private Boolean validateName()
    {
        if(String.valueOf(name.getText()).isEmpty())
        {
            name.setError(getResources().getString(R.string.enter_valid_name));
            return false;
        }

        if(name.getText().toString().trim().length() == 0)
        {
            name.setError(getResources().getString(R.string.enter_valid_name));
            return false;
        }

        else
        {
            name.setError(null);
            return true;
        }
    }

    private Boolean validateMobileNumber()
    {
        if(String.valueOf(mobileNumber.getText()).isEmpty())
        {
            mobileNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(mobileNumber.getText().toString().trim().length() < 10)
        {
            mobileNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(mobileNumber.getText().toString().trim().length() == 0)
        {
            mobileNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        else
        {
            mobileNumber.setError(null);
            return true;
        }
    }

    private Boolean validateEmail()
    {
        if(String.valueOf(email.getText()).isEmpty())
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        if(email.getText().toString().trim().length() == 0)
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        if(!email.getText().toString().trim().contains("@"))
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        if(!email.getText().toString().trim().contains("."))
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        else
        {
            email.setError(null);
            return true;
        }
    }

    private Boolean validateDateOfBirth()
    {
        if(String.valueOf(date_of_birth.getText()).isEmpty())
        {
            date_of_birth.setError(getResources().getString(R.string.enter_date_of_birth));
            return false;
        }

        if(date_of_birth.getText().toString().trim().length() == 0)
        {
            date_of_birth.setError(getResources().getString(R.string.enter_date_of_birth));
            return false;
        }

        else
        {
            date_of_birth.setError(null);
            return true;
        }
    }

    private Boolean validatePassword()
    {
        if(String.valueOf(password.getText()).trim().isEmpty())
        {
            password.setError(getResources().getString(R.string.enter_a_password));
            return false;
        }

        if(password.getText().toString().trim().length() == 0)
        {
            password.setError(getResources().getString(R.string.enter_a_password));
            return false;
        }

        else
        {
            password.setError(null);
            return true;
        }
    }

    private Boolean validatePasswordRe()
    {
        if(String.valueOf(confirmPassword.getText()).trim().isEmpty())
        {
            confirmPassword.setError(getResources().getString(R.string.enter_a_password));
            return false;
        }

        if(confirmPassword.getText().toString().trim().length() == 0)
        {
            confirmPassword.setError(getResources().getString(R.string.enter_a_password));
            return false;
        }

        if(!confirmPassword.getText().toString().trim().equals(password.getText().toString().trim())
        && !String.valueOf(confirmPassword.getText()).trim().isEmpty() && !String.valueOf(password.getText()).trim().isEmpty())
        {
            confirmPassword.setError(getResources().getString(R.string.password_mismatched));
            return false;
        }

        else
        {
            confirmPassword.setError(null);
            return true;
        }
    }

    private void httpRegistrationRequest()
    {
        StringRequest signUpRequest = new StringRequest(Request.Method.POST, signUpAPI,
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
                                goToOTPVerification();
                            }

                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(userRegistration.this, message,Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(userRegistration.this,String.valueOf(error),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("fnam", name.getText().toString());
                MyData.put("eml", email.getText().toString());
                MyData.put("ph", mobileNumber.getText().toString());
                MyData.put("dob", date_of_birth.getText().toString());
                MyData.put("pass", password.getText().toString());
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(signUpRequest);
    }
}
