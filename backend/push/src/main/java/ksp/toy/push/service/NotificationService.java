package ksp.toy.push.service;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {

	@Value("${fcm.key.path}")
	private String FCM_KEY_PATH;

	@PostConstruct
	public void init() {
		try {
			final ClassPathResource resource = new ClassPathResource(FCM_KEY_PATH);
			final FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
				.build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendNotification(String token) {
		final String now = LocalDateTime.now().toString();
		final Notification notification = Notification.builder()
			.setTitle("생일 케이크 꾸미기")
			.setBody("누군가 내 케이크에 장식을 추가했어요!")
			.setImage(
				"https://s3.us-west-2.amazonaws.com/secure.notion-static.com/528b7881-f0a5-441a-8855-33bd2bb8a6d0/cake.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220923%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220923T030922Z&X-Amz-Expires=86400&X-Amz-Signature=ac9b90c9a1e6928480df4b4ad73085cf172039936590c5f34a66898f2db32dad&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22cake.png%22&x-id=GetObject")
			.build();
		final Message message = Message.builder()
			.putData("time", now)
			.setNotification(notification)
			.setToken(token)
			.build();

		try {
			final String response = FirebaseMessaging.getInstance().send(message);
			log.debug("Send Notification Response: {}", response);
		} catch (FirebaseMessagingException e) {
			log.error("[FirebaseMessagingException] ", e);
			throw new RuntimeException(e);
		}
	}

}
