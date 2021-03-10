package com.example.alumninitjsr.models;

public class UserProfile {
    String id,alumni_id,name,mobile,email,batch,branch,current_city,hometown
            ,current_country,image,bio,profession,position,alternate_email
            ,linkedin,facebook,twitter,blood_group,dob,website,verification
            ,updated_on,user_type;

    public UserProfile() {
    }

    public UserProfile(String id, String alumni_id, String name, String mobile, String email, String batch, String branch, String current_city, String hometown, String current_country, String image, String bio, String profession, String position, String alternate_email, String linkedin, String facebook, String twitter, String blood_group, String dob, String website, String verification, String updated_on, String user_type) {
        this.id = id;
        this.alumni_id = alumni_id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.batch = batch;
        this.branch = branch;
        this.current_city = current_city;
        this.hometown = hometown;
        this.current_country = current_country;
        this.image = image;
        this.bio = bio;
        this.profession = profession;
        this.position = position;
        this.alternate_email = alternate_email;
        this.linkedin = linkedin;
        this.facebook = facebook;
        this.twitter = twitter;
        this.blood_group = blood_group;
        this.dob = dob;
        this.website = website;
        this.verification = verification;
        this.updated_on = updated_on;
        this.user_type = user_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlumni_id() {
        return alumni_id;
    }

    public void setAlumni_id(String alumni_id) {
        this.alumni_id = alumni_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(String current_city) {
        this.current_city = current_city;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getCurrent_country() {
        return current_country;
    }

    public void setCurrent_country(String current_country) {
        this.current_country = current_country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAlternate_email() {
        return alternate_email;
    }

    public void setAlternate_email(String alternate_email) {
        this.alternate_email = alternate_email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
