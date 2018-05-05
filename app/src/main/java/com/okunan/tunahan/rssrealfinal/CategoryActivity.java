package com.okunan.tunahan.rssrealfinal;

import android.support.v4.app.Fragment;

/**
 * Created by TUNAHAN on 05.01.2018.
 */

public class CategoryActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return CategoryFragment.newInstance();
    }
}
