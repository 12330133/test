package com.example.testview;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import background.DeleteLendListAsyncTask;
import background.GetBookAsyncTask;
import background.GetUserAsyncTask;
import background.UpdateBookAsyncTask;
import background.UpdateLendListAsyncTask;

import com.example.entity.Book;
import com.example.entity.LendList;
import com.example.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShareActivity extends Activity {
	
	private static final int INVISIBLE = 0;
	private Book book = null;
	private User borrower = null;
	private Button backbtn = null;
//	private Button deletebtn = null;
	private Button confirmbtn = null;
	private TextView bookname = null;
	private TextView borrowname = null;
	private TextView borrowaddress = null;
	private TextView borrowemail = null;
	private TextView borrowtel = null;
	private int check = 0;
	private LendList lendlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Bundle bundle = getIntent().getBundleExtra("boom");
		lendlist = (LendList)bundle.getSerializable("bookselect");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		Log.d("test","on ShareAct");
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.share, menu);
		return true;
	}
	
	public void init(){
		bookname = (TextView)findViewById(R.id.bookName);
		borrowname = (TextView)findViewById(R.id.name_borrower);
		borrowaddress = (TextView)findViewById(R.id.address_borrower);
		borrowemail = (TextView)findViewById(R.id.email_borrower);
		borrowtel = (TextView)findViewById(R.id.tel_borrower);
		backbtn = (Button)findViewById(R.id.backbtn);
//		deletebtn = (Button)findViewById(R.id.deletebtn);
		confirmbtn = (Button)findViewById(R.id.confirmbtn);
		
		DownLoadBorrower();
		bookname.setText(book.getBookName());
		borrowname.setText(borrower.getName());
		borrowaddress.setText(borrower.getHouse()+borrower.getRoom());
		borrowemail.setText(borrower.getEmail());
		borrowtel.setText(borrower.getPhone());
		
		backbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShareActivity.this.finish();
			}
			
		});
		
		confirmbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(check == 0){
					check = 1;
					UpLoadData_Return();
					confirmbtn.setText("Have Return");
				}
			}
			
		});
		
		
		
	}
	
	public void UpLoadData_Delete(){
		book.setIsLend("false");
		UpdateBookAsyncTask tsk = new UpdateBookAsyncTask();
		tsk.execute(book);
	}
	
	public void UpLoadData_Return(){
		lendlist.setBelongerID("----------");
		lendlist.setBookName("------------");
		lendlist.setLenderID("------------");
		lendlist.setBookID("--------------");
		lendlist.setIsConfirm("true");
		//lendlist.print();
		UpdateLendListAsyncTask ask = new UpdateLendListAsyncTask();
		ask.execute(lendlist);
		
		book.setIsLend("false");
		UpdateBookAsyncTask tsk = new UpdateBookAsyncTask();
		tsk.execute(book);
		Log.d("test","UpLoadData_Return end");
	}
	
	public void DownLoadBorrower(){
		ArrayList<Book> returnBooks = new ArrayList<Book>();
		GetBookAsyncTask gsk = new GetBookAsyncTask();
		try {
			returnBooks = gsk.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Book _book : returnBooks)
		{
			if(_book.get_ID().equals(lendlist.getBookID()))
			{
				book = new Book(_book);
				break;
			}
		}
		
		//get the belonger imformation
		ArrayList<User> returnUsers = new ArrayList<User>();
		GetUserAsyncTask usk = new GetUserAsyncTask();
		try {
			returnUsers = usk.execute().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Boolean lended = false;
		for(User _user : returnUsers)
		{
			if(_user.get_ID().equals(lendlist.getLenderID()))
			{
				borrower = new User(_user);
				lended = true;
			//	deletebtn.setVisibility(INVISIBLE);
				break;
			}
		}
		if(!lended)
		{
			borrower = new User();
			confirmbtn.setVisibility(INVISIBLE);
		}
	}

}
