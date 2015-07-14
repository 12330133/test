package com.example.testview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;

public class InitActivity extends Activity {

	private ViewPager vpager;
	private ImageView iv;
	private ArrayList<View> views;
	private ImageView[] imageviews;
	private ViewGroup vpics;
	private ViewGroup viewpoint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_init);
		
		LayoutInflater inflater = getLayoutInflater();
		views =new ArrayList<View>();
		views.add(inflater.inflate(R.layout.initview00, null));
		views.add(inflater.inflate(R.layout.initview01, null));
		views.add(inflater.inflate(R.layout.initview02,null));
		imageviews = new ImageView[views.size()];
		vpics=(ViewGroup)inflater.inflate(R.layout.activity_init, null);
		vpager = (ViewPager)vpics.findViewById(R.id.initvpager);
		viewpoint = (ViewGroup)vpics.findViewById(R.id.point);
		
		for(int i=0;i<views.size();i++){
			iv=new ImageView(InitActivity.this);
			iv.setLayoutParams(new LayoutParams(20,20));
			iv.setPadding(5, 0, 5, 0);
			imageviews[i]=iv;
			if(i==0){
				imageviews[i].setImageDrawable(getResources().getDrawable(R.drawable.point_focus));
			}else{
				imageviews[i].setImageDrawable(getResources().getDrawable(R.drawable.point));
			}
			viewpoint.addView(imageviews[i]);
		}
		
		setContentView(vpics);
		vpager.setAdapter(new myPagerAdapter());
		vpager.setOnPageChangeListener(new myPageChangeListener());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.init, menu);
		return true;
	}
	
	
	class myPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(View container,int position){
			((ViewPager)container).addView(views.get(position));
			return views.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container,int position,Object object){
			((ViewPager)container).removeView(views.get(position));
		}
		
	}
	
	class myPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<views.size();i++){
				imageviews[i].setImageDrawable(getResources().getDrawable(R.drawable.point_focus));
				if(arg0!=i){
					imageviews[i].setImageDrawable(getResources().getDrawable(R.drawable.point));
				}
			}
		}
		
	}
	
	public void startbutton(View v){
		Intent intent = new Intent();
		intent.setClass(InitActivity.this, LoginActivity.class);
		startActivity(intent);
		InitActivity.this.finish();
		
		
	}

}
