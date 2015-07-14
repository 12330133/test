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

public class UpdateLendListAsyncTask extends AsyncTask<Object, Void, Boolean>{
	@Override
	protected Boolean doInBackground(Object... params) {
		LendList lendlist = (LendList) params[0];
	
		try {

			QueryBuilder qb = new QueryBuilder();
			URL url = new URL(qb.buildUserUpdateURL(lendlist.get_ID(),"LendList"));
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			connection.setRequestProperty("Accept", "application/json");
			
			OutputStreamWriter osw = new OutputStreamWriter(
					connection.getOutputStream());
			osw.write(qb.setLendListDataInDB(lendlist)); 
			osw.flush();
			osw.close();
			if(connection.getResponseCode() <205)
			{
				Log.d("test","UpdateLendListAsyncTask connection.getResponseCode() "+String.valueOf(connection.getResponseCode()));
				return true;
			}
			else
			{
				Log.d("test","UpdateLendListAsyncTask !connection.getResponseCode() "+String.valueOf(connection.getResponseCode()));
				return false;
				
			}
			
		} catch (Exception e) {
			e.getMessage();
			return false;
			
		}

	}
}



