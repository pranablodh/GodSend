package com.godsend.com.activities.nearByHospitals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.activities.doctor.favouriteDoctor;
import com.godsend.com.recyclerViews.doctorList.doctorListAdapter;
import com.godsend.com.recyclerViews.doctorList.doctorListDataBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class nearByHospitalBooking extends AppCompatActivity
{

    private List<doctorListDataBinder> doctorList;
    RecyclerView nearByHospitalBookingRecyclerView;

    private int[] imageStar = {R.drawable.star_grey, R.drawable.star_red};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_hospital_booking);

        //Showing Action Bar
        Objects.requireNonNull(getSupportActionBar()).show();

        //UI Elements
        nearByHospitalBookingRecyclerView = (RecyclerView) findViewById(R.id.nearByHospitalBookingRecyclerView);
        nearByHospitalBookingRecyclerView.setHasFixedSize(true);
        nearByHospitalBookingRecyclerView.setLayoutManager(new LinearLayoutManager(nearByHospitalBooking.this));

        inflateRecyclerView();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToNearByHospital();
    }

    //Activity Switcher Functions
    private void goToNearByHospital()
    {
        Intent nearByHospital = new Intent(nearByHospitalBooking.this, com.godsend.com.activities.nearByHospitals.nearByHospitals.class);
        startActivity(nearByHospital);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        doctorList = new ArrayList<>();

        doctorList.add(new doctorListDataBinder(imageStar[0],"Aniruddha Das","MBBS", "Medicine", "10 years in Service", "Bengali"));
        doctorList.add(new doctorListDataBinder(imageStar[0],"Rajat Chowdhury","MBBS. MD", "Anesthesia", "15 years in Service", "English"));
        doctorListAdapter adapter = new doctorListAdapter(nearByHospitalBooking.this, doctorList);

        nearByHospitalBookingRecyclerView.setAdapter(adapter);
    }
}
