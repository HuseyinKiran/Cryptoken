package com.huseyinkiran.cryptoretrofitjava.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinDetailModel {
    @SerializedName("id")
    private String coinId;
    private String name;
    private String description;
    private String symbol;
    private int rank;
    @SerializedName("is_active")
    private boolean isActive;
    private List<Tag> tags;
    private List<TeamMember> team;
    private String logo;
    private Links links;

    public CoinDetailModel(String coinId, String name, String description, String symbol, int rank,
                           boolean isActive, List<Tag> tags, List<TeamMember> team, String logo, Links links) {
        this.coinId = coinId;
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.rank = rank;
        this.isActive = isActive;
        this.tags = tags;
        this.team = team;
        this.logo = logo;
        this.links = links;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<TeamMember> getTeam() {
        return team;
    }

    public void setTeam(List<TeamMember> team) {
        this.team = team;
    }
}

