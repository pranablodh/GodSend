package com.godsend.com.recyclerViews.appointments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsend.com.R;

import java.util.List;

public class activeAppointmentAdapter extends RecyclerView.Adapter<activeAppointmentAdapter.activeAppointmentViewHolder>
{
    private Context mCtx;
    private List<appointmentDataBinder> appointmentList;

    public activeAppointmentAdapter(Context mCtx, List<appointmentDataBinder> appointmentList)
    {
        this.mCtx = mCtx;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public activeAppointmentAdapter.activeAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_active_appointment, null);
        return new activeAppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final activeAppointmentAdapter.activeAppointmentViewHolder activeAppointmentViewHolder, int i)
    {
        appointmentDataBinder aDataBind = appointmentList.get(i);
        activeAppointmentViewHolder.bookingDate.setText(aDataBind.getBookingDate());
        activeAppointmentViewHolder.bookingDateSuffix.setText(aDataBind.getBookingDateSuffix());
        activeAppointmentViewHolder.monthYear.setText(aDataBind.getMonthYear());
        activeAppointmentViewHolder.doctorName.setText(aDataBind.getDoctorName());
        activeAppointmentViewHolder.hospitalName.setText(aDataBind.getHospitalName());
        activeAppointmentViewHolder.address.setText(aDataBind.getAddress());
        activeAppointmentViewHolder.time.setText(aDataBind.getTime());

        //On Click Listener
        activeAppointmentViewHolder.cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                cancelAppointment();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return appointmentList.size();
    }

    class activeAppointmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView bookingDate;
        TextView bookingDateSuffix;
        TextView monthYear;
        TextView doctorName;
        TextView hospitalName;
        TextView address;
        TextView time;
        Button cancelButton;

        activeAppointmentViewHolder(@NonNull View itemView)
        {
            super(itemView);
            bookingDate = itemView.findViewById(R.id.bookingDate);
            bookingDateSuffix = itemView.findViewById(R.id.bookingDateSuffix);
            monthYear = itemView.findViewById(R.id.monthYear);
            doctorName = itemView.findViewById(R.id.doctorName);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
            cancelButton = itemView.findViewById(R.id.cancelButton);
        }
    }

    //Showing Cancel Appointment Dialog
    private void cancelAppointment()
    {
        new AlertDialog.Builder(mCtx)
                .setTitle(mCtx.getResources().getString(R.string.cancel_heading))
                .setMessage(mCtx.getResources().getString(R.string.cancle_alert))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .setNegativeButton(android.R.string.no,  new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
