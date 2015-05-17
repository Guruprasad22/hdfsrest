package com.playground.httpfs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class RestClientService {
	
	public static String getDataFromUrl(String urlString) {
		String result = null;
		HttpURLConnection connection = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);			
//			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect();

			// Get the response
			StringBuffer sb = new StringBuffer();
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			result = sb.toString();
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;		
	}
}
