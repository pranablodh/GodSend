package com.godsend.com.recyclerViews.doctorList;

public class doctorListDataBinder
{
    private int ratingStar;
    private String doctorName = "";
    private String doctorDegree = "";
    private String doctorSpecialization = "";
    private String doctorExperience = "";
    private String doctorLanguage = "";

    public doctorListDataBinder(int ratingStar, String doctorName, String doctorDegree,
                                 String doctorSpecialization, String doctorExperience, String doctorLanguage)
    {
        this.ratingStar = ratingStar;
        this.doctorName = doctorName;
        this.doctorDegree = doctorDegree;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorExperience = doctorExperience;
        this.doctorLanguage = doctorLanguage;
    }

    public int getRatingStar()
    {
        return  ratingStar;
    }

    public String getDoctorName()
    {
        return doctorName;
    }

    public String getDoctorDegree()
    {
        return doctorDegree;
    }

    public String getDoctorSpecialization()
    {
        return doctorSpecialization;
    }

    public String getDoctorExperience()
    {
        return doctorExperience;
    }

    public String getDoctorLanguage()
    {
        return doctorLanguage;
    }
}
