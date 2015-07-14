package background;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.entity.Book;
import com.example.entity.LendList;
import com.example.entity.User;
import com.example.testview.R;

//¬d¸ß³Ð«Ø
public class QueryBuilder { 
	private Activity act = null;
	
	/**
	 * Specify your database name here
	 * @return
	 */
	public String getDatabaseName() {
		return "sys";
	}

	/**
	 * Specify your MongoLab API here
	 * @return
	 */
	public String getApiKey() {
		return "_uqZjcEk6pgTGD8IuY86XdNShUezxQSh";
	}
	
	/**
	 * This constructs the URL that allows you to manage your database, 
	 * collections and documents
	 * @return
	 */
	public String getBaseUrl()
	{
		return "https://api.mongolab.com/api/1/databases/"
				+ getDatabaseName() + "/collections/";
	}
	
	/**
	 * Completes the formating of your URL and adds your API key at the end
	 * @return
	 */
	public String docApiKeyUrl()
	{
		return "?apiKey="+getApiKey();
	}
	
	/**
	 * Get a specified document
	 * @param docid
	 * @return
	 */
	public String docApiKeyUrl(String docid)
	{
		return "/"+docid+"?apiKey="+getApiKey();
	}
	
	/**
	 * Returns the docs101 collection
	 * @return
	 */
	public String documentRequest(String tableName)
	{
		return tableName;
	}
	
	/**
	 * Builds a complete URL using the methods specified above
	 * @return
	 */
	public String buildSaveURL(String tableName)
	{
		return getBaseUrl()+documentRequest(tableName)+docApiKeyUrl();
	}
	
	/**
	 * This method is identical to the one above. 
	 * @return
	 */
	public String buildTableGetURL(String tableName)
	{
		return getBaseUrl()+documentRequest(tableName)+docApiKeyUrl();
	}
	
	/**
	 * Get a Mongodb document that corresponds to the given object id
	 * @param doc_id
	 * @return
	 */
	public String buildUserUpdateURL(String doc_id,String tableName)
	{
		//Log.d("test",getBaseUrl()+documentRequest(tableName)+docApiKeyUrl(doc_id));
		return getBaseUrl()+documentRequest(tableName)+docApiKeyUrl(doc_id);
	}
	//public String buildLendListUpdateURL
	
	/**
	 * Formats the contact details for MongoHLab Posting
	 * @param contact: Details of the person 
	 * @return
	 */
	public String createUserInDB(User user)
	{
		//Log.d("test", "createUserInDB");
		return String
		.format("{\"_id\":\"%s\",\"password\":\"%s\",\"name\":\"%s\",\"school\":\"%s\",\"profession\":\"%s\",\"room\":\"%s\",\"house\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\"}",
				user.get_ID(), user.getPassword(), user.getName(), user.getSchool(),user.getProfession(),user.getRoom(),user.getHouse(),user.getEmail(),user.getPhone());
	}
	
	/**
	 * Update a given contact record
	 * @param contact
	 * @return
	 */
	
	public String setUserDataInDB(User user) {
		
		return String.format("{ \"$set\" : " 
				+ "{\"_id\":\"%s\",\"password\":\"%s\",\"name\":\"%s\",\"school\":\"%s\",\"profession\":\"%s\",\"room\":\"%s\",\"house\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\"}" + "}",
				user.get_ID(), user.getPassword(), user.getName(), user.getSchool(),user.getProfession(),user.getRoom(),user.getHouse(),user.getEmail(),user.getPhone());
	}
	public String createLendlistInDB(LendList lendlist)
	{
		return String.format("{\"bookID\":\"%s\",\"bookName\":\"%s\",\"belongerID\":\"%s\",\"lenderID\":\"%s\",\"isconfirm\":%b}",
							lendlist.getBookID(),lendlist.getBookName(),lendlist.getBelongerID(),lendlist.getLenderID(),lendlist.getIsConfirm());
	}
	public String setLendListDataInDB(LendList lendlist) {
		// TODO Auto-generated method stub
		return String.format("{ \"$set\" : " 
				+ "{\"bookID\":\"%s\",\"bookName\":\"%s\",\"belongerID\":\"%s\",\"lenderID\":\"%s\",\"isconfirm\":%b}" + "}",
				lendlist.getBookID(),lendlist.getBookName(),lendlist.getBelongerID(),lendlist.getLenderID(),lendlist.getIsConfirm());
	}
	public String createBookInDB(Book book)
	{
		Log.d("test","i am in createBookInDB");
		return String.format("{\"bookName\":\"%s\",\"ISBN\":\"%s\",\"belongerID\":\"%s\",\"author\":\"%s\",\"info\":\"%s\",\"islend\":%b}",
							book.getBookName(),book.getISBN(),book.getBelongerID(),book.getAuthor(),book.getInfo(),book.getIsLend());
	}
	public String setBookDataInDB(Book book) {
		// TODO Auto-generated method stub
		return String.format("{ \"$set\" : " + "{\"bookName\":\"%s\",\"ISBN\":\"%s\",\"belongerID\":\"%s\",\"author\":\"%s\",\"info\":\"%s\",\"islend\":%b}" + "}",
				book.getBookName(),book.getISBN(),book.getBelongerID(),book.getAuthor(),book.getInfo(),book.getIsLend());
	}
	
	public void getActivity(Activity act_){
		act = act_;
	}
	
}
