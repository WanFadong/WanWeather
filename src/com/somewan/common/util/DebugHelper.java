package com.somewan.common.util;

/**
 * debug帮助类。开启关闭调试信息； 输出类名和方法名。 默认情况下，不输出调试信息； 调试的时候设为true;
 * 如果不想输出某个类中的调试信息，可以调用时加入参数false；
 * 
 * @author fadon
 *
 */

public class DebugHelper {
	private static final boolean SYSTEM_DEBUG = true;

	/*
	 * static { SYSTEM_DEBUG =
	 * java.lang.management.ManagementFactory.getRuntimeMXBean().
	 * getInputArguments().toString() .indexOf("jdwp") >= 0; }
	 */

	public static boolean getDebugState() {
		return SYSTEM_DEBUG;
	}

	public static boolean getDebugState(boolean classDebug) {
		return SYSTEM_DEBUG && classDebug;// 只要有一个不是true，那么就不输出调试信息。
	}

	/*
	 * public static String getDebugLocation() { String clazz =
	 * Thread.currentThread().getStackTrace()[2].getClassName(); String method =
	 * Thread.currentThread().getStackTrace()[2].getMethodName(); String
	 * location = "调用" + clazz + "类的" + method + "方法"; return location; }
	 */
	/**
	 * 获取此方法被调用时所在的类和方法； 不允许被其他方法间接调用，必须在调试方法中直接调用；
	 */
	public static void echoDebugLocation() {
		if (SYSTEM_DEBUG) {
			String clazz = Thread.currentThread().getStackTrace()[2].getClassName();
			String method = Thread.currentThread().getStackTrace()[2].getMethodName();
			String location = "调用" + clazz + "类的" + method + "方法";
			System.out.println(location);
		}
	}

	public static void main(String[] args) {
		System.out.println(DebugHelper.getDebugState(true));

		DebugHelper.echoDebugLocation();
	}

}
