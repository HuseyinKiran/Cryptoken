package com.huseyinkiran.cryptoretrofitjava.Model;

import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("coin_counter")
    private int coinCounter;
    @SerializedName("ico_counter")
    private int icoCounter;
    private String id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public int getCoinCounter() {
        return coinCounter;
    }

    public void setCoinCounter(int coinCounter) {
        this.coinCounter = coinCounter;
    }

    public int getIcoCounter() {
        return icoCounter;
    }

    public void setIcoCounter(int icoCounter) {
        this.icoCounter = icoCounter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

