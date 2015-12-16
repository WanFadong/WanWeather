package com.somewan.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileHelper {
	private static int COUNT = 0;

	public static void writeLog(String fileName, String content) {
		COUNT++;
		if (COUNT % 10 == 0) {
			if (DebugHelper.getDebugState())
				System.out.println("写入第" + COUNT + "个");
		}
		BufferedWriter writer = null;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			writer.write(content);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
