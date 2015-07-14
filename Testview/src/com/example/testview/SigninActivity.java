package com.example.testview;

import java.net.UnknownHostException;
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
import background.GetUserAsyncTask;
import background.SaveNewUserAsyncTask;

import com.example.entity.User;

public class SigninActivity extends Activity {
	private EditText userid=null;
	private EditText username=null;
	private EditText userpassword=null;
	private EditText userschool=null;
	private EditText userprofession=null;
	private EditText usertel=null;
	private EditText userdormitory=null;
	private EditText userroom=null;
	private EditText useremail=null;
	
	private Button backbtn=null;
	private Button signupbtn = null;
	
	private String id="";
	private String name="";
	private String password="";
	private String school="";
	private String profession="";
	private String tel="";
	private String dormitory="";
	private String room="";
	private String email="";
	private User user=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signin, menu);
		return true;
	}
	
	public void init(){
		userid=(EditText)findViewById(R.id.signuserID);
		username=(EditText)findViewById(R.id.signuserName);
		userpassword=(EditText)findViewById(R.id.signuserPassword);
		userschool=(EditText)findViewById(R.id.signuserSchool);
		userprofession=(EditText)findViewById(R.id.signuserProfession);
		usertel=(EditText)findViewById(R.id.signuserTel);
		userdormitory=(EditText)findViewById(R.id.signuserDormitory);
		userroom=(EditText)findViewById(R.id.signuserRoom);
		useremail=(EditText)findViewById(R.id.signuserEmail);
		signupbtn=(Button)findViewById(R.id.signbtn);
		/*
		signupbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				id=userid.getText().toString();
				name=username.getText().toString();;
				password=userpassword.getText().toString();
				school = userschool.getText().toString();
				profession=userprofession.getText().toString();
				tel=usertel.getText().toString();
				dormitory=userdormitory.getText().toString();
				room=userroom.getText().toString();
				email=useremail.getText().toString();
				user= new User(id,password,name,school,profession,room,dormitory,email,tel);
				Uploaddata();
				Bundle bundle = new Bundle();
				bundle.putSerializable("the_user", user);
				Intent intent = new Intent();
				intent.setClass(SigninActivity.this, TestActivity.class);
				intent.putExtra("boo", bundle);
				startActivity(intent);
				SigninActivity.this.finish();
			}
			
		});
		*/
		backbtn=(Button)findViewById(R.id.signbackbtn);
		backbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SigninActivity.this, LoginActivity.class);
				startActivity(intent);
				SigninActivity.this.finish();
			}
			
		});
		
	}
	
	ArrayList<User> returnValues = new ArrayList<User>();
	
	public void saveUser(View view) throws UnknownHostException {
		//kaze's work
		Log.d("test", "start");
		id=userid.getText().toString();
		name=username.getText().toString();;
		password=userpassword.getText().toString();
		school = userschool.getText().toString();
		profession=userprofession.getText().toString();
		tel=usertel.getText().toString();
		dormitory=userdormitory.getText().toString();
		room=userroom.getText().toString();
		email=useremail.getText().toString();
		user= new User(id,password,name,school,profession,room,dormitory,email,tel);
		
		GetUserAsyncTask task = new GetUserAsyncTask();
		Boolean check = false;
		try {
			returnValues = task.execute().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		for(User x: returnValues){
			if(x.get_ID().equals(id))
			{
				check = true;
				break;
			}
		}
		
		if(!check)
		{
			SaveNewUserAsyncTask tsk = new SaveNewUserAsyncTask();
		    tsk.getActivity(this);
			tsk.execute(user);
			Log.d("test", "finishsaveUser");
			//kaze testing
			//TextView error_msg = (TextView)findViewById(R.id.error_msg);
			//error_msg.setText(id + " "+name);
			//error_msg.setVisibility(View.VISIBLE);
			//kaze testing
			//~kaze's work
			
			Bundle bundle = new Bundle();
			bundle.putSerializable("the_user", user);
			Intent intent = new Intent();
			intent.setClass(SigninActivity.this, TestActivity.class);
			intent.putExtra("boo", bundle);
			startActivity(intent);
			SigninActivity.this.finish();
		}
		else
		{
			TextView error_msg = (TextView)findViewById(R.id.error_msg);
			error_msg.setText("This id is exist");
		}
	}
	

}
