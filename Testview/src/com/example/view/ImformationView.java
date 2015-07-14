package com.example.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import background.UpdateUserAsyncTask;

import com.example.entity.User;
import com.example.testview.HistoryActivity;
import com.example.testview.ItemActivity;
import com.example.testview.R;

public class ImformationView {
	private final static int RES_ID = R.layout.activity_imformation;
	
	private Activity m_act = null;
	private View m_view = null;
	private Button editbtn = null;
	private Button confirmbtn=null;
	private Button historybtn = null;
	private EditText userid=null;
	private EditText username = null;
	private EditText userschool = null;
	private EditText userprofession=null;
	private EditText usertel=null;
	private EditText userroom=null;
	private EditText userdormitory=null;
	private EditText useremail=null;
	private User user=null;
	public ImformationView(Activity _act){
		m_act = _act;
	}
	
	public void initView(){
		m_view = m_act.getLayoutInflater().inflate(RES_ID, null, false);
		userid=(EditText)m_view.findViewById(R.id.userID);
		System.out.println("USER ID --"+user.get_ID());
		userid.setText(user.get_ID()+"");
		username = (EditText)m_view.findViewById(R.id.userName);
		username.setText(user.getName());
		userschool=(EditText)m_view.findViewById(R.id.userSchool);
		userschool.setText(user.getSchool());
		userprofession=(EditText)m_view.findViewById(R.id.userProfession);
		userprofession.setText(user.getProfession());
		userroom=(EditText)m_view.findViewById(R.id.userRoom);
		userroom.setText(user.getRoom()+"");
		usertel = (EditText)m_view.findViewById(R.id.userTel);
		usertel.setText(user.getPhone());
		userdormitory=(EditText)m_view.findViewById(R.id.userDormitory);
		userdormitory.setText(user.getHouse());
		useremail=(EditText)m_view.findViewById(R.id.userEmail);
		useremail.setText(user.getEmail());
//		DisplayMetrics dm = new DisplayMetrics();
//		m_act.getWindowManager().getDefaultDisplay().getMetrics(dm);

		editbtn = (Button)m_view.findViewById(R.id.editbtn);
		confirmbtn=(Button)m_view.findViewById(R.id.Confirmbtn);
		historybtn = (Button)m_view.findViewById(R.id.historybtn);
		editbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("EDIT BUTTON IS CLICK");
				confirmbtn.setVisibility(View.VISIBLE);
				editbtn.setVisibility(View.INVISIBLE);
				username.setEnabled(true);
				username.setSelectAllOnFocus(true);
				userschool.setEnabled(true);
				userschool.setSelectAllOnFocus(true);
				userprofession.setEnabled(true);
				userprofession.setSelectAllOnFocus(true);
				usertel.setEnabled(true);
				usertel.setSelectAllOnFocus(true);
				userdormitory.setEnabled(true);
				userdormitory.setSelectAllOnFocus(true);
				useremail.setEnabled(true);
				useremail.setSelectAllOnFocus(true);
				userroom.setEnabled(true);
				userroom.setSelectAllOnFocus(true);
				
			}
			
		});
		
		confirmbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				editbtn.setVisibility(View.VISIBLE);
				confirmbtn.setVisibility(View.INVISIBLE);
				username.setEnabled(false);
				userschool.setEnabled(false);
				userdormitory.setEnabled(false);
				userroom.setEnabled(false);
				useremail.setEnabled(false);
				usertel.setEnabled(false);
				userprofession.setEnabled(false);
				
				UploadData();
				}
			
		});
		
		historybtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putSerializable("user", user);
				Intent it = new Intent();
				it.setClass(m_act, HistoryActivity.class);
				it.putExtra("boom", bundle);
				m_act.startActivity(it);
			}
			
		});
	}
	
	public View getView(){
		return m_view;
	}
	
	public void UploadData(){
		//user.print();
		user.setName(username.getText().toString());
		user.setSchool(userschool.getText().toString());
		user.setHouse(userdormitory.getText().toString());
		user.setRoom(userroom.getText().toString());
		user.setEmail(useremail.getText().toString());
		user.setPhone(usertel.getText().toString());
		user.setProfession(userprofession.getText().toString());
		//user.print();
		
		UpdateUserAsyncTask tsk = new UpdateUserAsyncTask();
		tsk.execute(user);
	}
	
	public void getUser(User u){
		user = new User("ss","pp","nn","scsc","prpr","rr","hh","ee","ph");
		user.UserCopy(u);
	}
	
}
