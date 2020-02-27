package com.godsend.com.activities.splashScreen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.godsend.com.R;

import static java.security.AccessController.getContext;

public class splashScreen extends AppCompatActivity
{

    private TextView appName;
    private TextView appDescription;
    private ImageView logo;

    Animation animZoomIn;
    Animation blink;
    Animation slideUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //UI Elements
        appName = (TextView) findViewById(R.id.appName);
        appDescription = (TextView) findViewById(R.id.appDescription);
        logo = (ImageView) findViewById(R.id.logo);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        blinkAnimationLogo();
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    private void blinkAnimationLogo()
    {
        blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        logo.setAnimation(blink);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                blinkAnimationAppName();
            }
        },1000);
    }

    private void blinkAnimationAppName()
    {
        blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        appName.setAnimation(blink);
        setMargins(appName, 0, 100, 0, 0);
        appName.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                blinkAnimationAppDescription();
            }
        },1000);
    }

    private void blinkAnimationAppDescription()
    {
        blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        appDescription.setAnimation(blink);
        appDescription.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //goToLogin();
                goToOnBoarding();
            }
        },1000);
    }

    private void setMargins(View view, int left, int top, int right, int bottom)
    {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)
        {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private void goToOnBoarding()
    {
        Intent onBoarding = new Intent(splashScreen.this, com.godsend.com.activities.onBoarding.onBoarding.class);
        startActivity(onBoarding);
        finish();
    }

    //Activity Switcher Functions
    private void goToLogin()
    {
        Intent login = new Intent(splashScreen.this, com.godsend.com.activities.userManagements.login.class);
        startActivity(login);
        finish();
    }
}

