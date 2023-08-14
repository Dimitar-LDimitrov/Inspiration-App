 package com.example.inspiration_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inspiration_app.R;
import com.example.inspiration_app.adapters.MyAdapter;
import com.example.inspiration_app.core.QuoteReaderService;
import com.example.inspiration_app.model.Quote;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LikedQuotesActivity extends AppCompatActivity {

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
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.findViewById(R.id.recyclerView);

        QuoteReaderService readerService = new QuoteReaderService();
        List<Quote> quoteList = readerService.getAllQuotes(this);
        List<Integer> likedQuotesIdList = readerService.getAllLikedQuotes(this);

        List<Quote> likedQuoteList = new ArrayList<>();
        for (Integer id : likedQuotesIdList) {
            likedQuoteList.add(findById(id, quoteList));
        }

        inflateListView(likedQuoteList);
    }

    private void inflateListView(List<Quote> likedQuoteList) {

        List<Quote> data = likedQuoteList;
        MyAdapter adapter = new MyAdapter(data);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private Quote findById(int id, List<Quote> quoteList) {
        for (Quote quote : quoteList) {
            if (quote.getId() == id) {
                return quote;
            }
        }
        return null;
    }
}