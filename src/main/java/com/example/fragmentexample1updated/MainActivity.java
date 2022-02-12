package com.example.fragmentexample1updated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mOpenButton;
    private Boolean isFragmentDisplayed = false;
    private static final String FRAGMENT_STATE="fragment-state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenButton = findViewById(R.id.open_button);

        if(savedInstanceState !=null){
            isFragmentDisplayed=savedInstanceState.getBoolean(FRAGMENT_STATE);

            if (isFragmentDisplayed){
                showFragment();
            }
            else{
                closeFragment();
            }
        }

        mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFragmentDisplayed){
                    showFragment();
                }
                else{
                   closeFragment();
                }
            }
        });

    }
    private void showFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();

        mOpenButton.setText(R.string.close);
        isFragmentDisplayed = true;

    }
    private void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction.remove(simpleFragment).commit();

        mOpenButton.setText(R.string.open);
        isFragmentDisplayed = false;

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(FRAGMENT_STATE,isFragmentDisplayed);
        super.onSaveInstanceState(outState);
    }
}
