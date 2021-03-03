package ckg13_zav_bot;

import java.io.IOException;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
public static void main(String[] args) throws TelegramApiException{
	//ApiContextInitializer.init();
//	System.getProperties().put("proxySet", "true");
//	System.getProperties().put("socketProxyHost", "127.0.0.1");
//	System.getProperties().put("socketProxyHost", "9150");
	
	TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
	api.registerBot(new Bot());
	System.out.println("Start");

/*	
	try {
	String s = Weather.getWeather("London");
	System.out.println(s);
	}
	catch (IOException e1){
		System.out.println("Города такого нет");
		e1.printStackTrace();
		
	}
*/	
	
}
}
