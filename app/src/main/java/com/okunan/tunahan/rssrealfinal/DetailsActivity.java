package com.okunan.tunahan.rssrealfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by TUNAHAN on 05.01.2018.
 */

public class DetailsActivity extends SingleFragmentActivity {

    private static final String EXTRA_GUID="com.okunan.tunahan.rssrealfinal.guid";

    public static Intent newIntent(Context context, String guid){
        Intent intent=new Intent(context,DetailsActivity.class);
        intent.putExtra(EXTRA_GUID,guid);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        String guid=(String)getIntent().getSerializableExtra(EXTRA_GUID);
        return DetailsFragment.newInstance(guid);
    }
}
