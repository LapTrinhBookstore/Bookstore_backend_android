package com.example.appbookstore;

import com.google.gson.annotations.SerializedName;

public class Users {
    private int id;
    private String name;
    private String email;
    private String password;
    private String email_verified_at;
    private String phoneNumber;
    private String dateOfBirth;
    private int gender;
    private String avatarImg;
    private int numberOfCoins;
    private String created_at;
    private String updated_at;

    public Users(int id, String name, String email, String password, String email_verified_at, String phoneNumber, String dateOfBirth, int gender, String avatarImg, int numberOfCoins, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.email_verified_at = email_verified_at;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.avatarImg = avatarImg;
        this.numberOfCoins = numberOfCoins;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Users() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public int getNumberOfCoins() {
        return numberOfCoins;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
