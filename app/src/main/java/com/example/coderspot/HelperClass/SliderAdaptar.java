package com.example.coderspot.HelperClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.coderspot.R;

public class SliderAdaptar extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdaptar(Context context) {
        this.context = context;
    }

    int images[] = {

            R.drawable.coderspotwelcom,
            R.drawable.step,
            R.drawable.dream,
            R.drawable.start

    };

    int heading[] = {

            R.string.hello_world,
            R.string.snd_title,
            R.string.thrd_title,
            R.string.forth_title

    };

    int desc[] = {

            R.string.welcome,
            R.string.snd_line,
            R.string.thrd_lin,
            R.string.forth_line

    };


    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        //hookes
        ImageView ImageView = view.findViewById(R.id.slider_image);
        TextView head = view.findViewById(R.id.slider_heading);
        TextView descp = view.findViewById(R.id.slider_desc);

        ImageView.setImageResource(images[position]);
        head.setText(heading[position]);
        descp.setText(desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
