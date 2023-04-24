package com.example.inspiration_app.core;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import com.example.inspiration_app.model.Quote;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuoteReaderService {

    private static final String LIKED_QUOTES = "likedQuotes.txt";
    private static final String ALL_QUOTES = "Quotes.txt";

    public List<Quote> getAllQuotes(Activity activity) {
        AssetManager assetManager = activity.getAssets();
        List<Quote> quotes = new ArrayList<>();

        try {
            InputStream inputStream = assetManager.open(ALL_QUOTES);
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()){
                try {
                    String[] splitted = scanner.nextLine().split("\\|");
                    Integer quoteId = Integer.valueOf(splitted[0]);
                    String quoteAuthor = splitted[1];
                    String quoteDesc = splitted[2];
                    String quoteImage = splitted[3];
                    Quote quote = new Quote(quoteId,quoteAuthor , quoteDesc, quoteImage);
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

    public List<Integer> getAllLikedQuotes(Activity activity) throws FileNotFoundException {
        List<Integer> likedIdList = new ArrayList<>();

        if (fileExists(activity, LIKED_QUOTES)) {
            Scanner scanner = new Scanner(activity.openFileInput(LIKED_QUOTES));
            while (scanner.hasNext()) {
                int id = Integer.parseInt(scanner.nextLine());
                likedIdList.add(id);
            }
        }

        return likedIdList;
    }

    private boolean fileExists(Activity activity, String filename) {
        File file = activity.getFileStreamPath(filename);
        return file != null && file.exists();
    }
}
