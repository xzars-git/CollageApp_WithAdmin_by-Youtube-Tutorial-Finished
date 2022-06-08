package com.example.collageapp.UI.About;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.collageapp.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_computer, "ABCAda 5", "Indonesia Merdeka 1945"));
        list.add(new BranchModel(R.drawable.ic_mech, "Apa kek", "Satu ditambah satu memang adalah dua"));

        adapter = new BranchAdapter(getContext(), list);
        viewPager = view.findViewById(R.id.aboutviewPager);
        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.collage_image);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/adminpanel-c4498.appspot.com/o/Gallery%2F%5BB%40ef9d3e2jpg?alt=media&token=352307b1-f2a7-42fa-b54b-c546c4ba08cf")
                .into(imageView);

        return view;
    }
}