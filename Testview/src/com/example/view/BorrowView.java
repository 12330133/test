package com.example.view;

import java.net.UnknownHostException;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import background.SaveNewBookAsyncTask;

import com.example.entity.Book;
import com.example.entity.User;
import com.example.testview.R;

public class BorrowView {
	private final static int RES_ID = R.layout.activity_borrow;
	private User user=null;
	private Book book=null;
	private String bookname=null;
	private String bookisbn=null;
	private String bookimformation=null;
	private String bookauthor=null;
	private EditText inputimformation = null;
	private EditText inputauthor = null;
	private EditText inputbookname=null;
	private EditText inputisbn = null;
	private Button addbookbtn=null;
	private Activity m_act = null;
	private View m_view = null;
	
	public BorrowView(Activity _act){
		m_act = _act;
	}
	
	public void initView(){
		
		m_view = m_act.getLayoutInflater().inflate(RES_ID, null, false);
		inputbookname=(EditText)m_view.findViewById(R.id.inputbookName);
		inputimformation = (EditText)m_view.findViewById(R.id.inputImformation);
		inputauthor=(EditText)m_view.findViewById(R.id.inputbookAuthor);
		inputisbn=(EditText)m_view.findViewById(R.id.inputbookISBN);
		bookname = inputbookname.getText().toString();
		bookisbn=inputisbn.getText().toString();
		bookimformation=inputimformation.getText().toString();
		bookauthor = inputauthor.getText().toString();
		addbookbtn=(Button)m_view.findViewById(R.id.addBookbtn);
		addbookbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
//				Log.d("test","onClick activity");
				// TODO Auto-generated method stub
				bookname = inputbookname.getText().toString();
				bookisbn=inputisbn.getText().toString();
				bookimformation=inputimformation.getText().toString();
				bookauthor = inputauthor.getText().toString();
				if(checkisISBN(bookisbn)){
					Log.d("test","ADD BOOK ISBN IS OK");
				}

				if(!bookname.equals("")&&!bookauthor.equals("")&&!bookisbn.equals("")){
//					Log.d("test","no blank edittext");
					book = new Book(bookname,bookisbn,bookauthor,bookimformation,false,user.get_ID()+"");
					try {
						UploadData();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						Log.d("test","UploadDate wrong");
						e.printStackTrace();
					}
				}
			}
			
		});
	}
	
	public boolean checkisISBN(String isbn){
		return true;
	}
	
	public void UploadData()throws UnknownHostException {
		Log.d("test","in saveBook");
		book = new Book(bookname,bookisbn,bookauthor,bookimformation,false,user.get_ID()+"");
		SaveNewBookAsyncTask tsk = new SaveNewBookAsyncTask();
		tsk.execute(book);
	}
	
	public View getView(){
		return m_view;
	}
	public void saveBook(View view) throws UnknownHostException
	{
		Log.d("test","in saveBook");
		if(!bookname.equals("")&&!bookauthor.equals("")&&bookisbn.equals("")){
			Log.d("test","in saveBook2");
			book = new Book(bookname,bookisbn,bookauthor,bookimformation,false,user.get_ID()+"");
			SaveNewBookAsyncTask tsk = new SaveNewBookAsyncTask();
			tsk.execute(book);
			//UploadData();
		}
		/*
		// TODO Auto-generated method stub
		if(checkisISBN(bookisbn)){
			System.out.println("ADD BOOK ISBN IS OK");
		}
		if(!bookname.equals("")&&!bookauthor.equals("")&&bookisbn.equals("")){
			book = new Book(bookname,bookisbn,bookauthor,bookimformation,false,user.get_ID()+"");
			UploadData();
		}
		*/
	}
	public void getUser(User u){
		user = new User("ss","pp","nn","scsc","prpr","rr","hh","ee","ph");
		user.UserCopy(u);
	}

}
