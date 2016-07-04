package com.liangmayong.androidblock.iconfont;

/**
 * IconValue
 * 
 * @author LiangMaYong
 * @version 1.0
 */
public class IconValue {

	private int unicode_value;
	private String fontPath;

	public String getFontPath() {
		return fontPath;
	}

	public int getUnicodeValue() {
		return unicode_value;
	}

	/**
	 * IconValue
	 * 
	 * @param fontPath
	 *            fontPath
	 * @param unicode_value
	 *            unicode_value
	 */
	public IconValue(String fontPath, int unicode_value) {
		this.fontPath = fontPath;
		this.unicode_value = unicode_value;
	}

}
