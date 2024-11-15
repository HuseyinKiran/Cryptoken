package com.huseyinkiran.cryptoretrofitjava.Model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("rank")
    private int rank;
    @SerializedName("is_active")
    private boolean isActive;

    public CryptoModel(String id, String name, String symbol, int rank, boolean isActive) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.isActive = isActive;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

