package ph.coreproc.android.mediaccess_smsreceiver.libs;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.Toast;

/**
 * Created by Kaelito on 6/19/15.
 */
public class UiUtils {

    public static void showAlert(Context context, String title, String message) {

        message = message.replace("\n", "<br>");

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(Html.fromHtml(message));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                return;
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return;
    }

    public static void setActionBarColorDefault(Context context){
        ((AppCompatActivity) context).getSupportActionBar().setElevation(0);
        ((AppCompatActivity) context).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#053A46")));
    }

}
