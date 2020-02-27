package com.godsend.com.recyclerViews.hospitalList;

public class nearByHospitalDataBinder
{
    private String hospitalName = "";
    private String distance = "";
    private String address = "";
    private String description = "";

    public nearByHospitalDataBinder(String hospitalName, String distance, String address, String description)
    {
        this.hospitalName = hospitalName;
        this.distance = distance;
        this.address = address;
        this.description = description;
    }

    public String getHospitalName()
    {
        return  hospitalName;
    }

    public String getDistance()
    {
        return distance;
    }

    public String getAddress()
    {
        return address;
    }

    public String getDescription()
    {
        return description;
    }
}
