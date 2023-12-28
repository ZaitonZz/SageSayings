package com.example.sagesayings.RecycleViewStuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sagesayings.R;

import java.util.ArrayList;

public class VerseAdapter extends RecyclerView.Adapter<VerseAdapter.VerseViewHolder> {
    private ArrayList<VerseItem> verseItems;

    public static class VerseViewHolder extends RecyclerView.ViewHolder{

        public TextView chapter;
        public TextView verse;

        public VerseViewHolder(@NonNull View itemView) {
            super(itemView);
            chapter = itemView.findViewById(R.id.VI_chapter);
            verse = itemView.findViewById(R.id.VI_verse);
        }
    }

    public VerseAdapter(ArrayList<VerseItem> verseItems) {
        this.verseItems = verseItems;
    }

    @NonNull
    @Override
    public VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.verse_item, parent, false);
        VerseViewHolder verseViewHolder = new VerseViewHolder(v);
        return verseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VerseViewHolder holder, int position) {
        VerseItem currentItem = verseItems.get(position);

        holder.chapter.setText(currentItem.getChapter());
        holder.verse.setText(currentItem.getVerse());

    }

    @Override
    public int getItemCount() {
        return verseItems.size();
    }
}
