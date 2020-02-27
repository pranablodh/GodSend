package com.godsend.com.recyclerViews.appointments;

public class emergencyAppointmentDataBinder
{
    private String hospitalName = "";
    private String hospitalAddress = "";
    private String hospitalDescription = "";

    public emergencyAppointmentDataBinder(String hospitalName, String hospitalAddress, String hospitalDescription)
    {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalDescription = hospitalDescription;
    }

    public String getHospitalName()
    {
        return hospitalName;
    }

    public String getHospitalAddress()
    {
        return hospitalAddress;
    }

    public String getHospitalDescription()
    {
        return hospitalDescription;
    }
}
