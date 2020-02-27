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

public class pastAppointmentAdapter extends RecyclerView.Adapter<pastAppointmentAdapter.pastAppointmentViewHolder>
{
    private Context mCtx;
    private List<appointmentDataBinder> appointmentList;

    public pastAppointmentAdapter(Context mCtx, List<appointmentDataBinder> appointmentList)
    {
        this.mCtx = mCtx;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public pastAppointmentAdapter.pastAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_past_appointment_list, null);
        return new pastAppointmentAdapter.pastAppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final pastAppointmentAdapter.pastAppointmentViewHolder pastAppointmentViewHolder, int i)
    {
        appointmentDataBinder aDataBind = appointmentList.get(i);
        pastAppointmentViewHolder.bookingDate.setText(aDataBind.getBookingDate());
        pastAppointmentViewHolder.bookingDateSuffix.setText(aDataBind.getBookingDateSuffix());
        pastAppointmentViewHolder.monthYear.setText(aDataBind.getMonthYear());
        pastAppointmentViewHolder.doctorName.setText(aDataBind.getDoctorName());
        pastAppointmentViewHolder.hospitalName.setText(aDataBind.getHospitalName());
        pastAppointmentViewHolder.address.setText(aDataBind.getAddress());
        pastAppointmentViewHolder.time.setText(aDataBind.getTime());
    }

    @Override
    public int getItemCount()
    {
        return appointmentList.size();
    }

    class pastAppointmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView bookingDate;
        TextView bookingDateSuffix;
        TextView monthYear;
        TextView doctorName;
        TextView hospitalName;
        TextView address;
        TextView time;

        pastAppointmentViewHolder(@NonNull View itemView)
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
