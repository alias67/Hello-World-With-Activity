package com.example.action.bar;


import com.example.action.bar.R;
import com.example.database.MyDatabase;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;


public class Person_Activity extends Activity {

	//private Cursor person;
	//private MyDatabase db;
	
	String path="";
    private static final int SELECT_PICTURE = 1;
	private String selectedImagePath;
    private ImageView image1;
    
 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        
        Bundle extras = getIntent().getExtras();  
        String value1 = extras.getString("Value1");
        Toast.makeText(getApplicationContext(),"Values are: "+value1  
               ,Toast.LENGTH_LONG).show(); 
        
        
         image1= (ImageView) findViewById(R.id.imageView1);
       
        registerForContextMenu(image1);
        
        image1.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
            	  openContextMenu(image1);
            }
        });
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_person, menu);
        return true;
    }
    
   @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
       super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Post Image");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_image, menu);
        
        
    }
   
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
      switch (item.getItemId())
      {
      case R.id.action_Done:
    	  
    	  break;
          case R.id.take_photo:
              takePhoto();
              break;
         
          case R.id.choose_gallery:
              Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
              photoPickerIntent.setType("image/*");
              startActivityForResult(photoPickerIntent, 1);
             
              break;
       
          case R.id.share_cancel:
              closeContextMenu();
              break;
          default:
            return super.onContextItemSelected(item);
      }
      return true;
    }
    
    public void takePhoto()
    {
         Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
         startActivityForResult(intent, 2);
    }
    
    public void startGallery(){
    	 Intent intent = new Intent();
          intent.setType("image/*");
          intent.setAction(Intent.ACTION_GET_CONTENT);
          startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }
   
       public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
        if (requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPath(selectedImageUri);
            System.out.println("Image Path : " + selectedImagePath);
            image1.setImageURI(selectedImageUri);
        }
        else if (requestCode == 2){
        	Bitmap photo = (Bitmap) data.getExtras().get("data"); 
        	image1.setImageBitmap(photo);
        }
    }
       }

      public String getPath(Uri uri) {
    String[] projection = { MediaStore.Images.Media.DATA };
    Cursor cursor = managedQuery(uri, projection, null, null, null);
    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    return cursor.getString(column_index);
      }

}
