package com.example.user.belajarretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Konsumen {
    @SerializedName("idkonsumen")
    @Expose
    private String idkonsumen;
    @SerializedName("namakonsumen")
    @Expose
    private String namakonsumen;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("alamatkonsumen")
    @Expose
    private String alamatkonsumen;
    @SerializedName("ttl")
    @Expose
    private String ttl;

    @SerializedName("response")
    @Expose
    private String responseServer;



    public String getIdkonsumen() {
        return idkonsumen;
    }

    public void setIdkonsumen(String idkonsumen) {
        this.idkonsumen = idkonsumen;
    }

    public String getNamakonsumen() {
        return namakonsumen;
    }

    public void setNamakonsumen(String namakonsumen) {
        this.namakonsumen = namakonsumen;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamatkonsumen() {
        return alamatkonsumen;
    }

    public void setAlamatkonsumen(String alamatkonsumen) {
        this.alamatkonsumen = alamatkonsumen;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getResponseServer() {
        return responseServer;
    }
}
