package soot.letsmeet.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import soot.letsmeet.R;
import soot.letsmeet.utils.SnackBarUtils;

/**
 * Created by ggla00 on 2017-05-05.
 */

public class CustomTextImputLayout  extends android.support.v7.widget.AppCompatEditText  {

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
