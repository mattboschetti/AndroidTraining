package com.example.training01.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.example.training01.LoginActivity;
import com.example.training01.LoginFragment;

public class DetailActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public DetailActivityTest(Class<LoginActivity> activityClass) {
		super(activityClass);
	}
	
	public DetailActivityTest() {
		super(LoginActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testLoginFragmentCreated() {
		LoginFragment login = (LoginFragment) getActivity().getSupportFragmentManager().findFragmentByTag("loginFragment");
		Assert.assertNotNull(login);
	}
	
}
