package br.com.concrete.leite.csrequestmatcher.data.model;

import com.google.gson.annotations.SerializedName;


public abstract class DataModel {

    @SerializedName("id")
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
