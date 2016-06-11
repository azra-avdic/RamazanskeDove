package com.coderock.azra.ramazanskedove;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Azra on 10.6.2016.
 */
public class AppInfoDialog {

    private static Dialog dialog;
    private static String text;

    public static void showDialog(Context context, String text) {
        dialog = new Dialog(context);
        AppInfoDialog.text = text;
        dialog.setCancelable(true);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.app_info_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        //TextView tvDialogText = (TextView) dialog.findViewById(R.id.tvAppInfo);
        // tvDialogText.setText(text);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public static boolean isShown() {
        return (dialog != null && dialog.isShowing());
    }

    public static void showSameDialog(Context context) {
        dialog.dismiss();
        showDialog(context, text);
    }

}

