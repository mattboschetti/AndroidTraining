package com.example.training01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int started;
	private int stopped;

	
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
				
				Intent detailsIntent = new Intent(getBaseContext(), Details.class);
				detailsIntent.putExtra("user", user);
				detailsIntent.putExtra("pwd", pwd);
				startActivity(detailsIntent);
			}
		});
		started = 1;
		stopped = 1;
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
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
