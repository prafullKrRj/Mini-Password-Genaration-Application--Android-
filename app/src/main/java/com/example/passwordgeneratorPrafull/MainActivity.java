package com.example.passwordgeneratorPrafull;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SeekBar charLimit;
    TextView indicator;
    Button copy;
    TextView passIndicator;
    TextView password;
    String pwd;
    int length;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch upperCase, lowerCase, digits, specialCharacters;
    protected boolean isUpper = false, isLower = false, isDigit = false, isSpecial = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copy = findViewById(R.id.copyButton);
        copy.setVisibility(View.INVISIBLE);

        charLimit = findViewById(R.id.charLimit);
        charLimit.setMax(30);
        charLimit.setProgress(0);
        charLimit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                indicator = findViewById(R.id.indicator);
                indicator.setText(String.valueOf(i));
                length = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        checker();
    }
    public void checker(){
        upperCase = findViewById(R.id.upperCase);
        upperCase.setOnCheckedChangeListener((compoundButton, b) -> isUpper = compoundButton.isChecked());

        lowerCase = findViewById(R.id.lowerCase);
        lowerCase.setOnCheckedChangeListener((compoundButton, b) -> isLower = compoundButton.isChecked());

        digits = findViewById(R.id.Digits);
        digits.setOnCheckedChangeListener((compoundButton, b) -> isDigit = compoundButton.isChecked());

        specialCharacters = findViewById(R.id.specialCharacters);
        specialCharacters.setOnCheckedChangeListener((compoundButton, b) -> isSpecial = compoundButton.isChecked());
    }
    @SuppressLint("SetTextI18n")
    public void generatePassword(View view) {
        if ((isDigit || isSpecial || isLower || isUpper) && length!=0){
            getPassword getPassword = new getPassword(isUpper, isLower, isSpecial, isDigit, length);
            copy.setVisibility(View.VISIBLE);
            pwd = getPassword.password();
            passIndicator = findViewById(R.id.textView2);
            passIndicator.setText("Password:");
            password = findViewById(R.id.password);
            password.setText(pwd);
        }
    }

    public void copy(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("generated password", pwd);
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "copied", Toast.LENGTH_SHORT).show();
    }
}

class getPassword{
    boolean upper, lower, special, digits;
    int len;
    String alpha = "qwertyuioplkjhgfdsazxcvbnm";
    String ALPHA = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    String digit = "1234567890";
    String sc = ".[]{}()<>*+-=!?^$|";

    public getPassword(boolean upper, boolean lower, boolean special, boolean digit, int len) {
        this.upper = upper;
        this.lower = lower;
        this.special = special;
        this.digits = digit;
        this.len = len;
    }
    public String password(){
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        while (str.length()<len){
            if (upper){
                int s = random.nextInt(ALPHA.length());
                str.append(ALPHA.charAt(s));
            }
            if (lower) {
                int s = random.nextInt(alpha.length());
                str.append(alpha.charAt(s));
            }
            if (digits) {
                int s = random.nextInt(digit.length());
                str.append(digit.charAt(s));
            }
            if (special) {
                int s = random.nextInt(sc.length());
                str.append(sc.charAt(s));
            }
        }
        return str.toString();
    }
}
