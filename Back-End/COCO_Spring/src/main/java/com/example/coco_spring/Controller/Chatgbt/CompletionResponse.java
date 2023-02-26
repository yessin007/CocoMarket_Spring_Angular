package com.example.coco_spring.Controller.Chatgbt;

import java.util.List;

public record CompletionResponse(Usage usage, List<Choice> choices) {
	
	public String firstAnswer() {
		if (choices == null || choices.isEmpty())
			return null;
		return choices.get(0).text;
	}
	
	record Usage(int total_tokens, int prompt_tokens, int completion_tokens) {}
	
	record Choice(String text) {}
}
