package com.example.training01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int started;
	private int stopped;
	private static final String USER_KEY = "user_key";
	private static final String PWD_KEY = "pwd_key";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		findViewById(R.id.btnOk).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText txtUser = (EditText) findViewById(R.id.txtUser);
				EditText txtPwd = (EditText) findViewById(R.id.txtPassword);
				String user = txtUser.getText() != null ? txtUser.getText().toString() : "Banzo";
				String pwd = txtPwd.getText() != null ? txtPwd.getText().toString() : "Pwd";
				
				saveUserAndPassword(user, pwd);
				
				Intent detailsIntent = new Intent(getBaseContext(), Details.class);
				detailsIntent.putExtra("user", user);
				detailsIntent.putExtra("pwd", pwd);
				startActivity(detailsIntent);
				finish();
			}
		});
		started = 1;
		stopped = 1;
	}
	
	private void fillUserAndPasswordControls() {
		String[] userAndPassword = loadUserAndPassword();
		EditText txtUser = (EditText) findViewById(R.id.txtUser);
		txtUser.setText(userAndPassword[0]);
		EditText txtPwd = (EditText) findViewById(R.id.txtPassword);
		txtPwd.setText(userAndPassword[1]);	
	}
	
	private void saveUserAndPassword(String user, String pwd) {
		Editor editor = getPreferences(MODE_PRIVATE).edit();
		editor.putString(USER_KEY, user);
		editor.putString(PWD_KEY, pwd);
		editor.apply();
		Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
	}
	
	private boolean isUserDataAlreadySaved() {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		return sharedPreferences.contains(USER_KEY) && sharedPreferences.contains(PWD_KEY);
	}
	
	private String[] loadUserAndPassword() {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

		String[] response = new String[2];
		response[0] = sharedPreferences.getString(USER_KEY, "user");
		response[1] = sharedPreferences.getString(PWD_KEY, "pwd");
		return response;
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("Stopped: " + stopped + " times");
		Toast.makeText(getApplicationContext(), "Stopped " + stopped + " times", Toast.LENGTH_SHORT).show();
		stopped++;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("Started: " + started + " times");
		Toast.makeText(getApplicationContext(), "Started " + started + " times", Toast.LENGTH_SHORT).show();
		started++;
		
		if (isUserDataAlreadySaved()) {
			fillUserAndPasswordControls();
			Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
