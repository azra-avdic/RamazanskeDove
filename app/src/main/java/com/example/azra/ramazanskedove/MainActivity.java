package com.example.azra.ramazanskedove;


import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle; // current title
    private  ViewPager viewPager;
    private  TextView tvSinglePageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        // Set the padding to match the Status Bar height
       // toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        getSupportActionBar().setTitle("Ramazanske dove");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new com.example.azra.ramazanskedove.CustomPagerAdapter(this));
        viewPager.setCurrentItem(Utils.getDayInRamadan() < 0 ? 0: Utils.getDayInRamadan());
        Log.d("TAG", Utils.getTodayAsStringDayAndMonth());
        TextView tvToday = (TextView) findViewById(R.id.tvToday);
        if (tvToday != null) {
            tvToday.setText(Utils.getTodaysDateAsTitle());
        }

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[5];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_copy, "Ramazanske dove");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_refresh, "Sehur dova");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_action_share, "Iftar dova");
        drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_action_copy, "O aplikaciji");
        drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_action_refresh, "Kontakt");


        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        tvSinglePageContent = (TextView) findViewById(R.id.tvSinglePageContent);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        switch (position) {
            case 0:
                //todo viewPager visible and on correct position
                viewPager.setVisibility(View.VISIBLE);
                tvSinglePageContent.setVisibility(View.GONE);
                break;
            case 1:
                //todo vp gone && text for Sehur
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.iftar_doa);
                tvSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 2:
                //todo vp gone && Iftar dova
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.iftar_doa);
                tvSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 3:
                //todo vp gone && text about app
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.about_app);
                tvSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 4:
                //todo vp gone && some text for contact
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.contact);
                tvSinglePageContent.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }


        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(mNavigationDrawerItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    // A method to find height of the status bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
