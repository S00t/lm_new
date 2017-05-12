package soot.letsmeet.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public class ViewUtils {


    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    public static void dismissAllDialogs(FragmentManager manager) {
        List<Fragment> fragmentsActive = manager.getFragments();
        if (fragmentsActive == null)
            return;

        for (Fragment fragment : fragmentsActive) {
            if (fragment instanceof AppCompatDialogFragment) {
                AppCompatDialogFragment dialogFragment = (AppCompatDialogFragment) fragment;
                dialogFragment.dismissAllowingStateLoss();
            }

            FragmentManager childFragmentManager = fragment != null ? fragment.getChildFragmentManager() : null;
            if (childFragmentManager != null)
                dismissAllDialogs(childFragmentManager);
        }
    }

}
