package com.android.littledev.duplom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ViewFlipper newest = (ViewFlipper) findViewById(R.id.main_new);
        ViewFlipper recommended = (ViewFlipper) findViewById(R.id.main_recommended);
        Database database = new Database(this);
        database.onUpgrade(database.getWritableDatabase(), 0,1);
        database.insertItem(R.drawable.blazer, "Чоловічий блейзер", "UAH", 5, 800, "Clothes", "Men", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.suit, "Чоловічий костюм", "UAH", 5, 800, "Clothes", "Men", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.jacket, "Чоловічий піджак", "UAH", 5, 800, "Clothes", "Men", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.jacket_2, "Чоловічий піджак", "UAH", 5, 800, "Clothes", "Men", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.svitshot_1, "Жіночий світшот", "UAH", 5, 800, "Clothes", "Woman", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.hat_1, "Шапка", "UAH", 5, 800, "Hats", "Men", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.accessories_2, "Жіночі окуляри", "UAH", 5, 800, "Accessories", "Woman", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.accessorie_1, "Брелок", "UAH", 5, 800, "Accessories", "Woman", "Adult", "Outerwear", "Summer", "Descripion");
        database.insertItem(R.drawable.shoes_1, "Капці", "UAH", 5, 800, "Shoes", "Men", "Adult", "Outerwear", "Summer", "Descripion");
        Cursor newestCursor = database.getNewest();
        Cursor recommendedCursor = database.getRecommended();
        addNew(newest, newestCursor);
        addNew(recommended, recommendedCursor);
        newest.setInAnimation(this, R.anim.flip_in);
        newest.setOutAnimation(this, R.anim.flip_out);
        recommended.setInAnimation(this, R.anim.flip_in);
        recommended.setOutAnimation(this, R.anim.flip_out);
        newest.startFlipping();
        recommended.startFlipping();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BasketActivity.class));
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

    void addNew(ViewFlipper newFlipper, final Cursor data){

        int index = 0;
        data.moveToFirst();

        while (index < data.getCount()){
            LinearLayout containerLayout = new LinearLayout(this);
            LinearLayout newLayout = new LinearLayout(this);
            LinearLayout newTextLayout = new LinearLayout(this);

            for (int i = 0; i < 3 && index < data.getCount(); i++){
                ImageButton newButton = new ImageButton(this);
                TextView newText = new TextView(this);
                LinearLayout.LayoutParams bParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams tParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                float density =  this.getResources().getDisplayMetrics().density;
                newButton.setTag(data.getInt(0));
                bParams.setMarginEnd((int) Math.ceil(10 * density));
                bParams.width = (int) Math.ceil(100 * density);
                bParams.height = (int) Math.ceil(100 * density);
                newButton.setImageResource(data.getInt(1));
                newButton.setLayoutParams(bParams);
                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ItemInfo.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",(Integer) v.getTag());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
                newText.setText(data.getString(2));
                newText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tParams.setMarginEnd((int) Math.ceil(10 * density));
                tParams.width = (int) Math.ceil(100 * density);
                tParams.height = (int) Math.ceil(35 * density);
                newText.setLayoutParams(tParams);
                newTextLayout.addView(newText);
                newLayout.addView(newButton);
                data.moveToNext();
                index++;
            }
            containerLayout.setOrientation(LinearLayout.VERTICAL);
            containerLayout.addView(newLayout);
            containerLayout.addView(newTextLayout);
            newFlipper.addView(containerLayout);
        }

    }
}
