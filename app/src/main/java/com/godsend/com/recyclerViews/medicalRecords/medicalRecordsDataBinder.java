package com.godsend.com.recyclerViews.medicalRecords;

public class medicalRecordsDataBinder
{
    private String diseaseName = "";
    private String prescriptions = "";
    private String description = "";

    public medicalRecordsDataBinder(String diseaseName, String prescriptions, String description)
    {
        this.diseaseName = diseaseName;
        this.prescriptions = prescriptions;
        this.description = description;
    }

    public String getDiseaseName()
    {
        return  diseaseName;
    }

    public String getPrescriptions()
    {
        return prescriptions;
    }

    public String getDescription()
    {
        return description;
    }
}
