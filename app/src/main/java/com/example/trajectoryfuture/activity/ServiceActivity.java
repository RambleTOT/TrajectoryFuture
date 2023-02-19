package com.example.trajectoryfuture.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trajectoryfuture.R;
import com.squareup.picasso.Picasso;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imageButtonBack;
    private TextView textTitle, textDescription, textLink;
    private ImageView imageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        init();
        loadData();
    }

    private void init(){
        imageButtonBack = findViewById(R.id.imageButtonBack);
        imageButtonBack.setOnClickListener(this);
        textTitle = findViewById(R.id.textTitle);
        textDescription = findViewById(R.id.textDescription);
        textLink = findViewById(R.id.textLink);
        imageService = findViewById(R.id.imageService);
    }

    private void loadData(){
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String image = intent.getStringExtra(MainActivity.EXTRA_URL_ICON);
        String des = intent.getStringExtra(MainActivity.EXTRA_DESCRIPTION);
        String link = intent.getStringExtra(MainActivity.EXTRA_URL_LINK);
        SpannableString ss = new SpannableString(link);
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                String uri = link;
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(uri));
                startActivity(i2);
            }
        };
        ss.setSpan(cs, 0, link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Picasso.with(this).load(image).fit().centerInside().into(imageService);
        textTitle.setText(name);
        textDescription.setText(des);
        textLink.setText(ss);
        textLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonBack:
                Intent intent = new Intent(ServiceActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ServiceActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}