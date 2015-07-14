package com.example.testview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.User;
import com.example.testview.R.color;
import com.example.view.BorrowView;
import com.example.view.ImformationView;
import com.example.view.SearchView;

public class TestActivity extends Activity {

	Context context = null;
	private ImageView cursor;
	private ViewPager mpager;
	private List<View> listview;
	private TextView t1,t2,t3;
	private int offset = 0;
	private int currIndex = 0;
	private int bmpW;
	private User user=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	Bundle bundle = getIntent().getBundleExtra("boo");
    	user = (User)bundle.getSerializable("the_user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //getUser();
        context = TestActivity.this;
		//System.out.println("<________________________>"+user.getName());
        InitImageView();
		InitTextView();
		InitViewPager();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }
    
    private void InitTextView(){
    	t1 = (TextView)findViewById(R.id.text1);
    	t2 = (TextView)findViewById(R.id.text2);
    	t3 = (TextView)findViewById(R.id.text3);
    	
    	t1.setOnClickListener(new MyOnClickListener(0));
    	t2.setOnClickListener(new MyOnClickListener(1));
    	t3.setOnClickListener(new MyOnClickListener(2));
    }
    
   

    private void InitViewPager(){
    	mpager = (ViewPager) findViewById(R.id.vpager);
		listview = new ArrayList<View>();
		SearchView _searchView = new SearchView(this);
		_searchView.getUser(user);
		_searchView.initView();
		
		listview.add(_searchView.getView());
		BorrowView _borrowView = new BorrowView(this);
		_borrowView.getUser(user);
		_borrowView.initView();
		listview.add(_borrowView.getView());
		ImformationView _imformationView = new ImformationView(this);
		_imformationView.getUser(user);
		_imformationView.initView();
		
		listview.add(_imformationView.getView());
		mpager.setAdapter(new MypagerAdapter(listview));
		mpager.setCurrentItem(0);
		mpager.setOnPageChangeListener(new MyOnPagerChangeListener());
		TextView t1=(TextView) findViewById(R.id.text1);
		t1.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color_in));
		t1.setTextColor(Color.WHITE);
		
    }
    
    private void InitImageView(){
    	cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / 3 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);
    }
    
    public class MypagerAdapter extends PagerAdapter{

    	public List<View> mlistView;
    	
    	public MypagerAdapter(List<View> listview_){
    		this.mlistView = listview_;
    	}
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
	//		System.out.println("SIZE =  "+ mlistView.size());
			return mlistView.size();
			
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
	//		System.out.println("ISVIEWFROMOBJECT");
			return arg0 == arg1;
		}
    	@Override
    	public void destroyItem(ViewGroup container, int position,
                Object object) {
            ((ViewPager) container).removeView(mlistView.get(position));
        }
		
		public void finishUpdate(View arg0){
			
		}
		
		public void restoreState(Parcelable arg0,ClassLoader arg1){
			
		}
		
		public Object instantiateItem(View arg0,int arg1){
	//		System.out.println("instantiateITEM    -----"+arg1);
			((ViewPager) arg0).addView(mlistView.get(arg1),0);
			return mlistView.get(arg1);
		}
		
		public void startUpdate(View arg0){
			
		}
		
    }
    
    public class MyOnClickListener implements View.OnClickListener{

    	private int index = 0;
    	
    	public MyOnClickListener(int i){
    		index = i;
    	}
    	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mpager.setCurrentItem(index);
	//		System.out.println("ONCLICKING --- INDEX "+index);
		}
    	
    }
    
    public class MyOnPagerChangeListener implements OnPageChangeListener{

    	
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
			int one = offset * 2+bmpW;
	    	int two = one* 2;
	    	
	    	TextView m_t1 = null;
	    	TextView m_t2 = null;
	    	TextView m_t3 = null;
	    	
	    	m_t1=(TextView) findViewById(R.id.text1);
			m_t2=(TextView) findViewById(R.id.text2);
			m_t3=(TextView) findViewById(R.id.text3);
			
	    	
			// TODO Auto-generated method stub
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;
			}
	//		Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			cursor.startAnimation(animation);
			switch(arg0){
			case 0:
				m_t1.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color_in));
				m_t1.setTextColor(Color.WHITE);
				m_t2.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color));
				m_t2.setTextColor(color.text_color);
				m_t3.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color));
				m_t3.setTextColor(color.text_color);
				break;
			case 1:
				m_t1.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color));
				m_t1.setTextColor(color.text_color);
				m_t2.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color_in));
				m_t2.setTextColor(Color.WHITE);
				m_t3.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color));
				m_t3.setTextColor(color.text_color);
				break;
			case 2:
				m_t1.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color));
				m_t1.setTextColor(color.text_color);
				m_t2.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color));
				m_t2.setTextColor(color.text_color);
				m_t3.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_top_color_in));
				m_t3.setTextColor(Color.WHITE);
				break;
			}
		}
    }
    
    public void getUser(){
    	user = new User("12330134","default","user name","userschool","Soft ware","210","zhishanyuan","123123@mail.com","1231312312");
    }
    
}
