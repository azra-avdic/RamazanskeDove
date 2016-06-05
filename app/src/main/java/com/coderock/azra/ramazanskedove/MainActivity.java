package com.coderock.azra.ramazanskedove;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Drawer
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle; // current title
    private ViewPager viewPager;
    private TextView tvSinglePageContent;
    private ScrollView svSinglePageContent;
    private LinearLayout llToday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface fontHelvetica = Typeface.createFromAsset(this.getAssets(), "fonts/Helvetica.ttf");
        Typeface fontHelveticaObl = Typeface.createFromAsset(this.getAssets(), "fonts/Helvetica-Oblique.ttf");
        Typeface fontHelveticaNeueMedium = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeue-Medium.otf");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the padding to match the Status Bar height
        // toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        getSupportActionBar().setTitle("Ramazanske dove");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new com.coderock.azra.ramazanskedove.CustomPagerAdapter(this));
        viewPager.setCurrentItem(Utils.getDayInRamadan() < 0 ? 0 : Utils.getDayInRamadan());

        TextView tvToday = (TextView) findViewById(R.id.tvToday);
        if (tvToday != null) {
            tvToday.setText(Utils.getTodaysDateAsTitle());
        }

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[4];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.ico_book, "Ramazanske dove");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ico_iftar, "Iftarska dova");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ico_info, "O aplikaciji");
        drawerItem[3] = new ObjectDrawerItem(R.drawable.ico_contact, "Kontakt");

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setSelection(0);
        mDrawerList.setItemChecked(0, true);

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
        if (tvSinglePageContent != null) {
            tvSinglePageContent.setTypeface(fontHelvetica);
        }

        svSinglePageContent = (ScrollView) findViewById(R.id.svSinglePageContent);
        llToday = (LinearLayout) findViewById(R.id.llToday);

        if (tvToday != null) {
            tvToday.setTypeface(fontHelveticaNeueMedium);
        }

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
        mDrawerList.clearChoices();
        mDrawerList.requestLayout();
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);

        switch (position) {
            case 0:
                //todo viewPager visible and on correct position
                llToday.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                svSinglePageContent.setVisibility(View.GONE);
                break;
            case 1:
                //todo vp gone && Iftar dova
                llToday.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.iftar_doa);
                svSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 2:
                //todo vp gone && text about app
                llToday.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.about_app);
                svSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 3:
                //todo vp gone && some text for contact
                llToday.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                svSinglePageContent.setVisibility(View.VISIBLE);
                setClickableText();
                break;
            default:
                break;
        }
        setTitle(mNavigationDrawerItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"avdic.azra@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "avdic.azra@gmail.com", null));

       // emailIntent.setData(Uri.parse("mailto:"));
       // emailIntent.setType("text/plain");
       // emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
      //  emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ramazanske dove Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Ovdje napišite svoj feedback...");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished", "sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setClickableText() {

        String staticPart = "Za sva pitanja i sugestije, molimo obratite se putem emaila:\n\n\n";
        SpannableString clickablePart = new SpannableString("avdic.azra@gmail.com");

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                sendEmail();
            }
        };
        clickablePart.setSpan(clickableSpan, 0, clickablePart.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        clickablePart.setSpan(new UnderlineSpan(), 0, clickablePart.length(), 0);
        clickablePart.setSpan(new StyleSpan(Typeface.BOLD), 0, clickablePart.length(), 0);
        clickablePart.setSpan(new ForegroundColorSpan(Utils.getColor(this, R.color.white)), 0, clickablePart.length(), 0);

        tvSinglePageContent.setText(staticPart);
        tvSinglePageContent.append(clickablePart);

        tvSinglePageContent.setMovementMethod(LinkMovementMethod.getInstance());
    }

}