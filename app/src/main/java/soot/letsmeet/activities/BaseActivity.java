package soot.letsmeet.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by ggla00 on 2017-05-04.
 */

public abstract class BaseActivity extends AppCompatActivity {

        protected static final String INTENT_EXTRA_ACCOUNT = "extra_account";
        // purpose: if try to open same fragment as is currently display -> do nothing
        protected int activeFragment = -1;
        protected Fragment currentFragment;
        protected Toolbar mToolbar;
        protected TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


        protected void setUpAppBar() {
//            mToolbar = (Toolbar) findViewById(R.id.appToolbar);
//            mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

            setSupportActionBar(mToolbar);
        }

        protected void setTabLayouVisibility(int visibility){
            if (mTabLayout != null){
                if (View.VISIBLE == visibility)
                    mTabLayout.setVisibility(View.VISIBLE);
                if (View.GONE == visibility)
                    mTabLayout.setVisibility(View.GONE);
                if (View.INVISIBLE == visibility)
                    mTabLayout.setVisibility(View.INVISIBLE);
            }
        }

}

