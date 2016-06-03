package com.example.azra.ramazanskedove;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Azra on 2.6.2016.
 */
public class DrawerItemCustomAdapter extends ArrayAdapter<ObjectDrawerItem> {

    Context mContext;
    int layoutResourceId;
    ObjectDrawerItem data[] = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, ObjectDrawerItem[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Typeface fontHelveticaNeue = Typeface.createFromAsset(mContext.getAssets(), "fonts/HelveticaNeue.otf");
        Typeface fontHelveticaObl = Typeface.createFromAsset(mContext.getAssets(),"fonts/Helvetica-Oblique.ttf");
        Typeface fontHelveticaNeueMedium = Typeface.createFromAsset(mContext.getAssets(),"fonts/HelveticaNeue-Medium.otf");
        Typeface fontHelveticaNeueItalic = Typeface.createFromAsset(mContext.getAssets(),"fonts/HelveticaNeue-Italic.otf");

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView tvOption = (TextView) listItem.findViewById(R.id.tvOption);

        ObjectDrawerItem drawerItem = data[position];
        imageViewIcon.setImageResource(drawerItem.icon);
        tvOption.setText(drawerItem.name);
        tvOption.setTypeface(fontHelveticaNeueItalic);
        return listItem;
    }


}
