package ckg13_zav_bot;

import java.io.IOException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

	// get request from telefram and send answers
	// принимает запросы от кregisterBotлиента-Телеграм и отправляет ответы
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
			// System.out.println("some mess has gotten!");

			//сообщение от бота
			Message msg = update.getMessage();
			String text = msg.getText();
			System.out.println(text);

			try {
				//отправляется ответ
				text = Weather.getWeather(text);
				
			} catch (IOException e1) {
				
				System.out.println("Города такого нет");
				e1.printStackTrace();
			}
			
//			try {
//				
//			     text = JsonUtils.getWeather(text);
//			
//			}    catch (org.json.simple.parser.ParseException | IOException e){
//				         e.printStackTrace();
//			     }
			//ответ боту
			SendMessage answer = new SendMessage();
			answer.setChatId(msg.getChatId().toString());
			answer.setText(text);
			//answer.setText("yr message was: " + text);
			
			
			
			try {
				// отправка ответа боту
				execute(answer);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		}
	}

	// возвращает юзернейм бота
	public String getBotUsername() {
		//return "SomeNewU_bot";
		return "Ckg13_yu_bot";
	}

	// возвращает токен для подключения к боту
	@Override
	public String getBotToken() {
		//return "1699868671:AAFLrB44nXRNfEWwFtw5_gkImIYzt3q0xkI";
		return "1615540082:AAGt834-a8PRojgdtsWMk6KFvi8V5J3Bwd8";
	}

}
