package ru.magicwolf.producthuntclient;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class OneNewsFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.news_fragment, container, false);

        ImageView thumbnail = (ImageView) thisView.findViewById(R.id.thumbnail);
        TextView newsNameTV = (TextView) thisView.findViewById(R.id.newsNameTV);
        TextView newsDescriptionTV = (TextView) thisView.findViewById(R.id.newsDescriptionTV);
        TextView upVotesCountTV = (TextView) thisView.findViewById(R.id.upVotesCountTV);

        Bundle arguments = getArguments();
        thumbnail.setImageBitmap((Bitmap) arguments.getParcelable("thumbnailBitmap"));

        newsNameTV.setText(arguments.getString("newsName"));
        newsDescriptionTV.setText(arguments.getString("newsDescription"));
        upVotesCountTV.setText(arguments.getString("upVotesCount"));

        thisView.setOnClickListener(this);
        return thisView;
    }

    @Override
    public void onClick(View v) {
        Log.i("INFO", "click");
        ((MainActivity) getActivity()).showAboutOnePost(getArguments());
    }
}
