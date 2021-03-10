package com.example.alumninitjsr.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alumninitjsr.APIClient;
import com.example.alumninitjsr.Home;
import com.example.alumninitjsr.R;
import com.example.alumninitjsr.models.UserProfile;
import com.example.alumninitjsr.responses.LoginResponse;
import com.example.alumninitjsr.responses.ProfileResponse;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumniProfile extends Fragment {

    ImageView alumniLinkedIn,alumniFb,alumniTwitter,alumniWebsite;
    static String token,alumniId;
    CircleImageView proImg;
    TextView proName,proBio,proDob,proBatch,proBranch,proProfession,proPosition,proCurAdd,proHometown,proEmail,proMob;

    public AlumniProfile() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_alumni_profile, container, false);
        setUpViews(view);

        token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.W3sidXNlcm5hbWUiOiJhYmhpc2hla2pudmsiLCJhbHVtbmlfaWQiOiIxOTcwUEkxIiwiZW1haWwiOm51bGx9XQ.nTnYSVYsV6HWiUKPU4lim00SGjTgGeeZHi0o_Z4utXs";
        alumniId="1970PI2";
        loadData();

        return view;
    }

    private void setUpViews(View view){
        alumniFb=view.findViewById(R.id.alumni_fb);
        alumniWebsite=view.findViewById(R.id.alumni_web);
        alumniLinkedIn=view.findViewById(R.id.alumni_linked_in);
        alumniTwitter=view.findViewById(R.id.alumni_twitter);
        proImg=view.findViewById(R.id.pro_img);
        proName=view.findViewById(R.id.pro_name);
        proBio=view.findViewById(R.id.pro_bio);
        proDob=view.findViewById(R.id.pro_dob);
        proBatch=view.findViewById(R.id.pro_batch);
        proBranch=view.findViewById(R.id.pro_branch);
        proProfession=view.findViewById(R.id.pro_profession);
        proPosition=view.findViewById(R.id.pro_position);
        proCurAdd=view.findViewById(R.id.pro_cur_address);
        proHometown=view.findViewById(R.id.pro_hometown);
        proEmail=view.findViewById(R.id.pro_email);
        proMob=view.findViewById(R.id.pro_mobile);
    }
    private void loadData(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token",token)
                .addFormDataPart("alumni_id",alumniId)
                .build();

        Call<ProfileResponse> call= APIClient.getApiInterface().alumniProfile(requestBody);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    ProfileResponse profileResponse = new ProfileResponse();
                    profileResponse=response.body();
                    Toast.makeText(getContext(),profileResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    viewData(profileResponse.getResponse());
                }
                else {
                    Toast.makeText(getContext(),"Failed to load data!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void viewData(final UserProfile userProfile){
        proName.setText(userProfile.getName());
        proBio.setText(userProfile.getBio());
        proDob.setText("Born on "+userProfile.getDob());
        proBatch.setText("Batch of "+userProfile.getBatch());
        proBranch.setText(userProfile.getBranch());
        proProfession.setText("Profession: "+userProfile.getProfession());
        proPosition.setText("Position: "+userProfile.getPosition());
        proCurAdd.setText("Lives at "+userProfile.getCurrent_city()+" "+userProfile.getCurrent_country());
        proHometown.setText("From "+userProfile.getHometown()+", India");
        proMob.setText("Phone: "+userProfile.getMobile());

        /*if(!userProfile.getLinkedin().isEmpty()){
            alumniWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(userProfile.getLinkedin()));
                    startActivity(i);
                }
            });
        }
        if(!userProfile.getWebsite().isEmpty()){
            alumniWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(userProfile.getWebsite()));
                    startActivity(i);
                }
            });
        }
        if(!userProfile.getFacebook().isEmpty()){
            alumniWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(userProfile.getFacebook()));
                    startActivity(i);
                }
            });
        }
        if(!userProfile.getTwitter().isEmpty()){
            alumniWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(userProfile.getTwitter()));
                    startActivity(i);
                }
            });
        }
        if(!userProfile.getImage().isEmpty()){
            Glide.with(proImg.getContext()).load(userProfile.getImage()).into(proImg);
        }*/
    }
}