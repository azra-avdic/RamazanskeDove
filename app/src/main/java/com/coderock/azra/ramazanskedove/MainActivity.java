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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentPage.onRewindClickListener {

    @BindView(R.id.tvSinglePageContent) TextView tvSinglePageContent;
    @BindView(R.id.svSinglePageContent) ScrollView svSinglePageContent;
    @BindView(R.id.llToday) LinearLayout llToday;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.left_drawer) ListView mDrawerList;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tvToday) TextView tvToday;
    @BindArray(R.array.navigation_drawer_items_array)  String[] mNavigationDrawerItemTitles;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle; // current title

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Utils.getString(this, R.string.toolbar_title));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mTitle = getTitle();

        PagesAdapter pagesAdapter = new PagesAdapter(getSupportFragmentManager(),this);
        pagesAdapter.setRewindClickListener(this);
        viewPager.setAdapter(pagesAdapter);
        viewPager.setCurrentItem(Utils.getDayInRamadan() < 0 ? 0 : Utils.getDayInRamadan());

        if (tvToday != null) {
            tvToday.setText(getCurrentDateInTitleFormat());
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
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mTitle);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onRewindClick() {
        Log.d("TAG", "onRewindClick callback");
        viewPager.setCurrentItem(0);
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
                llToday.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                svSinglePageContent.setVisibility(View.GONE);
                break;
            case 1:
                llToday.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.iftar_doa);
                svSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 2:
                llToday.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                tvSinglePageContent.setText(R.string.about_app);
                svSinglePageContent.setVisibility(View.VISIBLE);
                break;
            case 3:
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

    protected void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email_mailto), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.email_dialog_title)));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, R.string.email_exception_toast_mssg, Toast.LENGTH_SHORT).show();
        }
    }

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
        DrawerItem[] drawerItem = new DrawerItem[4];
        drawerItem[0] = new DrawerItem(R.drawable.book, mNavigationDrawerItemTitles[0]);
        drawerItem[1] = new DrawerItem(R.drawable.dish, mNavigationDrawerItemTitles[1]);
        drawerItem[2] = new DrawerItem(R.drawable.information, mNavigationDrawerItemTitles[2]);
        drawerItem[3] = new DrawerItem(R.drawable.envelope, mNavigationDrawerItemTitles[3]);

        DrawerAdapter adapter = new DrawerAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setSelection(0);
        mDrawerList.setItemChecked(0, true);

        //todo check if this toggle is necessary
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
            public void onDrawerOpened(View drawerView) {super.onDrawerOpened(drawerView);}
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void setFonts(){
        Typeface fontHelvetica = Typeface.createFromAsset(this.getAssets(), "fonts/Helvetica.ttf");
        Typeface fontHelveticaNeueMedium = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeue-Medium.otf");

        if (tvSinglePageContent != null) {
            tvSinglePageContent.setTypeface(fontHelvetica);
        }

        if (tvToday != null) {
            tvToday.setTypeface(fontHelveticaNeueMedium);
        }
    }

    public static String getCurrentDateInTitleFormat(){
        if(Utils.getDayInRamadan() < 0){
            return Utils.getTodayDayAndMonthAsString();
        }else {
            int day = Utils.getDayInRamadan() + 1;
            String ramadanDate = day + ". Ramazan";
            return Utils.getTodayDayAndMonthAsString() + " / " + ramadanDate;
        }
    }
}