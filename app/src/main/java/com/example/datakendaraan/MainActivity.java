package com.example.datakendaraan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.datakendaraan.adapter.AdapterListSimple;
import com.example.datakendaraan.modelKendaraan.KendaraanModel;
import com.example.datakendaraan.service.APIClient;
import com.example.datakendaraan.service.APIInterfacesRest;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView lstKendaraan;
    ImageButton btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_tambah = findViewById(R.id.btn_tambah);
        lstKendaraan = findViewById(R.id.lstKendaraan);



        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_mobil.class);
                startActivity(intent);
            }
        });


        calllstKendaraan();
    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void calllstKendaraan(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<KendaraanModel> call3 = apiInterface.getmobil();
        call3.enqueue(new Callback<KendaraanModel>() {
            @Override
            public void onResponse(Call<KendaraanModel> call, Response<KendaraanModel> response) {
                progressDialog.dismiss();
                KendaraanModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {

                    AdapterListSimple adapter = new AdapterListSimple(MainActivity.this,data.getData().getKendaraan());

                    lstKendaraan.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    lstKendaraan.setItemAnimator(new DefaultItemAnimator());
                    lstKendaraan.setAdapter(adapter);

                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<KendaraanModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
