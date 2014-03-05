package com.mcc.myctiycard.app;

import java.util.LinkedList;
import java.util.List;

import afinal.FinalHttp;
import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{

	private List<Activity> mList = new LinkedList<Activity>();
	private static MyApplication instance;
	private static String session = "";
	private static boolean islogin = false;
	private static String username = "";
	private static String passwd = "";
	private static String phone = "";
	private static String email = "";
	
	// private SharedPreferences sharedPreferences;
	// private Editor editor;
	private MyApplication() {
	}

	public synchronized static MyApplication getInstance() {
		if (null == instance) {
			instance = new MyApplication();
		}
		return instance;
	}

	public List<Activity> getmList() {
		return mList;
	}

	// add Activity
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	public void removeActivity(Activity activity) {
		mList.remove(activity);

	}

	public void exit() {
		try {

			for (Activity activity : mList) {
				if (!activity.isFinishing())
					Log.v("axc", activity.getLocalClassName().toString());
				activity.finish();
			}
			mList.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
			FinalHttp.finishi();;

		}
	}

	public void exit0() {
		try {

			for (Activity activity : mList) {
				if (!activity.isFinishing())
					Log.v("axc", activity.getLocalClassName().toString());
				activity.finish();
			}
			mList.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FinalHttp.finishi();
		}
	}

	
	
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

	public static String getSession() {
		return session;
	}

	public static void setSession(String session) {
		MyApplication.session = session;
	}

	public static boolean isIslogin() {
		return islogin;
	}

	public static void setIslogin(boolean islogin) {
		MyApplication.islogin = islogin;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		MyApplication.username = username;
	}

	public static String getPasswd() {
		return passwd;
	}

	public static void setPasswd(String passwd) {
		MyApplication.passwd = passwd;
	}

	public static String getPhone() {
		return phone;
	}

	public static void setPhone(String phone) {
		MyApplication.phone = phone;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		MyApplication.email = email;
	}
	
	
	

}
