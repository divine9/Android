package com.fixpocket.Activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fixpocket.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Guest extends AppCompatActivity implements View.OnClickListener {
    private TextView toolBarTitle;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest);
        init();
        toolBarTitle.setText("GUEST");
        backButton.setOnClickListener(this);

    }

    public void init() {
        toolBarTitle = (TextView) findViewById(R.id.toolBarTitle);
        backButton = (ImageView) findViewById(R.id.back_button);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                finish();
                break;

        }
    }
}
