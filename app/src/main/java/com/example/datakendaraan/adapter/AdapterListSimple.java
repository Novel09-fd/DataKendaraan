package com.example.datakendaraan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.aplikasi_reviewfilm.R;
//import com.example.aplikasi_reviewfilm.movieshow.Moviedb;
import com.example.datakendaraan.R;
import com.example.datakendaraan.modelKendaraan.Kendaraan;
import com.example.datakendaraan.modelKendaraan.KendaraanModel;
import com.squareup.picasso.Picasso;

public class AdapterListSimple extends RecyclerView.Adapter<AdapterListSimple.ViewHolder> {

    java.util.List<Kendaraan> data;
    Context context;


    public AdapterListSimple(Context context, java.util.List<Kendaraan> data){



        this.data = data;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);


        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.txt_merk.setText(data.get(position).getMerekKendaraan());
        holder.txt_cc.setText(data.get(position).getCc());
        holder.txt_tahun.setText(data.get(position).getTahunKendaraan());

        String image =  data.get(position).getFotoKendaraan();
        Picasso.get().load(image).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder  {
       public TextView txt_merk,txt_cc,txt_tahun ;
       public ImageView imageView;


        public ViewHolder(View itemView) {


            super(itemView);

           txt_merk = (TextView) itemView.findViewById(R.id.txt_merk);
           txt_cc = (TextView) itemView.findViewById(R.id.txt_cc);
           txt_tahun = (TextView) itemView.findViewById(R.id.txt_tahun);


           imageView = (ImageView)itemView.findViewById(R.id.imageView);


        }

    }


}
