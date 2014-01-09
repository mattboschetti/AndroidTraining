package com.example.training01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

public class UserData {

	public static boolean validateUserAndPassword(String user, String password, Activity activity) {
		try {
			return getStoredUserAndPassword(user, password, activity);
		} catch (FileNotFoundException e) {
			return saveUserAndPassword(user, password, activity);
		}
	}

	private static boolean saveUserAndPassword(String user, String password, Activity activity) {
		try {
			FileOutputStream fileWrite = activity.openFileOutput(LoginUtils.USER_DETAILS_FILE, Context.MODE_PRIVATE);
			fileWrite.write((user + "," + password).getBytes());
			return true;
		} catch (Exception e) {
			UIUtils.toast(e.getMessage(), activity.getApplicationContext());
		}
		return false;
	}

	private static boolean getStoredUserAndPassword(String user, String password, Activity activity) throws FileNotFoundException {
		FileInputStream fileRead = activity.openFileInput(LoginUtils.USER_DETAILS_FILE);
		int content;
		StringBuffer sb = new StringBuffer();
		try {
			while ((content = fileRead.read()) != -1) {
				sb.append(Character.toString((char) content));
			}
			String[] userData = sb.toString().split(",");
			return userData[0].equals(user) && userData[1].equals(password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
