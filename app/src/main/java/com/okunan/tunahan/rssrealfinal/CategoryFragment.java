package com.okunan.tunahan.rssrealfinal;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CategoryFragment extends Fragment implements View.OnClickListener {

    Button worldButton;
    Button businessButton;
    Button tecnologyButton;
    Button sportButton;
    Button scienceButton;
    Button healtButton;
    Button artButton;
    Button travelButton;
    Button carButton;
    Button realEstateButton;
    Button styleButton;
    Button usButton;


    public static CategoryFragment newInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        worldButton = (Button) view.findViewById(R.id.word_button);
        businessButton = (Button) view.findViewById(R.id.business_button);
        tecnologyButton = (Button) view.findViewById(R.id.tecnology_button);
        sportButton = (Button) view.findViewById(R.id.sport_button);
        scienceButton = (Button) view.findViewById(R.id.science_button);
        healtButton = (Button) view.findViewById(R.id.healt_button);
        artButton = (Button) view.findViewById(R.id.art_button);
        carButton = (Button) view.findViewById(R.id.auto_mobil_button);
        travelButton = (Button) view.findViewById(R.id.travel_button);
        realEstateButton = (Button) view.findViewById(R.id.real_estate_button);
        styleButton = (Button) view.findViewById(R.id.style_button);
        usButton = (Button) view.findViewById(R.id.us_button);

        worldButton.setOnClickListener(this);
        businessButton.setOnClickListener(this);
        tecnologyButton.setOnClickListener(this);
        sportButton.setOnClickListener(this);
        scienceButton.setOnClickListener(this);
        healtButton.setOnClickListener(this);
        artButton.setOnClickListener(this);
        travelButton.setOnClickListener(this);
        carButton.setOnClickListener(this);
        realEstateButton.setOnClickListener(this);
        styleButton.setOnClickListener(this);
        usButton.setOnClickListener(this);
        internetCheck();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.word_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.WORD);
                startActivity(intent);
            }
            break;
            case R.id.business_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.BUSINESS);
                startActivity(intent);
            }
            break;
            case R.id.tecnology_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.TECNOLOGY);
                startActivity(intent);
            }
            break;
            case R.id.sport_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.SPORT);
                startActivity(intent);
            }
            break;
            case R.id.science_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.SCIENCE);
                startActivity(intent);
            }
            break;
            case R.id.healt_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.HEALTH);
                startActivity(intent);
            }
            break;
            case R.id.art_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.ART);
                startActivity(intent);
            }
            break;
            case R.id.travel_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.TRAVEL);
                startActivity(intent);
            }
            break;
            case R.id.auto_mobil_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.AUTO_MOBIL);
                startActivity(intent);
            }
            break;
            case R.id.real_estate_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.REAL_ESTATE);
                startActivity(intent);
            }
            break;
            case R.id.style_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.STYLE);
                startActivity(intent);
            }
            break;
            case R.id.us_button: {
                Intent intent = NewsActivity.newIntent(getActivity(), Constant.US);
                startActivity(intent);
            }
            break;

        }
    }

    public void internetCheck() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        if (connected==true){
            Toast.makeText(getActivity(), R.string.internet_access, Toast.LENGTH_SHORT).show();
            setEnableButton();
        }
        else{
            Toast.makeText(getActivity(), R.string.internet_dont_access, Toast.LENGTH_SHORT).show();
            setDisableButton();
        }
    }
    private void setDisableButton(){
        worldButton.setEnabled(false);
        businessButton.setEnabled(false);
        tecnologyButton.setEnabled(false);
        sportButton.setEnabled(false);
        scienceButton.setEnabled(false);
        healtButton.setEnabled(false);
        artButton.setEnabled(false);
        travelButton.setEnabled(false);
        carButton.setEnabled(false);
        realEstateButton.setEnabled(false);
        styleButton.setEnabled(false);
        usButton.setEnabled(false);
    }
    private void setEnableButton(){
        worldButton.setEnabled(true);
        businessButton.setEnabled(true);
        tecnologyButton.setEnabled(true);
        sportButton.setEnabled(true);
        scienceButton.setEnabled(true);
        healtButton.setEnabled(true);
        artButton.setEnabled(true);
        travelButton.setEnabled(true);
        carButton.setEnabled(true);
        realEstateButton.setEnabled(true);
        styleButton.setEnabled(true);
        usButton.setEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.category_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:  {
                internetCheck();

            }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
