package soot.letsmeet.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import soot.letsmeet.R;
import soot.letsmeet.databinding.ProgressBinding;

/**
 * Created by Soot on 20/05/2017.
 */

public class ProgressCustomView extends FrameLayout {
    public interface ProgressInterface {
        int STATE_NORMAL = 1, STATE_LOADING = 2;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({STATE_NORMAL, STATE_LOADING})
        @interface ProgresViewState {
        }

        void setProgresViewState(@ProgresViewState int viewState, @Nullable String title, @Nullable Boolean mShowBlur);
    }

    private ProgressInterface mProgressInterface;
    private ProgressBinding mBinding;

    public void setProgressInterface(ProgressInterface progressInterface) {
        mProgressInterface = progressInterface;
    }

    public ProgressCustomView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public ProgressCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.progress, this, true);

        if (attrs == null)
            return;
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressCustomView, 0, 0);

        String mProgressTitle;
        boolean mShowBlur;
        try {
            mProgressTitle = a.getString(R.styleable.ProgressCustomView_progressTitle);
            mShowBlur = a.getBoolean(R.styleable.ProgressCustomView_progressShowBlur, false);
        } finally {
            a.recycle();
        }

        setmProgressTitle(mProgressTitle);
        setmShowBlur(mShowBlur);
    }

    public void setmProgressTitle(String mProgressTitle) {
        if (mProgressTitle == null || mProgressTitle.isEmpty())
            mProgressTitle = "";
        mBinding.progressTitle.setText(mProgressTitle);
    }

    /**
     * Metoda ustawiaj¹ca pojawienie siê czêœciowo przezroczystego t³a
     * @param mShowBlur wartoœæ logiczna
     */
    public void setmShowBlur(@Nullable Boolean mShowBlur) {
        if (mShowBlur == null) {
            mBinding.progressBlur.setVisibility(GONE);
            return;
        }
        mBinding.progressBlur.setVisibility(mShowBlur ? VISIBLE : GONE);
    }

    /**
     * Metoda ustawiaj¹ca widocznoœæ widoku
     * @param isVisible True - widoczny, False - nie widoczny
     */
    public void setProgressVisibe(boolean isVisible) {
        if (isVisible) {
            this.setVisibility(VISIBLE);
        } else {
            this.setVisibility(GONE);
        }
    }
}
