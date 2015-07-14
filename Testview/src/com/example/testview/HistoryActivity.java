package com.example.testview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import background.GetLendListAsyncTask;

import com.example.adapter.HistoryAdapter;
import com.example.entity.Book;
import com.example.entity.LendList;
import com.example.entity.User;

public class HistoryActivity extends Activity {
	private LinkedList<LendList> borrowlist=null;
	private LinkedList<LendList> sharelist=null;
	private User user=null;
	private Button backbtn = null;
	private HistoryAdapter share_adapter = null;
	private HistoryAdapter borrow_adapter = null;
	private ListView share_lv = null;
	private ListView borrow_lv = null;
	
	private ArrayList<LendList> returnValue = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Bundle bundle = getIntent().getBundleExtra("boom");
		user = (User)bundle.getSerializable("user");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		returnValue = new ArrayList<LendList>();
		borrowlist = new LinkedList<LendList>();
		sharelist = new LinkedList<LendList>();
		DownLoadData();
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}
	
	public void init(){
//		user.print();  //checked
		backbtn = (Button)findViewById(R.id.backbtn_);
		backbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				HistoryActivity.this.finish();
			}
			
		});
		
		share_lv = (ListView)findViewById(R.id.sharelist);
		borrow_lv = (ListView)findViewById(R.id.borrowlist);
		share_adapter = new HistoryAdapter(this);
		share_adapter.setdata(sharelist);
		share_adapter.fakedata();
		borrow_adapter = new HistoryAdapter(this);
		borrow_adapter.setdata(borrowlist);
		borrow_adapter.fakedata();
		share_lv.setAdapter(share_adapter);
		share_lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putSerializable("bookselect", sharelist.get(arg2));
				Intent it = new Intent();
				it.setClass(HistoryActivity.this, ShareActivity.class);
				it.putExtra("boom", bundle);
				HistoryActivity.this.startActivity(it);
				
			}
			
		});
		borrow_lv.setAdapter(borrow_adapter);
		borrow_lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putSerializable("bookselect", borrowlist.get(arg2));
				Intent it = new Intent();
				it.setClass(HistoryActivity.this, BorrowedActivity.class);
				it.putExtra("boom", bundle);
				startActivity(it);
			}
		});
	};
	
	// kaze's
	
	
	public void DownLoadData(){
		GetLendListAsyncTask ask = new GetLendListAsyncTask();
		try {
			returnValue = ask.execute().get();
		} catch (InterruptedException e) {
			Log.d("test","cant get returnValues" + e.toString());
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.d("test","cant get returnValues" + e.toString());
			e.printStackTrace();
		}
		//Log.d("test","His returnValue.size() = "+returnValue.size());
		for(LendList lendlist : returnValue){
			//lendlist.print();
			if(lendlist.getBelongerID().equals(user.get_ID())){
				Log.d("test","His returnValue.size() = "+returnValue.size());
				sharelist.add(lendlist);
			}
			else if(lendlist.getLenderID().equals(user.get_ID())){
				Log.d("test","His returnValue.size() = "+returnValue.size());
				borrowlist.add(lendlist);
			}
		}
	}
	

}
