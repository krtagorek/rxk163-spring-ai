package dev.rxk163.spring_ai_prompts;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class PromptYouTubeController {
	
	private final ChatClient chatClient;
	
	@Value("classpath:/prompts/youtube.st")
	private Resource ytPromptResource;
	
	
	public PromptYouTubeController(ChatClient.Builder chatClientBuilder) {
		//super();
		this.chatClient = chatClientBuilder.build();
	}

	//http://localhost:8080/youtube/popular?genre=music
	//http://localhost:8080/youtube/popular --no parameter brings back default genre tech
		

	@GetMapping("popular")
	public String findPopularYouTubersByGenre(@RequestParam(value="genre", defaultValue="tech") String genre) {
		
		PromptTemplate promptTemplate = new PromptTemplate(ytPromptResource);
		Prompt prompt = promptTemplate.create(Map.of("genre",genre));
		
		return chatClient.prompt(prompt).call().content();
		
	}

}
