package com.example.action.bar;

//import com.example.explicit_intent1.MainActivityTwo;


import com.example.action.bar.R;
import com.example.database.MyDatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {


	private Cursor person;
	private MyDatabase db;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = new MyDatabase(this);
		person = db.getPersons(); // you would not typically call this on the main thread

		ListAdapter adapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_1, 
				person, 
				new String[] {"Name"}, 
				new int[] {android.R.id.text1});

		ListView listview = (ListView) findViewById(R.id.listView1);
		//getListView()
		listview.setAdapter(adapter);
    }

    @Override
	protected void onDestroy() {
		super.onDestroy();
		person.close();
		db.close();
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }
    
    /**      * On selecting action bar icons      * */  
    @Override 
    public boolean onOptionsItemSelected(MenuItem item) {   
    	// Take appropriate action for each action item click       
    	switch (item.getItemId()) {      
    	case R.id.action_search:     
    		// search action        
    		return true;        
    		case R.id.action_add_person:   
    			// location found       
    			AddPerson();         
    			return true;       
    		case R.id.action_refresh:    
    				// refresh           
    				return true;    
    		case R.id.action_help:   
    					// help action        
    					return true;        
    		default:           
    							return super.onOptionsItemSelected(item);   
    	}  
    }    
    
    /**      * Launching new activity      * */ 
    private void AddPerson() {  
    	 Intent i = new Intent(getApplicationContext(),  Person_Activity.class);  
         i.putExtra("Value1", "Add Person");  
          // Set the request code to any code you like, you can identify the  
          // callback via this code  
          startActivity(i); 
    	} 
    	
    }
