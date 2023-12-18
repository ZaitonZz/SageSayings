package com.example.sagesayings.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Stack;

public class ReadScreen extends AppCompatActivity {

    private AVLChapterTree chapterTree;
    private TextView currentChapter, readChapter, readVerses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readProverbsData();

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_read_screen);
        View rectBg = findViewById(R.id.read_roundedbg);
        currentChapter = findViewById(R.id.current_chapter);
        readChapter = findViewById(R.id.read_chapters);
        readVerses = findViewById(R.id.read_verses);;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        rectBg.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels, (int) (displayMetrics.heightPixels * .97)));
        ViewGroup.LayoutParams versesParam = readVerses.getLayoutParams();
        versesParam.width = (int) (displayMetrics.widthPixels * 0.75);
        readVerses.setLayoutParams(versesParam);

        updateVerses(Integer.parseInt(currentChapter.getText().toString()));
    }

    private void updateVerses(int chapter) {
        // retrieves correct chapter from ChapterTree
        ChapterNode chapterNode = chapterTree.searchNode(chapter, chapterTree.getRootNode());
        // access verseNodes from Verse Tree
        Stack<VerseNode> currentVerses = chapterNode.getAvlVerseTree().inOrder();
        // replace text in read_chapters
        readChapter.setText(chapter);
        // replace text in read_verses
        StringBuilder stringBuilder = new StringBuilder();
        while (!currentVerses.isEmpty()){
            stringBuilder.append(currentVerses.pop().toString());
            stringBuilder.append("\n");
        }
        readVerses.setText(stringBuilder.reverse().toString());
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
}