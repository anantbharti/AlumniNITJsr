package com.example.alumninitjsr.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumninitjsr.APIClient;
import com.example.alumninitjsr.Home;
import com.example.alumninitjsr.R;
import com.example.alumninitjsr.responses.LoginResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class SignIn extends Fragment {

    TextView signUp,forgotPwdBtn;
    EditText loginId,loginPwd;
    Button loginBtn;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    CheckBox remMe;

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
        sharedPreferences=getActivity().getSharedPreferences("Credentials",MODE_PRIVATE);
        loginId.setText(sharedPreferences.getString("username",""));
        loginPwd.setText(sharedPreferences.getString("password",""));
        if(sharedPreferences.getString("remMe","").equals("1")){
            remMe.setChecked(true);
        }

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
                    if(remMe.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", loginUsername);
                        editor.putString("password", loginPassword);
                        editor.putString("remMe","1");
                        editor.commit();
                    }else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", "");
                        editor.putString("password", "");
                        editor.putString("remMe","0");
                        editor.commit();
                    }
                    startLogin(loginUsername,loginPassword);
                }
            }
        });
        return view;
    }

    private void startLogin(String loginUsername,String loginPassword){

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username",loginUsername)
                .addFormDataPart("password",loginPassword)
                .build();


        Call<LoginResponse> call= APIClient.getApiInterface().loginUser(requestBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse=response.body();
                    Toast.makeText(getContext(),loginResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    if(loginResponse.getStatus()==1){
                        startActivity(new Intent(getActivity(), Home.class));
                        getActivity().finish();
                    }
                }
                else {
                    Toast.makeText(getContext(),"Login failed!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpViews(View v){
        signUp=v.findViewById(R.id.sign_up_btn);
        loginId=v.findViewById(R.id.login_id);
        loginPwd=v.findViewById(R.id.login_pwd);
        loginBtn=v.findViewById(R.id.login_btn);
        forgotPwdBtn=v.findViewById(R.id.forgot_pwd_btn);
        remMe=v.findViewById(R.id.rem_me);
    }

}