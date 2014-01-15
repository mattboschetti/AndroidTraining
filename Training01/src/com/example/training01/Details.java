package com.example.training01;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Details extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		String user = getIntent().getExtras().getString(LoginUtils.USER_KEY);
		String pwd = getIntent().getExtras().getString(LoginUtils.PASSWORD_KEY);
		
		TextView welcomeMessage = (TextView) findViewById(R.id.viewDetailsContent);
		welcomeMessage.setText("Bem vindo, " + user);

		TextView hoursTotal = (TextView) findViewById(R.id.viewHoursTotal);
		hoursTotal.setText("Total de horas do mes eh: " + pwd.length());
		
		findViewById(R.id.buttonAdicionarAtividades).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentNovaActivity = new Intent(getBaseContext(), NewTask.class);
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
	protected void onStart() {
		super.onStart();
		TaskDAO dao = new TaskDAO();
		List<String> all = dao.listAll(getApplicationContext());
		fillTaskList(all);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}
	
	private void fillTaskList(List<String> tasks) {
        ListView mDrawerList = (ListView) findViewById(R.id.listView);
        mDrawerList.setAdapter(new TaskAdapter(getBaseContext(), R.layout.task_item, tasks));
	}
	
	public class TaskAdapter extends ArrayAdapter<String> {

        private List<String> itens;

        public TaskAdapter(Context context, int textViewResourceId, List<String> itens) {
                super(context, textViewResourceId, itens);
                this.itens = itens;        
        }

        @Override
        public int getCount() {
                return itens.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View gridView = convertView;
                if (convertView == null) { // if it's not recycled, initialize some
                        // attributes
                        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        gridView = inflater.inflate(R.layout.task_item, parent, false);
                }
                String task = itens.get(position);
                ((TextView) gridView.findViewById(R.id.the_task_item)).setText(task);
                return gridView;
        }

	}


}
