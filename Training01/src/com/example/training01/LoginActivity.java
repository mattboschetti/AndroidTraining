package com.example.training01;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.Toast;

import com.example.training01.LoginFragment.OnLoginClickListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends FragmentActivity implements OnLoginClickListener {

	private static AsyncHttpClient client = new AsyncHttpClient();
	
	@Override
	public void onClick(String message) {
		UIUtils.toast(message, getApplicationContext());
	}
	
	private void requestSiteDoDeivison() {
		RequestParams params = new RequestParams();
		params.add("gender", "m");
		client.post("http://www.dueloamais.com.br:8080/dueloamais-api/ws/tag/list", params, new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
		    	UIUtils.toast(response.substring(1, response.length()-1).replaceAll("\"", ""), getApplicationContext());
		    }
		    
		    @Override
		    public void onFailure(Throwable error) {
		    	UIUtils.toast(error.getMessage(), getApplicationContext());
		    }
		});
	}
	
	private int started;
	private int stopped;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		openLoginFragment();
		requestSiteDoDeivison();
		started = 1;
		stopped = 1;
		
	}
	
	private void openLoginFragment() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.login_container, new LoginFragment(), "loginFragment");
		transaction.commit();
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
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
