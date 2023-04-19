package com.example.inspiration_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.inspiration_app.R;
import com.example.inspiration_app.core.QuoteReaderService;
import com.example.inspiration_app.model.Quote;

import java.io.FileNotFoundException;
import java.util.List;

public class LikedQuotesActivity extends AppCompatActivity {

    private ListView likedQuotesListView;
    private List<Quote> quoteList;
    private List<Integer> likedQuotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_quotes);

        try {
            initView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void initView() throws FileNotFoundException {
        likedQuotesListView.findViewById(R.id.likedQuotesListView);
        quoteList = new QuoteReaderService().getAllQuotes(this);
        likedQuotesList = new QuoteReaderService().getAllLikedQuotes(this);
    }
}