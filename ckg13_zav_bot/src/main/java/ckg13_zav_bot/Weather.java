package ckg13_zav_bot;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;


import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Weather {
	
	
//токен подключения к сервису погоды
	private static final String API_KEY = "cc277aae9d1c740d04b2606344dba984";

//шаблон запроса
	private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?" + "lang=ru&"
			+ "units=metric&" + "q=%s&" + "appid=%s";

	public static String getWeather(String city) throws IOException {
//		public String getWeather(String city) throws IOException {
		//System.out.println("hello");
		// подставили в строку запроса название города и токен подключения

   		String requestUrl = String.format(REQUEST_URL, city, API_KEY);
		// выполнили запрос
		URL url = new URL(requestUrl);
		// тут лежит ответ, выполняю запрос и получаю ответ
		URLConnection conn = url.openConnection();

		// извлекаю данные из ответа
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);

		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedReader reader = new BufferedReader(isr);

		StringBuffer buffer = new StringBuffer();

		// записали все строки из ответа в буфер
		reader.lines().forEach(line -> buffer.append(line));

		System.out.println("buffer - " + buffer);

		
		String result = buffer.toString();
		


		
		System.out.println("result - " + result);

		// десериализация из джейсон json-строки в объект
		JSONObject json = new JSONObject(result);
		System.out.println("json-record  " + json);


		Map<String, Object> jsonMap = json.toMap();

		Map<String, Object> mainMap = (Map<String, Object>) jsonMap.get("main");
		System.out.println("jsonMap.get(\"main\"): " + mainMap);

		
		
		int temp;
		if (mainMap.get("temp") instanceof BigDecimal) {
			temp = ((BigDecimal) mainMap.get("temp")).intValue();
		} else {
			temp = (Integer) mainMap.get("temp");
		}


		Integer humid = (Integer) mainMap.get("humidity");
		Integer press = (Integer) mainMap.get("pressure");

		Integer visib = (Integer) jsonMap.get("visibility");

		city = (String) jsonMap.get("name");
		
		// получение массива
		JSONArray jarr = (JSONArray) json.getJSONArray("weather");
		String descr = jarr.getJSONObject(0).getString("description");
		for (int m = 0; m < jarr.length(); m++) {
			System.out.println("JSONArray: length- " + jarr.length() +"  The " + m + 
					" element of the array: " + descr);
					
		}
result = String.format("Город %s:\nтемпература: %+d\nописание: %s\nвлажность: %d\nдавление: %d\nвидимость: %d", city,temp,descr,humid,press,visib);

		return result;
		

	}

}










