package com.godsend.com.recyclerViews.doctorList;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsend.com.R;
import com.godsend.com.activities.userManagements.userRegistration;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class doctorListAdapter extends RecyclerView.Adapter<doctorListAdapter.doctorListViewHolder>
{
    private Context mCtx;
    private List<doctorListDataBinder> doctorList;

    //Dialog Box Elements
    private Dialog bookAppointmentDialog;
    private Button bookingButton;
    private EditText bookingDate;
    private EditText bookingTime;
    private TextView doctorNameBooking;

    public doctorListAdapter(Context mCtx, List<doctorListDataBinder> doctorList)
    {
        this.mCtx = mCtx;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public doctorListAdapter.doctorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_doctor_list, null);
        return new doctorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final doctorListAdapter.doctorListViewHolder doctorListViewHolder, int i)
    {
        doctorListDataBinder aDataBind = doctorList.get(i);
        doctorListViewHolder.ratingStar.setBackgroundResource(aDataBind.getRatingStar());
        doctorListViewHolder.doctorName.setText(aDataBind.getDoctorName());
        doctorListViewHolder.doctorDegree.setText(aDataBind.getDoctorDegree());
        doctorListViewHolder.doctorSpecialization.setText(aDataBind.getDoctorSpecialization());
        doctorListViewHolder.doctorExperience.setText(aDataBind.getDoctorExperience());
        doctorListViewHolder.doctorLanguage.setText(aDataBind.getDoctorLanguage());

        //Dialog Initialization
        bookAppointmentDialog = new Dialog(mCtx);

        //On Click Listener
        doctorListViewHolder.bookAppointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showBookAppointmentDialog(doctorListViewHolder.doctorName.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return doctorList.size();
    }

    class doctorListViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ratingStar;
        TextView doctorName;
        TextView doctorDegree;
        TextView doctorSpecialization;
        TextView doctorExperience;
        TextView doctorLanguage;
        Button bookAppointment;

        doctorListViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ratingStar = itemView.findViewById(R.id.ratingStar);
            doctorName = itemView.findViewById(R.id.doctorName);
            doctorDegree = itemView.findViewById(R.id.doctorDegree);
            doctorSpecialization = itemView.findViewById(R.id.doctorSpecialization);
            doctorExperience = itemView.findViewById(R.id.doctorExperience);
            doctorLanguage = itemView.findViewById(R.id.doctorLanguage);
            bookAppointment = itemView.findViewById(R.id.bookAppointment);
        }
    }

    private void showBookAppointmentDialog(String docName)
    {
        bookAppointmentDialog.setContentView(R.layout.dialog_book_appointment);
        bookAppointmentDialog.setCancelable(true);
        doctorNameBooking = (TextView) bookAppointmentDialog.findViewById(R.id.doctorNameBooking);
        bookingButton = (Button) bookAppointmentDialog.findViewById(R.id.bookingButton);
        bookingDate =  (EditText) bookAppointmentDialog.findViewById(R.id.bookingDate);
        bookingTime =  (EditText) bookAppointmentDialog.findViewById(R.id.bookingTime);
        String doctorName = "Booking For Dr. " + docName;
        doctorNameBooking.setText(doctorName);

        bookingDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pickDate();
            }
        });

        bookingTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pickTime();
            }
        });

        bookingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        Objects.requireNonNull(bookAppointmentDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bookAppointmentDialog.show();
    }

    //Date Picker
    private void pickDate()
    {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(mCtx, new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday)
            {
                selectedmonth = selectedmonth + 1;
                String Date = selectedday + "/" + selectedmonth + "/" + selectedyear;
                bookingDate.setText(Date);
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    //Date Picker
    private void pickTime()
    {
        Calendar mCurrentTime=Calendar.getInstance();
        int mHour = mCurrentTime.get(Calendar.HOUR);
        int mMinute = mCurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(mCtx, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                if(selectedHour < 12)
                {
                    bookingTime.setText( selectedHour + ":" + selectedMinute + " A.M.");
                }

                else
                {
                    selectedHour -= 12;
                    bookingTime.setText( selectedHour + ":" + selectedMinute + " P.M.");
                }

            }
        }, mHour, mMinute,false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
