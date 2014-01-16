package com.example.training01;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UIUtils {

	public static void toast(String message, Context context) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	public static String getEditTextFieldValue(int id, Activity activity) {
		EditText editText = (EditText) activity.findViewById(id);
		return editText.getText() != null ? editText.getText().toString() : "";
	}
	
	public static void setEditTextFieldValue(int id, String value, Activity activity) {
		EditText txtUser = (EditText) activity.findViewById(id);
		txtUser.setText(value);
	}
	
	public static void setEditTextFieldValue(int id, String value, View view) {
		EditText txtUser = (EditText) view.findViewById(id);
		txtUser.setText(value);
	}
	
	public static void hideShowComponent(int component, int visibility, Activity activity) {
		View view = (View) activity.findViewById(component);
		view.setVisibility(visibility);
	}
	
}
