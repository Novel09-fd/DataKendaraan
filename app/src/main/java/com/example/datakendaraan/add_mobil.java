package com.example.datakendaraan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datakendaraan.addKendaraan.AddModel;
import com.example.datakendaraan.merkkendaraan.MerkModel;
import com.example.datakendaraan.modelKendaraan.Kendaraan;
import com.example.datakendaraan.modelKendaraan.KendaraanModel;
import com.example.datakendaraan.service.APIClient;
import com.example.datakendaraan.service.APIInterfacesRest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_mobil  extends AppCompatActivity {


    Spinner spn_merk;
    EditText txt_Cc;
    Button btn_send , btn_camera , btn_gallery;
    Date tanggal;
    CalendarView thn_pembuatan;
    ImageView img_mobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_mobil);

        //Button
        btn_send = findViewById(R.id.btn_send);
        btn_camera = findViewById(R.id.btn_camera);
        btn_gallery = findViewById(R.id.btn_gallery);

        //image
        img_mobil = findViewById(R.id.img_mobil);


        //Edittext
        txt_Cc = findViewById(R.id.txt_Cc);

        thn_pembuatan = findViewById(R.id.thn_pembuatan);

        //Spinner
        spn_merk = findViewById(R.id.spn_merk);


        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder1();
            }
        });



        callmobil();

        spinerMerk();

    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;

    public RequestBody toRequestBody(String value) {
        if (value==null){
            value="";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

   // public void callmobil(String merek_kendaraan , String cc , String tahun_kendaraan , String foto_kendaraan){
    //GET
        public void callmobil() {

       // RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"),byteArray);
       // MultipartBody.Part bodyImg = MultipartBody.Part.createFormData("foto_kendaraan", "dewa.png", requestFile);


        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(add_mobil.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<KendaraanModel> call3 = apiInterface.getmobil();
        //Call<KendaraanModel> call3 = apiInterface.getmobil(toRequestBody(merek_kendaraan), toRequestBody(cc) , toRequestBody(tahun_kendaraan) , bodyImg);
        call3.enqueue(new Callback<KendaraanModel>() {
            @Override
            public void onResponse(Call<KendaraanModel> call, Response<KendaraanModel> response) {
                progressDialog.dismiss();
                KendaraanModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();

                if (data !=null) {


                    Toast.makeText(add_mobil.this,data.getMessage(),Toast.LENGTH_LONG).show();
//
//                    txt_Question.setText(data.getData().getSoalQuizAndroid().get(0).getPertanyaan().toString());
//                    btn_A.setText(data.getData().getSoalQuizAndroid().get(0).getA().toString());
//                    btn_B.setText(data.getData().getSoalQuizAndroid().get(0).getB().toString());
//                    btn_C.setText(data.getData().getSoalQuizAndroid().get(0).getC().toString());
//                    btn_D.setText(data.getData().getSoalQuizAndroid().get(0).getD().toString());


//
//                    String image = "http://dewabrata.com:80/dewa/uploads/soal_quiz_android/" + data.getData().getSoalQuizAndroid().get(0).getGambar()+"20200110101347-2020-01-10soal_quiz_android101342.jpg";
//                    Picasso.get().load(image).into(gambar1);

                 /*   AdapterListSimple adapter = new AdapterListSimple(MainActivity.this,data.getData().getMoviedb());

                    lst_Movie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    lst_Movie.setItemAnimator(new DefaultItemAnimator());
                    lst_Movie.setAdapter(adapter);

                     */





                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(add_mobil.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(add_mobil.this, e.getMessage(), Toast.LENGTH_LONG).show();
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


    //camera
    private int CAMERA_REQUEST3 = 100;
    //gallery
    private int REQUEST_GALLERY = 100;

     void openCamera() {

         Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
      //   startActivityForResult(cameraIntent, CAMERA _REQUEST);
     }

    public void openFolder1() {

        Intent folderIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(folderIntent, REQUEST_GALLERY);

    }

    Bitmap bitmap;
    byte[] byteArray;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            img_mobil.setImageURI(selectedImage);
            Bitmap bitmap = ((BitmapDrawable) img_mobil.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byteArray = baos.toByteArray();
            // img_btn1.setImageBitmap(bitmap);

        }
    }


    public void spinerMerk(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        /*progressDialog = new ProgressDialog(AddActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();*/
        Call<MerkModel> call3 = apiInterface.getmerk();
        call3.enqueue(new Callback<MerkModel>() {
            @Override
            public void onResponse(Call<MerkModel> call, Response<MerkModel> response) {
                //progressDialog.dismiss();
                MerkModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {


                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.getData().getMerkKendaraan().size(); i++){
                        listSpinner.add(data.getData().getMerkKendaraan().get(i).getNamaKendaraan());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(add_mobil.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spn_merk.setAdapter(adapter);

                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(add_mobil.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(add_mobil.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MerkModel> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });}


        //POST

     public void tambahmobil(String merek_kendaraan , String cc , String tahun_kendaraan , String foto_kendaraan){



         RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"),byteArray);
         MultipartBody.Part bodyImg = MultipartBody.Part.createFormData("foto_kendaraan", "dewa.png", requestFile);


        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(add_mobil.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
      //  Call<AddModel> call3 = apiInterface.addkendaraan();
        Call<AddModel> call3 = apiInterface.addkendaraan(toRequestBody(merek_kendaraan), toRequestBody(cc) , toRequestBody(tahun_kendaraan) , bodyImg);
        call3.enqueue(new Callback<AddModel>() {
            @Override
            public void onResponse(Call<AddModel> call, Response<AddModel> response) {
                progressDialog.dismiss();
                AddModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();

                if (data !=null) {


                    Toast.makeText(add_mobil.this,data.getMessage(),Toast.LENGTH_LONG).show();
//
//                    txt_Question.setText(data.getData().getSoalQuizAndroid().get(0).getPertanyaan().toString());
//                    btn_A.setText(data.getData().getSoalQuizAndroid().get(0).getA().toString());
//                    btn_B.setText(data.getData().getSoalQuizAndroid().get(0).getB().toString());
//                    btn_C.setText(data.getData().getSoalQuizAndroid().get(0).getC().toString());
//                    btn_D.setText(data.getData().getSoalQuizAndroid().get(0).getD().toString());


//
//                    String image = "http://dewabrata.com:80/dewa/uploads/soal_quiz_android/" + data.getData().getSoalQuizAndroid().get(0).getGambar()+"20200110101347-2020-01-10soal_quiz_android101342.jpg";
//                    Picasso.get().load(image).into(gambar1);

                 /*   AdapterListSimple adapter = new AdapterListSimple(MainActivity.this,data.getData().getMoviedb());

                    lst_Movie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    lst_Movie.setItemAnimator(new DefaultItemAnimator());
                    lst_Movie.setAdapter(adapter);

                     */





                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(add_mobil.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(add_mobil.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }


}
