package com.okunan.tunahan.rssrealfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.okunan.tunahan.rssrealfinal.Model.RSSObject;
import com.squareup.picasso.Picasso;


/**
 * Created by TUNAHAN on 05.01.2018.
 */

public class DetailsFragment extends Fragment {

    private static final String GUID = "guid";

    public static DetailsFragment newInstance(String guid) {
        Bundle args = new Bundle();
        DetailsFragment detailsFragment = new DetailsFragment();
        args.putSerializable(GUID, guid);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String guid = (String) getArguments().getSerializable(GUID);
        RSSObject.myItem = RSSObject.getItem(guid);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        TextView titleTextView = (TextView) view.findViewById(R.id.details_title_text_view);
        TextView contentTextView = (TextView) view.findViewById(R.id.details_content_text_view);
        TextView dateTextView = (TextView) view.findViewById(R.id.details_date_text_view);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.details_description_text_view);
        TextView authorTextView = (TextView) view.findViewById(R.id.details_author_text_view);
        ImageView contentImageView=(ImageView) view.findViewById(R.id.details_image_view);

        //{link=https://static01.nyt.com/images/2018/01/09/science/09SCI-ZIMMER1/09SCI-ZIMMER1-moth.jpg}

        titleTextView.setText(RSSObject.myItem.getTitle());
        contentTextView.setText(RSSObject.myItem.getContent());
        dateTextView.setText(RSSObject.myItem.getPubDate());
        descriptionTextView.setText(RSSObject.myItem.getDescription());
        authorTextView.setText(RSSObject.myItem.getAuthor());
        String gelenLink = RSSObject.myItem.getEnclosure().toString();
        if (gelenLink.contains("https://")) {
            String linkDonustur[] = gelenLink.split("\\{link=");
            loadImageFromUrl(contentImageView,linkDonustur[1].toString().substring(0,linkDonustur[1].length()-1));
        }
        else
        {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_name);
            contentImageView.setImageBitmap(bm);

        }
        return view;
    }


    private void loadImageFromUrl(ImageView imageView,String url) {
        Picasso.with(getActivity()).load(url).placeholder(R.mipmap.ic_wait_image)
                .error(R.mipmap.ic_wait_image)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
