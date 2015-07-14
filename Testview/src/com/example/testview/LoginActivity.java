package com.example.testview;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.entity.User;

import background.GetUserAsyncTask;
import background.UserLoginAsyncTask;

public class LoginActivity extends Activity {
	private boolean check_ok;
	private EditText inputid=null;
	private EditText inputpassword=null;
	private User user = null;
	private String id=null;
	private String password = null;
	private Button loginbtn=null;
	private Button signupbtn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void init(){
		check_ok = false;
		inputid = (EditText)findViewById(R.id.login_input_name);
		inputpassword = (EditText)findViewById(R.id.login_input_password);
		id="";
		password="";
		loginbtn=(Button)findViewById(R.id.loginbtn);
		loginbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0)  {
				// TODO Auto-generated method stub
				id=inputid.getText().toString();
				password=inputpassword.getText().toString();
				UpLoad_check();
//				Log.d("test","check  1  ok  ----------");
				//if(id.equals("11111111"))
				//	check_ok=true;
				if(check_ok){
					Bundle bundle = new Bundle();
					bundle.putSerializable("the_user", user);
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, TestActivity.class);
					intent.putExtra("boo", bundle);
					startActivity(intent);
					LoginActivity.this.finish();
				}else{
					TextView error_msg = (TextView)findViewById(R.id.error_msg);
					error_msg.setVisibility(View.VISIBLE);
				}
			}
			
		});
		
		signupbtn=(Button)findViewById(R.id.signinbtn);
		signupbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, SigninActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
			}
		});
	}
	
	//KAZE's
	ArrayList<User> returnValues = new ArrayList<User>();
	public void UpLoad_check(){
		//KAZE's 
		
		//Get your cloud contacts
		GetUserAsyncTask task = new GetUserAsyncTask();
		try {
			returnValues = task.execute().get();
		} catch (InterruptedException e) {
			Log.d("test",e.toString());
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.d("test",e.toString());
			e.printStackTrace();
		}
		Log.d("test","returnValues.size() = "+String.valueOf(returnValues.size()));
		for(User x: returnValues){
			if(x.get_ID().equals(id))
			{
//				Log.d("test",x.get_ID());
				if(x.getPassword().equals(password))
				{
					check_ok=true;
					try{
						user = new User();
//						x.print();
						user.UserCopy(x);
					} catch (Exception e) {
						e.printStackTrace();
//						Log.d("test","wrong with user.UserCopy(x)");
						user.print();
					}
					break;
				}
			}
		}
		//KAZE's
	}

}
