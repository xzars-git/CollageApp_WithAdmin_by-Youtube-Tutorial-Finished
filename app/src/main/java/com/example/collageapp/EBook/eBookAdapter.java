package com.example.collageapp.EBook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collageapp.R;

import java.util.List;

public class eBookAdapter extends RecyclerView.Adapter<eBookAdapter.EbookViewHolder> {

    private Context context;
    private List<eBookData> list;

    public eBookAdapter(Context context, List<eBookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout, parent, false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, int position) {

        holder.eBook_Name.setText(list.get(holder.getAdapterPosition()).getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PDFViewer.class);
                intent.putExtra("pdfUrl", list.get(holder.getAdapterPosition()).getPdfUrl());
                context.startActivity(intent);
            }
        });

        holder.eBook_DownloadUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(holder.getAdapterPosition()).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private ImageView eBook_DownloadUrl;
        private AppCompatTextView eBook_Name;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);

            eBook_Name = itemView.findViewById(R.id.eBook_PDFName);
            eBook_DownloadUrl = itemView.findViewById(R.id.eBook_DownloadUrl);
        }
    }
}
