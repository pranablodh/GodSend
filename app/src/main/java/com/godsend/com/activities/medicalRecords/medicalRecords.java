package com.godsend.com.activities.medicalRecords;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.godsend.com.R;
import com.godsend.com.fragments.addNewRecord;
import com.godsend.com.fragments.myRecords;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.Objects;

public class medicalRecords extends AppCompatActivity
{

    //UI Elements Variables
    private myRecords MyRecords;
    private addNewRecord AddNewRecord;
    private FrameLayout main_frame;

    //Context Variable
    public static Context contextOfApplication;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.myRecords:
                    setFragments(MyRecords);
                    return true;

                case R.id.addNewRecord:
                    setFragments(AddNewRecord);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Setting Action Bar Title
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.medical_records));

        //UI Elements
        MyRecords = new myRecords();
        AddNewRecord = new addNewRecord();
        main_frame = (FrameLayout) findViewById(R.id.main_frame);

        //By Default My Records Will Be Selected
        navView.getMenu().getItem(0).setChecked(true);
        setFragments(MyRecords);

        //Context Assignment
        contextOfApplication = getApplicationContext();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToHomePage();
    }

    //Retuning Context to Fragments
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    //Activity Switcher Functions
    private void goToHomePage()
    {
        Intent homePage = new Intent(medicalRecords.this, com.godsend.com.activities.userManagements.homepage.class);
        startActivity(homePage);
        finish();
    }

    //Fragment Switcher
    private void setFragments(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
