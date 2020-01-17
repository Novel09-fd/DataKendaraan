
package com.example.datakendaraan.merkkendaraan;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("merk_kendaraan")
    @Expose
    private List<MerkKendaraan> merkKendaraan = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1306459579998580951L;

    protected Data(Parcel in) {
        in.readList(this.merkKendaraan, (com.example.datakendaraan.merkkendaraan.MerkKendaraan.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param merkKendaraan
     */
    public Data(List<MerkKendaraan> merkKendaraan) {
        super();
        this.merkKendaraan = merkKendaraan;
    }

    public List<MerkKendaraan> getMerkKendaraan() {
        return merkKendaraan;
    }

    public void setMerkKendaraan(List<MerkKendaraan> merkKendaraan) {
        this.merkKendaraan = merkKendaraan;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(merkKendaraan);
    }

    public int describeContents() {
        return  0;
    }

}
