package com.app.telugunewspaper.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.telugunewspaper.R;
import com.app.telugunewspaper.models.CategoriesModel;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoriesModel> categoriesModelArrayList;

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> reviewList) {
        this.context=context;
        this.categoriesModelArrayList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_model, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {
        CategoriesModel model=categoriesModelArrayList.get(pos);
        viewHolder.tv_catgoryname.setText(model.getCatagoryname());
    }

    @Override
    public int getItemCount() {

        return categoriesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_catgoryname;
        public ViewHolder(View itemView)
        {
            super(itemView);
            tv_catgoryname=(TextView) itemView.findViewById(R.id.tv_catgoryname);
        }
    }
}
