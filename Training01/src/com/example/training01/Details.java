package com.example.training01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Details extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		String user = getIntent().getExtras().getString("user");
		String pwd = getIntent().getExtras().getString("pwd");
		
		TextView welcomeMessage = (TextView) findViewById(R.id.viewDetailsContent);
		welcomeMessage.setText("Bem vindo, " + user);

		TextView hoursTotal = (TextView) findViewById(R.id.viewHoursTotal);
		hoursTotal.setText("Total de horas do mes eh: " + pwd.length());
		
		findViewById(R.id.buttonAdicionarAtividades).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentNovaActivity = new Intent(getBaseContext(), NovaAtividade.class);
				startActivity(intentNovaActivity);
			}
		});
		
		findViewById(R.id.buttonFecharAplicacao).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
