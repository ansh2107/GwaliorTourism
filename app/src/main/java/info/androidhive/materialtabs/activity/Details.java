package info.androidhive.materialtabs.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import info.androidhive.materialtabs.R;

public class Details extends AppCompatActivity {

    public static int buttonIndex;
    public static int pagerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().show();
*/

        setContentView(R.layout.list_element);

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        TextView name = (TextView) findViewById(R.id.textView3);
        TextView description = (TextView) findViewById(R.id.textView4);
        TextView timing = (TextView) findViewById(R.id.textView1);
        TextView t = (TextView) findViewById(R.id.t);
        TextView d = (TextView) findViewById(R.id.d);

        Typeface typeface_1 = Typeface.createFromAsset(this.getAssets(),"KaushanScript.otf");
        Typeface typeface_2 = Typeface.createFromAsset(this.getAssets(),"cinzel.ttf");
   //     Typeface typeface_3 = Typeface.createFromAsset(this.getAssets(),"opensans.ttf");

        name.setTypeface(typeface_2);
        description.setTypeface(typeface_1);
   //     t.setTypeface(typeface_3);
   //     d.setTypeface(typeface_3);

     //   timing.setTypeface(typeface_1);

        buttonIndex = TravelActivity.a;
        pagerIndex = TravelActivity.b;
            if(buttonIndex==1)
            {
              //  imageView.setImageResource(TravelActivity.rest_image.get(pagerIndex));
              //  Picasso.with(this).load(TravelActivity.rest_image(pagerIndex)).resize(120, 60).into(imageView);
                Picasso.with(this).load(TravelActivity.rest_image.get(pagerIndex)).resize(120, 550).into(imageView);
                name.setText(TravelActivity.rest_name.get(pagerIndex));
                description.setText(TravelActivity.rest_overview.get(pagerIndex));
            }
            else if(buttonIndex==2)
            {
                Picasso.with(this).load(TravelActivity.attractions_image.get(pagerIndex)).resize(120, 550).into(imageView);
                name.setText(TravelActivity.attractions_name.get(pagerIndex));
                timing.setText(TravelActivity.attractions_timing.get(pagerIndex));
                description.setText(TravelActivity.attractions_overview.get(pagerIndex));
            }
            else
            {
                Picasso.with(this).load(TravelActivity.hotel_image.get(pagerIndex)).resize(120, 550).into(imageView);
                name.setText(TravelActivity.hotel_name.get(pagerIndex));
                description.setText(TravelActivity.hotel_overview.get(pagerIndex));
            }

    }

}
