package com.analytics5paisa.courses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerView> {

    private List<Integer> images;
    private List<String> desc;
    private Context context;

    public ViewPagerAdapter(List<Integer> images, List<String> desc, Context context) {
        this.images = images;
        this.desc = desc;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewPagerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.welcomelayout,parent,false);

        return new ViewPagerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerView holder, int position) {
        holder.imageView.setImageResource(images.get(position));
        holder.textView.setText(desc.get(position));

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewPagerView extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewPagerView(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.welcomeImage);
            textView=itemView.findViewById(R.id.welcomeText);
        }
    }
}
