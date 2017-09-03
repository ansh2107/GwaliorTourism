package info.androidhive.materialtabs.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;

public class TravelActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener   {

    static final int NUM_ITEMS = 6;

    public static ArrayList<String> attractions_name, attractions_timing, attractions_overview, attractions_image,
            hotel_name, hotel_location, hotel_overview,hotel_rate,hotel_image,
            rest_name, rest_overview, rest_location,rest_timing,rest_image;

    JSONParser jParser5 = new JSONParser();
    JSONParser jParser6 = new JSONParser();
    JSONParser jParser7 = new JSONParser();
    private Toolbar toolbar;
    JSONObject json, json1, json2;

    String sn, name, rate, overview, timing, location,img;

    public static int a=1,b=1,x=1;
    ImageButton button;
    ImageButton button1 ;
    ImageButton button2 ;
    ViewPager viewPager;
    PagerAdapter adapter;
    Button map,view_more;
    ViewPagerAdapter ViewPagerAdapter;
    static  Context c,pass;

/*
    Typeface typeface = Typeface.createFromAsset(this.getAssets(),"KaushanScript.otf");
    search.setTypeface(typeface);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pass = TravelActivity.this;
        new attraction().execute();


        button = (ImageButton) findViewById(R.id.imageButton1);
        button1 = (ImageButton) findViewById(R.id.imageButton2);
        button2 = (ImageButton) findViewById(R.id.imageButton3);
        map = (Button) findViewById(R.id.map);
        view_more = (Button)findViewById(R.id.view_more);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        map.setOnClickListener(this);
        view_more.setOnClickListener(this);

        c = TravelActivity.this;
        //  getSupportActionBar().hide();

    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton1:
                button.setBackgroundResource(R.drawable.eat_sel);
                button1.setBackgroundResource(R.drawable.do_unsel);
                button2.setBackgroundResource(R.drawable.loc_unsel);
                a=1;
                b=0;
                c = TravelActivity.this;
                viewPager = (ViewPager) findViewById(R.id.ViewPager);
                ViewPagerAdapter = new ViewPagerAdapter(TravelActivity.this,rest_name,rest_image,rest_timing);
                PageListener pageListener = new PageListener();
                viewPager.setOnPageChangeListener(pageListener);
                viewPager.setAdapter(ViewPagerAdapter);
                break;

            case R.id.imageButton2:

                button.setBackgroundResource(R.drawable.eat_unsel);
                button1.setBackgroundResource(R.drawable.do_sel);
                button2.setBackgroundResource(R.drawable.loc_unsel);
                a=2;
                b=0;
                c = TravelActivity.this;
                viewPager = (ViewPager) findViewById(R.id.ViewPager);
                ViewPagerAdapter = new ViewPagerAdapter(TravelActivity.this, attractions_name, attractions_image,attractions_timing);
                viewPager.setAdapter(ViewPagerAdapter);
                viewPager.addOnPageChangeListener(this);


                break;

            case R.id.imageButton3:
                button.setBackgroundResource(R.drawable.eat_unsel);
                button1.setBackgroundResource(R.drawable.do_unsel);
                button2.setBackgroundResource(R.drawable.loc_sel);
                a=3;
                b=0;
                c = TravelActivity.this;
                viewPager = (ViewPager) findViewById(R.id.ViewPager);
                ViewPagerAdapter = new ViewPagerAdapter(TravelActivity.this, hotel_name,hotel_image,hotel_rate);

                PageListener pageListener2 = new PageListener();
                viewPager.setOnPageChangeListener(pageListener2);
                viewPager.setAdapter(ViewPagerAdapter);
                break;

            case R.id.map:
                Intent maps =new Intent(this,MapsActivity.class);
                startActivity(maps);
                break;

            case R.id.view_more:
                Intent view = new Intent(this,ScrollableTabsActivity.class);
                startActivity(view);
        }



    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        x=position;
    }

    @Override
    public void onPageSelected(int position) {

        b=position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class PageListener extends ViewPager.SimpleOnPageChangeListener {
        public void onPageSelected(int position) {
            int x = position;

        }
    }

    private class attraction extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(TravelActivity.this);

        /** progress dialog to show user that the backup is processing. */
        /**
         * application context.
         */

        protected void onPreExecute() {

            this.dialog.setMessage("Logging in...");
            this.dialog.show();
        }


        @Override
        protected String doInBackground(String... urls) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            /*params.add(new BasicNameValuePair("roll_number", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("port", port));*/
            // params.add(new BasicNameValuePair("ip", "1"));
            String log = "http://176.32.230.250/anshuli.com/tourism/attractions.php";


            json = jParser5.makeHttpRequest(log, "POST", params);


