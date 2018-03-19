package com.app.dueday.maya;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.app.dueday.maya.adapter.EventListAdapter;
import com.app.dueday.maya.module.EventListViewItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView mylistview;
    private List<String> myProjects;
    ArrayList<EventListViewItem> data;
    EventListAdapter adapter;
    public static final String EXTRA_PName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///////////////////////////////////////////////////////////////////////////////////////////
        mylistview = (ListView) findViewById(R.id.projectList);

        data = new ArrayList<EventListViewItem>();

        // set up ListView content
        // Create an ArrayAdapter from List
        adapter = new EventListAdapter(this, data);

        // DataBind ListView with items from ArrayAdapter
        View header = (View)getLayoutInflater().inflate(R.layout.list_event_item, null);
        mylistview.setAdapter(adapter);
        mylistview.setOnItemClickListener(onClickProjListView);

//

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_addProject);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent;
                intent = new Intent(getApplicationContext(), CreateProject.class);
                startActivityForResult(intent, 1);
//                startActivity(intent);
            }
        });

    }


    /***
     * pass selected text to the next activity
     */
    private AdapterView.OnItemClickListener onClickProjListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
            intent = new Intent(getApplicationContext(), Calendar.class);
//            String projectName = myProjects.get(position);
            String projectName = data.get(position).title;
            intent.putExtra(EXTRA_PName, projectName);
            startActivity(intent);
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            String title = intent.getStringExtra("title");
            System.out.print("+++++");
            System.out.print(title);
            EventListViewItem e = new EventListViewItem(R.drawable.ic_launcher_foreground, title);
            data.add(e);
            adapter.notifyDataSetChanged();
        }
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home_page, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
//        if (id == R.id.action_setting) {
//            Intent intent;
//            intent = new Intent(getApplicationContext(), Setting.class);
//
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_social) {
            // Handle the camera action
        } else if (id == R.id.nav_travel) {

        } else if (id == R.id.nav_work) {


        } else if (id == R.id.nav_own) {
            Intent intent;
            intent = new Intent(getApplicationContext(), OwnCalendar.class);

            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
