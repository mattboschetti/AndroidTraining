package com.example.training01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private int started;
	private int stopped;
	private int loginRetries;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		started = 1;
		stopped = 1;
		loginRetries = 1;
		
		findViewById(R.id.btnOk).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String user = getEditTextFieldValue(R.id.txtUser);
				String pwd = getEditTextFieldValue(R.id.txtPassword);
				
				if (!validateUserAndPassword(user, pwd)) {
					toast("Invalid user or password, try again! Tentative: " + loginRetries);
					loginRetries++;
					return;
				}
				saveUserAndPassword(user, pwd);
				
				Intent detailsIntent = new Intent(getBaseContext(), Details.class);
				detailsIntent.putExtra(LoginUtils.USER_KEY, user);
				detailsIntent.putExtra(LoginUtils.PASSWORD_KEY, pwd);
				startActivity(detailsIntent);
				finish();
			}
		});
	}
	
	private boolean validateUserAndPassword(String user, String password) {
		try {
			FileInputStream fileRead = openFileInput(LoginUtils.USER_DETAILS_FILE);
			int content;
			StringBuffer sb = new StringBuffer();
			try {
				while ((content = fileRead.read()) != -1) {
					sb.append(Character.toString((char) content));
				}
				//toast(sb.toString());
				String[] userData = sb.toString().split(",");
				return userData[0].equals(user) && userData[1].equals(password);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				FileOutputStream fileWrite = openFileOutput(LoginUtils.USER_DETAILS_FILE, MODE_PRIVATE);
				fileWrite.write((user + "," + password).getBytes());
				return true;
			} catch (FileNotFoundException e1) {
				toast(e1.getMessage());
			} catch (IOException e1) {
				toast(e1.getMessage());
			}
		}
		return false;
	}
	
	private String getEditTextFieldValue(int id) {
		EditText editText = (EditText) findViewById(id);
		return editText.getText() != null ? editText.getText().toString() : "";
	}
	
	private void setEditTextFieldValue(int id, String value) {
		EditText txtUser = (EditText) findViewById(id);
		txtUser.setText(value);
	}
	
	private void fillUserAndPasswordControls() {
		Map<String, String> userAndPassword = loadUserAndPassword();
		setEditTextFieldValue(R.id.txtUser, userAndPassword.get(LoginUtils.USER_KEY));
		setEditTextFieldValue(R.id.txtPassword, userAndPassword.get(LoginUtils.PASSWORD_KEY));
	}
	
	private void saveUserAndPassword(String user, String pwd) {
		Editor editor = getPreferences(MODE_PRIVATE).edit();
		editor.putString(LoginUtils.USER_KEY, user);
		editor.putString(LoginUtils.PASSWORD_KEY, pwd);
		editor.apply();
	}
	
	private boolean isUserDataAlreadySaved() {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		return sharedPreferences.contains(LoginUtils.USER_KEY) && sharedPreferences.contains(LoginUtils.PASSWORD_KEY);
	}
	
	private Map<String, String> loadUserAndPassword() {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

		Map<String, String> data = new HashMap<String, String>();
		data.put(LoginUtils.USER_KEY, sharedPreferences.getString(LoginUtils.USER_KEY, "user"));
		data.put(LoginUtils.PASSWORD_KEY, sharedPreferences.getString(LoginUtils.PASSWORD_KEY, "pwd"));
		return data;
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Toast.makeText(getApplicationContext(), "Stopped " + stopped + " times", Toast.LENGTH_SHORT).show();
		stopped++;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Toast.makeText(getApplicationContext(), "Started " + started + " times", Toast.LENGTH_SHORT).show();
		started++;
		
		if (isUserDataAlreadySaved()) fillUserAndPasswordControls();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void toast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}

}
