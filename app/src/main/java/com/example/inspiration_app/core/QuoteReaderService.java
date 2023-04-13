package com.example.inspiration_app.core;

import android.app.Activity;
import android.content.res.AssetManager;

import com.example.inspiration_app.model.Quote;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuoteReaderService {

    private List<Quote> readAllQuotes(Activity activity) {
        AssetManager assetManager = activity.getAssets();
        List<Quote> quotes = new ArrayList<>();
        int quoteId = 0;

        try {
            InputStream inputStream = assetManager.open("Quotes.txt");
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()){
                quoteId++;
                try {
                    String[] splitted = scanner.nextLine().split("\\|");
                    Quote quote = new Quote(quoteId, splitted[0], splitted[1]);
                    quotes.add(quote);

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }

        return quotes;
    }
}
