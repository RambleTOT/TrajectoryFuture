package com.example.trajectoryfuture.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trajectoryfuture.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ItemViewHolder> {

    private Context context;
    private ArrayList<ItemModel> itemModels;
    private MyOnItemClickListener itemClickListener;

    public interface MyOnItemClickListener{
        void onItemClick(int position);
    }

    public AdapterItem(Context context, ArrayList<ItemModel> itemModels) {
        this.context = context;
        this.itemModels = itemModels;
    }

    public void setItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_services, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel itemModel = itemModels.get(position);

        String urlIcon = itemModel.getIconItem();
        String textItem = itemModel.getTextItem();

        holder.textItemAdapter.setText(textItem);
        Picasso.with(context).load(urlIcon).fit().centerInside().into(holder.iconItemAdapter);
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView iconItemAdapter;
        public TextView textItemAdapter;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iconItemAdapter = itemView.findViewById(R.id.icon_item);
            textItemAdapter = itemView.findViewById(R.id.text_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            itemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
