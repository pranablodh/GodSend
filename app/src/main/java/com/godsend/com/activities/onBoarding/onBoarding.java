package com.godsend.com.activities.onBoarding;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.godsend.com.R;

public class onBoarding extends AppCompatActivity
{

    //Background Color Changer Element
    AnimationDrawable animationDrawable;
    LinearLayout animationBackground;

    //On Boarding Element
    private ViewPager viewPager;
    private LinearLayout buttonContainer;
    private sliderAdapter SliderAdapter;
    private TextView[] mDots;
    private Button previous;
    private Button next;
    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //UI Elements
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        buttonContainer = (LinearLayout) findViewById(R.id.buttonContainer);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        previous.setVisibility(View.INVISIBLE);

        //On Boarding Element Variables
        SliderAdapter = new sliderAdapter(this);
        viewPager.setAdapter(SliderAdapter);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        buttonContainer = (LinearLayout) findViewById(R.id.buttonContainer);

        //Animated Background
        animationBackground = (LinearLayout) findViewById(R.id.animationBackground);
        animationDrawable = (AnimationDrawable) animationBackground.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(3000);

        //Adding Dots
        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(viewListener);

        //On Click Listener
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(next.getText().equals("Finish"))
                {
                    goToLogin();
                }

                viewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        previous.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                viewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
        {
            animationDrawable.start();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
        {
            animationDrawable.stop();
        }
    }

    //Activity Switcher Functions
    private void goToLogin()
    {
        Intent login = new Intent(onBoarding.this, com.godsend.com.activities.userManagements.login.class);
        startActivity(login);
        finish();
    }

    //Dots Adding Function
    public void addDotsIndicator(int position)
    {
        mDots = new TextView[SliderAdapter.getCount()];
        buttonContainer.removeAllViews();

        for(int i = 0; i < mDots.length; i++)
        {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(ContextCompat.getColor(this, R.color.semiTransparentColor));

            buttonContainer.addView(mDots[i]);
        }

        if(mDots.length > 0)
        {
            mDots[position].setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        }
    }

    //On Boarding Flipper
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {

        }

        @Override
        public void onPageSelected(int position)
        {
            addDotsIndicator(position);
            mCurrentPage = position;

            if(position == 0)
            {
                previous.setEnabled(false);
                previous.setVisibility(View.INVISIBLE);
                next.setEnabled(true);
                next.setText(getString(R.string.next));
            }

            else if (position == mDots.length - 1)
            {
                previous.setEnabled(true);
                previous.setVisibility(View.VISIBLE);
                next.setEnabled(true);
                next.setText(getString(R.string.finish_button));
            }

            else
            {
                previous.setEnabled(true);
                previous.setVisibility(View.VISIBLE);
                next.setEnabled(true);
                next.setText(getString(R.string.next));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };
}
