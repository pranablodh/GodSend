package com.godsend.com.activities.appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.recyclerViews.doctorList.doctorListAdapter;
import com.godsend.com.recyclerViews.doctorList.doctorListDataBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class regularAppointment extends AppCompatActivity
{

    private List<doctorListDataBinder> doctorList;
    RecyclerView regularAppointmentRecyclerView;
    private int[] imageStar = {R.drawable.star_grey, R.drawable.star_red};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_appointment);

        //Setting Action Bar Title
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.regular_appointment));

        //UI Elements
        regularAppointmentRecyclerView = (RecyclerView) findViewById(R.id.regularAppointmentRecyclerView);
        regularAppointmentRecyclerView.setHasFixedSize(true);
        regularAppointmentRecyclerView.setLayoutManager(new LinearLayoutManager(regularAppointment.this));

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
        Intent homePage = new Intent(regularAppointment.this, com.godsend.com.activities.userManagements.homepage.class);
        startActivity(homePage);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        doctorList = new ArrayList<>();

        doctorList.add(new doctorListDataBinder(imageStar[0],"Aniruddha Das","MBBS", "Medicine", "10 years in Service", "Bengali"));
        doctorList.add(new doctorListDataBinder(imageStar[1],"Rajat Chowdhury","MBBS. MD", "Anesthesia", "15 years in Service", "English"));
        doctorListAdapter adapter = new doctorListAdapter(regularAppointment.this, doctorList);

        regularAppointmentRecyclerView.setAdapter(adapter);
    }
}
