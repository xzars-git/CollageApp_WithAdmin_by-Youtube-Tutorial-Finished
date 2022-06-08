package com.example.collageapp.EBook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.collageapp.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class eBook extends AppCompatActivity {

    private RecyclerView eBook_RecyclerView;
    private DatabaseReference reference;
    private List<eBookData> list;
    private eBookAdapter adapter;
    LinearLayout shimmer_layout;
    ShimmerFrameLayout shimmerFrameLayout;

    public eBook() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("E-Books");
        eBook_RecyclerView = findViewById(R.id.eBook_RecyclerView);
        reference = FirebaseDatabase.getInstance("https://adminpanel-c4498-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("E-Book");
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmer_layout = findViewById(R.id.shimmer_layout);
        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataDnapshot : snapshot.getChildren() ){
                    eBookData data = dataDnapshot.getValue(eBookData.class);
                    list.add(data);
                }

                adapter = new eBookAdapter(eBook.this, list);
                eBook_RecyclerView.setLayoutManager(new LinearLayoutManager(eBook.this));
                eBook_RecyclerView.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmer_layout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(eBook.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }
}