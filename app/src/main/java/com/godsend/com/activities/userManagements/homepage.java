package com.godsend.com.activities.userManagements;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.godsend.com.R;
import com.godsend.com.sharedPreference.sharedPreferenceClass;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class homepage extends AppCompatActivity
{

    //UI Elements Variables
    private CircleImageView profilePicture;
    private TextView profileName;
    private TextView email;
    private TextView creationDate;
    private TextView address;
    private CardView appointment;
    private CardView searchNearByHospital;
    private CardView searchDoctor;
    private CardView checkAppointment;
    private CardView medicalRecords;

    //Shared Preference for User Info Storage
    SharedPreferences userInfoStorage;
    private static final String userInfoPreferenceName = sharedPreferenceClass.userInfoSharedPreferenceName;
    private static final String userInfo_key_name = sharedPreferenceClass.userInfoSharedPreferenceKeyName;
    private static final String userInfo_key_createdOn = sharedPreferenceClass.userInfoSharedPreferenceKeyCreatedOn;
    private static final String userInfo_key_address = sharedPreferenceClass.userInfoSharedPreferenceKeyAddress;
    private static final String userInfo_key_email = sharedPreferenceClass.userInfoSharedPreferenceKeyEmail;
    private static final String userInfo_key_mobile = sharedPreferenceClass.userInfoSharedPreferenceKeyMobile;

    //Dialog Box Variables
    private Dialog appointmentDialog;
    private Dialog doctorDialog;
    private Dialog appointmentTypesDialog;
    private Dialog insuranceDialog;
    private Dialog patientTypeDialog;

    //Appointment Dialog Variables
    private TextView activeAppointment;
    private TextView pastAppointment;
    private TextView cancelledAppointments;

    //Doctor Dialog Variables
    private TextView browseDoctor;
    private TextView favouriteDoctor;

    //Appointment Type Dialog Variables
    private TextView regularAppointment;
    private TextView emergencyAppointment;

    //Insurance Dialog Variables
    private TextView haveInsurance;
    private TextView noInsurance;

    //Patient Type Dialog Variables
    private TextView outPatientDepartment;
    private TextView inPatientDepartment;

    //String to Store Intent Data
    private String name = "";
    private String emailID = "";
    private String createdOn = "";
    private String Address = "";
    private String mobile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //UI Elements
        profilePicture = (CircleImageView) findViewById(R.id.profilePicture);
        profileName = (TextView) findViewById(R.id.profileName);
        email = (TextView) findViewById(R.id.email);
        creationDate = (TextView) findViewById(R.id.creationDate);
        address = (TextView) findViewById(R.id.address);
        appointment = (CardView) findViewById(R.id.appointment);
        searchNearByHospital = (CardView) findViewById(R.id.searchNearByHospital);
        searchDoctor = (CardView) findViewById(R.id.searchDoctor);
        checkAppointment = (CardView) findViewById(R.id.checkAppointment);
        medicalRecords = (CardView) findViewById(R.id.medicalRecords);

        //Intent Data Fetching and Updating Text Views
        //Intent i = getIntent();
        //name = i.getStringExtra("userName");
        //emailID = i.getStringExtra("email");
        //createdOn = i.getStringExtra("createdOn");
        //Address = i.getStringExtra("address");
        getUserData();

        //Appointment Dialog Elements
        appointmentDialog = new Dialog(homepage.this);
        appointmentDialog.setContentView(R.layout.dialog_appointments);
        appointmentDialog.setCancelable(true);

        //Doctor Dialog Elements
        doctorDialog = new Dialog(homepage.this);
        doctorDialog.setContentView(R.layout.dailog_doctor);
        doctorDialog.setCancelable(true);

        //Appointment Type Dialog Elements
        appointmentTypesDialog = new Dialog(homepage.this);
        appointmentTypesDialog.setContentView(R.layout.dialog_appointments_types);
        appointmentTypesDialog.setCancelable(true);

        //Insurance Dialog Elements
        insuranceDialog = new Dialog(homepage.this);
        insuranceDialog.setContentView(R.layout.dialog_insurance);
        insuranceDialog.setCancelable(true);

        //Patient Type Dialog Elements
        patientTypeDialog = new Dialog(homepage.this);
        patientTypeDialog.setContentView(R.layout.dialog_patient_type);
        patientTypeDialog.setCancelable(true);

        //On Click Listener
        appointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showPatientTypesDialog();
            }
        });

        searchNearByHospital.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToNearbyHospital();
            }
        });

        searchDoctor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        checkAppointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showAppointmentDialog();
            }
        });

        medicalRecords.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToMedicalRecords();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToLogin();
    }

    //Setting Up User Info
    private void setUserInfo()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                profileName.setText(name);
                email.setText(emailID);
                creationDate.setText(createdOn);
                address.setText(Address);
            }
        });
    }

    public void getUserData()
    {
        userInfoStorage = getSharedPreferences(userInfoPreferenceName, Context.MODE_PRIVATE);
        name = userInfoStorage.getString(userInfo_key_name,"");
        emailID = userInfoStorage.getString(userInfo_key_email,"");
        createdOn = userInfoStorage.getString(userInfo_key_createdOn,"");
        Address = userInfoStorage.getString(userInfo_key_address,"");
        mobile = userInfoStorage.getString(userInfo_key_mobile,"");

        setUserInfo();
    }

    //Activity Switcher Functions
    private void goToLogin()
    {
        Intent login = new Intent(homepage.this, com.godsend.com.activities.userManagements.login.class);
        startActivity(login);
        finish();
    }

    private void goToActiveAppointments()
    {
        Intent activeAppointments = new Intent(homepage.this, com.godsend.com.activities.appointments.activeAppointments.class);
        startActivity(activeAppointments);
        finish();
    }

    private void goToPastAppointments()
    {
        Intent pastAppointments = new Intent(homepage.this, com.godsend.com.activities.appointments.pastAppointments.class);
        startActivity(pastAppointments);
        finish();
    }

    private void goToCancelledAppointments()
    {
        Intent cancelledAppointments = new Intent(homepage.this, com.godsend.com.activities.appointments.cancelledAppointments.class);
        startActivity(cancelledAppointments);
        finish();
    }

    private void goToNearbyHospital()
    {
        Intent browseDoctor = new Intent(homepage.this, com.godsend.com.activities.nearByHospitals.nearByHospitals.class);
        startActivity(browseDoctor);
        finish();
    }

    private void goToMedicalRecords()
    {
        Intent medicalRecords = new Intent(homepage.this, com.godsend.com.activities.medicalRecords.medicalRecords.class);
        startActivity(medicalRecords);
        finish();
    }

    private void goToRegularAppointment()
    {
        Intent regularAppointment = new Intent(homepage.this, com.godsend.com.activities.appointments.regularAppointment.class);
        startActivity(regularAppointment);
        finish();
    }

    private void goToEmergencyAppointment()
    {
        Intent emergencyAppointment = new Intent(homepage.this, com.godsend.com.activities.appointments.emergencyAppointment.class);
        startActivity(emergencyAppointment);
        finish();
    }

    private void goToFavouriteDoctor()
    {
        Intent favouriteDoctor = new Intent(homepage.this, com.godsend.com.activities.doctor.favouriteDoctor.class);
        startActivity(favouriteDoctor);
        finish();
    }

    //Inflating Appointment Dialog
    private void showAppointmentDialog()
    {
        activeAppointment = (TextView) appointmentDialog.findViewById(R.id.activeAppointment);
        pastAppointment = (TextView) appointmentDialog.findViewById(R.id.pastAppointment);
        cancelledAppointments = (TextView) appointmentDialog.findViewById(R.id.cancelledAppointments);

        activeAppointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToActiveAppointments();
            }
        });

        pastAppointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToPastAppointments();
            }
        });

        cancelledAppointments.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToCancelledAppointments();
            }
        });

        Objects.requireNonNull(appointmentDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        appointmentDialog.show();
    }

    //Inflating Doctor Dialog
    private void showDoctorDialog()
    {
        browseDoctor = (TextView) doctorDialog.findViewById(R.id.browseDoctor);
        favouriteDoctor = (TextView) doctorDialog.findViewById(R.id.favouriteDoctor);

        browseDoctor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToRegularAppointment();
            }
        });

        favouriteDoctor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToFavouriteDoctor();
            }
        });

        Objects.requireNonNull(doctorDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        doctorDialog.show();
    }

    //Inflating Appointment Types Dialog
    private void showAppointmentTypesDialog()
    {
        regularAppointment = (TextView) appointmentTypesDialog.findViewById(R.id.regularAppointment);
        emergencyAppointment = (TextView) appointmentTypesDialog.findViewById(R.id.emergencyAppointment);

        regularAppointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDoctorDialog();
            }
        });

        emergencyAppointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToEmergencyAppointment();
            }
        });

        Objects.requireNonNull(appointmentTypesDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        appointmentTypesDialog.show();
    }

    //Inflating Insurance Dialog
    private void showInsuranceDialog()
    {
        haveInsurance = (TextView) insuranceDialog.findViewById(R.id.haveInsurance);
        noInsurance = (TextView) insuranceDialog.findViewById(R.id.noInsurance);

        haveInsurance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(homepage.this, "Need information on Insurance Form", Toast.LENGTH_SHORT).show();
            }
        });

        noInsurance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(homepage.this, "Clarification Needed", Toast.LENGTH_SHORT).show();
            }
        });

        Objects.requireNonNull(insuranceDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        insuranceDialog.show();
    }

    //Inflating Patient Types Dialog
    private void showPatientTypesDialog()
    {
        outPatientDepartment = (TextView) patientTypeDialog.findViewById(R.id.outPatientDepartment);
        inPatientDepartment = (TextView) patientTypeDialog.findViewById(R.id.inPatientDepartment);

        outPatientDepartment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showAppointmentTypesDialog();
            }
        });

        inPatientDepartment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showInsuranceDialog();
            }
        });

        Objects.requireNonNull(patientTypeDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        patientTypeDialog.show();
    }
}
