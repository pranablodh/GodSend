package com.godsend.com.recyclerViews.appointments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsend.com.R;

import java.util.List;

public class cancelledAppointmentAdapter extends RecyclerView.Adapter<cancelledAppointmentAdapter.cancelledAppointmentViewHolder>
{
    private Context mCtx;
    private List<appointmentDataBinder> appointmentList;

    public cancelledAppointmentAdapter(Context mCtx, List<appointmentDataBinder> appointmentList)
    {
        this.mCtx = mCtx;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public cancelledAppointmentAdapter.cancelledAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_cancelled_appointment, null);
        return new cancelledAppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final cancelledAppointmentAdapter.cancelledAppointmentViewHolder cancelledAppointmentViewHolder, int i)
    {
        appointmentDataBinder aDataBind = appointmentList.get(i);
        cancelledAppointmentViewHolder.bookingDate.setText(aDataBind.getBookingDate());
        cancelledAppointmentViewHolder.bookingDateSuffix.setText(aDataBind.getBookingDateSuffix());
        cancelledAppointmentViewHolder.monthYear.setText(aDataBind.getMonthYear());
        cancelledAppointmentViewHolder.doctorName.setText(aDataBind.getDoctorName());
        cancelledAppointmentViewHolder.hospitalName.setText(aDataBind.getHospitalName());
        cancelledAppointmentViewHolder.address.setText(aDataBind.getAddress());
        cancelledAppointmentViewHolder.time.setText(aDataBind.getTime());
    }

    @Override
    public int getItemCount()
    {
        return appointmentList.size();
    }

    class cancelledAppointmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView bookingDate;
        TextView bookingDateSuffix;
        TextView monthYear;
        TextView doctorName;
        TextView hospitalName;
        TextView address;
        TextView time;

        cancelledAppointmentViewHolder(@NonNull View itemView)
        {
            super(itemView);
            bookingDate = itemView.findViewById(R.id.bookingDate);
            bookingDateSuffix = itemView.findViewById(R.id.bookingDateSuffix);
            monthYear = itemView.findViewById(R.id.monthYear);
            doctorName = itemView.findViewById(R.id.doctorName);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
        }
    }
}

