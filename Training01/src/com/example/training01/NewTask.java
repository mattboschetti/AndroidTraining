package com.example.training01;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class NewTask extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);

		findViewById(R.id.button_save_activity).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText date = (EditText) findViewById(R.id.edit_date);
				String dateStr = date.getText() != null ? date.getText().toString() : ""; 
				EditText desc = (EditText) findViewById(R.id.edit_description);
				String descStr = desc.getText() != null ? desc.getText().toString() : "";
				
				if (dateStr.trim().isEmpty() || descStr.trim().isEmpty()) {
					Toast.makeText(getApplicationContext(), "Preencha os campos acima! E cozinha menos!", Toast.LENGTH_SHORT).show();
					return;
				}
				
				Toast.makeText(getApplicationContext(), "Date: " + dateStr + "\nDesc: " + descStr, Toast.LENGTH_SHORT).show();
				finish();
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
