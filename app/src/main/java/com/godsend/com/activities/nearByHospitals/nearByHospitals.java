package com.godsend.com.activities.nearByHospitals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.recyclerViews.hospitalList.nearByHospitalAdapter;
import com.godsend.com.recyclerViews.hospitalList.nearByHospitalDataBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class nearByHospitals extends AppCompatActivity
{

    private List<nearByHospitalDataBinder> hospitalList;
    RecyclerView nearByHospitalsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_hospitals);

        //Setting Action Bar Title
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.nearby_hospitals));

        //UI Elements
        nearByHospitalsRecyclerView = (RecyclerView) findViewById(R.id.nearByHospitalsRecyclerView);
        nearByHospitalsRecyclerView.setHasFixedSize(true);
        nearByHospitalsRecyclerView.setLayoutManager(new LinearLayoutManager(nearByHospitals.this));

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
        Intent homePage = new Intent(nearByHospitals.this, com.godsend.com.activities.userManagements.homepage.class);
        startActivity(homePage);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        hospitalList = new ArrayList<>();

        hospitalList.add(new nearByHospitalDataBinder("Apollo Hospital", "10 Kilometers", "Canal Circular Road", "Finest Hospital"));
        hospitalList.add(new nearByHospitalDataBinder("Fortis", "500 Meters", "E.M. Bypass", "Best In Neuron Surgery"));
        nearByHospitalAdapter adapter = new nearByHospitalAdapter(nearByHospitals.this, hospitalList);

        nearByHospitalsRecyclerView.setAdapter(adapter);
    }
}
