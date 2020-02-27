package com.godsend.com.recyclerViews.hospitalList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsend.com.R;

import java.util.List;

public class nearByHospitalAdapter extends RecyclerView.Adapter<nearByHospitalAdapter.nearByHospitalViewHolder>
{
    private Context mCtx;
    private List<nearByHospitalDataBinder> hospitalList;

    public nearByHospitalAdapter(Context mCtx, List<nearByHospitalDataBinder> hospitalList)
    {
        this.mCtx = mCtx;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public nearByHospitalAdapter.nearByHospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_near_by_hospital, null);
        return new nearByHospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final nearByHospitalAdapter.nearByHospitalViewHolder nearByHospitalViewHolder, int i)
    {
        nearByHospitalDataBinder aDataBind = hospitalList.get(i);
        nearByHospitalViewHolder.hospitalName.setText(aDataBind.getHospitalName());
        nearByHospitalViewHolder.distance.setText(aDataBind.getDistance());
        nearByHospitalViewHolder.address.setText(aDataBind.getAddress());
        nearByHospitalViewHolder.description.setText(aDataBind.getDescription());

        //On Click Listener
        nearByHospitalViewHolder.checkButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToNearByHospitalBookingList();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return hospitalList.size();
    }

    class nearByHospitalViewHolder extends RecyclerView.ViewHolder
    {
        TextView hospitalName;
        TextView distance;
        TextView address;
        TextView description;
        Button checkButton;

        nearByHospitalViewHolder(@NonNull View itemView)
        {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            distance = itemView.findViewById(R.id.distance);
            address = itemView.findViewById(R.id.address);
            description = itemView.findViewById(R.id.description);
            checkButton = itemView.findViewById(R.id.checkButton);
        }
    }

    //Activity Switcher Function
    private void goToNearByHospitalBookingList()
    {
        Intent nearByHospitalBookingList = new Intent(mCtx, com.godsend.com.activities.nearByHospitals.nearByHospitalBooking.class);
        mCtx.startActivity(nearByHospitalBookingList);
    }
}
