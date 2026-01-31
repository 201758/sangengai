package com.sangeng.springaiquickstart.controller;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

