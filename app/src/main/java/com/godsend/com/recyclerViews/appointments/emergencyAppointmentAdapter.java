package com.godsend.com.recyclerViews.appointments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.godsend.com.R;

import java.util.List;
import java.util.Objects;

public class emergencyAppointmentAdapter extends RecyclerView.Adapter<emergencyAppointmentAdapter.emergencyAppointmentViewHolder>
{
    private Context mCtx;
    private List<emergencyAppointmentDataBinder> appointmentList;
    private Dialog dialogAmbulance;
    private TextView ambulanceYes;
    private TextView ambulanceNo;

    public emergencyAppointmentAdapter(Context mCtx, List<emergencyAppointmentDataBinder> appointmentList)
    {
        this.mCtx = mCtx;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public emergencyAppointmentAdapter.emergencyAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_emergency_appointment, null);
        return new emergencyAppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final emergencyAppointmentAdapter.emergencyAppointmentViewHolder emergencyAppointmentViewHolder, int i)
    {
        emergencyAppointmentDataBinder aDataBind = appointmentList.get(i);
        emergencyAppointmentViewHolder.hospitalName.setText(aDataBind.getHospitalName());
        emergencyAppointmentViewHolder.hospitalAddress.setText(aDataBind.getHospitalAddress());
        emergencyAppointmentViewHolder.hospitalDescription.setText(aDataBind.getHospitalDescription());

        //On Click Listener
        emergencyAppointmentViewHolder.emergencyAppointmentCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showAmbulanceDialog();
            }
        });

        //Dialog Initialization
        dialogAmbulance = new Dialog(mCtx);
    }

    @Override
    public int getItemCount()
    {
        return appointmentList.size();
    }

    class emergencyAppointmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView hospitalName;
        TextView hospitalAddress;
        TextView hospitalDescription;
        CardView emergencyAppointmentCard;

        emergencyAppointmentViewHolder(@NonNull View itemView)
        {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalAddress = itemView.findViewById(R.id.hospitalAddress);
            hospitalDescription = itemView.findViewById(R.id.hospitalDescription);
            emergencyAppointmentCard = itemView.findViewById(R.id.emergencyAppointmentCard);
        }
    }

    //Ambulance Dialog
    private void showAmbulanceDialog()
    {
        dialogAmbulance.setContentView(R.layout.dialog_ambulance);
        dialogAmbulance.setCancelable(true);
        ambulanceYes = (TextView) dialogAmbulance.findViewById(R.id.ambulanceYes);
        ambulanceNo = (TextView) dialogAmbulance.findViewById(R.id.ambulanceNo);

        ambulanceYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        ambulanceNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        Objects.requireNonNull(dialogAmbulance.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAmbulance.show();
    }
}
