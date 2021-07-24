package com.trystar.geopose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    String Number1, Number2, Number3;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mToolbar;
    NavigationView navigationView;
    Menu menu;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Number1 = getIntent().getStringExtra("Number1");
            Number2 = getIntent().getStringExtra("Number2");
            Number3 = getIntent().getStringExtra("Number3");
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        //Start of Slider


        // Navigation view header
        navigationView = findViewById(R.id.navigation);
        textView = findViewById(R.id.textView);
        //toolbar=findViewById(R.id.mToolbar);

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        navigationView.bringToFront();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setUpNavigationView();

        menu = navigationView.getMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //end od slider menu

        Vibrator myVib;
        View view_enter;
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        view_enter = findViewById(R.id.view_enter);
        view_enter.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                myVib.vibrate(50);
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                return false;
            }

        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.sos:
                        //Toast.makeText(this, "SOS message sending", Toast.LENGTH_LONG).show();
                        SosSendFunction();
                        break;
                    case R.id.home:
                        //Toast.makeText(this, "Home", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.friends:
                        //Toast.makeText(this, "Opening Friend Zone", Toast.LENGTH_LONG).show();
                        OpenFriendZone();
                        break;
                    case R.id.logout:
                        //Toast.makeText(this, "Log Out", Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), Register.class));
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

            private void SosSendFunction() {


                String message = "Hey, I am in problem look over my location on the GeoPos app and help me immediately.";

                SmsManager mySmsManager = SmsManager.getDefault();
                mySmsManager.sendTextMessage(Number1, null, message, null, null);
                mySmsManager.sendTextMessage(Number2, null, message, null, null);
                mySmsManager.sendTextMessage(Number3, null, message, null, null);
            }

            private void OpenFriendZone() {
                startActivity(new Intent(this, FriendZone.class));
            }

    /*public void BtnCurrentLocation(View view) {

        startActivity(new Intent(this,MapsActivity.class));

    }*/
        }
