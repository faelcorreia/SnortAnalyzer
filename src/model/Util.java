package model;

public class Util {
	public static String longToIP(long i) {
		String ip = "";
		ip += String.valueOf(i / 16777216) + ".";
		i = i - (i / 16777216) * 16777216;
		ip += String.valueOf(i / 65536) + ".";
		i = i - (i / 65536) * 65536;
		ip += String.valueOf(i / 256) + ".";
		i = i - (i / 256) * 256;
		ip += String.valueOf(i);
		return ip;
	}
}
