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

public class SaveNewLendListAsyncTask extends AsyncTask<LendList, Void, Boolean>{
	private Activity act = null;

	@Override
	protected Boolean doInBackground(LendList... params) {
		// TODO Auto-generated method stub
		try 
		{
//			Log.d("test", "in SaveNewBookAsyncTask.doinbackground");
			LendList lendlist = params[0];
//			Log.d("test", book.getBookName());
			QueryBuilder qb = new QueryBuilder();
			//qb.getActivity(act);
			
			HttpClient httpClient = new DefaultHttpClient();
//			Log.d("test",qb.buildSaveURL("Book"));
	        HttpPost request = new HttpPost(qb.buildSaveURL("LendList"));
//	        Log.d("test",qb.createBookInDB(book));
//	        book.print();
	        StringEntity se =new StringEntity(qb.createLendlistInDB(lendlist));
//	        Log.d("test","testing1");
	        request.addHeader("content-type", "application/json");
//	        Log.d("test","testing2");
	        request.setEntity(se);
//	        Log.d("test","testing3");
//	        Log.d("test",request.getMethod());
//	        Log.d("test",String.valueOf(request.containsHeader("content-type")));
	        HttpResponse response = httpClient.execute(request);
//	        Log.d("test","execute");
//	        Log.d("test",String.valueOf(response.getStatusLine().getStatusCode()));
//	        Log.d("test","testing4");
	        if(response.getStatusLine().getStatusCode()<205)
	        {	        
	        	Log.d("test","SaveNewLendListAsyncTask response.getStatusLine().getStatusCode()");
	        	return true;
	        }
	        else
	        {
		        Log.d("test","SaveNewLendListAsyncTask !response.getStatusLine().getStatusCode()");
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
