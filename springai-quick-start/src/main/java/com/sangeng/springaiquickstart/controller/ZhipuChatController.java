package com.sangeng.springaiquickstart.controller;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zhipu")
public class ZhipuChatController {


    private final ChatModel chatModel;

    public ZhipuChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello ";
    }

    @GetMapping("/simple")
    public String simple(@RequestParam(name = "query") String query) {
        // invoke model
        String call = chatModel.call(query);
        return call;
    }

    @GetMapping("/message")
    public String message(@RequestParam(name = "query") String query) {
        SystemMessage systemMessage = new SystemMessage("You are a helpful assistant.");
        UserMessage userMessage=new UserMessage(query);
        // invoke model
        String call = chatModel.call(systemMessage, userMessage);
    return call;
    }

    @GetMapping("/changeOptions")
    public ChatResponse chatOptions(@RequestParam(name = "query") String query){
        SystemMessage systemMessage = new SystemMessage("you are a helpful assistant.");
        UserMessage userMessage = new UserMessage(query);
        ZhiPuAiChatOptions zhiPuAiChatOptions = new ZhiPuAiChatOptions();
        zhiPuAiChatOptions.setModel("glm-4.5");
        zhiPuAiChatOptions.setMaxTokens(15536);
        zhiPuAiChatOptions.setTemperature(0.0);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage), zhiPuAiChatOptions);
        return chatModel.call(prompt);

    }
    @GetMapping("/chatResponse")
    public String chatResponse(@RequestParam(name = "query") String query){
        SystemMessage systemMessage = new SystemMessage("you are a helpful assistant.");
        UserMessage userMessage = new UserMessage(query);
        ZhiPuAiChatOptions zhiPuAiChatOptions = new ZhiPuAiChatOptions();
        zhiPuAiChatOptions.setModel("glm-4.5");
        zhiPuAiChatOptions.setMaxTokens(15536);
        zhiPuAiChatOptions.setTemperature(0.0);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage), zhiPuAiChatOptions);
        ChatResponse chatResponse = chatModel.call(prompt);
        return chatResponse.getResult().getOutput().getText();

    }

}

