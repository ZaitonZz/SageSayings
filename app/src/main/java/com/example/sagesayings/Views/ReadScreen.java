package com.example.sagesayings.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sagesayings.AVLImplementation.AVLChapterTree;
import com.example.sagesayings.AVLImplementation.AVLVerseTree;
import com.example.sagesayings.AVLImplementation.ChapterNode;
import com.example.sagesayings.AVLImplementation.VerseNode;
import com.example.sagesayings.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class ReadScreen extends AppCompatActivity {

    private AVLChapterTree chapterTree;
    private TextView currentChapter, readChapter, readVerses;
    private ImageButton next, prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_read_screen);
        readProverbsData();
        View rectBg = findViewById(R.id.read_roundedbg);
        currentChapter = findViewById(R.id.current_chapter);
        readChapter = findViewById(R.id.read_chapters);
        readVerses = findViewById(R.id.read_verses);
        next = findViewById(R.id.read_next);
        prev = findViewById(R.id.read_prev);
        ScrollView scrollView = findViewById(R.id.read_scroll);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        rectBg.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels, (int) (displayMetrics.heightPixels * .97)));
        ViewGroup.LayoutParams scrollViewLayoutParams = scrollView.getLayoutParams();
        scrollViewLayoutParams.height = (int) (displayMetrics.heightPixels * 0.75);
        scrollViewLayoutParams.width = (int) (displayMetrics.widthPixels * 0.9);
        scrollView.setLayoutParams(scrollViewLayoutParams);

        updateVerses(Integer.parseInt(currentChapter.getText().toString()));

    }
    public void nextChapter(View view){
        if (! (Integer.parseInt( currentChapter.getText().toString()) > 30 ) ){
            int chap = Integer.parseInt(currentChapter.getText().toString()) + 1;
            updateVerses( chap );
            currentChapter.setText(String.valueOf(chap));
        }
    }
    public void startAudioScreen(View view){
        Intent intent = new Intent(ReadScreen.this, AudioScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startVOTScreen(View view){
        Intent intent = new Intent(ReadScreen.this, VerseOfTheDayScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startSearchScreen(View view){
        Intent intent = new Intent(ReadScreen.this, SearchScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void prevChapter(View view){
        if (! (Integer.parseInt( currentChapter.getText().toString()) < 2 ) ){
            int chap = Integer.parseInt(currentChapter.getText().toString()) - 1;
            updateVerses( chap );
            currentChapter.setText(String.valueOf(chap));
        }
    }

    private void updateVerses(int chapter) {
        // retrieves correct chapter from ChapterTree
        ChapterNode chapterNode = chapterTree.searchNode(chapter, chapterTree.getRootNode());
        // access verseNodes from Verse Tree
        Queue<VerseNode> currentVerses = chapterNode.getAvlVerseTree().inOrder();
        // replace text in read_chapters
        readChapter.setText(String.valueOf(chapter));
        // replace text in read_verses
        StringBuilder stringBuilder = new StringBuilder();
        while (!currentVerses.isEmpty()){
            stringBuilder.append(currentVerses.poll().toString());
            stringBuilder.append("\n");
        }
        readVerses.setText(stringBuilder.toString());
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
            while ( (line = reader.readLine()) != null ){
                //split
                String[] tokens = line.split("\\|");

                //access chapter tree via token1
                ChapterNode chapter = chapterTree.searchNode(Integer.parseInt(tokens[0]), chapterTree.getRootNode());

                if (chapter == null){ // if chapter has not yet been created, create that chapter and place it in avl tree
                    chapter = new ChapterNode(Integer.parseInt(tokens[0]), new AVLVerseTree());
                    chapterTree.insert(chapter);
                }

                //access chapterNode's verse tree
                chapter.getAvlVerseTree().insert(new VerseNode(Integer.parseInt(tokens[1]), tokens[2], tokens[3] ));
            }
        } catch (IOException e){
            Log.wtf("ReadScreen", "Error reading data file on line" + line, e);
        }

    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

}