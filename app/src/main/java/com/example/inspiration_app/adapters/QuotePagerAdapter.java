package com.example.inspiration_app.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.example.inspiration_app.R;
import com.example.inspiration_app.model.Quote;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class QuotePagerAdapter extends PagerAdapter {

    private static final String LIKED_QUOTES = "likedQuotes.txt";
    private List<Quote> quotes;
    private Context context;

    public QuotePagerAdapter(Context context, List<Quote> quotes) {
        this.quotes = quotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.quotes.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Quote currQuote = quotes.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.activity_main, container, false);

        TextView author = layout.findViewById(R.id.authorHolder);
        TextView quote = layout.findViewById(R.id.quoteHolder);

        author.setText(currQuote.getAuthor());
        quote.setText(currQuote.getQuote());

        likeQuote(currQuote, layout, quote);

        container.addView(layout);
        return layout;
    }

    private void likeQuote(Quote currQuote, ViewGroup layout, TextView quote) {
        ImageButton likeButton = layout.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean isAlreadyLiked = false;
                    if (fileExists(LIKED_QUOTES)) {
                        Scanner scanner = new Scanner(context.openFileInput(LIKED_QUOTES));
                        while (scanner.hasNext()) {
                            int id = Integer.parseInt(scanner.nextLine());
                            Log.d("QuotePagerAdapter", "Liked " + id + " where liked quote = " + quote.getId());
                            if (id == currQuote.getId())
                                isAlreadyLiked = true;
                        }
                        if (isAlreadyLiked) {
                            Toast.makeText(context, "This quote is already liked :-)", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    FileOutputStream fout = context.openFileOutput(LIKED_QUOTES,Context.MODE_APPEND);
                    PrintWriter printWriter = new PrintWriter(fout, true);
                    printWriter.println(currQuote.getId());
                    printWriter.close();

                    Toast.makeText(context, "Quote Added to Liked List", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean fileExists(String filename) {
        File file = context.getFileStreamPath(filename);
        return file != null && file.exists();
    }

    // When you slide to next quote/item the Android destroy prev item, bcs of saving memory
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
