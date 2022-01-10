package com.example.appbookstore;

import com.google.gson.annotations.SerializedName;

public class bankAccountModel {
    private int id;
    private int IdUser;
    private String accountNumber;
    private int bankID;
    private int amountOfMoney;

    public bankAccountModel(int id, int idUser, String accountNumber, int bankID, int amountOfMoney) {
        this.id = id;
        IdUser = idUser;
        this.accountNumber = accountNumber;
        this.bankID = bankID;
        this.amountOfMoney = amountOfMoney;
    }

    public bankAccountModel() {

    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return IdUser;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBankID() {
        return bankID;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}
