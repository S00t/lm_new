package soot.letsmeet.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import soot.letsmeet.R;

import static android.support.v4.view.ViewCompat.setBackground;

/**
 * Created by ggla00 on 2017-05-08.
 */

public class SnackBarUtils {

    public static Snackbar makeSnackBar(Context context, View parent, String message){
        Snackbar snackbar = createEmptySnackBar(context, parent, false);
        snackbar.setText(message);

        return snackbar;
    }
    public static Snackbar makeSnackBarWithAction(Context context, View parent, String message, String actionMessage, View.OnClickListener listener){
        Snackbar snackbar = createEmptySnackBar(context, parent, true);
        snackbar.setText(message);

        snackbar.setAction(actionMessage, listener);

        return snackbar;
    }
    private static Snackbar createEmptySnackBar(Context context, View parent, boolean action){
        int textColor = context.getResources().getColor(R.color.white);
        Snackbar snackbar = Snackbar.make(parent, "", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();


        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        TextView snackBarMessage = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        snackBarMessage.setTextColor(textColor);

        // customize action button
        if (action){
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);
            setBackground(textView, context.getResources().getDrawable(R.drawable.round_button_green));
        }

        return snackbar;
    }
}
