package com.example.training01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class NewTaskFragment extends Fragment {
	
	private OnAddTaskListener mAddTaskListener;
	
	public interface OnAddTaskListener {
		public void onTaskAdd();		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return (ViewGroup) inflater.inflate(R.layout.fragment_new_task, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivity().findViewById(R.id.button_save_activity).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText date = (EditText) getActivity().findViewById(R.id.edit_date);
				String dateStr = date.getText() != null ? date.getText().toString() : ""; 
				EditText desc = (EditText) getActivity().findViewById(R.id.edit_description);
				String descStr = desc.getText() != null ? desc.getText().toString() : "";
				
				if (dateStr.trim().isEmpty() || descStr.trim().isEmpty()) {
					Toast.makeText(getActivity().getApplicationContext(), "Preencha os campos acima! E cozinha menos!", Toast.LENGTH_SHORT).show();
					return;
				}
				
				new TaskDAO().saveData(getContext(), dateStr, descStr);
				
				mAddTaskListener.onTaskAdd();
			}
		});
	}
	
	private final Context getContext() {
		return getActivity().getApplicationContext();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof OnAddTaskListener)) throw new IllegalArgumentException("Activity must implement OnAddTaskListener");
		mAddTaskListener = (OnAddTaskListener) activity;
	}
	
	

}
