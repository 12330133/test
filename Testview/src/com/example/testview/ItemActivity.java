package com.example.testview;

import java.net.UnknownHostException;
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
import background.GetUserAsyncTask;
import background.SaveNewLendListAsyncTask;
import background.UpdateBookAsyncTask;

import com.example.entity.Book;
import com.example.entity.LendList;
import com.example.entity.User;

public class ItemActivity extends Activity {

	private Button backbtn=null;
	private Button borrowbtn = null;
	private Book book=null;
	private User user=null;
	private User bookonwer = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle bundle = getIntent().getBundleExtra("boom");
		book = (Book)bundle.getSerializable("bookselect");
		user = (User)bundle.getSerializable("the_user");
		System.out.println(book.getBookName()+"  --ITEM ACTIVITY HAVE GET");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initButton();
		initdata();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item, menu);
		return true;
	}
	
	public void initButton(){
		backbtn = (Button)findViewById(R.id.backbtn);
		borrowbtn = (Button)findViewById(R.id.borrowbtn);
		backbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ItemActivity.this.finish();
			}
			
		});
		borrowbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					borrowbook();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
	
	public void initdata(){
		TextView bookname = (TextView)findViewById(R.id.bookName);
		bookname.setText(book.getBookName());
		TextView bookISBN = (TextView)findViewById(R.id.ISBNnum);
		bookISBN.setText(book.getISBN());
		
		TextView bookauthor = (TextView)findViewById(R.id.AuthorName);
//		Log.d("test",user.get_ID());
//		getUser(book.getBelongerID());
//		user.print();
		bookauthor.setText(book.getAuthor());
		
		//kaze's
		getBookOnwer(book.getBelongerID());
		
		TextView username=(TextView)findViewById(R.id.OwnerName);
		username.setText(bookonwer.getName());
		TextView phone = (TextView)findViewById(R.id.OwnerTelNum);
		phone.setText(bookonwer.getPhone());
		TextView address = (TextView)findViewById(R.id.Address);
		address.setText(bookonwer.getHouse()+"  "+bookonwer.getRoom());
		TextView imformation=(TextView)findViewById(R.id.Imformation);
		imformation.setText(book.getInfo());
	}
	
	public void getUser(String string){
		user = new User(string+"","default","user name","userschool","Soft ware","210","zhishanyuan","123123@mail.com","1231312312");
		
	}
	ArrayList<User> returnValues = new ArrayList<User>();
	public void getBookOnwer(String onwer)
	{
		GetUserAsyncTask task = new GetUserAsyncTask();
		try {
			returnValues = task.execute().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
//		Log.d("test","in testing");
		for(User x: returnValues){
			if(x.get_ID().equals(onwer))
			{
				bookonwer = new User();
				bookonwer.UserCopy(x);
				break;
			}
		}
		//KAZE's
	}
	private void borrowbook() throws UnknownHostException
	{
		LendList lendlist = new LendList();
		lendlist.setBookID(book.get_ID());
		lendlist.setBookName(book.getBookName());
		lendlist.setBelongerID(bookonwer.get_ID());
		lendlist.setLenderID(user.get_ID());
		lendlist.setIsConfirm("false");
		SaveNewLendListAsyncTask tsk = new SaveNewLendListAsyncTask();
	    tsk.getActivity(this);
		tsk.execute(lendlist);
		
		//update book islend state
		book.setIsLend("true");
		UpdateBookAsyncTask usk = new UpdateBookAsyncTask();
		usk.execute(book);
		
	}
}
