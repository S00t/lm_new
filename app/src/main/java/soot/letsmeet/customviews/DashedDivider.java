package soot.letsmeet.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import soot.letsmeet.R;

/**
 * Created by ggla00 on 2017-05-05.
 */

public class DashedDivider extends android.support.v7.widget.AppCompatImageView {

//    private int sdk;

    public DashedDivider(Context context) {
        super(context);
        init(context, null);
    }

    public DashedDivider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DashedDivider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,@Nullable AttributeSet attrs) {
        if (attrs == null)
            return;


        //TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.DashedDivider, 0, 0);

        try {


        } finally {
            //typedArray.recycle();
        }
//        sdk = android.os.Build.VERSION.SDK_INT;
//        if (sdk >= android.os.Build.VERSION_CODES.HONEYCOMB) {
//            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
//        setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_line_dashed_list_divider));

    }
}