package com.liangmayong.androidblock.utils;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * PreferencesUtils
 * 
 * @author LiangMaYong
 * @version 1.0
 */
public class PreferencesUtils {
	private static PreferencesUtils preferences;
	// DEFAULT_PREFERENCES_NAME
	public static final String DEFAULT_PREFERENCES_NAME = "app_preferences";

	/**
	 * getDefaultPreferences
	 * 
	 * @param context
	 *            context
	 * @return preferences
	 */
	public static PreferencesUtils getDefaultPreferences(Context context) {
		return getPreferences(context, DEFAULT_PREFERENCES_NAME);
	}

	/**
	 * getPreferences
	 * 
	 * @param context
	 *            context
	 * @param filename
	 *            filename
	 * @return preferences
	 */
	@SuppressLint("DefaultLocale")
	public static PreferencesUtils getPreferences(Context context, String filename) {
		if (preferences == null) {
			synchronized (PreferencesUtils.class) {
				preferences = new PreferencesUtils();
			}
		} else {
			preferences.setContext(context);
		}
		if (filename == null || "".equals(filename)) {
			filename = DEFAULT_PREFERENCES_NAME;
		}
		preferences.setSharedPreferencesName(filename.toLowerCase());
		return preferences;
	}

	private PreferencesUtils() {
	}

	private void setContext(Context context) {
		this.context = context;
	}

	private void setSharedPreferencesName(String sharedPreferencesName) {
		this.sharedPreferencesName = sharedPreferencesName;
	}

	private String sharedPreferencesName;
	private Context context;

	private Map<String, String> preferencesMap = new HashMap<String, String>();

	/**
	 * get string
	 * 
	 * @param key
	 *            key
	 * @param defValue
	 *            defValue
	 * @return string
	 */
	public String getString(String key, String defValue) {
		if (preferencesMap.containsKey(key)) {
			try {
				return (String) preferencesMap.get(key);
			} catch (Exception e) {
			}
		}
		String mString = "";
		try {
			SharedPreferences Host = context.getSharedPreferences(sharedPreferencesName, 0);
			mString = new String(DesUtils.decrypt(Host.getString(key, defValue), key));
		} catch (Exception e) {
		}
		return mString;
	}

	/**
	 * get int
	 * 
	 * @param key
	 *            key
	 * @param defValue
	 *            defValue
	 * @return int
	 */
	public int getInt(String key, int defValue) {
		int mInt = 0;
		try {
			String string = getString(key, defValue + "");
			mInt = Integer.parseInt(string);
		} catch (Exception e) {
		}
		return mInt;
	}

	/**
	 * get boolean
	 * 
	 * @param key
	 *            key
	 * @param defValue
	 *            defValue
	 * @return boolean
	 */
	public boolean getBoolean(String key, boolean defValue) {
		boolean retu = false;
		try {
			retu = "Yes".equals(getString(key, defValue ? "Yes" : "No"));
		} catch (Exception e) {
		}
		return retu;
	}

	/**
	 * get float
	 * 
	 * @param key
	 *            key
	 * @param defValue
	 *            defValue
	 * @return float
	 */
	public float getFloat(String key, float defValue) {
		float retu = 0;
		try {
			String string = getString(key, defValue + "");
			retu = Float.parseFloat(string);
		} catch (Exception e) {
		}
		return retu;
	}

	/**
	 * get long
	 * 
	 * @param key
	 *            key
	 * @param defValue
	 *            defValue
	 * @return long
	 */
	public long getLong(String key, long defValue) {
		long retu = 0;
		try {
			String string = getString(key, defValue + "");
			retu = Long.parseLong(string);
		} catch (Exception e) {
		}
		return retu;
	}

	/**
	 * set string
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void setString(String key, String value) {
		try {
			SharedPreferences.Editor Home = context.getSharedPreferences(sharedPreferencesName, 0).edit();
			Home.putString(key, DesUtils.encrypt(value.getBytes(), key));
			Home.commit();
			preferencesMap.put(key, value);
		} catch (Exception e) {
		}
	}

	/**
	 * set long
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void setLong(String key, long value) {
		setString(key, value + "");
	}

	/**
	 * set float
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void setFloat(String key, float value) {
		setString(key, value + "");
	}

	/**
	 * set boolean
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void setBoolean(String key, boolean value) {
		setString(key, value ? "Yes" : "No");
	}

	/**
	 * set int
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void setInt(String key, int value) {
		setString(key, value + "");
	}

	/**
	 * remove
	 * 
	 * @param key
	 *            key
	 */
	public void remove(String key) {
		try {
			SharedPreferences.Editor sp = context.getSharedPreferences(sharedPreferencesName, 0).edit();
			sp.remove(key);
			sp.commit();
		} catch (Exception e) {
		}
	}
}
