package com.example.hp.carrent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private Button nextreg,btnsignin;
private CheckBox save;
private EditText etusername,etpassword;
private SharedPreferences mRef;
private static final String PREF_NAME = "Preffile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRef = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        bind();
        getPreference();
        nextreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(save.isChecked()){
                    Boolean saveboolean = save.isChecked();
                    SharedPreferences.Editor editor = mRef.edit();
                    editor.putString("username",etusername.getText().toString());
                    editor.putString("password",etpassword.getText().toString());
                    editor.putBoolean("boolean",saveboolean);
                    editor.apply();
                }else{
                    mRef.edit().clear().apply();
                }
                etusername.getText().clear();
                etpassword.getText().clear();
                save.setChecked(false);
            }
        });
    }
    private void bind(){
        nextreg = findViewById(R.id.btnnxtrgt);
        save = findViewById(R.id.check_save);
        etusername = findViewById(R.id.et_login_username);
        etpassword = findViewById(R.id.et_login_password);
        btnsignin = findViewById(R.id.button_signin);
    }
    private void getPreference(){
        SharedPreferences sp = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        if(sp.contains("username")){
            String u = sp.getString("username","not found.");
            etusername.setText(u);
        }
        if(sp.contains("password")){
            String u = sp.getString("password","not found.");
            etpassword.setText(u);
        }
        if(sp.contains("boolean")){
            Boolean b = sp.getBoolean("boolean",false);
            save.setChecked(b);
        }
    }
}
