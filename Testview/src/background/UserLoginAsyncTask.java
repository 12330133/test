package background;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.example.entity.*;

public class UserLoginAsyncTask extends AsyncTask<User, Void, Boolean>{

	@Override
	protected Boolean doInBackground(User... params) {
		// TODO Auto-generated method stub
		try 
		{
			User user = params[0];
			QueryBuilder qb = new QueryBuilder();
			
			HttpClient httpClient = new DefaultHttpClient();
	        HttpPost request = new HttpPost(qb.buildSaveURL("User"));
	
	        StringEntity se =new StringEntity(qb.createUserInDB(user));
	        request.addHeader("content-type", "application/json");
	        request.setEntity(se);
	        HttpResponse response = httpClient.execute(request);
	        
	        if(response.getStatusLine().getStatusCode()<205)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
		} catch (Exception e) {
			//e.getCause();
			String val = e.getMessage();
			String val2 = val;
			return false;
		}
	}

}
