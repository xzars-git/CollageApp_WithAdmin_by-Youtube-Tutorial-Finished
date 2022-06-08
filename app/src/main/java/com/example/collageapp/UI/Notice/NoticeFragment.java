package com.example.collageapp.UI.Notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collageapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {


    private RecyclerView recyclerView_DeleteNotice;
    private ProgressBar progress_Bar;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;

    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notice, container, false);
        recyclerView_DeleteNotice = view.findViewById(R.id.recyclerView_DeleteNotice);
        progress_Bar = view.findViewById(R.id.progress_Bar);
        reference = FirebaseDatabase.getInstance("https://adminpanel-c4498-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Notice");
        recyclerView_DeleteNotice.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_DeleteNotice.setHasFixedSize(true);
        getNotice();
        return view;
    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshotData : snapshot.getChildren()){
                    NoticeData data = snapshotData.getValue(NoticeData.class);
                    list.add(0, data);
                }

                adapter = new NoticeAdapter(getContext(), list);
                adapter.notifyDataSetChanged();
                progress_Bar.setVisibility(View.GONE);
                recyclerView_DeleteNotice.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}