            //visible
            return null;


        }


        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {  //gone
            // //System.out.println("RESULT : " + result);


            try {
                // Checking for SUCCESS TAG


                attractions_name = new ArrayList<String>();
                attractions_overview = new ArrayList<String>();
                attractions_timing = new ArrayList<String>();
                attractions_image = new ArrayList<String>();
                //Toast.makeText(MainActivity_Reciever.this, (CharSequence) json, 1).show();

                JSONArray account = json.getJSONArray("attract");
                for (int i = 0; i < account.length(); i++) {
                        json = account.getJSONObject(i);

                        overview = json.getString("OVERVIEW");
                        timing = json.getString("TIMINGS");
                        img = json.getString("IMAGE");
                        //sn = json.getString("SN");
                        name = json.getString("NAME");

                        attractions_overview.add(overview);
                        attractions_timing.add(timing);

                        attractions_name.add(name);
                        attractions_image.add(img);
                  }


                    //  pref.setBatch(studClass);
       /*         attractions_image.add("View Moreee");
                attractions_image.add("http://176.32.230.250/anshuli.com/tourism/download.png");

*/
                this.dialog.dismiss();
                new hotel().execute();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Network connection Error!!!", Toast.LENGTH_LONG).show();
            }


        }
    }


    private class rest extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(TravelActivity.this);

        protected void onPreExecute() {
            this.dialog.setMessage("resting" +
                    "" +
                    "" +
                    " in...");
            this.dialog.show();

        }


        @Override
        protected String doInBackground(String... urls) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
           String log = "http://176.32.230.250/anshuli.com/tourism/restaurants.php";


            json2 = jParser7.makeHttpRequest(log, "POST", params);


            //visible
            return null;


        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {  //gone
            // //System.out.println("RESULT : " + result);


            try {
                // Checking for SUCCESS TAG


                rest_name = new ArrayList<String>();
                rest_overview = new ArrayList<String>();
                rest_location = new ArrayList<String>();
                rest_timing = new ArrayList<String>();
                rest_image = new ArrayList<String>();

                JSONArray account = json2.getJSONArray("res");
                for (int i = 0; i < account.length(); i++) {
                    json2 = account.getJSONObject(i);


                    overview = json2.getString("OVERVIEW");
                    location = json2.getString("LOCATION");
                    img = json2.getString("IMAGE");
                    name = json2.getString("NAME");
                    timing = json2.getString("TIMINGS");
                    rest_name.add(name);
                    rest_overview.add(overview);
                    rest_location.add(location);
                    rest_timing.add(timing);
                    rest_image.add(img);

                }/*
                rest_image.add("View More");
                rest_image.add("http://176.32.230.250/anshuli.com/tourism/download.png");
*/
                this.dialog.dismiss();


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Network connection Error!!!", Toast.LENGTH_LONG).show();
            }


        }
    }


    private class hotel extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(TravelActivity.this);

        /** progress dialog to show user that the backup is processing. */
        /**
         * application context.
         */

        protected void onPreExecute() {
            this.dialog.setMessage("hotelling in...");
            this.dialog.show();

        }


        @Override
        protected String doInBackground(String... urls) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            /*params.add(new BasicNameValuePair("roll_number", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("port", port));*/
            // params.add(new BasicNameValuePair("ip", "1"));
            String log = "http://176.32.230.250/anshuli.com/tourism/hotels.php";


            json1 = jParser6.makeHttpRequest(log, "POST", params);


            //visible
            return null;


        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {  //gone
            // //System.out.println("RESULT : " + result);


            try {
                // Checking for SUCCESS TAG


                hotel_name = new ArrayList<String>();
                hotel_overview = new ArrayList<String>();
                hotel_location = new ArrayList<String>();
                hotel_rate = new ArrayList<String>();
                hotel_image = new ArrayList<String>();
                //Toast.makeText(MainActivity_Reciever.this, (CharSequence) json, 1).show();

                JSONArray account = json1.getJSONArray("hotels");
                for (int i = 0; i < account.length(); i++) {
                    json1 = account.getJSONObject(i);


                    overview = json1.getString("OVERVIEW");
                    location = json1.getString("LOCATION");
                    rate = json1.getString("RATE");
                    img = json1.getString("IMAGE");
                    //sn = json.getString("SN");
                    name = json1.getString("NAME");
                    hotel_name.add(name);
                    hotel_overview.add(overview);
                    hotel_location.add(location);
                    hotel_rate.add(rate);
                    hotel_image.add(img);

                    ;                             //  pref.setBatch(studClass);

                }

/*

                hotel_name.add("View More");
                hotel_image.add("http://176.32.230.250/anshuli.com/tourism/download.png");
*/

                this.dialog.dismiss();
                new rest().execute();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Network connection Error!!!", Toast.LENGTH_LONG).show();
            }

            button1.performClick();
        }

    }


}
