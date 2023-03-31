package com.example.digiagro;

import static com.example.digiagro.R.id.dash2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class expfarmer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout seedlay, landlay, datalay, calclay, recordlay, farminglay;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expfarmer);

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
        navigationView.setCheckedItem(dash2);

        seedlay = findViewById(R.id.seedlay);
        landlay = findViewById(R.id.landlay);
        datalay = findViewById(R.id.datalay);
        calclay = findViewById(R.id.calclay);
        recordlay = findViewById(R.id.recordlay);
        farminglay = findViewById(R.id.farminglay);
        recordlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rec = new Intent(expfarmer.this, records.class);
                startActivity(rec);
            }
        });

        seedlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(expfarmer.this, seed.class);
                startActivity(i);
            }
        });
        landlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(expfarmer.this, land.class);
                startActivity(i);
            }
        });
        calclay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cal = new Intent(expfarmer.this, calc.class);
                startActivity(cal);
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
                Intent n = new Intent(this, dashboard.class);
                startActivity(n);
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
            case R.id.dash2:
                break;
            case R.id.how:
                Intent how = new Intent(this, webView1.class);
                startActivity(how);
                break;
        }
        return true;
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
