package com.example.alumninitjsr.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumninitjsr.APIClient;
import com.example.alumninitjsr.R;
import com.example.alumninitjsr.responses.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends Fragment {

    TextView signUp,forgotPwdBtn;
    EditText loginId,loginPwd;
    Button loginBtn;
    ProgressDialog progressDialog;

    public SignIn() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);
        setUpViews(view);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Logging in...");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp signUp = new SignUp();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.wrapper,signUp);
                ft.commit();
            }

        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername=loginId.getText().toString().trim();
                String loginPassword=loginPwd.getText().toString().trim();

                if(loginUsername.isEmpty()){
                    Toast.makeText(getContext(),"Enter username!",Toast.LENGTH_SHORT).show();
                }
                else if(loginPassword.isEmpty()){
                    Toast.makeText(getContext(),"Enter password!",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    startLogin(loginUsername,loginPassword);
                }
            }
        });
        return view;
    }

    private void startLogin(String loginUsername,String loginPassword){
        HashMap<String,String> loginRequest=new HashMap<String, String>();
        loginRequest.put("username",loginUsername);
        loginRequest.put("password",loginPassword);
        Call<LoginResponse> call= APIClient.getApiInterface().loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse=response.body();
                    Toast.makeText(getContext(),loginResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"Login failed!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Throwable "+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpViews(View v){
        signUp=v.findViewById(R.id.sign_up_btn);
        loginId=v.findViewById(R.id.login_id);
        loginPwd=v.findViewById(R.id.login_pwd);
        loginBtn=v.findViewById(R.id.login_btn);
        forgotPwdBtn=v.findViewById(R.id.forgot_pwd_btn);
    }

}