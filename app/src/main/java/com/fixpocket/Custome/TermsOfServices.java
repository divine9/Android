package com.fixpocket.Custome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fixpocket.R;

public class TermsOfServices extends AppCompatActivity {
    private TextView toolBarTitle;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_of_services);
        toolBarTitle = (TextView)findViewById(R.id.toolBarTitle);
        toolBarTitle.setText("TERMS OF SERVICES");
        backButton = (ImageView)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
