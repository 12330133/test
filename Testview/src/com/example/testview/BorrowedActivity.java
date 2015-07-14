package com.example.testview;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import background.GetBookAsyncTask;
import background.GetUserAsyncTask;
import background.UpdateLendListAsyncTask;
import background.UpdateUserAsyncTask;

import com.example.entity.Book;
import com.example.entity.LendList;
import com.example.entity.User;

public class BorrowedActivity extends Activity {
	private Book book = null;
	private User borrower = null;
	private Button backbtn = null;
	private Button gotbookbtn = null;
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
		setContentView(R.layout.activity_borrowed);
		
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.borrowed, menu);
		return true;
	}
	
	public void init(){
		DownLoadBorrower();
		bookname = (TextView)findViewById(R.id.bookName);
		borrowname = (TextView)findViewById(R.id.name_lender);
		borrowaddress = (TextView)findViewById(R.id.address_lender);
		borrowemail = (TextView)findViewById(R.id.email_lender);
		borrowtel = (TextView)findViewById(R.id.tel_lender);
		
		bookname.setText(book.getBookName());
		borrowname.setText(borrower.getName());
		borrowaddress.setText(borrower.getHouse()+borrower.getRoom());
		borrowemail.setText(borrower.getEmail());
		borrowtel.setText(borrower.getName());
		
		backbtn = (Button)findViewById(R.id.backbtn_b);
		gotbookbtn = (Button)findViewById(R.id.getbookbtn);
		backbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BorrowedActivity.this.finish();
			}
			
		});
		gotbookbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(check == 0){
					check = 1;
					UpLoadData();
					gotbookbtn.setText("Have Got the Book");
				}
			}
		});
	}
	
	// uncheck because of something wrong in activity changing by minglong's
	public void UpLoadData(){
		lendlist.setIsConfirm("true");
		//lendlist.print();
		UpdateLendListAsyncTask tsk = new UpdateLendListAsyncTask();
		tsk.execute(lendlist);
	}
	
	public void DownLoadBorrower(){
		
		//get the books imformation
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
				//book.print();
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
		for(User _user : returnUsers)
		{
			if(_user.get_ID().equals(lendlist.getBelongerID()))
			{
				borrower = new User(_user);
				break;
			}
		}
		
	}

}
