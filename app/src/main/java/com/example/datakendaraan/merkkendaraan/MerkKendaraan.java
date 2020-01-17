
package com.example.datakendaraan.merkkendaraan;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerkKendaraan implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama_kendaraan")
    @Expose
    private String namaKendaraan;
    public final static Creator<MerkKendaraan> CREATOR = new Creator<MerkKendaraan>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MerkKendaraan createFromParcel(Parcel in) {
            return new MerkKendaraan(in);
        }

        public MerkKendaraan[] newArray(int size) {
            return (new MerkKendaraan[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7011315917550374418L;

    protected MerkKendaraan(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.namaKendaraan = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MerkKendaraan() {
    }

    /**
     * 
     * @param namaKendaraan
     * @param id
     */
    public MerkKendaraan(String id, String namaKendaraan) {
        super();
        this.id = id;
        this.namaKendaraan = namaKendaraan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(namaKendaraan);
    }

    public int describeContents() {
        return  0;
    }

}
