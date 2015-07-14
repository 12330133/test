package background;

import com.example.entity.User;
import background.QueryBuilder;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateUserAsyncTask extends AsyncTask<Object, Void, Boolean>{
	@Override
	protected Boolean doInBackground(Object... params) {
		User user = (User) params[0];
	
		try {

			QueryBuilder qb = new QueryBuilder();
			URL url = new URL(qb.buildUserUpdateURL(user.get_ID(),"User"));
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			connection.setRequestProperty("Accept", "application/json");
			
			OutputStreamWriter osw = new OutputStreamWriter(
					connection.getOutputStream());
			
			osw.write(qb.setUserDataInDB(user)); 
			osw.flush();
			osw.close();
			if(connection.getResponseCode() <205)
			{

				return true;
			}
			else
			{
				return false;
				
			}
			
		} catch (Exception e) {
			e.getMessage();
			return false;
			
		}

	}
}



