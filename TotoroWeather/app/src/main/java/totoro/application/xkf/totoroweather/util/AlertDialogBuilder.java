package totoro.application.xkf.totoroweather.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class AlertDialogBuilder {
    public static void createDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", listener)
                .create()
                .show();
    }
}
