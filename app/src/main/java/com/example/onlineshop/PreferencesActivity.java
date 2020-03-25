package com.example.onlineshop;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class PreferencesActivity extends AppCompatActivity {

    Switch location;
    TextView signature;

    private final String PREFS = "PREFS";
    private final String LOCATION = "LOCATION";
    private final String SIGNATURE = "SIGNATURE";

    private final String STORAGE_PREFERENCES ="mypreferences.txt";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        location = findViewById(R.id.location);
        signature = findViewById(R.id.signature);

        this.loadPreferences();
        location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                savePreferences();
            }
        });

        signature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                savePreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(LOCATION, location.isChecked());
        editor.putString(SIGNATURE, signature.getText().toString());

        String persistedPreferences = "location: " + location.isChecked() + ", signature: " + signature.getText().toString();
        writeFile(STORAGE_PREFERENCES, persistedPreferences);
        Toast.makeText(getApplicationContext(), "Preferences saved in internal storage", Toast.LENGTH_SHORT).show();

        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        location.setChecked(preferences.getBoolean(LOCATION, false));
        signature.setText(preferences.getString(SIGNATURE, signature.getText().toString()));

        String persistedPreferences = readFile(STORAGE_PREFERENCES);
        Toast.makeText(getApplicationContext(), "Preferences read from internal storage: " + persistedPreferences, Toast.LENGTH_SHORT).show();
    }

    private void writeFile(String filename, String body) {
        try{
            File file = new File(this.getFilesDir(), filename);
            FileWriter writer = new FileWriter(file);
            writer.append(body);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String readFile(String filename) {
        try {
            FileInputStream fis = this.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();
        } catch (IOException ignored) {
            return "";
        }
    }
}