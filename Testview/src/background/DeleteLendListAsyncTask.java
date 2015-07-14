package background;

import com.example.entity.LendList;
import background.QueryBuilder;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DeleteLendListAsyncTask extends AsyncTask<Object, Void, Boolean>{
	@Override
	protected Boolean doInBackground(Object... params) {
		LendList lendlist = (LendList) params[0];
	
		try {
			Log.d("test","hello");
			QueryBuilder qb = new QueryBuilder();
			URL url = new URL(qb.buildUserUpdateURL(lendlist.get_ID(),"LendList"));
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			connection.setRequestProperty("async",
					"true");
			connection.setRequestProperty("timeout", "300000");
			connection.setRequestProperty("success", "");
			connection.setRequestProperty("error", "");
			Log.d("test","hello");
			OutputStreamWriter osw = new OutputStreamWriter(
					connection.getOutputStream());
			Log.d("test","hello");
//			Log.d("test", osw.getEncoding());

			//osw.write(qb.setLendListDataInDB(lendlist)); 
//			Log.d("test", osw.getEncoding());
			osw.flush();
			osw.close();
			Log.d("test","hello");
			if(connection.getResponseCode() <205)
			{
				Log.d("test","DeleteLendListAsyncTask connection.getResponseCode() "+String.valueOf(connection.getResponseCode()));
				return true;
			}
			else
			{
				Log.d("test","DeleteLendListAsyncTask !connection.getResponseCode() "+String.valueOf(connection.getResponseCode()));
				return false;
				
			}
			
		} catch (Exception e) {
			e.getMessage();
			return false;
			
		}

	}
}



