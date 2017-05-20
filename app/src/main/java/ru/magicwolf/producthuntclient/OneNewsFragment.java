package ru.magicwolf.producthuntclient;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class OneNewsFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.news_fragment, container, false);

        ImageView thumbnail = (ImageView) thisView.findViewById(R.id.thumbnail);
        TextView newsNameTV = (TextView) thisView.findViewById(R.id.newsNameTV);
        TextView newsDescriptionTV = (TextView) thisView.findViewById(R.id.newsDescriptionTV);
        TextView upVotesCountTV = (TextView) thisView.findViewById(R.id.upVotesCountTV);

        try {
            DownloadThumbnail downloadThumbnail= new DownloadThumbnail();
            Bitmap btm= downloadThumbnail.execute(getArguments().getString("thumbnailURL")).get();
            thumbnail.setImageBitmap(btm);
        } catch (ExecutionException | InterruptedException e)  {
            e.printStackTrace();
        }
        newsNameTV.setText(getArguments().getString("newsName"));
        newsDescriptionTV.setText(getArguments().getString("newsDescription"));
        upVotesCountTV.setText(getArguments().getString("upVotesCount"));

        return thisView;
    }

    @Override
    public void onClick(View v) {

    }
}

class DownloadThumbnail extends AsyncTask<String,Void,Bitmap>
{
    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]); // params[0] = URL of thumbnail
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e)  {
            e.printStackTrace();
        }
        return null;
    }

}