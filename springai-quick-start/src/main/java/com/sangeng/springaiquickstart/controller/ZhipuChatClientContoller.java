package com.sangeng.springaiquickstart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatClient")
public class ZhipuChatClientContoller {

    private final ChatClient chatClient;

    public ZhipuChatClientContoller(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/simple")
    public String simple(@RequestParam(name = "query") String query) {
        SystemMessage systemMessage = new SystemMessage("You are a helpful assistant.");
        UserMessage userMessage = new UserMessage(query);
        ZhiPuAiChatOptions zhiPuAiChatOptions = new ZhiPuAiChatOptions();
        zhiPuAiChatOptions.setModel("glm-4.5");
        zhiPuAiChatOptions.setMaxTokens(15536);
        zhiPuAiChatOptions.setTemperature(0.0);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage), zhiPuAiChatOptions);
        return chatClient.prompt(prompt)
                .call()
                .content();

    }
    @GetMapping("/chatClient")
    public String chatClient(@RequestParam(name = "query") String query){
        SystemMessage systemMessage = new SystemMessage("you are a helpful assistant.");
        UserMessage userMessage = new UserMessage(query);
        ZhiPuAiChatOptions zhiPuAiChatOptions = new ZhiPuAiChatOptions();
        zhiPuAiChatOptions.setModel("glm-4.5");
        zhiPuAiChatOptions.setMaxTokens(15536);
        zhiPuAiChatOptions.setTemperature(0.0);

        return chatClient.prompt()
                .system("you are a helpful assistant.")
                .user(query)
                .options(zhiPuAiChatOptions)
                .call()
                .content();
    }
}
