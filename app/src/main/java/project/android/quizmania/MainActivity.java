package project.android.quizmania;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import project.android.quizmania.Adapter.TabFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView name_user, email_user;
   // private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        tabs.setTabTextColors(getResources().getColor(android.R.color.darker_gray), getResources().getColor(android.R.color.white));
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

                 NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);

        name_user = (TextView) findViewById(R.id.name_user_drawer);
        email_user = (TextView) findViewById(R.id.email_user_drawer);

//        // SqLite database handler
//        db = new SQLiteHandler(getApplicationContext());

        // session manager
//        session = new SessionManager(getApplicationContext());
//
//        if (!session.isLoggedIn()) {
//            logoutUser();
//        }

////        Fetching user details from sqlite
////        HashMap<String, String> user = db.getUserDetails();
//
////        String name = user.get("name");
////        String email = user.get("email");
//
//         Displaying the user details on the screen
//        name_user.setText(name);
//        email_user.setText(email);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
            startActivity(new Intent(MainActivity.this, ProfilActivity.class));

        } else if (id == R.id.nav_kuis_sekarang)    {

        } else if (id == R.id.nav_atur_Waktu_Kuis){

        } else if (id == R.id.nav_teman) {
            startActivity(new Intent(MainActivity.this, Friends.class));
            return true;
        } else if (id == R.id.nav_peringkat){
            startActivity(new Intent(MainActivity.this, Ranking.class));
            return true;
        } else if (id == R.id.nav_pengaturan) {
            startActivity(new Intent(MainActivity.this, PengaturanActivity.class));
            return true;
        } else if (id == R.id.nav_bantuan){

        } else if (id == R.id.nav_info) {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));
            return true;
        } else if (id == R.id.nav_keluar){
            logoutUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {
        //session.setLogin(false);

//        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
