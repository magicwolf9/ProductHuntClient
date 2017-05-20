package ru.magicwolf.producthuntclient;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import ru.magicwolf.producthuntclient.POJOs.Category;
import ru.magicwolf.producthuntclient.POJOs.Post;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SetMenu(navigationView);
    }
    void SetMenu(NavigationView navigationView){
        Menu menu = navigationView.getMenu();
        List<Category> categories = new RequestToAPI().LoadCategoriesList(this.getResources().getString(R.string.acсess_token));

        for(int i = 0; i < categories.size(); i++){ //может буть null
            Log.i("INFO", "ItemId "+ i + "= " + categories.get(i).id);
            menu.add(1, categories.get(i).id, i, categories.get(i).name);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String name = item.getTitle().toString().toLowerCase();
        Log.i("INFO", "ItemId = " + name);
                RequestToAPI requestToAPI = new RequestToAPI();
        List<Post> postList = requestToAPI.LoadPostsInCategory(this.getResources().getString(R.string.acсess_token), name);

        LinearLayout fContainer = (LinearLayout) findViewById(R.id.newsContainer);
        if(fContainer.getChildCount() > 0) fContainer.removeAllViews();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for(int i = 0; i < postList.size(); i++) {
            //Log.i("INFO", "Post number" + i +" =  "+ postList.get(i).thumbnail.imageUrl + "   catId =" + postList.get(i).categoryId + "    itemId = " + name); // Дописать ловлю ошибки если в категории нет постов
            OneNewsFragment oneNews = new OneNewsFragment();
            Bundle toFragmentBundle = new Bundle();
            toFragmentBundle.putString("thumbnailURL", postList.get(i).thumbnail.imageUrl);
            toFragmentBundle.putString("newsName", postList.get(i).name);
            toFragmentBundle.putString("newsDescription", postList.get(i).tagline);
            toFragmentBundle.putString("upVotesCount", postList.get(i).votesCount.toString());
            oneNews.setArguments(toFragmentBundle);
            fragmentTransaction.add(R.id.newsContainer, oneNews);
        }
        fragmentTransaction.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
