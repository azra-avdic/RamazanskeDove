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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvSinglePageContent) TextView tvSinglePageContent;
    @BindView(R.id.svSinglePageContent) ScrollView svSinglePageContent;
    @BindView(R.id.llToday) LinearLayout llToday;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.left_drawer) ListView mDrawerList;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tvToday) TextView tvToday;
    @BindArray(R.array.navigation_drawer_items_array)  String[] mNavigationDrawerItemTitles;

    ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle; // current title

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ramazanske dove");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mTitle = getTitle();


        viewPager.setAdapter(new com.coderock.azra.ramazanskedove.CustomPagerAdapter(getSupportFragmentManager(),this));
        viewPager.setCurrentItem(Utils.getDayInRamadan() < 0 ? 0 : Utils.getDayInRamadan());

        if (tvToday != null) {
            tvToday.setText(Utils.getTodaysDateAsTitle());
        }

        setupDrawerList();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        setFonts();
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
                //viewPager visible
                llToday.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                svSinglePageContent.setVisibility(View.GONE);
                break;
            case 1:
                //viewPager gone && single page content: Iftar dova
                llToday.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.iftar_doa);
                svSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 2:
                //viewPager gone && single page content: About app
                llToday.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.about_app);
                svSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 3:
                //viewPager gone && single page content: Contact form
                llToday.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                svSinglePageContent.setVisibility(View.VISIBLE);
                setClickableText(tvSinglePageContent);
                break;
            default:
                break;
        }
        setTitle(mNavigationDrawerItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    /**
     * Starts Activity with chooser dialog: email clients on phone, populates email subject and recipient
     */

    protected void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "avdic.azra@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ramazanske dove - Pitanja i sugestije");

        try {
            startActivity(Intent.createChooser(emailIntent, "Pošalji email koristeći..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Email client nije instaliran na vašem telefonu.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param tvSinglePageContent -TextView object
     *  method populates TextView with contact text and email which is clickable: click invokes sendEmail action
     */

    public void setClickableText(TextView tvSinglePageContent) {

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

    public void setupDrawerList(){
        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[4];
        drawerItem[0] = new ObjectDrawerItem(R.drawable.ico_book, mNavigationDrawerItemTitles[0]);
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ico_iftar, mNavigationDrawerItemTitles[1]);
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ico_info, mNavigationDrawerItemTitles[2]);
        drawerItem[3] = new ObjectDrawerItem(R.drawable.ico_contact, mNavigationDrawerItemTitles[3]);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setSelection(0);
        mDrawerList.setItemChecked(0, true);

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
    }

    public void setFonts(){
        Typeface fontHelvetica = Typeface.createFromAsset(this.getAssets(), "fonts/Helvetica.ttf");
        Typeface fontHelveticaObl = Typeface.createFromAsset(this.getAssets(), "fonts/Helvetica-Oblique.ttf");
        Typeface fontHelveticaNeueMedium = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeue-Medium.otf");

        if (tvSinglePageContent != null) {
            tvSinglePageContent.setTypeface(fontHelvetica);
        }

        if (tvToday != null) {
            tvToday.setTypeface(fontHelveticaNeueMedium);
        }
    }

}