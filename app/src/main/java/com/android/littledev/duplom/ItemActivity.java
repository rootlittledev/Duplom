package com.android.littledev.duplom;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;



public class ItemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] tags;
    public static String narrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        add_item();
        tags = new String[3];
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemActivity.this, BasketActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_main){
            if(!this.getClass().getName().equals(MainActivity.class.getName()))
                startActivity(new Intent(this, MainActivity.class));
        }
        else if (id == R.id.nav_search){
            if(!this.getClass().getName().equals(SearchActivity.class.getName()))
                startActivity(new Intent(this, SearchActivity.class));
        }
        else if (id == R.id.all_items) {
            ItemActivity.narrow = "none";
            startActivity(new Intent(this, ItemActivity.class));
        } else if (id == R.id.nav_clothes) {
            ItemActivity.narrow = "Clothes";
            startActivity(new Intent(this, ItemActivity.class));
        } else if (id == R.id.nav_accessories) {
            ItemActivity.narrow = "Accessories";
            startActivity(new Intent(this, ItemActivity.class));
        } else if (id == R.id.nav_shoes) {
            ItemActivity.narrow = "Shoes";
            startActivity(new Intent(this, ItemActivity.class));
        } else if (id == R.id.nav_hats) {
            ItemActivity.narrow = "Hats";
            startActivity(new Intent(this, ItemActivity.class));
        } else if (id == R.id.nav_basket) {
            if(!this.getClass().getName().equals(BasketActivity.class.getName())) {
                startActivity(new Intent(this, BasketActivity.class));
            }
        } else if (id == R.id.nav_profile) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setCategoryTag(String categoryTag){
        tags[0] = categoryTag;
    }
    public void setTypeTag(String typeTag){
        tags[1] = typeTag;
    }
    public void setSeasonTag(String seasonTag){
        tags[2] = seasonTag;
    }


    void add_item(){
        Database database = new Database(this);
        Cursor cursor =  database.getItemShort(narrow);
        LinearLayout layout = (LinearLayout)findViewById(R.id.container);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (cursor.moveToFirst()) {
            do {
                FrameLayout frame = new FrameLayout(this);
                int id = View.generateViewId();
                frame.setId(id);
                layout.addView(frame);
                Fragment itemsFragment = ItemFragment.newInstance(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getFloat(5));
                ft.add(id, itemsFragment);
            } while (cursor.moveToNext());
        }
        ft.commit();
    }
}