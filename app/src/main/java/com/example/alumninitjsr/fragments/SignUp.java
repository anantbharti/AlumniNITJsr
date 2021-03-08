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
import android.widget.Button;
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
    EditText regDob,regName,regUsername,regEmail,regLinkedIn,regHometown,regMobNo,regPwd;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Spinner branchSpinner,batchSpinner;
    Button register;
    static String rDob,rName,rUsername,rEmail,rLinkedIn,rHometown,rMobNo,rBranch,rBatch,rPwd;
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validFields()){
                    if(validUsername()){
                        Toast.makeText(getContext(),"Registering...",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(),"Username already exists! Change username",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(),"Fill all the details!",Toast.LENGTH_SHORT);
                }
            }
        });

        return  view;
    }

    private boolean validUsername(){
        return true;
    }

    private boolean validFields(){
        rName=regName.getText().toString().trim();
        if(rName.isEmpty()){
            Toast.makeText(getContext(),"Enter name!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rUsername=regUsername.getText().toString().trim();
        if(rUsername.isEmpty()){
            Toast.makeText(getContext(),"Enter username!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rEmail=regEmail.getText().toString().trim();
        if(rEmail.isEmpty()){
            Toast.makeText(getContext(),"Enter e-mail id!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rLinkedIn=regLinkedIn.getText().toString().trim();
        if(rLinkedIn.isEmpty()){
            Toast.makeText(getContext(),"Enter linkedIn profile!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rHometown=regHometown.getText().toString().trim();
        if(rHometown.isEmpty()){
            Toast.makeText(getContext(),"Enter hometown!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rDob=regDob.getText().toString().trim();
        if(rDob.isEmpty()){
            Toast.makeText(getContext(),"Enter date of birth!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rMobNo=regMobNo.getText().toString().trim();
        if(rMobNo.isEmpty()){
            Toast.makeText(getContext(),"Enter mobile number!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rBranch=branchSpinner.getSelectedItem().toString().trim();
        if(rBranch.equals("Select Branch")){
            Toast.makeText(getContext(),"Select branch!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rBatch=batchSpinner.getSelectedItem().toString().trim();
        if(rBatch.equals("Select Batch")){
            Toast.makeText(getContext(),"Select batch!",Toast.LENGTH_SHORT).show();
            return false;
        }
        rPwd=regPwd.getText().toString().trim();
        if(rPwd.isEmpty()){
            Toast.makeText(getContext(),"Enter password!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setUpViews(View view){
        signIn=view.findViewById(R.id.sign_in_btn);
        regDob=view.findViewById(R.id.reg_dob);
        branchSpinner = view.findViewById(R.id.select_branch);
        batchSpinner=view.findViewById(R.id.select_batch);
        regName=view.findViewById(R.id.reg_name);
        regUsername=view.findViewById(R.id.reg_username);
        regEmail=view.findViewById(R.id.reg_email);
        regLinkedIn=view.findViewById(R.id.reg_linkedIn_profile);
        regHometown=view.findViewById(R.id.reg_hometown);
        regMobNo=view.findViewById(R.id.reg_mob_no);
        regPwd=view.findViewById(R.id.reg_password);
        register=view.findViewById(R.id.btn_register);
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
        branchSpinner.setAdapter(dataAdapter);

        List<String> list = new ArrayList<>();
        list.add(0,"Select Batch");
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