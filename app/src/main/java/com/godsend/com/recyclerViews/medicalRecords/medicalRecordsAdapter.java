package com.godsend.com.recyclerViews.medicalRecords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsend.com.R;

import java.util.List;

public class medicalRecordsAdapter extends RecyclerView.Adapter<medicalRecordsAdapter.medicalRecordsViewHolder>
{
    private Context mCtx;
    private List<medicalRecordsDataBinder> reportList;

    public medicalRecordsAdapter(Context mCtx, List<medicalRecordsDataBinder> reportList)
    {
        this.mCtx = mCtx;
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public medicalRecordsAdapter.medicalRecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_medical_records, null);
        return new medicalRecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final medicalRecordsAdapter.medicalRecordsViewHolder medicalRecordsViewHolder, int i)
    {
        medicalRecordsDataBinder aDataBind = reportList.get(i);
        medicalRecordsViewHolder.diseaseName.setText(aDataBind.getDiseaseName());
        medicalRecordsViewHolder.prescriptions.setText(aDataBind.getPrescriptions());
        medicalRecordsViewHolder.description.setText(aDataBind.getDescription());

        medicalRecordsViewHolder.attachedFileLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(mCtx,"This Will Open An Link Into Your Browser", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return reportList.size();
    }

    class medicalRecordsViewHolder extends RecyclerView.ViewHolder
    {
        TextView diseaseName;
        TextView prescriptions;
        TextView description;
        TextView attachedFileLink;

        medicalRecordsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            diseaseName = itemView.findViewById(R.id.diseaseName);
            prescriptions = itemView.findViewById(R.id.prescriptions);
            description = itemView.findViewById(R.id.description);
            attachedFileLink = itemView.findViewById(R.id.attachedFileLink);
        }
    }
}
