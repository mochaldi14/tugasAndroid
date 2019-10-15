package com.aldi.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        final EditText username= (EditText) findViewById(R.id.username);
        final EditText password= (EditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.login);
        Button button1 = (Button) findViewById(R.id.register);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post("http://192.168.6.116/latihan/user.php")
                        .addBodyParameter("username", username.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    if (hasil.getBoolean("sukses")){
                                        Toast.makeText(MainActivity.this, "berhasil",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "password atau username salah",Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JsonIOException e){
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
  //              Toast
            }
        });
    }
}
