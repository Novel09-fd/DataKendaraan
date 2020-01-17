
package com.example.datakendaraan.modelKendaraan;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("kendaraan")
    @Expose
    private List<Kendaraan> kendaraan = null;
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
    private final static long serialVersionUID = -4207927108289704711L;

    protected Data(Parcel in) {
        in.readList(this.kendaraan, (com.example.datakendaraan.modelKendaraan.Kendaraan.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param kendaraan
     */
    public Data(List<Kendaraan> kendaraan) {
        super();
        this.kendaraan = kendaraan;
    }

    public List<Kendaraan> getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(List<Kendaraan> kendaraan) {
        this.kendaraan = kendaraan;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(kendaraan);
    }

    public int describeContents() {
        return  0;
    }

}
