package com.example.backend.controller;

import com.example.backend.domain.ActivePerson;
import com.example.backend.model.Greeting;
import com.example.backend.model.HelloMessage;
import com.example.backend.repo.ActivePersonRepository;
import com.example.backend.repo.NightlyPersonRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private final ActivePersonRepository activePersonRepository;
    private final NightlyPersonRepository nightlyPersonRepository;

    public GreetingController(ActivePersonRepository activePersonRepository, NightlyPersonRepository nightlyPersonRepository) {
        this.activePersonRepository = activePersonRepository;
        this.nightlyPersonRepository = nightlyPersonRepository;
    }

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay

        String fullName = message.getName();
        String[] parts = fullName != null ? fullName.split(" ", 2) : new String[]{"", ""};
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";

        boolean correct = nightlyPersonRepository.existsByFirstNameAndLastName(firstName, lastName);

        ActivePerson activePerson = new ActivePerson(firstName, lastName);
        activePerson.setGuessedCorrectly(correct);
        activePersonRepository.save(activePerson);

        String resultMessage = "Guessed: " + HtmlUtils.htmlEscape(fullName) + ". Result: " + (correct ? "Correct" : "Incorrect");
		System.out.println(resultMessage);
		return new Greeting(resultMessage);
	}

}