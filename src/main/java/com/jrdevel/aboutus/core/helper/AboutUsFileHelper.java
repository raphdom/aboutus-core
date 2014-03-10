package com.jrdevel.aboutus.core.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Raphael Domingues
 *
 */
public class AboutUsFileHelper {

	public static boolean imageResizeSupported(String fileType){
		String[] filesSupported = {"jpeg","gif","bmp","png","tiff"};
		for (String ft : filesSupported){
			if (fileType.contains(ft)){
				return true;
			}
		}
		return false;
	}

	public static String getNameOfFile(String mediaPath){

		File directory = new File(mediaPath + year() + "/" + monthDay()+"/");

		if (!directory.exists()){
			directory.mkdirs();
		}

		return mediaPath + year() + "/" + monthDay() + "/" + generateUniqueFileName("upload-",".bin");

	}

	public static String year() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(cal.getTime());

	}

	public static String monthDay() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		return sdf.format(cal.getTime());

	}

	public static String generateUniqueFileName(String prefix, String suffix){
		return (prefix != null ? prefix : "" ) + System.nanoTime() + (suffix != null ? suffix : "" ) ;
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		byte[] bytes = new byte[(int)length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}

		is.close();
		return bytes;
	}

}
