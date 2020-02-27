package com.godsend.com.activities.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.activities.appointments.regularAppointment;
import com.godsend.com.recyclerViews.doctorList.doctorListAdapter;
import com.godsend.com.recyclerViews.doctorList.doctorListDataBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class favouriteDoctor extends AppCompatActivity
{

    private List<doctorListDataBinder> doctorList;
    RecyclerView favouriteDoctorRecyclerView;
    private int[] imageStar = {R.drawable.star_grey, R.drawable.star_red};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_doctor);

        //Setting Action Bar Title
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.favourite_doctor));

        //UI Elements
        favouriteDoctorRecyclerView = (RecyclerView) findViewById(R.id.favouriteDoctorRecyclerView);
        favouriteDoctorRecyclerView.setHasFixedSize(true);
        favouriteDoctorRecyclerView.setLayoutManager(new LinearLayoutManager(favouriteDoctor.this));

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
        Intent homePage = new Intent(favouriteDoctor.this, com.godsend.com.activities.userManagements.homepage.class);
        startActivity(homePage);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        doctorList = new ArrayList<>();

        doctorList.add(new doctorListDataBinder(imageStar[0],"Aniruddha Das","MBBS", "Medicine", "10 years in Service", "Bengali"));
        doctorList.add(new doctorListDataBinder(imageStar[0],"Rajat Chowdhury","MBBS. MD", "Anesthesia", "15 years in Service", "English"));
        doctorListAdapter adapter = new doctorListAdapter(favouriteDoctor.this, doctorList);

        favouriteDoctorRecyclerView.setAdapter(adapter);
    }
}
