package com.example.bkgut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



/*        Spinner dropdown = findViewById(R.id.dropspinner);
        String[] items = new String[]{"A","B","C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        Spinner dropdown1 = findViewById(R.id.dropspinner1);
        String[] items1 = new String[]{"A","B","C"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,items);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.setAdapter(adapter1);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //sgetMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Uri uri;
        Intent intent;


        if (id == R.id.nav_home) {
            //startActivity(new Intent(MainActivity.this, MainActivity.class));
            Home hm = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.child_oof,  hm).commit();
        } else if (id == R.id.nav_karriere) {
            Karriere kr = new Karriere();
            getSupportFragmentManager().beginTransaction().replace(R.id.child_oof,  kr).commit();
        } else if (id == R.id.nav_termine) {
            Termine tr = new Termine();
            getSupportFragmentManager().beginTransaction().replace(R.id.child_oof,  tr).commit();
        } else if (id == R.id.nav_kontakte) {
            Kontakte kt = new Kontakte();
            getSupportFragmentManager().beginTransaction().replace(R.id.child_oof,  kt).commit();
        } else if (id == R.id.facebook) {
            uri = Uri.parse("https://de-de.facebook.com/BKGuT");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (id == R.id.insta) {
            uri = Uri.parse("https://www.instagram.com/bkgut_aachen");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (id == R.id.reddit) {
            uri = Uri.parse("https://www.reddit.com/r/justneckbeardthings");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
