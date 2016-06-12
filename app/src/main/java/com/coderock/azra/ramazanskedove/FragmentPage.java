package com.coderock.azra.ramazanskedove;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentPage extends Fragment {
    public static final String POSITION = "POSITION";
    public static final String viberId ="com.viber.voip";
    public static final String msnId ="com.facebook.orca";
    public static final String whatsup = "com.whatsapp";
    private int position;
    private Unbinder unbinder;
    private onRewindClickListener rewindClickListener;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDoaArab)
    TextView tvDoaArab;
    @BindView(R.id.tvDoa)
    TextView tvDoa;
    @BindView(R.id.tvHadisContent)
    TextView tvHadis;
    @BindView(R.id.tvHadisTitle)
    TextView tvHadisTitle;
    @BindView(R.id.ivUp)
    ImageView ivUp;
    @BindView(R.id.ivDown)
    ImageView ivDown;
    @BindView(R.id.svHadis)
    ScrollView svHadis;
    @BindView(R.id.ivOptions)
    ImageView ivOptions;

    // newInstance constructor for creating fragment with argument
    public static FragmentPage newInstance(int position) {
        FragmentPage fragmentPage = new FragmentPage();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragmentPage.setArguments(args);
        return fragmentPage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(POSITION, 0);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        unbinder = ButterKnife.bind(this, view);

        tvTitle.setText(getPageTitle(position));
        tvDoaArab.setText(getDoaArab(position));
        tvDoa.setText(getDoa(position));
        tvHadis.setText(getHadis(position));

        setFonts();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivDown.getVisibility() == View.VISIBLE) {
                    svHadis.setVisibility(View.GONE);
                    ivDown.setVisibility(View.GONE);
                    ivUp.setVisibility(View.VISIBLE);
                } else {
                    svHadis.setVisibility(View.VISIBLE);
                    ivDown.setVisibility(View.VISIBLE);
                    ivUp.setVisibility(View.GONE);
                }
            }
        };

        tvHadisTitle.setOnClickListener(listener);
        ivUp.setOnClickListener(listener);
        ivDown.setOnClickListener(listener);

        ivOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getContext(), ivOptions);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.dropdown_menu, popup.getMenu());
                if(position == 0 ){
                    Menu popupMenu = popup.getMenu();
                    popupMenu.findItem(R.id.back).setEnabled(false);
                }

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.back:
                                rewindClickListener.onRewindClick();
                                return true;
                            case R.id.share:
                                share();
                                return true;
                            default:
                                return true;
                        }
                    }
                });

                popup.show(); //showing popup menu
            }

        });

        return view;
    }

    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return getActivity().getBaseContext().getString(customPagerEnum.getmTitleResId());
    }

    public CharSequence getDoaArab(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return getActivity().getBaseContext().getString(customPagerEnum.getmDoaArabResId());
    }

    public CharSequence getDoa(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return getActivity().getBaseContext().getString(customPagerEnum.getmDoaResId());
    }

    public CharSequence getHadis(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return getActivity().getBaseContext().getString(customPagerEnum.getmHadisResId());
    }

    public enum CustomPagerEnum {
        DAY1(R.string.day1_title, R.string.daily_doa1_arabic, R.string.daily_doa1, R.string.daily_hadis1),
        DAY2(R.string.day2_title, R.string.daily_doa2_arabic, R.string.daily_doa2, R.string.daily_hadis2),
        DAY3(R.string.day3_title, R.string.daily_doa3_arabic, R.string.daily_doa3, R.string.daily_hadis3),
        DAY4(R.string.day4_title, R.string.daily_doa4_arabic, R.string.daily_doa4, R.string.daily_hadis4),
        DAY5(R.string.day5_title, R.string.daily_doa5_arabic, R.string.daily_doa5, R.string.daily_hadis5),
        DAY6(R.string.day6_title, R.string.daily_doa6_arabic, R.string.daily_doa6, R.string.daily_hadis6),
        DAY7(R.string.day7_title, R.string.daily_doa7_arabic, R.string.daily_doa7, R.string.daily_hadis7),
        DAY8(R.string.day8_title, R.string.daily_doa8_arabic, R.string.daily_doa8, R.string.daily_hadis8),
        DAY9(R.string.day9_title, R.string.daily_doa9_arabic, R.string.daily_doa9, R.string.daily_hadis9),
        DAY10(R.string.day10_title, R.string.daily_doa10_arabic, R.string.daily_doa10, R.string.daily_hadis10),
        DAY11(R.string.day11_title, R.string.daily_doa11_arabic, R.string.daily_doa11, R.string.daily_hadis11),
        DAY12(R.string.day12_title, R.string.daily_doa12_arabic, R.string.daily_doa12, R.string.daily_hadis12),
        DAY13(R.string.day13_title, R.string.daily_doa13_arabic, R.string.daily_doa13, R.string.daily_hadis13),
        DAY14(R.string.day14_title, R.string.daily_doa14_arabic, R.string.daily_doa14, R.string.daily_hadis14),
        DAY15(R.string.day15_title, R.string.daily_doa15_arabic, R.string.daily_doa15, R.string.daily_hadis15),
        DAY16(R.string.day16_title, R.string.daily_doa16_arabic, R.string.daily_doa16, R.string.daily_hadis16),
        DAY17(R.string.day17_title, R.string.daily_doa17_arabic, R.string.daily_doa17, R.string.daily_hadis17),
        DAY18(R.string.day18_title, R.string.daily_doa18_arabic, R.string.daily_doa18, R.string.daily_hadis18),
        DAY19(R.string.day19_title, R.string.daily_doa19_arabic, R.string.daily_doa19, R.string.daily_hadis19),
        DAY20(R.string.day20_title, R.string.daily_doa20_arabic, R.string.daily_doa20, R.string.daily_hadis20),
        DAY21(R.string.day21_title, R.string.daily_doa21_arabic, R.string.daily_doa21, R.string.daily_hadis21),
        DAY22(R.string.day22_title, R.string.daily_doa22_arabic, R.string.daily_doa22, R.string.daily_hadis22),
        DAY23(R.string.day23_title, R.string.daily_doa23_arabic, R.string.daily_doa23, R.string.daily_hadis23),
        DAY24(R.string.day24_title, R.string.daily_doa24_arabic, R.string.daily_doa24, R.string.daily_hadis24),
        DAY25(R.string.day25_title, R.string.daily_doa25_arabic, R.string.daily_doa25, R.string.daily_hadis25),
        DAY26(R.string.day26_title, R.string.daily_doa26_arabic, R.string.daily_doa26, R.string.daily_hadis26),
        DAY27(R.string.day27_title, R.string.daily_doa27_arabic, R.string.daily_doa27, R.string.daily_hadis27),
        DAY28(R.string.day28_title, R.string.daily_doa28_arabic, R.string.daily_doa28, R.string.daily_hadis28),
        DAY29(R.string.day29_title, R.string.daily_doa29_arabic, R.string.daily_doa29, R.string.daily_hadis29);

        private int mTitleResId;
        private int mDoaArabResId;
        private int mDoaResId;
        private int mHadisResId;


        CustomPagerEnum(int titleResId, int doaArabResId, int doaResId, int hadisResId) {
            mTitleResId = titleResId;
            mDoaArabResId = doaArabResId;
            mDoaResId = doaResId;
            mHadisResId = hadisResId;
        }

        public int getmTitleResId() {
            return mTitleResId;
        }

        public int getmDoaArabResId() {
            return mDoaArabResId;
        }

        public int getmDoaResId() {
            return mDoaResId;
        }

        public int getmHadisResId() {
            return mHadisResId;
        }
    }

    public void setFonts() {
        Typeface fontHelvetica = Typeface.createFromAsset(getActivity().getBaseContext().getAssets(), "fonts/Helvetica.ttf");
        Typeface fontHelveticaObl = Typeface.createFromAsset(getActivity().getBaseContext().getAssets(), "fonts/Helvetica-Oblique.ttf");

        tvTitle.setTypeface(fontHelvetica);
        tvDoaArab.setTypeface(fontHelvetica);
        tvDoa.setTypeface(fontHelvetica);
        tvHadis.setTypeface(fontHelvetica);
        tvHadisTitle.setTypeface(fontHelvetica);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface onRewindClickListener {
        void onRewindClick();
    }

    public void setRewindClickListener(onRewindClickListener rewindClickListener) {
        this.rewindClickListener = rewindClickListener;
    }

    protected void share() {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        List<Intent> targetedShareIntents = new ArrayList<Intent>();
        List<ResolveInfo> resInfo = getActivity().getPackageManager().queryIntentActivities(shareIntent, 0);
        if (!resInfo.isEmpty()) {
            for (ResolveInfo resolveInfo : resInfo) {
                String packageName = resolveInfo.activityInfo.packageName;
                Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
                if (TextUtils.equals(packageName, msnId)|| TextUtils.equals(packageName, viberId)) {
                    targetedShareIntent.setType("text/plain");
                    String message = "\"" + (String) getDoa(position) + "\"" + " - preuzeto iz aplikacije " ;
                    String appLink = "https://play.google.com/store/apps/details?id=com.coderock.azra.ramazanskedove";
                    String appShortLink = "https://goo.gl/A5J1ZV";
                    targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, message + appShortLink);
                    targetedShareIntent.setPackage(packageName);
                    targetedShareIntents.add(targetedShareIntent);
                }
            }
            if(targetedShareIntents.size() != 0) {
                Intent chooserIntent = Intent.createChooser(targetedShareIntents.get(0), Utils.getString(getContext(), R.string.share_dialog_title));
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[targetedShareIntents.size()]));
                startActivity(chooserIntent);
            } else
            {
                Toast.makeText(getContext(), R.string.toast_app_not_installed, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
