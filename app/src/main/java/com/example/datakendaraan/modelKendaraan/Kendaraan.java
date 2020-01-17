
package com.example.datakendaraan.modelKendaraan;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kendaraan implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("merek_kendaraan")
    @Expose
    private String merekKendaraan;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("tahun_kendaraan")
    @Expose
    private String tahunKendaraan;
    @SerializedName("foto_kendaraan")
    @Expose
    private String fotoKendaraan;
    public final static Creator<Kendaraan> CREATOR = new Creator<Kendaraan>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Kendaraan createFromParcel(Parcel in) {
            return new Kendaraan(in);
        }

        public Kendaraan[] newArray(int size) {
            return (new Kendaraan[size]);
        }

    }
    ;
    private final static long serialVersionUID = 739928850627425868L;

    protected Kendaraan(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.merekKendaraan = ((String) in.readValue((String.class.getClassLoader())));
        this.cc = ((String) in.readValue((String.class.getClassLoader())));
        this.tahunKendaraan = ((String) in.readValue((String.class.getClassLoader())));
        this.fotoKendaraan = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Kendaraan() {
    }

    /**
     * 
     * @param cc
     * @param merekKendaraan
     * @param id
     * @param tahunKendaraan
     * @param fotoKendaraan
     */
    public Kendaraan(String id, String merekKendaraan, String cc, String tahunKendaraan, String fotoKendaraan) {
        super();
        this.id = id;
        this.merekKendaraan = merekKendaraan;
        this.cc = cc;
        this.tahunKendaraan = tahunKendaraan;
        this.fotoKendaraan = fotoKendaraan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerekKendaraan() {
        return merekKendaraan;
    }

    public void setMerekKendaraan(String merekKendaraan) {
        this.merekKendaraan = merekKendaraan;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getTahunKendaraan() {
        return tahunKendaraan;
    }

    public void setTahunKendaraan(String tahunKendaraan) {
        this.tahunKendaraan = tahunKendaraan;
    }

    public String getFotoKendaraan() {
        return fotoKendaraan;
    }

    public void setFotoKendaraan(String fotoKendaraan) {
        this.fotoKendaraan = fotoKendaraan;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(merekKendaraan);
        dest.writeValue(cc);
        dest.writeValue(tahunKendaraan);
        dest.writeValue(fotoKendaraan);
    }

    public int describeContents() {
        return  0;
    }

}
