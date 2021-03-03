package ckg13_zav_bot;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONArray;
import org.json.*;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


public class JsonUtils {
	//токен подключения к сервису погоды
		private static final String API_KEY = "cc277aae9d1c740d04b2606344dba984";

	//шаблон запроса
		private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?" 
	+ "lang=ru&"
	+ "units=metric&" 
	+ "q=%s&" 
	+ "appid=%s";

		public static String getWeather(String city) throws IOException, ParseException {
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
			
			System.out.println(buffer);

			// System.out.println("done");
			String result = buffer.toString();

			// десериализация из джейсон json-строки в объект
			
	JSONObject weatherJO = (JSONObject)JSONValue.parseWithException(result);	
			System.out.println("name of city "+ weatherJO.get("name"));
			
	JSONArray weatherArray = (JSONArray)weatherJO.get("weather");
	
	JSONObject weatherData = (JSONObject)weatherArray.get(0);
	
	System.out.println("weather for this moment "+ weatherData.get("main"));
	System.out.println("description "+ weatherData.get("description"));
	
	//result = String.format(
	//		"Текущая температура в городе %s:  +%d \n описание %s: \n влажность: %d \n давление: %d \n видимость: %d",
	//		city, weatherJO, weatherArray, weatherData);
	
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("name", weatherJO);
	jsonObject.put("weather", 0);
	jsonObject.put("description",weatherData);
	
//	result = String.format("Текущая температура в городе %s:  +%d \n описание %s: \n ",
//			city, temp, weatherData.get("description") );
			return jsonObject.toString();
			// }

		}

	
}
