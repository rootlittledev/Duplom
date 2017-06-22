package com.android.littledev.duplom;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LittleDev on 22-Jun-17.
 */

public class ItemInfo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int amount;
    private float cost_per_item;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Database database = new Database(this);
        Bundle bundle = getIntent().getExtras();
        Cursor cursor = database.getItemLong(bundle.getInt("id"));
        ImageView item_image = (ImageView) this.findViewById(R.id.item_image);
        TextView item_label = (TextView) this.findViewById(R.id.item_label);
        TextView item_currency = (TextView) this.findViewById(R.id.item_currency);
        EditText item_amount = (EditText) this.findViewById(R.id.item_amount);
        TextView item_cost = (TextView) this.findViewById(R.id.item_cost);
        TextView item_year = (TextView) this.findViewById(R.id.tag_1);
        TextView item_genre = (TextView) this.findViewById(R.id.tag_2);
        TextView item_publisher = (TextView) this.findViewById(R.id.tag_3);
        TextView item_description = (TextView) this.findViewById(R.id.description);


        this.cost_per_item = cost_per_item;

        cursor.moveToFirst();
        item_image.setImageResource(cursor.getInt(1));
        item_label.setText(cursor.getString(2));
        item_currency.setText(cursor.getString(3));
        this.amount = cursor.getInt(4);
        item_amount.setText("1");
        item_cost.setText(Float.toString(cursor.getFloat(5)));
        item_year.setText(cursor.getString(6));
        item_genre.setText(cursor.getString(7));
        item_publisher.setText(cursor.getString(8));
        item_description.setText(cursor.getString(9));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}