package com.example.collageapp.UI.Facullty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.collageapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FaculltyFragment extends Fragment {

    private RecyclerView matematika_Departement, sejarah_Departement, bahasa_Departement;
    private LinearLayout bahasa_NoData, matematika_NoData, sejarah_NoData;
    private List<TeacherData> mtk, sejarah, bahasa;
    private DatabaseReference reference, dbRef;
    private TeacherAdapter teacherAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_facullty, container, false);

        matematika_Departement = view.findViewById(R.id.matematika_Departement);
        sejarah_Departement = view.findViewById(R.id.sejarah_Departement);
        bahasa_Departement = view.findViewById(R.id.bahasa_Departement);
        bahasa_NoData = view.findViewById(R.id.bahasa_NoData);
        matematika_NoData = view.findViewById(R.id.matematika_NoData);
        sejarah_NoData = view.findViewById(R.id.sejarah_NoData);

        reference = FirebaseDatabase.getInstance("https://adminpanel-c4498-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Teacher");

        matematika_Departement();
        sejarah_Departement();
        bahasa_Departement();

        return view;
    }

    private void matematika_Departement() {
        dbRef = reference.child("Matematika");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mtk = new ArrayList<>();
                if(!snapshot.exists()){
                    matematika_NoData.setVisibility(View.VISIBLE);
                    matematika_Departement.setVisibility(View.GONE);
                }else {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        mtk.add(data);
                    }
                    matematika_Departement.setHasFixedSize(true);
                    matematika_Departement.setLayoutManager(new LinearLayoutManager(getContext()));
                    teacherAdapter = new TeacherAdapter(mtk, getContext());
                    matematika_Departement.setAdapter(teacherAdapter);
                    matematika_NoData.setVisibility(View.GONE);
                    matematika_Departement.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void bahasa_Departement() {
        dbRef = reference.child("Bahasa Indonesia");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bahasa = new ArrayList<>();
                if(!snapshot.exists()){
                    bahasa_NoData.setVisibility(View.VISIBLE);
                    bahasa_Departement.setVisibility(View.GONE);
                }else {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        bahasa.add(data);
                    }
                    bahasa_Departement.setHasFixedSize(true);
                    bahasa_Departement.setLayoutManager(new LinearLayoutManager(getContext()));
                    teacherAdapter = new TeacherAdapter(bahasa, getContext());
                    bahasa_Departement.setAdapter(teacherAdapter);
                    bahasa_NoData.setVisibility(View.GONE);
                    bahasa_Departement.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sejarah_Departement() {
        dbRef = reference.child("Sejarah");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sejarah = new ArrayList<>();
                if(!snapshot.exists()){
                    sejarah_NoData.setVisibility(View.VISIBLE);
                    sejarah_Departement.setVisibility(View.GONE);
                }else {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        sejarah.add(data);
                    }
                    sejarah_Departement.setHasFixedSize(true);
                    sejarah_Departement.setLayoutManager(new LinearLayoutManager(getContext()));
                    teacherAdapter = new TeacherAdapter(sejarah, getContext());
                    sejarah_Departement.setAdapter(teacherAdapter);
                    sejarah_NoData.setVisibility(View.GONE);
                    sejarah_Departement.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}