package dev.hasan.ruhullahapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "BookActivity";
    private TextView chapterNameTV;
    private TextView chapterContentTV;
    private FirebaseFirestore db;
    private DocumentReference chapContDocRef;
    private DocumentReference chapNameDocRef;
    private ArrayList<String> chapNameList;
    private ArrayList<String> chapContList;
    private NetChecker netChecker;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Local Functions
        init();

        if (netChecker.isConnected()) {
            dataReadFromFirebase();
            Log.e(TAG,"Internet is available");
        } else {
            dataReadFromSP();
        }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookActivity.this, NotificationActivity.class));
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Dynamic Menu
        //displayMenu();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }


    private void dataReadFromSP() {

    }

    private void displayMenu() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final Menu menu = navigationView.getMenu();

        //Dynamic Menu Build
        //Chapter 1.1
        String headerData = chapNameList.get(0);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(0));
                chapterContentTV.setText(chapContList.get(0));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.2
        headerData = chapNameList.get(1);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(1));
                chapterContentTV.setText(chapContList.get(1));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.3
        headerData = chapNameList.get(2);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(2));
                chapterContentTV.setText(chapContList.get(2));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.4
        headerData = chapNameList.get(3);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(3));
                chapterContentTV.setText(chapContList.get(3));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.5
        headerData = chapNameList.get(4);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(4));
                chapterContentTV.setText(chapContList.get(4));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.6
        headerData = chapNameList.get(5);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(5));
                chapterContentTV.setText(chapContList.get(5));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.7
        headerData = chapNameList.get(6);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(6));
                chapterContentTV.setText(chapContList.get(6));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.8
        headerData = chapNameList.get(7);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(7));
                chapterContentTV.setText(chapContList.get(7));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.9
        headerData = chapNameList.get(8);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(8));
                chapterContentTV.setText(chapContList.get(8));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.10
        headerData = chapNameList.get(9);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(9));
                chapterContentTV.setText(chapContList.get(9));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.11
        headerData = chapNameList.get(10);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(10));
                chapterContentTV.setText(chapContList.get(10));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.12
        headerData = chapNameList.get(11);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(11));
                chapterContentTV.setText(chapContList.get(11));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.13
        headerData = chapNameList.get(12);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(12));
                chapterContentTV.setText(chapContList.get(12));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 1.14
        headerData = chapNameList.get(13);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(13));
                chapterContentTV.setText(chapContList.get(13));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.1
        headerData = chapNameList.get(14);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(14));
                chapterContentTV.setText(chapContList.get(14));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.2
        headerData = chapNameList.get(15);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(15));
                chapterContentTV.setText(chapContList.get(15));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.3
        headerData = chapNameList.get(16);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(16));
                chapterContentTV.setText(chapContList.get(16));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.4
        headerData = chapNameList.get(17);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(17));
                chapterContentTV.setText(chapContList.get(17));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.5
        headerData = chapNameList.get(18);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(18));
                chapterContentTV.setText(chapContList.get(18));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.6
        headerData = chapNameList.get(19);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(19));
                chapterContentTV.setText(chapContList.get(19));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.7
        headerData = chapNameList.get(20);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(20));
                chapterContentTV.setText(chapContList.get(20));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.8
        headerData = chapNameList.get(21);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(21));
                chapterContentTV.setText(chapContList.get(21));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.9
        headerData = chapNameList.get(22);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(22));
                chapterContentTV.setText(chapContList.get(22));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.10
        headerData = chapNameList.get(23);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(23));
                chapterContentTV.setText(chapContList.get(23));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.11
        headerData = chapNameList.get(24);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(24));
                chapterContentTV.setText(chapContList.get(24));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.12
        headerData = chapNameList.get(25);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(25));
                chapterContentTV.setText(chapContList.get(25));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.13
        headerData = chapNameList.get(26);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(26));
                chapterContentTV.setText(chapContList.get(26));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.14
        headerData = chapNameList.get(27);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(27));
                chapterContentTV.setText(chapContList.get(27));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.15
        headerData = chapNameList.get(28);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(28));
                chapterContentTV.setText(chapContList.get(28));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.16
        headerData = chapNameList.get(29);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(29));
                chapterContentTV.setText(chapContList.get(29));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.17
        headerData = chapNameList.get(30);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(30));
                chapterContentTV.setText(chapContList.get(30));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.18
        headerData = chapNameList.get(31);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(31));
                chapterContentTV.setText(chapContList.get(31));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.19
        headerData = chapNameList.get(32);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(32));
                chapterContentTV.setText(chapContList.get(32));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.20
        headerData = chapNameList.get(33);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(33));
                chapterContentTV.setText(chapContList.get(33));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.21
        headerData = chapNameList.get(34);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(34));
                chapterContentTV.setText(chapContList.get(34));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 2.22
        headerData = chapNameList.get(35);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(35));
                chapterContentTV.setText(chapContList.get(35));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 3
        headerData = chapNameList.get(36);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(36));
                chapterContentTV.setText(chapContList.get(36));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 4
        headerData = chapNameList.get(37);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(37));
                chapterContentTV.setText(chapContList.get(37));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 5
        headerData = chapNameList.get(38);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(38));
                chapterContentTV.setText(chapContList.get(38));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 6
        headerData = chapNameList.get(39);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                chapterNameTV.setText(chapNameList.get(39));
                chapterContentTV.setText(chapContList.get(39));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 7
        headerData = chapNameList.get(40);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(40));
                chapterContentTV.setText(chapContList.get(40));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        //Chapter 8
        headerData = chapNameList.get(41);
        Log.e(TAG, "in document successfull - " + headerData);
        menu.add(headerData).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                chapterNameTV.setText(chapNameList.get(41));
                chapterContentTV.setText(chapContList.get(41));
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    private void init() {
        netChecker = new NetChecker(BookActivity.this);
        chapterNameTV = findViewById(R.id.chapterNameTV);
        chapterContentTV = findViewById(R.id.chapterContentTV);

        chapNameList = new ArrayList();
        chapContList = new ArrayList();

        db = FirebaseFirestore.getInstance();

        chapNameDocRef = db.collection("Chapters").document("ChapterNames");
        chapContDocRef = db.collection("Chapters").document("Contents");

        pref = PreferenceManager.getDefaultSharedPreferences(BookActivity.this);

    }

    private void dataReadFromFirebase() {


        chapNameDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot namesChap = task.getResult();
                    Log.e(TAG, "in task successfull - " + namesChap);
                    if (namesChap.exists()) {
                        String content = namesChap.getString("One1");
                        pref.edit().putString("One1",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One2");
                        pref.edit().putString("One2",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One3");
                        pref.edit().putString("One3",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One4");
                        pref.edit().putString("One4",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One5");
                        pref.edit().putString("One5",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One6");
                        pref.edit().putString("One6",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One7");
                        pref.edit().putString("One7",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One8");
                        pref.edit().putString("One8",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One9");
                        pref.edit().putString("One9",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One10");
                        pref.edit().putString("One10",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One11");
                        pref.edit().putString("One11",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One12");
                        pref.edit().putString("One12",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One13");
                        pref.edit().putString("One13",content);
                        chapNameList.add(content);

                        content = namesChap.getString("One14");
                        pref.edit().putString("One14",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two01");
                        pref.edit().putString("two01",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two02");
                        pref.edit().putString("two02",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two03");
                        pref.edit().putString("two03",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two04");
                        pref.edit().putString("two04",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two05");
                        pref.edit().putString("two05",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two06");
                        pref.edit().putString("two06",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two07");
                        pref.edit().putString("two07",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two08");
                        pref.edit().putString("two08",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two09");
                        pref.edit().putString("two09",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two10");
                        pref.edit().putString("two10",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two11");
                        pref.edit().putString("two11",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two12");
                        pref.edit().putString("two12",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two13");
                        pref.edit().putString("two13",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two14");
                        pref.edit().putString("two14",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two15");
                        pref.edit().putString("two15",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two16");
                        pref.edit().putString("two16",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two17");
                        pref.edit().putString("two17",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two18");
                        pref.edit().putString("two18",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two19");
                        pref.edit().putString("two19",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two20");
                        pref.edit().putString("two20",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two21");
                        pref.edit().putString("two21",content);
                        chapNameList.add(content);

                        content = namesChap.getString("two22");
                        pref.edit().putString("two22",content);
                        chapNameList.add(content);

                        content = namesChap.getString("three");
                        pref.edit().putString("three",content);
                        chapNameList.add(content);

                        content = namesChap.getString("four");
                        pref.edit().putString("four",content);
                        chapNameList.add(content);

                        content = namesChap.getString("five");
                        pref.edit().putString("five",content);
                        chapNameList.add(content);

                        content = namesChap.getString("six");
                        pref.edit().putString("six",content);
                        chapNameList.add(content);

                        content = namesChap.getString("seven");
                        pref.edit().putString("seven",content);
                        chapNameList.add(content);

                        content = namesChap.getString("eight");
                        pref.edit().putString("eight",content);
                        chapNameList.add(content);


                        Log.e(TAG, "ChapNameList - " + chapNameList);
                        Log.e(TAG, "ChapNameSize - " + chapNameList.size());
                        chapterNameTV.setText(chapNameList.get(0));
                        /*chapterNameTV.setText(headerData);*/

                    }
                }
            }
        });

        chapContDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot contentsChap = task.getResult();
                    if (contentsChap.exists()) {
                        chapContList.add(contentsChap.getString("one01"));
                        chapContList.add(contentsChap.getString("one02"));
                        chapContList.add(contentsChap.getString("one03"));
                        chapContList.add(contentsChap.getString("one04"));
                        chapContList.add(contentsChap.getString("one05"));
                        chapContList.add(contentsChap.getString("one06"));
                        chapContList.add(contentsChap.getString("one07"));
                        chapContList.add(contentsChap.getString("one08"));
                        chapContList.add(contentsChap.getString("one09"));
                        chapContList.add(contentsChap.getString("one10"));
                        chapContList.add(contentsChap.getString("one11"));
                        chapContList.add(contentsChap.getString("one12"));
                        chapContList.add(contentsChap.getString("one13"));
                        chapContList.add(contentsChap.getString("one14"));
                        chapContList.add(contentsChap.getString("two01"));
                        chapContList.add(contentsChap.getString("two02"));
                        chapContList.add(contentsChap.getString("two03"));
                        chapContList.add(contentsChap.getString("two04"));
                        chapContList.add(contentsChap.getString("two05"));
                        chapContList.add(contentsChap.getString("two06"));
                        chapContList.add(contentsChap.getString("two07"));
                        chapContList.add(contentsChap.getString("two08"));
                        chapContList.add(contentsChap.getString("two09"));
                        chapContList.add(contentsChap.getString("two10"));
                        chapContList.add(contentsChap.getString("two11"));
                        chapContList.add(contentsChap.getString("two12"));
                        chapContList.add(contentsChap.getString("two13"));
                        chapContList.add(contentsChap.getString("two14"));
                        chapContList.add(contentsChap.getString("two15"));
                        chapContList.add(contentsChap.getString("two16"));
                        chapContList.add(contentsChap.getString("two17"));
                        chapContList.add(contentsChap.getString("two18"));
                        chapContList.add(contentsChap.getString("two19"));
                        chapContList.add(contentsChap.getString("two20"));
                        chapContList.add(contentsChap.getString("two21"));
                        chapContList.add(contentsChap.getString("two22"));
                        chapContList.add(contentsChap.getString("three"));
                        chapContList.add(contentsChap.getString("four"));
                        chapContList.add(contentsChap.getString("five"));
                        chapContList.add(contentsChap.getString("six"));
                        chapContList.add(contentsChap.getString("seven"));
                        chapContList.add(contentsChap.getString("eight"));

                        Log.e(TAG, "ChapcontList - " + chapContList);
                        Log.e(TAG, "ChapterContentSize - " + chapContList.size());
                        chapterContentTV.setText(chapContList.get(0));

                        displayMenu();
                    }
                }
            }
        });




        /*Log.e(TAG, "ChapterOne - " + chapContList.get(1));
        Log.e(TAG, "ChapterContent - " + chapNameList.get(1));*/

    }


    public void nextButton(View view) {
    }
}
