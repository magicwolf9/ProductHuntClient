package ru.magicwolf.producthuntclient.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class LoadBitmap extends AsyncTask<String,Void,Bitmap>
{
    @Override
    protected Bitmap doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            //Передавать заглушку когда картинка не загрузилась
        }
        return null;
    }

}