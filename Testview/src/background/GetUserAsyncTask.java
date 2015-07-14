package background;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.example.entity.User;
import background.QueryBuilder;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
/**
 * Async Task to retrieve your stored contacts from mongolab
 * @author KYAZZE MICHAEL
 *
 */
public class GetUserAsyncTask extends AsyncTask<User, Void, ArrayList<User>> {
	static BasicDBObject user = null;
	static String OriginalObject = "";
	static String server_output = null;
	static String temp_output = null;

	@Override
	protected ArrayList<User> doInBackground(User... arg0) {
		
		ArrayList<User> users = new ArrayList<User>();
		try 
		{			
//			Log.d("test","GetUserAsyncTask");
			QueryBuilder qb = new QueryBuilder();
//			Log.d("test","GetUserAsyncTask2");
	        URL url = new URL(qb.buildTableGetURL("User"));
//	        Log.d("test","GetUserAsyncTask3");
	        HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
//	        Log.d("test","GetUserAsyncTask4");
	        conn.setRequestMethod("GET");
//	        Log.d("test","GetUserAsyncTask5");
			conn.setRequestProperty("Accept", "application/json");
//			Log.d("test","GetUserAsyncTask6");
			if (conn.getResponseCode() != 200) {
				Log.d("test","true");
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
//			Log.d("test","GetUserAsyncTask7");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
//			Log.d("test","GetUserAsyncTask8");
			while ((temp_output = br.readLine()) != null) {
				server_output = temp_output;
			}
//			Log.d("test",server_output);
            // create a basic db list
			String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
			Object o = com.mongodb.util.JSON.parse(mongoarray);
//			Log.d("test",mongoarray);

			DBObject dbObj = (DBObject) o;
			BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");
			
			for (Object obj : contacts) {
				DBObject userObj = (DBObject) obj; 
				
				User temp = new User(); 
				temp.set_ID(userObj.get("_id").toString());
				temp.setPassword(userObj.get("password").toString());
				temp.setName(userObj.get("name").toString());
				temp.setSchool(userObj.get("school").toString());
				temp.setProfession(userObj.get("profession").toString());
				temp.setRoom(userObj.get("room").toString());
				temp.setHouse(userObj.get("house").toString());
				temp.setEmail(userObj.get("email").toString());
				temp.setPhone(userObj.get("phone").toString());		
				users.add(temp);
			
			}
		
		}catch (Exception e) {
			e.getMessage();
		}
		
		return users; 
	}
}
