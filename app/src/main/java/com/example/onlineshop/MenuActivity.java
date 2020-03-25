package com.example.onlineshop;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("onCreate", "Hit onCreate");
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        Log.i("onPause", "Hit onPause");

        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("onResume", "Hit onResume");

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i("OnDestroy", "Hit OnDestroy");

        super.onDestroy();
    }

}
