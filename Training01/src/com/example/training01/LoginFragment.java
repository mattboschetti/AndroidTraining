package com.example.training01;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


public class LoginFragment extends Fragment {
	
	private int loginRetries;
	private OnLoginClickListener mLoginClickListener;
	
	public interface OnLoginClickListener {
		public void onClick(String message);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		loginRetries = 1;
		
		getView().findViewById(R.id.btnOk).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String user = UIUtils.getEditTextFieldValue(R.id.txtUser, getActivity());
				String pwd = UIUtils.getEditTextFieldValue(R.id.txtPassword, getActivity());
				
				if (!UserData.validateUserAndPassword(user, pwd, getActivity())) {
					UIUtils.toast("Invalid user or password, try again! Tentative: " + loginRetries, getActivity().getApplicationContext());
					loginRetries++;
					return;
				}
				saveUserAndPassword(user, pwd);
				
				Intent detailsIntent = new Intent(getActivity().getBaseContext(), DetailsActivity.class);
				detailsIntent.putExtra(LoginUtils.USER_KEY, user);
				detailsIntent.putExtra(LoginUtils.PASSWORD_KEY, pwd);
				
				mLoginClickListener.onClick("Callback called");
				
				startActivity(detailsIntent);
				getActivity().finish();
			}
		});
	}	
	
	private void saveUserAndPassword(String user, String pwd) {
		Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
		editor.putString(LoginUtils.USER_KEY, user);
		editor.putString(LoginUtils.PASSWORD_KEY, pwd);
		editor.apply();
	}
	
	private void fillUserAndPasswordControls() {
		Map<String, String> userAndPassword = loadUserAndPassword();
		UIUtils.setEditTextFieldValue(R.id.txtUser, userAndPassword.get(LoginUtils.USER_KEY), this.getView());
		UIUtils.setEditTextFieldValue(R.id.txtPassword, userAndPassword.get(LoginUtils.PASSWORD_KEY), this.getView());
	}
	
	private boolean isUserDataAlreadySaved() {
		SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
		return sharedPreferences.contains(LoginUtils.USER_KEY) && sharedPreferences.contains(LoginUtils.PASSWORD_KEY);
	}
	
	private Map<String, String> loadUserAndPassword() {
		SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

		Map<String, String> data = new HashMap<String, String>();
		data.put(LoginUtils.USER_KEY, sharedPreferences.getString(LoginUtils.USER_KEY, "user"));
		data.put(LoginUtils.PASSWORD_KEY, sharedPreferences.getString(LoginUtils.PASSWORD_KEY, "pwd"));
		return data;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (isUserDataAlreadySaved()) fillUserAndPasswordControls();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof OnLoginClickListener)) throw new IllegalArgumentException("Activity must extend OnLoginClickListener");
		mLoginClickListener = (OnLoginClickListener) activity;
	}

}
