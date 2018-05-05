package com.okunan.tunahan.rssrealfinal;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.okunan.tunahan.rssrealfinal.Adapter.FeedAdapter;
import com.okunan.tunahan.rssrealfinal.Common.HTTPDataHandler;
import com.okunan.tunahan.rssrealfinal.Model.RSSObject;

/**
 * Created by TUNAHAN on 05.01.2018.
 */

public class NewsFragment extends Fragment {

    public static final String CATEGORY_ID = "category_id";
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    RSSObject mRSSObject;

    private int mCategoryId;
    private String commonRssLink;


    public static NewsFragment newInstance(int kategoriId) {
        Bundle args = new Bundle();
        NewsFragment newsFragment = new NewsFragment();
        args.putSerializable(CATEGORY_ID, kategoriId);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCategoryId = (int) getArguments().getSerializable(CATEGORY_ID);
        commonRssLink = getCategoryLink();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(getCategoryName()));
        // setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        if (internetCheck()){
            loadRSS();
        }
        else{
            Toast.makeText(getActivity(), R.string.internet_dont_access, Toast.LENGTH_SHORT).show();
        }


        return view;
    }
    public boolean internetCheck() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        return connected;
    }

    private void loadRSS() {

        AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(getActivity());

            @Override
            protected void onPreExecute() {
                mDialog.setMessage(getString(R.string.wait));
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHttpData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                mRSSObject = new Gson().fromJson(s, RSSObject.class);
                FeedAdapter feedAdapter = new FeedAdapter(mRSSObject, getActivity());
                mRecyclerView.setAdapter(feedAdapter);
                feedAdapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(Constant.RSS_TO_JSON_API_COMMON);
        url_get_data.append(commonRssLink);
        loadRSSAsync.execute(url_get_data.toString());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.news_menu, menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:  loadRSS();   return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private int getCategoryName() {

        switch (mCategoryId) {
            case 0:
                return R.string.news_word;
            case 1:
                return R.string.news_business;
            case 2:
                return R.string.news_technology;
            case 3:
                return R.string.news_sport;
            case 4:
                return R.string.news_science;
            case 5:
                return R.string.news_healt;
            case 6:
                return R.string.news_art;
            case 7:
                return R.string.news_travel;
            case 8:
                return R.string.news_auto_mobil;
            case 9:
                return R.string.news_real_estate;
            case 10:
                return R.string.news_style;
            case 11:
                return R.string.news_us;
            default:
                return -1;
        }
    }

    private String getCategoryLink() {
        switch (mCategoryId) {
            case 0:
                return Constant.RSS_WORD_LINK;
            case 1:
                return Constant.RSS_BUSINES_LINK;
            case 2:
                return Constant.RSS_TECNOLOGY_LINK;
            case 3:
                return Constant.RSS_SPORT_LINK;
            case 4:
                return Constant.RSS_SCIENCE_LINK;
            case 5:
                return Constant.RSS_HEALT_LINK;
            case 6:
                return Constant.RSS_ART_LINK;
            case 7:
                return Constant.RSS_TRAVEL_LINK;
            case 8:
                return Constant.RSS_AUTO_MOBIL_LINK;
            case 9:
                return Constant.RSS_REAL_ESTATE_LINK;
            case 10:
                return Constant.RSS_STYLE_LINK;
            case 11:
                return Constant.RSS_US_LINK;
            default:
                return "";
        }
    }


}
