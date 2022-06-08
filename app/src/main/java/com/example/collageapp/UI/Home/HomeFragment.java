package com.example.collageapp.UI.Home;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.collageapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ImageSlider sliderDenzcoskun;
    private ImageView map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        sliderDenzcoskun = view.findViewById(R.id.sliderDenzcoskun);
        List<SlideModel> list = new ArrayList<>();
        list.add(new SlideModel(R.drawable.img_iklan1, ScaleTypes.CENTER_CROP));
        list.add(new SlideModel(R.drawable.img_iklan2, ScaleTypes.CENTER_CROP));
        list.add(new SlideModel(R.drawable.img_iklan3, ScaleTypes.CENTER_CROP));
        list.add(new SlideModel(R.drawable.img_iklan4, ScaleTypes.CENTER_CROP));

        sliderDenzcoskun.setImageList(list);

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Indonesian Education University, Campus Purwakarta, Jl. Veteran No.8, Nagri Kaler, Purwakarta, Purwakarta Regency, West Java 41115");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}