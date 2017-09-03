package info.androidhive.materialtabs.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.androidhive.materialtabs.R;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    ArrayList<String> name;
    ArrayList<String> image;
    ArrayList<String> timing;
    public static int cPos;

    int[] flag;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, ArrayList<String> name,ArrayList<String> image,ArrayList<String> timing) {
        this.context = context;
        this.name = name;
        this.timing = timing;
        this.image = image;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables

        TextView txtpopulation;
        TextView timings;
        ImageView imgflag;

        //TravelActivity.b = position;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_custom_swip, container,false);

        txtpopulation = (TextView) itemView.findViewById(R.id.population);
        timings = (TextView) itemView.findViewById(R.id.timings);
        imgflag = (ImageView)itemView.findViewById(R.id.img_android);

        if(position==4){
            txtpopulation.setText(" ");
            timings.setText(" ");
            Picasso.with(context).load("http://176.32.230.250/anshuli.com/tourism/download.png").resize(120, 60).into(imgflag);
        }
        else{
            txtpopulation.setText(name.get(position));
            timings.setText(timing.get(position));
            Picasso.with(context).load(image.get(position)).resize(120, 60).into(imgflag);
        }

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        imgflag.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(TravelActivity.b==4){

                Intent intent =new Intent(context,ScrollableTabsActivity.class);
                context.startActivity(intent);
                 }
            else{
                Intent intent =new Intent(context,Details.class);
                context.startActivity(intent);
                }
            }
        });

        return itemView;

    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
