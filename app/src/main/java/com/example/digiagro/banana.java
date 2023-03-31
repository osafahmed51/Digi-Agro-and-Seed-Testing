package com.example.digiagro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class banana extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView morebtntxt;
    FirebaseAuth mAuth;
    LinearLayout showmore,moretxt;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana);
        mAuth = FirebaseAuth.getInstance();
        moretxt=findViewById(R.id.moretxt);
        morebtntxt=findViewById(R.id.morebtntxt);
        showmore = findViewById(R.id.showmorely);
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
        showmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moretxt.getVisibility()==moretxt.GONE)
                {
                    morebtntxt.setText("Hide More Detail:");
                    moretxt.setVisibility(moretxt.VISIBLE);

                }
                else if(moretxt.getVisibility()==moretxt.VISIBLE){

                    morebtntxt.setText("View More Detail:");
                    moretxt.setVisibility(moretxt.GONE);

                }

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