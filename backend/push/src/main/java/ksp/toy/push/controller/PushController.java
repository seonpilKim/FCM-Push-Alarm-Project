package ksp.toy.push.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ksp.toy.push.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PushController {

	private final NotificationService notificationService;

	@PostMapping("/push")
	public String push(@RequestParam String token) {
		log.info("token : {}", token);
		notificationService.sendNotification(token);
		return "success";
	}

}
