package com.example.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import background.GetBookAsyncTask;

import com.example.adapter.ItemAdapter;
import com.example.entity.Book;
import com.example.entity.User;
import com.example.testview.ItemActivity;
import com.example.testview.R;

public class SearchView {

	private final static int RES_ID = R.layout.activity_search;
	
	private Activity m_act = null;
	private View m_view = null;
	private LinkedList<Book> booklist=null;
	private Button m_btn = null;
	private EditText m_et = null;
	private ListView m_lv = null;
	private ItemAdapter m_adapter = null;
	private User user = null;
	
	public SearchView(Activity _act){
		m_act = _act;
	}
	
	@SuppressWarnings("deprecation")
	public void initView(){
		booklist=new LinkedList<Book>();
		m_view = m_act.getLayoutInflater().inflate(RES_ID, null, false);
		m_btn = (Button)m_view.findViewById(R.id.btn);
		m_et = (EditText)m_view.findViewById(R.id.searchtxt);
		m_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try{
					setbooklist();
					m_adapter.fakeData(booklist);
				} catch (Exception e){
					Log.d("test","Search btn comes something wrong");
					m_et.setText("");
				}
			}
		});
		m_lv = (ListView)m_view.findViewById(R.id.searchlist);
		m_adapter = new ItemAdapter(m_act);
		m_lv.setAdapter(m_adapter);
		
		m_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("You CLICK ITEM "+arg2);
				Bundle bundle = new Bundle();
				bundle.putSerializable("bookselect", booklist.get(arg2));
				bundle.putSerializable("the_user", user);
				Intent it = new Intent();
				it.setClass(m_act, ItemActivity.class);
				it.putExtra("boom", bundle);
				m_act.startActivity(it);
			}
		});
	}
	
	public View getView(){
		return m_view;
	}
	
	// kaze's
	ArrayList<Book> returnValues = new ArrayList<Book>();
	
	public void setbooklist(){
		booklist.clear();
		//KAZE's 

		//Get your cloud contacts
		Log.d("test","start GetBookAsyncTask");
		GetBookAsyncTask task = new GetBookAsyncTask();
		Log.d("test","end GetBookAsyncTask");
		try {
			returnValues = task.execute().get();
		} catch (InterruptedException e) {
			Log.d("test","cant get returnValues");
		} catch (ExecutionException e) {
			Log.d("test","cant get returnValues");
			e.printStackTrace();
		}
//		Log.d("test",String.valueOf(returnValues.size()));
		for(Book x: returnValues){
			if(x.getBookName().contains(m_et.getText().toString()) && !x.getBelongerID().equals(user.get_ID()))
			{
				if(!x.getIsLend())
				{
					Book b = new Book(x);
					booklist.add(b);
				}
			}
		}
	}
	
	public void getUser(User u){
		user = new User();
		user.UserCopy(u);
	}
	
}
