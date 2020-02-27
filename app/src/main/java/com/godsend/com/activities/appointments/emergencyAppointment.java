package com.godsend.com.activities.appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.recyclerViews.appointments.emergencyAppointmentAdapter;
import com.godsend.com.recyclerViews.appointments.emergencyAppointmentDataBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class emergencyAppointment extends AppCompatActivity
{

    private List<emergencyAppointmentDataBinder> appointmentList;
    RecyclerView emergencyAppointmentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_appointment);

        //Setting Action Bar Title
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.emergency_appointment));

        //UI Elements
        emergencyAppointmentRecyclerView = (RecyclerView) findViewById(R.id.emergencyAppointmentRecyclerView);
        emergencyAppointmentRecyclerView.setHasFixedSize(true);
        emergencyAppointmentRecyclerView.setLayoutManager(new LinearLayoutManager(emergencyAppointment.this));

        inflateRecyclerView();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToHomePage();
    }

    //Activity Switcher Functions
    private void goToHomePage()
    {
        Intent homePage = new Intent(emergencyAppointment.this, com.godsend.com.activities.userManagements.homepage.class);
        startActivity(homePage);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        appointmentList = new ArrayList<>();

        appointmentList.add(new emergencyAppointmentDataBinder("Fortis Hospital","E.M. Bypass","Best In Class In Service"));
        appointmentList.add(new emergencyAppointmentDataBinder("Medica Hospital","Mukundupur","Best In Class In Service, Since 1960."));
        emergencyAppointmentAdapter adapter = new emergencyAppointmentAdapter(emergencyAppointment.this, appointmentList);

        emergencyAppointmentRecyclerView.setAdapter(adapter);
    }
}
