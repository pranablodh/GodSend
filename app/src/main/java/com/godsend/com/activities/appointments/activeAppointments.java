package com.godsend.com.activities.appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.recyclerViews.appointments.activeAppointmentAdapter;
import com.godsend.com.recyclerViews.appointments.appointmentDataBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class activeAppointments extends AppCompatActivity
{

    private List<appointmentDataBinder> appointmentList;
    RecyclerView activeAppointmentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_appointments);

        //Setting Action Bar Title
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.active_appointment));

        //UI Elements
        activeAppointmentRecyclerView = (RecyclerView) findViewById(R.id.activeAppointmentRecyclerView);
        activeAppointmentRecyclerView.setHasFixedSize(true);
        activeAppointmentRecyclerView.setLayoutManager(new LinearLayoutManager(activeAppointments.this));

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
        Intent homePage = new Intent(activeAppointments.this, com.godsend.com.activities.userManagements.homepage.class);
        startActivity(homePage);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        appointmentList = new ArrayList<>();

        appointmentList.add(new appointmentDataBinder("30","th","April 2019", "Rajat Chowdhury", "Fortis", "Kolkata", "10 A.M."));
        appointmentList.add(new appointmentDataBinder("31","th","April 2018", "Aniruddha Das", "Apollo", "Chennai", "12 A.M."));
        activeAppointmentAdapter adapter = new activeAppointmentAdapter(activeAppointments.this, appointmentList);

        activeAppointmentRecyclerView.setAdapter(adapter);
    }
}
