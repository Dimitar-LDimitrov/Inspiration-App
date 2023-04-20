package com.example.inspiration_app.adapters;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.inspiration_app.R;
import com.example.inspiration_app.model.Quote;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Quote> mData;

    public MyAdapter(List<Quote> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liked_quotes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quote quote = mData.get(position);
        holder.quoteHolder.setText(quote.getQuote());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quoteHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            quoteHolder = itemView.findViewById(R.id.quoteTextView);
        }
    }
}