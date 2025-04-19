package com.pendu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> animPendu;
    private ImageView[] animPenduMat = new ImageView[10];
    private Button checkLetterBtn ;
    private Button playBtn;
    private EditText letterEditText ;
    private TextView word2FindTextView;

    private String word2Find;
    private String[] words2Find = new String[10];
    private int count =0;
    private int decount ;
    private int numWord2Find=0;
    private int currentScene = 0;
    private char[] charWord2Find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        animPenduMat[0] = findViewById(R.id.pendu_1);
        animPenduMat[1] = findViewById(R.id.pendu_2);
        animPenduMat[2] = findViewById(R.id.pendu_3);
        animPenduMat[3] = findViewById(R.id.pendu_4);
        animPenduMat[4] = findViewById(R.id.pendu_5);
        animPenduMat[5] = findViewById(R.id.pendu_6);
        animPenduMat[6] = findViewById(R.id.pendu_7);
        animPenduMat[7] = findViewById(R.id.pendu_8);
        animPenduMat[8] = findViewById(R.id.pendu_9);
        animPenduMat[9] = findViewById(R.id.pendu_10);

        letterEditText = findViewById(R.id.letterEditText);
        checkLetterBtn = findViewById(R.id.checkBtn);
        playBtn = findViewById(R.id.playBtn);
        word2FindTextView = findViewById(R.id.wordTxtView);

        words2Find[0] = "constitutionnellement";
        words2Find[1] = "insensiblement";
        words2Find[2] = "arabesque";
        words2Find[3] = "antépénultième";
        words2Find[4] = "antirouille";
        words2Find[5] = "zoologie";
        words2Find[6] = "pitoresque";
        words2Find[7] = "abracadabresque";
        words2Find[8] = "pythagore";
        words2Find[9] = "hachiparmentier";



        initiateWord2Find(words2Find[numWord2Find]);

        checkLetterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean letterIsPresent = false;

                char char2Test = letterEditText.getText().toString().trim().charAt(0);
                if (char2Test != ' ') {
                    for ( int i=0; i< word2Find.length(); i++){
                        if (word2Find.charAt(i)== char2Test) {
                            charWord2Find[i] = char2Test;
                            String step = matrix2String(charWord2Find);
                            word2FindTextView.setText(step);
                            letterIsPresent = true;
                            decount--;
                        }
                    }
                    if (!letterIsPresent && count < 7 ) {
                        animPenduMat[count].setVisibility(View.INVISIBLE);
                        animPenduMat[count+1].setVisibility(View.VISIBLE);
                        currentScene++;
                        letterIsPresent = false;
                        count++;
                    } else if(count == 7){
                        animPenduMat[count].setVisibility(View.INVISIBLE);
                        animPenduMat[count+1].setVisibility(View.VISIBLE);
                        count =0;
                        playBtn.setVisibility(View.VISIBLE);
                        //word2FindTextView.setText("");
                    } else if (decount == 0){
                        animPenduMat[currentScene].setVisibility(View.INVISIBLE);
                        animPenduMat[9].setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.VISIBLE);
                    }
                }
                letterIsPresent = false;
                letterEditText.setText("");
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=1; i<10; i++) animPenduMat[i].setVisibility(View.INVISIBLE);
                animPenduMat[0].setVisibility(View.VISIBLE);
                numWord2Find++;
                initiateWord2Find(words2Find[numWord2Find]);
            }
        });
    }

    private void initiateWord2Find(String word) {
        word2Find = word;
        decount = word2Find.length();
        charWord2Find = new char[decount];
        for (int i=0; i< decount; i++) charWord2Find[i]+= '-';
        word2FindTextView.setText(matrix2String(charWord2Find));
        letterEditText.setText(" ");
        animPenduMat[9].setVisibility(View.INVISIBLE);
        currentScene=0;
        playBtn.setVisibility(View.INVISIBLE);

    }
    private String matrix2String(char[] matrix){
        StringBuilder sb = new StringBuilder();
        for (char c : matrix){
            sb.append(c);
        }
    return sb.toString();
    }
}