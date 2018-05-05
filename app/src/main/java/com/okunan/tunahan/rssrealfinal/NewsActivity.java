package com.okunan.tunahan.rssrealfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsActivity extends SingleFragmentActivity {

    public static final String EXTRA_CATEGORY_ID="com.okunan.tunahan.rssrealfinal";

    public static Intent newIntent(Context packageContext,int kategoriId){
        Intent intent=new Intent(packageContext,NewsActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,kategoriId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        int kategoriId=(int)getIntent().getSerializableExtra(EXTRA_CATEGORY_ID);
        return NewsFragment.newInstance(kategoriId);
    }
}
