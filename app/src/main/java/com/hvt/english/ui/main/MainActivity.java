package com.hvt.english.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hvt.english.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, new CategoryDetailFragment());
//        transaction.commit();
    }
}
