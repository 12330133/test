package background;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.entity.*;
import com.example.testview.R;

public class SaveNewUserAsyncTask extends AsyncTask<User, Void, Boolean>{
	private Activity act = null;

	@Override
	protected Boolean doInBackground(User... params) {
		// TODO Auto-generated method stub
		try 
		{
//			Log.d("test", "in SaveNewUserAsyncTask.doinbackground");
			User user = params[0];
//			Log.d("test", user.get_ID());
			QueryBuilder qb = new QueryBuilder();
			qb.getActivity(act);
			
			HttpClient httpClient = new DefaultHttpClient();
//			Log.d("test",qb.buildSaveURL("User"));
	        HttpPost request = new HttpPost(qb.buildSaveURL("User"));
//	        Log.d("test",qb.createUserInDB(user));
	        StringEntity se =new StringEntity(qb.createUserInDB(user));
//	        Log.d("test","testing1");
	        request.addHeader("content-type", "application/json");
//	        Log.d("test","testing2");
	        request.setEntity(se);
//	        Log.d("test","testing3");
//	        Log.d("test",request.getMethod());
//	        Log.d("test",String.valueOf(request.containsHeader("content-type")));
	        HttpResponse response = httpClient.execute(request);
//	        Log.d("test","99");
//	        Log.d("test",String.valueOf(response.getStatusLine().getStatusCode()));
//	        Log.d("test","testing4");
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
			Log.d("test","something wrong : "+e.getMessage());
			return false;
		}
	}
	
	public void getActivity(Activity act_){
		act = act_;
	}
	

}
