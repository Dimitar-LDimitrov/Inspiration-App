package com.example.inspiration_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private int quotesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readQuotesFromFile();
    }

    private void readQuotesFromFile() {
        AssetManager assetManager = getAssets();

        try {
            InputStream inputStream = assetManager.open("Quotes.txt");
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()){
                quotesCount++;
                String temp = scanner.nextLine();
                String[] splitted = temp.split("\\|");
                String author = splitted[0];
                String quote = splitted[1];
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}