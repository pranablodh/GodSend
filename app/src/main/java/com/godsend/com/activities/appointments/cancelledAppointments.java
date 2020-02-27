package com.godsend.com.activities.appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.recyclerViews.appointments.appointmentDataBinder;
import com.godsend.com.recyclerViews.appointments.cancelledAppointmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class cancelledAppointments extends AppCompatActivity
{

    private List<appointmentDataBinder> appointmentList;
    RecyclerView cancelledAppointmentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelled_appointments);

        //Setting Action Bar Title
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.cancelled_appointments));

        //UI Elements
        cancelledAppointmentRecyclerView = (RecyclerView) findViewById(R.id.cancelledAppointmentRecyclerView);
        cancelledAppointmentRecyclerView.setHasFixedSize(true);
        cancelledAppointmentRecyclerView.setLayoutManager(new LinearLayoutManager(cancelledAppointments.this));

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
        Intent homePage = new Intent(cancelledAppointments.this, com.godsend.com.activities.userManagements.homepage.class);
        startActivity(homePage);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        appointmentList = new ArrayList<>();

        appointmentList.add(new appointmentDataBinder("30","th","April 2019", "Rajat Chowdhury", "Fortis", "Kolkata", "10 A.M."));
        appointmentList.add(new appointmentDataBinder("31","th","April 2018", "Aniruddha Das", "Apollo", "Chennai", "12 A.M."));
        cancelledAppointmentAdapter adapter = new cancelledAppointmentAdapter(cancelledAppointments.this, appointmentList);

        cancelledAppointmentRecyclerView.setAdapter(adapter);
    }
}
