package com.app.telugunewspaper.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aphidmobile.flip.FlipViewController;
import com.app.telugunewspaper.R;
import com.app.telugunewspaper.adapters.CategoriesAdapter;
import com.app.telugunewspaper.adapters.FlipAdapter;
import com.app.telugunewspaper.models.CategoriesModel;
import com.app.telugunewspaper.models.NewsDataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FlipAdapter.OnPageClickListener,View.OnClickListener{

    @BindView(R.id.layout_flipview)
    RelativeLayout layout_flipview;
    @BindView(R.id.layout_categories)
    RelativeLayout layout_categories;
    @BindView(R.id.recyclerview_categories)
    RecyclerView recyclerview_categories;
    @BindView(R.id.layout_comment)
    RelativeLayout layout_comment;
    private FlipViewController flipView;
    private int img_resources[]={R.drawable.image1,R.drawable.image2,R.drawable.image3,
    R.drawable.pokhara,R.drawable.potala_palace,R.drawable.samye_monastery,R.drawable.sera_monastery,
    R.drawable.tashilunpo_monastery,R.drawable.zhangmu_port};
    private ArrayList<NewsDataModel> newsDataModelList=new ArrayList<>();
    private ArrayList<CategoriesModel> categoriesModelArrayList=new ArrayList<>();
    private FlipAdapter mAdapter;
    private CategoriesAdapter categoriesAdapter;
    boolean isdown;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        isdown = true;
        recyclerview_categories.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false));
        flipView = new FlipViewController(this);
        flipView.setAnimationBitmapFormat(Bitmap.Config.RGB_565);
        mAdapter=new FlipAdapter(this,newsDataModelList);
        mAdapter.setActivity(MainActivity.this);
        mAdapter.setOnPageClickListener(this);
        flipView.setAdapter(mAdapter);
        layout_flipview.addView(flipView);
        layout_comment.setOnClickListener(this);

        categoriesAdapter=new CategoriesAdapter(MainActivity.this,categoriesModelArrayList);
        recyclerview_categories.setAdapter(categoriesAdapter);
        setDumnyData();
        onPageClick();
    }

    private void setDumnyData()
    {
        NewsDataModel model;
        for(int i=0;i<img_resources.length;i++)
        {
          model=new NewsDataModel();
          model.setImageid(img_resources[i]);
          model.setTitle("Sample News Number "+i);
          model.setVideoid("z-oi4q-FEtA");
          model.setDescription("Android is an open source and Linux-based operating system for mobile devices such as smartphones and tablet computers. Android was developed by the Open Handset Alliance, led by Google, and other companies.");
          newsDataModelList.add(model);
        }
        mAdapter.notifyDataSetChanged();

        CategoriesModel categoriesModel;
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("Flash news");
        categoriesModelArrayList.add(categoriesModel);
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("Sports");
        categoriesModelArrayList.add(categoriesModel);
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("Cricket");
        categoriesModelArrayList.add(categoriesModel);
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("National");
        categoriesModelArrayList.add(categoriesModel);
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("International");
        categoriesModelArrayList.add(categoriesModel);
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("Politics");
        categoriesModelArrayList.add(categoriesModel);
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("AP");
        categoriesModelArrayList.add(categoriesModel);
        categoriesModel=new CategoriesModel();
        categoriesModel.setCatagoryname("TS");
        categoriesModelArrayList.add(categoriesModel);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flipView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flipView.onPause();
    }

    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                -view.getHeight());                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                -view.getHeight(),                 // fromYDelta
                0); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void onPageClick() {
        if (isdown) {
            slideDown(layout_categories);
        } else {
            slideUp(layout_categories);
        }
        isdown = !isdown;
        if(isdown)
            handler.removeCallbacks(th);
        else
           handler.postDelayed(th,10000);
    }

    Runnable th=new Runnable() {
        @Override
        public void run() {
            onPageClick();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(th);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId())
       {
           case R.id.layout_comment:
               Intent intent=new Intent(MainActivity.this,CommentsActivity.class);
               startActivity(intent);
               break;

       }
    }
}