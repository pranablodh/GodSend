package com.godsend.com.activities.onBoarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.godsend.com.R;

public class sliderAdapter extends PagerAdapter
{
    Context mCtx;
    LayoutInflater layoutInflater;

    public sliderAdapter(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    //Array
    public int[] images = {R.drawable.hospital_onboarding, R.drawable.floppy_disk, R.drawable.medical_book, R.drawable.search_home_onboarding};

    public String[] headings = {"GodSend", "Save Records", "Book Appointment", "Search Hospital"};

    public String[] footers = {"An App That Strives to Bring\nHealthcare Right at Your Fingertip.",
                                "Your Medical Records are\nImportant to Identify Critical\nDetails. Save and Retrieve Them\nWith Ease",
                                "Search for Doctors, Choose by\nTheir Details and Then Pay The\nFees and Book An Appointment.\nIt is Easy",
                                "Search Your Nearest Hospitals.\nKnow About Them and Get Their\nLocation on Map"};

    @Override
    public int getCount()
    {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater) mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_slide_layout, container, false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.sliderImage);
        TextView header = (TextView) view.findViewById(R.id.header);
        TextView footer = (TextView) view.findViewById(R.id.footer);

        sliderImage.setImageResource(images[position]);
        header.setText(headings[position]);
        footer.setText(footers[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((LinearLayout) object);
    }
}
