package com.example.digiagro;

import static com.example.digiagro.R.id.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class seeAttributes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout fruit, veg, crop, tree;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_attributes);
        fruit = findViewById(R.id.fruitlay);
        veg = findViewById(R.id.veglay);
        crop = findViewById(R.id.croplay);
        tree = findViewById(R.id.treelay);

        mAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_open);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_view);
//        navigationView.setCheckedItem(Home);

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(seeAttributes.this, fruits.class);
                startActivity(i);
            }
        });
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(seeAttributes.this, vegs.class);
                startActivity(ii);
            }
        });
        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(seeAttributes.this, crops.class);
                startActivity(iii);
            }
        });
        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iiii = new Intent(seeAttributes.this, treees.class);
                startActivity(iiii);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                Intent i = new Intent(this, dashboard2.class);
                startActivity(i);
                break;
            case R.id.logout:
                mAuth.signOut();
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
                finish();
                break;
            case R.id.dash:
                Intent x = new Intent(this, dashboard.class);
                startActivity(x);
                break;
            case R.id.profile:
                break;
            case R.id.adduser:
                mAuth.signOut();
                Intent ii = new Intent(this, MainActivity2.class);
                startActivity(ii);
            case R.id.share:
                break;
            case R.id.About:
                Intent a = new Intent(this, webView.class);
                startActivity(a);
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}