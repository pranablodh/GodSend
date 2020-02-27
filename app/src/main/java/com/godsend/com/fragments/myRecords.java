package com.godsend.com.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.godsend.com.R;
import com.godsend.com.recyclerViews.hospitalList.nearByHospitalDataBinder;
import com.godsend.com.recyclerViews.medicalRecords.medicalRecordsAdapter;
import com.godsend.com.recyclerViews.medicalRecords.medicalRecordsDataBinder;

import java.util.ArrayList;
import java.util.List;

public class myRecords extends Fragment
{

    //UI Elements Variables
    private List<medicalRecordsDataBinder> reportList;
    RecyclerView myRecordsRecyclerView;

    public myRecords()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_records, container, false);

        //UI Elements
        myRecordsRecyclerView = (RecyclerView) view.findViewById(R.id.myRecordsRecyclerView);
        myRecordsRecyclerView.setHasFixedSize(true);
        myRecordsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        inflateRecyclerView();

        return view;
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        reportList = new ArrayList<>();

        reportList.add(new medicalRecordsDataBinder("Broken Hand", "X-Ray", "Do immediate X-Ray, then CT Scan"));
        reportList.add(new medicalRecordsDataBinder("Common Cold", "Paracetamol", "If problem persists then do ELISA test"));
        medicalRecordsAdapter adapter = new medicalRecordsAdapter(getActivity(), reportList);
        myRecordsRecyclerView.setAdapter(adapter);
    }
}
