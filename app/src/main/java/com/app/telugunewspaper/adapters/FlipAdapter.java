package com.app.telugunewspaper.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.telugunewspaper.R;
import com.app.telugunewspaper.activities.MainActivity;
import com.app.telugunewspaper.activities.VideoFragment;
import com.app.telugunewspaper.models.NewsDataModel;
import java.util.List;

public class FlipAdapter extends BaseAdapter {

    private OnPageClickListener onPageClickListener;
    private LayoutInflater inflater;

    private int repeatCount = 1;

    private List<NewsDataModel> newsDataModelList;
    private Context context;
    int width,height;
    private MainActivity activity;
    FragmentTransaction ft;

    public FlipAdapter(Context context, List<NewsDataModel> newsDataModelList) {
        inflater = LayoutInflater.from(context);
        this.newsDataModelList =newsDataModelList;
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        this.width=metrics.widthPixels;
        this.height=metrics.heightPixels;
        this.context=context;
        Log.e("image req width","width= "+width);
        Log.e("image req height","height="+height/2);
    }

    @Override
    public int getCount() {
        return newsDataModelList.size() * repeatCount;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public void setActivity(MainActivity activity)
    {
        this.activity=activity;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.newsmodel, null);
            holder = new ViewHolder();
            holder.iv_image=(ImageView)convertView.findViewById(R.id.iv_image);
            holder.iv_image.getLayoutParams().height=height/2;
            holder.tv_description=(TextView)convertView.findViewById(R.id.tv_description);
            holder.tv_title=(TextView)convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final NewsDataModel data = newsDataModelList.get(position % newsDataModelList.size());
        holder.iv_image.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(context.getResources(),data.getImageid()),width,height/2));
        holder.tv_title.setText(data.getTitle());
        holder.tv_description.setText(data.getDescription());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPageClickListener.onPageClick();
            }
        });

        return convertView;
    }

    public void removeData(int index) {
        if (newsDataModelList.size() > 1) {
            newsDataModelList.remove(index);
        }
    }

    public class ViewHolder
    {
      private TextView tv_title,tv_description;
      private ImageView iv_image;
    }

    public void setOnPageClickListener(OnPageClickListener onPageClickListener)
    {
      this.onPageClickListener=onPageClickListener;
    }
    public interface OnPageClickListener
    {
        void onPageClick();
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
