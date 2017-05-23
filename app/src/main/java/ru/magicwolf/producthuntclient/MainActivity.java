package ru.magicwolf.producthuntclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.magicwolf.producthuntclient.AsyncTasks.LoadBitmap;
import ru.magicwolf.producthuntclient.POJOs.Category;
import ru.magicwolf.producthuntclient.POJOs.Post;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RelativeLayout newsInfo;
    LinearLayout postListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        postListView = (LinearLayout) findViewById(R.id.newsContainer); // контейнер для списка продуктов
        newsInfo = (RelativeLayout) findViewById(R.id.newsinfo); // страница продукта

        newsInfo.setVisibility(View.GONE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SetMenu(navigationView);
    }

    @Override
    public void onPause(){
        super.onPause();

    }

    void SetMenu(NavigationView navigationView){
        Menu menu = navigationView.getMenu();
        List<Category> categories = new RequestToAPI().LoadCategoriesList(this.getResources().getString(R.string.acсess_token));

        if(categories != null) {
            for (int i = 0; i < categories.size(); i++) { //может буть null
                Log.i("INFO", "ItemId " + i + "= " + categories.get(i).id);
                Category category = categories.get(i);
                menu.add(1, category.id, i, category.name);
            }
            onNavigationItemSelected(menu.findItem(1));
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if(this.findViewById(R.id.drawer_layout).isActivated()) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
            }
        } catch(NullPointerException e){
            e.printStackTrace();

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        TextView categoryNameTV = (TextView) findViewById(R.id.nameTV);
        categoryNameTV.setText(item.getTitle());

        showPosts(item);

        final MenuItem _item = item;
        final SwipeRefreshLayout forPullToRefresh = (SwipeRefreshLayout) findViewById(R.id.forPullToRefresh);
        forPullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("INFO", "we are there");
                showPosts(_item);
                forPullToRefresh.setRefreshing(false);
            }
        });

        return true;
    }

    void showPosts(MenuItem item){
        Log.i("INFO", "we are there2");
        showListPosts();

        String name = item.getTitle().toString().toLowerCase();
        Log.i("INFO", "ItemId = " + name);
        RequestToAPI requestToAPI = new RequestToAPI();
        List<Post> postList = requestToAPI.LoadPostsInCategory(this.getResources().getString(R.string.acсess_token), name);

        LinearLayout fContainer = (LinearLayout) findViewById(R.id.newsContainer);
        if(fContainer.getChildCount() > 0) fContainer.removeAllViews();

        CreatePostsFragments create = new CreatePostsFragments();
        create.execute(getFragmentManager(), postList);
    }

    void showAboutOnePost(final Bundle args){
        Log.i("INFO", "method1");
        //View postListView = findViewById(R.id.newsContainer);
        postListView.setVisibility(View.GONE);
        //View newsInfo = findViewById(R.id.newsinfo);

        TextView nameTV =(TextView) newsInfo.findViewById(R.id.newsNameTV);
        TextView newsDescriptionTV =(TextView) newsInfo.findViewById(R.id.newsDescriptionTV);
        TextView upVotesCountTV =(TextView) newsInfo.findViewById(R.id.upVotesCountTV);

        Button btnBack =(Button) newsInfo.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "click");
                showListPosts();
            }
        });

        Button btnGetIt =(Button) newsInfo.findViewById(R.id.btnGetIt);
        final String url = args.getString("discussionURL");
        btnGetIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView webview = new WebView(getApplicationContext()) {
                    @Override
                    public boolean onKeyDown(int keyCode, KeyEvent event) {
                        if ((keyCode == KeyEvent.KEYCODE_BACK) ) { // Ловля нажатия кнопки назад
                            finish();
                            return true;
                        }

                        return super.onKeyDown(keyCode, event);
                    }
                };
                setContentView(webview);
                webview.loadUrl(url);
            }
        });

        nameTV.setText(args.getString("newsName"));
        newsDescriptionTV.setText(args.getString("newsDescription"));
        upVotesCountTV.setText(args.getString("upVotesCount"));


        newsInfo.setVisibility(View.VISIBLE);

        for(int i = 0; i < newsInfo.getChildCount(); i++){
            newsInfo.getChildAt(0).setVisibility(View.VISIBLE);
        }


        ImageView screenshotPlace = (ImageView) newsInfo.findViewById(R.id.newsScreenshot);
        LoadBitmap loadBitmap = new LoadBitmap();
        try {
            screenshotPlace.setImageBitmap(loadBitmap.execute(args.getString("screenshotURL")).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //Ставить заглушку когда картинка не загрузилась
        }


    }

    void showListPosts(){

        Log.i("INFO", "method2");
        //View newsInfo = findViewById(R.id.newsinfo);
        newsInfo.setVisibility(View.GONE);
        //View postListView = findViewById(R.id.newsContainer);
        postListView.setVisibility(View.VISIBLE);

        for(int i = 0; i < postListView.getChildCount(); i++){
            postListView.getChildAt(0).setVisibility(View.VISIBLE);
        }
    }
}

class CreatePostsFragments extends AsyncTask<Object,Void,Void>
{
    @Override
    protected Void doInBackground(Object... params) {
        android.app.FragmentManager fragmentManager = (android.app.FragmentManager) params[0];
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        List<Post> postList = (List<Post>) params[1];
        OneNewsFragment oneNews;
        Post post;
        Bundle toFragmentBundle;
        for(int i = 0; i < postList.size(); i++) {
            //Log.i("INFO", "Post number" + i +" =  "+ postList.get(i).thumbnail.imageUrl + "   catId =" + postList.get(i).categoryId + "    itemId = " + name); // Дописать ловлю ошибки если в категории нет постов
            post = postList.get(i);
            oneNews = new OneNewsFragment();
            toFragmentBundle = new Bundle();

            try {
                URL url = new URL(post.thumbnail.imageUrl);
                Bitmap btm =  BitmapFactory.decodeStream(url.openConnection().getInputStream());
                toFragmentBundle.putParcelable("thumbnailBitmap", btm);
            } catch (IOException e) {
                e.printStackTrace();
                //Передавать заглушку когда картинка не загрузилась
            }

            toFragmentBundle.putString("newsName", post.name);
            toFragmentBundle.putString("newsDescription", post.tagline);
            toFragmentBundle.putString("upVotesCount", post.votesCount.toString());
            toFragmentBundle.putString("discussionURL", post.discussionUrl);
            toFragmentBundle.putString("screenshotURL", post.screenshotUrl._850px);

            oneNews.setArguments(toFragmentBundle);
            fragmentTransaction.add(R.id.newsContainer, oneNews);

        }
        fragmentTransaction.commit();
        return null;
    }

}