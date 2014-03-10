package com.jrdevel.aboutus.core.util;

/**
 * @author Raphael Domingues
 *
 */
public class PasswordGenerator {
	
	public static String passGenerator(int passLength){
		java.util.Random r = new java.util.Random();
		char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
				'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '@', 'l' ,'L',
				'1', '2','3','!','|','"','#','$','%','&','/','(',')','='};
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < passLength; i++) {
			sb.append(goodChar[r.nextInt(goodChar.length)]);
		}
		return sb.toString();
	}

}
