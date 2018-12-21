package com.example.yuekaodemo02;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yuekaodemo02.fragment.Afragment;
import com.example.yuekaodemo02.fragment.Bfragment;
import com.example.yuekaodemo02.fragment.Cfragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frame;
    private RadioButton rg1;
    private RadioButton rg2;
    private RadioButton rg3;
    private RadioGroup rg;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame,new Afragment()).commit();

    }

    private void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        rg1 = (RadioButton) findViewById(R.id.rg1);
        rg2 = (RadioButton) findViewById(R.id.rg2);
        rg3 = (RadioButton) findViewById(R.id.rg3);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg1.setOnClickListener(this);
        rg2.setOnClickListener(this);
        rg3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rg1:
                manager.beginTransaction().replace(R.id.frame,new Afragment()).commit();

                break;
            case R.id.rg2:
                manager.beginTransaction().replace(R.id.frame,new Bfragment()).commit();
                break;
            case R.id.rg3:
                manager.beginTransaction().replace(R.id.frame,new Cfragment()).commit();
                break;
        }
    }
}
