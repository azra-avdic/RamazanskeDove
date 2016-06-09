package com.coderock.azra.ramazanskedove;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

    Context mContext;
    int layoutResourceId;
    DrawerItem data[] = null;

    public DrawerAdapter(Context mContext, int layoutResourceId, DrawerItem[] data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        DrawerItem drawerItem = data[position];

        holder.imageViewIcon.setImageResource(drawerItem.icon);
        holder.tvOption.setText(drawerItem.name);

        Typeface fontHelveticaNeueItalic = Typeface.createFromAsset(mContext.getAssets(),"fonts/HelveticaNeue-Italic.otf");
        holder.tvOption.setTypeface(fontHelveticaNeueItalic);

        return view;
    }

    static class ViewHolder {

        @BindView(R.id.tvOption) TextView tvOption;
        @BindView(R.id.imageViewIcon) ImageView imageViewIcon;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }




}
