package background;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.example.entity.Book;
import background.QueryBuilder;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
/**
 * Async Task to retrieve your stored contacts from mongolab
 * @author KYAZZE MICHAEL
 *
 */
public class GetBookAsyncTask extends AsyncTask<Book, Void, ArrayList<Book>> {
	static BasicDBObject user = null;
	static String OriginalObject = "";
	static String server_output = null;
	static String temp_output = null;

	@Override
	protected ArrayList<Book> doInBackground(Book... arg0) {
		
		ArrayList<Book> books = new ArrayList<Book>();
		try 
		{			
//			Log.d("test","helloworld1");
			QueryBuilder qb = new QueryBuilder();
//			Log.d("test","helloworld2");
	        URL url = new URL(qb.buildTableGetURL("Book"));
//	        Log.d("test","helloworld3");
	        HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
//	        Log.d("test","helloworld4");
	        conn.setRequestMethod("GET");
//	        Log.d("test","helloworld5");
			conn.setRequestProperty("Accept", "application/json;charset=utf-8");
//			Log.d("test","helloworld6");
			if (conn.getResponseCode() != 200) {
				
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
//			Log.d("test","helloworld7");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
//			Log.d("test","helloworld8");
			while ((temp_output = br.readLine()) != null) {
				server_output = temp_output;
			}
//			Log.d("test","helloworld9");
            // create a basic db list
			String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
//			Log.d("test",mongoarray);
			Object o = com.mongodb.util.JSON.parse(mongoarray);
//			Log.d("test","helloworld11");

			DBObject dbObj = (DBObject) o;
			BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");
//			Log.d("test","helloworld12");
			for (Object obj : contacts) {
//				Log.d("test","in for ");
				DBObject bookObj = (DBObject) obj; 
				Book temp = new Book(); 
				temp.set_ID(bookObj.get("_id").toString());
				temp.setBookName(bookObj.get("bookName").toString());
				temp.setISBN(bookObj.get("ISBN").toString());
				temp.setBelongerID(bookObj.get("belongerID").toString());
				temp.setAuthor(bookObj.get("author").toString());
				temp.setInfo(bookObj.get("info").toString());	
				temp.setIsLend(bookObj.get("islend").toString());
				System.out.println(Boolean.getBoolean(bookObj.get("islend").toString()));
//				Log.d("test","----"+ bookObj.get("islend").toString()+"----");
//				temp.print();
				books.add(temp);
			}
			Log.d("test",String.valueOf(books.size()));
		
		}catch (Exception e) {
			Log.d("test",e.toString());
			e.getMessage();
		}
		
		return books; 
	}
}
