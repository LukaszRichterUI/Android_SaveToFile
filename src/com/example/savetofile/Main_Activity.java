package com.example.savetofile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main_Activity extends Activity 
{ //main activity

	//SD Storage
	String extStorageDirectory = null;
	
	// GUI elements
	EditText editTextBox = null;
	Button saveToFile = null;
	
	//Log tag
	private static String TAG = "SAVE_FILE_TO_SD";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{// onCreate
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//initialize GUI elements
		InitializeGUI();
		
		//Button
		MakeButtonListener();

	}//onCreate

	
	//Initialize UI element
	private void InitializeGUI()
	{
		saveToFile = (Button) findViewById(R.id.saveButton);
		editTextBox = (EditText) findViewById(R.id.editTextBox);
	}
	
	//button
	private void MakeButtonListener()
	{
		saveToFile.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {	
            	String textToSave = editTextBox.getText().toString();
        		
            	//get the external storage directory
        		extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        		Log.i(TAG, "External Directory: " + extStorageDirectory);
        		
            	//save operation
            	try
            	{
            		//make a directory for the file
            		File myDirectory = new File("/sdcard/Save_to_File/");
            		myDirectory.mkdirs();
            		Log.i(TAG, "Made the directory");
            		
            		//make the files 
            		Log.i(TAG, "Directory is: " + extStorageDirectory + "/Save_to_File/");
            		
            		File mySaveFile = new File(extStorageDirectory, extStorageDirectory + "mySaveFile.txt");
            		mySaveFile.createNewFile();
            		
            		FileOutputStream fOut = new FileOutputStream(mySaveFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            		
                    myOutWriter.append(textToSave);
                    
                    myOutWriter.close();
                    fOut.close();
                    
                    Log.i(TAG, "File Successfully written to SD card");
            	}
            	catch(IOException e)
            	{
            		Log.i(TAG, "FAILURE WRITTING TO SD: " + e.getMessage().toString());
            	}
            	
            }
        });
	}
	//button
}// main activity
