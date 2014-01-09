package com.example.training01;

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
				String user = UIUtils.getEditTextFieldValue(R.id.txtUser, getActivity());
				String pwd = UIUtils.getEditTextFieldValue(R.id.txtPassword, getActivity());
				
				if (!UserData.validateUserAndPassword(user, pwd, getActivity())) {
					UIUtils.toast("Invalid user or password, try again! Tentative: " + loginRetries, getApplicationContext());
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
	
	private final Activity getActivity() {
		return this;
	}
	
	private void fillUserAndPasswordControls() {
		Map<String, String> userAndPassword = loadUserAndPassword();
		UIUtils.setEditTextFieldValue(R.id.txtUser, userAndPassword.get(LoginUtils.USER_KEY), this);
		UIUtils.setEditTextFieldValue(R.id.txtPassword, userAndPassword.get(LoginUtils.PASSWORD_KEY), this);
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
}
