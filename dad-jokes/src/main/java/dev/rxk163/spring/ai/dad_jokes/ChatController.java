package dev.rxk163.spring.ai.dad_jokes;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	
	private final ChatClient chatClient;
	
	public ChatController(ChatClient.Builder chatClientBuilder) {
		super();
		this.chatClient = chatClientBuilder.build();
	}

	
	@GetMapping("/dad-jokes")
	public String generate(@RequestParam(value="message", defaultValue="Tell me a dad joke") String message) {
		
		return chatClient.prompt().user(message).call().content();
		
	}

	
}
