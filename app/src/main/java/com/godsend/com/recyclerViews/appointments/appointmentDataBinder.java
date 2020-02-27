package com.godsend.com.recyclerViews.appointments;

import android.util.Log;

public class appointmentDataBinder
{
    private String bookingDate = "";
    private String bookingDateSuffix = "";
    private String monthYear = "";
    private String doctorName = "";
    private String hospitalName = "";
    private String address = "";
    private String time = "";

    public appointmentDataBinder(String bookingDate, String bookingDateSuffix, String monthYear,
                                 String doctorName, String hospitalName, String address, String time)
    {
        this.bookingDate = bookingDate;
        this.bookingDateSuffix = bookingDateSuffix;
        this.monthYear = monthYear;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.address = address;
        this.time = time;
    }

    public String getBookingDate()
    {
        return  bookingDate;
    }

    public String getBookingDateSuffix()
    {
        return bookingDateSuffix;
    }

    public String getMonthYear()
    {
        return monthYear;
    }

    public String getDoctorName()
    {
        return doctorName;
    }

    public String getHospitalName()
    {
        return hospitalName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getTime()
    {
        return time;
    }
}
