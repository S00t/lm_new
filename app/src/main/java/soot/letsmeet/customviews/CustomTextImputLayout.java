package soot.letsmeet.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.annotation.StyleableRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import soot.letsmeet.R;
import soot.letsmeet.utils.SnackBarUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

/**
 * Created by ggla00 on 2017-05-05.
 */

public class CustomTextImputLayout  extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener {

    private int mMaxLines = 1;
    private int mMaxCharacters = 51;



    public CustomTextImputLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextImputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextImputLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        // change font
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Dosis-Light.ttf");

        setTypeface(tf);
        setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void setOnFocusChangeListener(){
        setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
            InputMethodManager imm =  (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        TextWatcher watcher = new TextWatcher() {

            private String text;
            private int beforeCursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                text = s.toString();
                beforeCursorPosition = start;
            }

            @Override
            public void afterTextChanged(Editable s) {

            /* turning off listener */
                removeTextChangedListener(this);

            /* handling lines limit exceed */
                if (CustomTextImputLayout.this.getLineCount() > mMaxLines) {
                    CustomTextImputLayout.this.setText(text);
                    CustomTextImputLayout.this.setSelection(beforeCursorPosition);
                }

            /* handling character limit exceed */
                if (s.toString().length() > mMaxCharacters) {
                    CustomTextImputLayout.this.setText(text);
                    CustomTextImputLayout.this.setSelection(beforeCursorPosition);
                    SnackBarUtils.makeSnackBar(
                            CustomTextImputLayout.this.getContext(),
                            getRootView(),
                            CustomTextImputLayout.this.getContext().getString(R.string.error_max_characters_reached) + mMaxCharacters);
                }

            /* turning on listener */
                addTextChangedListener(this);

            }
        };

        this.addTextChangedListener(watcher);
    }
}
