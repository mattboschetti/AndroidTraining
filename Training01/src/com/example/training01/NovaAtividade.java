package com.example.training01;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class NovaAtividade extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nova_atividade);
		
		
		findViewById(R.id.button_save_activity).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText date = (EditText) findViewById(R.id.edit_date);
				String dateStr = date.getText() != null ? date.getText().toString() : ""; 
				EditText desc = (EditText) findViewById(R.id.edit_description);
				String descStr = desc.getText() != null ? desc.getText().toString() : "";
				
				Toast.makeText(getApplicationContext(), "Date: " + dateStr + "\nDesc: " + descStr, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nova_atividade, menu);
		return true;
	}

}
