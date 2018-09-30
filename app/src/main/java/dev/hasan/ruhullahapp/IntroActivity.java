package dev.hasan.ruhullahapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, NotificationActivity.class));
            }
        });

    }

    public void moreBooks(View view) {
        startActivity(new Intent(IntroActivity.this, MoreBookActivity.class));
    }

    public void readBook(View view) {
        startActivity(new Intent(IntroActivity.this, BookActivity.class));
        finish();
    }

    public void share(View view) {
        Toast.makeText(this,"Share Option will pop up",Toast.LENGTH_SHORT).show();
    }


}
