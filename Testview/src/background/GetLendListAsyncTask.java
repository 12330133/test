package background;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;

import com.example.entity.LendList;
import background.QueryBuilder;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
/**
 * Async Task to retrieve your stored contacts from mongolab
 * @author KYAZZE MICHAEL
 *
 */
public class GetLendListAsyncTask extends AsyncTask<LendList, Void, ArrayList<LendList>> {
	static BasicDBObject user = null;
	static String OriginalObject = "";
	static String server_output = null;
	static String temp_output = null;

	@Override
	protected ArrayList<LendList> doInBackground(LendList... arg0) {
		
		ArrayList<LendList> lendlists = new ArrayList<LendList>();
		try 
		{	
			QueryBuilder qb = new QueryBuilder();
	        URL url = new URL(qb.buildTableGetURL("LendList"));
	        HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
	        conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json;charset=utf-8");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			while ((temp_output = br.readLine()) != null) {
				server_output = temp_output;
			}
			
            // create a basic db list
			String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
			Object o = com.mongodb.util.JSON.parse(mongoarray);
			

			DBObject dbObj = (DBObject) o;
			BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");
			
			for (Object obj : contacts) {
				DBObject userObj = (DBObject) obj; 
				
				LendList temp = new LendList(); 
				temp.set_ID(userObj.get("_id").toString());
				temp.setBookID(userObj.get("bookID").toString());
				temp.setBookName(userObj.get("bookName").toString());
				temp.setBelongerID(userObj.get("belongerID").toString());
				temp.setLenderID(userObj.get("lenderID").toString());
				temp.setIsConfirm(userObj.get("isconfirm").toString());
				lendlists.add(temp);
			
			}
		
		}catch (Exception e) {
			e.getMessage();
		}
		
		return lendlists; 
	}
}
