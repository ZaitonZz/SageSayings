package com.example.sagesayings.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sagesayings.AVLImplementation.AVLChapterTree;
import com.example.sagesayings.AVLImplementation.AVLVerseTree;
import com.example.sagesayings.AVLImplementation.ChapterNode;
import com.example.sagesayings.AVLImplementation.VerseNode;
import com.example.sagesayings.R;
import com.example.sagesayings.RecycleViewStuff.VerseAdapter;
import com.example.sagesayings.RecycleViewStuff.VerseItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ResultsScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public String query;
    private AVLChapterTree chapterTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);
        hideSystemUI();

        ArrayList<VerseItem> verseItems = new ArrayList<>();
        Intent intent = getIntent();
        query = intent.getStringExtra("search");
        readProverbsData();

        populateDisplay(verseItems);

        recyclerView = findViewById(R.id.results_recycler);
        TextView textQuery = findViewById(R.id.results_query);
        textQuery.setText(String.format("Results of \"%s\"", query));
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new VerseAdapter(verseItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void populateDisplay(ArrayList<VerseItem> verseItems) {
        for (int i = 1; i < 32; i++) {
            ChapterNode chapterNode = chapterTree.searchNode(i, chapterTree.getRootNode());
            int j = 1;
            VerseNode verseNode = chapterNode.getAvlVerseTree().searchNode(j);
            while (verseNode != null){
                if (verseNode.contains(query)!= null){
                    verseItems.add(new VerseItem("Proverbs " + chapterNode.getChapterNumber()
                            + ":" + verseNode.getVerseNumber(), verseNode.getFormattedText()));
                }
                verseNode = chapterNode.getAvlVerseTree().searchNode(j);
                j++;
            }
        }
    }

    public void backButton(View view){
        super.onBackPressed();
    }
    private void readProverbsData() {
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        chapterTree = new AVLChapterTree();

        String line = "";

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                //split
                String[] tokens = line.split("\\|");

                //access chapter tree via token1
                ChapterNode chapter = chapterTree.searchNode(Integer.parseInt(tokens[0]), chapterTree.getRootNode());

                if (chapter == null) { // if chapter has not yet been created, create that chapter and place it in avl tree
                    chapter = new ChapterNode(Integer.parseInt(tokens[0]), new AVLVerseTree());
                    chapterTree.insert(chapter);
                }

                //access chapterNode's verse tree
                chapter.getAvlVerseTree().insert(new VerseNode(Integer.parseInt(tokens[1]), tokens[2], tokens[3]));
            }
        } catch (IOException e) {
            Log.wtf("SearchScreen", "Error reading data file on line" + line, e);
        }
    }
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }
}