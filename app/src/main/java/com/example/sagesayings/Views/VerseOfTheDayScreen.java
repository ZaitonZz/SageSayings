package com.example.sagesayings.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import java.util.Random;

public class VerseOfTheDayScreen extends AppCompatActivity {

    private Random random;
    private TextView votVerse;
    private TextView votChapter;
    private ImageView verseImage;
    private AVLChapterTree chapterTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_verse_of_the_day_screen);
        readProverbsData();
        hideSystemUI();

        View rectBg = findViewById(R.id.vot_roundedbg);
        votVerse = findViewById(R.id.vot_verse);
        votChapter = findViewById(R.id.vot_chapter);
        verseImage = findViewById(R.id.vot_daily_image);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        rectBg.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels, (int) (displayMetrics.heightPixels * .97)));
        random = new Random();
        generateRandomVerse();
    }

    private void generateRandomVerse() {
        int chap = Math.abs(random.nextInt(32));
        ChapterNode chapterNode = chapterTree.searchNode(chap, chapterTree.getRootNode());
        VerseNode verseNode = chapterNode.getAvlVerseTree().findMax();
        int ver = Math.abs(random.nextInt(verseNode.getVerseNumber() + 1));
        verseNode = chapterNode.getAvlVerseTree().searchNode(ver);
        int[] imageArray = {R.drawable.scene_one, R.drawable.scene_two, R.drawable.scene_three, R.drawable.scene_four};

        votChapter.setText(String.format("Proverbs %d:%d", chap, ver));
        votVerse.setText(verseNode.getFormattedText());
        verseImage.setImageResource(getRandomImage(imageArray));
    }

    public int getRandomImage(int[] imageArray) {
        Random random = new Random();
        int randomIndex = random.nextInt(imageArray.length);
        return imageArray[randomIndex];
    }

    public void startReadScreen(View view){
        Intent intent = new Intent(VerseOfTheDayScreen.this, ReadScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startAudioScreen(View view){
        Intent intent = new Intent(VerseOfTheDayScreen.this, AudioScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startSearchScreen(View view){
        Intent intent = new Intent(VerseOfTheDayScreen.this, SearchScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
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
            Log.wtf("VOTScreen", "Error reading data file on line" + line, e);
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

}