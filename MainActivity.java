package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageFlag;
    ListView listView;
    Button buttonRandom;
    TextView textScore;

    String correctAnswer;
    int correct = 0;
    int total = 0;


    int[] flags = {
            R.drawable.czech,
            R.drawable.francji,
            R.drawable.niemien,
            R.drawable.wloch
    };

    String[] countries = {
            "Czechy",
            "Francja",
            "Niemcy",
            "Włochy"
    };

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

        imageFlag = findViewById(R.id.imageFlag);
        listView = findViewById(R.id.listViewAnswers);
        buttonRandom = findViewById(R.id.buttonRandom);
        textScore = findViewById(R.id.textScore);

        loadNewFlag();

        buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewFlag();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            total++;

            String selected = parent.getItemAtPosition(position).toString();

            if (selected.equals(correctAnswer)) {
                correct++;
                Toast.makeText(this, "✅ Dobrze!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "❌ Źle! To była: " + correctAnswer, Toast.LENGTH_SHORT).show();
            }

            updateScore();
        });
    }

    private void loadNewFlag() {
        Random random = new Random();
        int index = random.nextInt(flags.length);

        imageFlag.setImageResource(flags[index]);
        correctAnswer = countries[index];

        ArrayList<String> answers = new ArrayList<>(Arrays.asList(countries));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                answers
        );

        listView.setAdapter(adapter);
    }

    private void updateScore() {
        textScore.setText("odpowiedziałeś " + correct + " / " + total);
    }

}