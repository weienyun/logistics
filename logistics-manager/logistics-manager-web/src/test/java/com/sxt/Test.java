package com.sxt;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Test {
	public static void main(String[] args) {
		String s ="";
		Integer i = null;
		if (s==null||"".equals(s)) {
			System.out.println(i);
		}else{
			System.out.println(Integer.parseInt(s));
		}
		String ss = " ";
		System.out.println(s==ss);
		
	}
}
