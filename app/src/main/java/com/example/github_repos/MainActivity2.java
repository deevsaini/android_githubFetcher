package com.example.github_repos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    TextView name,stars,watcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.name2);
        stars=findViewById(R.id.stars);
        watcher=findViewById(R.id.watch);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("name");
        String Stars = intent.getStringExtra("stars");
        String Watch= intent.getStringExtra("watch");

        name.setText("Name - "+ Name);
        stars.setText("Stars = "+ Stars);
        watcher.setText("Wathers Count -"+Watch);
    }
}