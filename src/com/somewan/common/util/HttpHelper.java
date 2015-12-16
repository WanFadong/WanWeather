package com.somewan.common.util;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;

@SuppressWarnings("deprecation")
public class HttpHelper {
	private static final int MAX_CONNECT = 5;
	private static int COUNT = 0;

	public static void sendHttpGet(final String address, final HttpCallbackListener listener) {
		COUNT++;
		sendHttpGet(address, listener, 1);
	}

	/**
	 * time表示尝试的次数,用于超时重连
	 * 
	 * @param address
	 * @param listener
	 * @param time
	 */
	private static void sendHttpGet(final String address, final HttpCallbackListener listener, int time) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpClient httpClient = null;
				HttpResponse httpResponse = null;
				try {
					httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(address);
					httpResponse = httpClient.execute(httpGet);
					// if (httpResponse.getStatusLine().getStatusCode() == 200)
					// {
					// 只要不抛出异常，不管成功还是失败（比如服务器问题503），都交给用户处理
					HttpEntity entity = httpResponse.getEntity();
					String response = EntityUtils.toString(entity, "utf-8");
					if (COUNT % 500 == 0) {
						System.out.println("已处理" + COUNT + "个请求");
					}
					if (listener != null) {
						listener.onFinish(response);
					}
					// }
				} catch (Exception e) {
					// 超时重连
					if (time < MAX_CONNECT) {
						int newTime = time;
						newTime++;
						sendHttpGet(address, listener, newTime);
					} else {
						// 日志记录；返回错误；
						log(address, httpResponse);
						if (listener != null) {
							listener.onError(e);
						}
					}
				} finally {
					httpClient.getConnectionManager().shutdown();
				}
			}
		}).start();
	}

	private static void log(String address, HttpResponse response) {
		// 记录address以及response的statuscode
		BufferedWriter writer = null;
		try {
			File file = new File("http_error_log.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(address);
			if (response != null) {
				int code = response.getStatusLine().getStatusCode();
				writer.write("|" + code);
			} else {
				writer.write("null");
			}
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
