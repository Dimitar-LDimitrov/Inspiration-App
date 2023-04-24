package com.example.inspiration_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.inspiration_app.adapters.QuotePagerAdapter;
import com.example.inspiration_app.core.QuoteReaderService;
import com.example.inspiration_app.model.Quote;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Quote> allQuotes = new QuoteReaderService().getAllQuotes(this);

        ViewPager viewPager = findViewById(R.id.viewPager);
        QuotePagerAdapter adapter = new QuotePagerAdapter(this, allQuotes);
        viewPager.setAdapter(adapter);
    }
}