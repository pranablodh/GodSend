package com.godsend.com.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.godsend.com.R;
import com.godsend.com.activities.medicalRecords.medicalRecords;
import com.godsend.com.activities.userManagements.login;
import com.godsend.com.api.apiClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class addNewRecord extends Fragment
{
    //UI Elements Variables
    private EditText recordName;
    private EditText descriptions;
    private Button browseFile;
    private Button upload;
    private Spinner recordType;

    //Image Picker Elements
    private static final int PICK_FROM_GALLERY = 1;
    private Uri filePath;
    private Bitmap bitmap;
    private String FinalEncodedImage = "";

    //Progress Dialog Box
    private Dialog progressDialog;

    //API
    private String medicalRecordUploadAPI = apiClass.baseURL + apiClass.medicalRecordAPI;

    public addNewRecord()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_record, container, false);

        //UI Elements
        recordName = (EditText) view.findViewById(R.id.recordName);
        descriptions = (EditText) view.findViewById(R.id.descriptions);
        browseFile = (Button) view.findViewById(R.id.browseFile);
        upload = (Button) view.findViewById(R.id.upload);
        recordType = (Spinner) view.findViewById(R.id.recordType);

        //Dialog Initializer
        progressDialog = new Dialog(Objects.requireNonNull(getActivity()));

        browseFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pickImageFromGallery();
            }
        });

        upload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showProgressDialog();
                httpMedicalRecordRequest();
            }
        });

        return view;
    }

    //Show Progress Dialog
    private void showProgressDialog()
    {
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.dialog_progress);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();
    }

    //Image Picker
    private void pickImageFromGallery()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try
            {
                bitmap = BitmapFactory.decodeStream(medicalRecords.getContextOfApplication().getContentResolver().openInputStream(filePath));
                FinalEncodedImage = base64Encoding(bitmap);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //Base64 Encoding
    private String base64Encoding(Bitmap mPhoto)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        assert mPhoto != null;
        mPhoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void httpMedicalRecordRequest()
    {
        StringRequest loginRequest = new StringRequest(Request.Method.POST, medicalRecordUploadAPI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject server_response = new JSONObject(response);
                            String message = server_response.getString("msg");
                            String Response = server_response.getString("status");

                            if(Response.equals("success"))
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }

                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e)
                        {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Toast.makeText(getActivity(), String.valueOf(e), Toast.LENGTH_SHORT).show();
                            Log.d("JSON_DATA",String.valueOf(e));
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("pro_id", "22");
                MyData.put("hos_id", "33");
                MyData.put("doc_id", "44");
                MyData.put("image", FinalEncodedImage);
                MyData.put("rec_ty", recordType.getSelectedItem().toString());
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        requestQueue.add(loginRequest);
    }
}
