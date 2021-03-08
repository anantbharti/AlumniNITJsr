package com.example.alumninitjsr.fragments;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumninitjsr.MainActivity;
import com.example.alumninitjsr.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class SignUp extends Fragment {

    TextView signIn;
    EditText regDob;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Spinner spinner,batchSpinner;

    public SignUp() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        setUpViews(view);
        setSpinner();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn signIn = new SignIn();
                assert getFragmentManager() != null;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.wrapper,signIn);
                ft.commit();
            }
        });

        regDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCal();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                regDob.setText(date);
            }
        };


        return  view;
    }

    private void setUpViews(View view){
        signIn=view.findViewById(R.id.sign_in_btn);
        regDob=view.findViewById(R.id.reg_dob);
        spinner = view.findViewById(R.id.select_branch);
        batchSpinner=view.findViewById(R.id.select_batch);
    }

    private void setSpinner(){
        List<String> branches = new ArrayList<>();
        branches.add(0,"Select Branch");
        branches.add("Civil Engineering");
        branches.add("Computer Science and Engineering");
        branches.add("Electrical Engineering");
        branches.add("Electronics and Communication Engineering");
        branches.add("Mechanical Engineering");
        branches.add("Materials and Metallurgical Engineering");
        branches.add("Production and Industrial Engineering");
        branches.add("Master in Computer Applications");
        ArrayAdapter<String> dataAdapter;
        dataAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,branches);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        List<String> list = new ArrayList<>();
        list.add(0,"Batch");
        for(int i=1960;i<=2021;i++){
            list.add(""+i);
        }
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        batchSpinner.setAdapter(adapter);
    }

    private void openCal(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}