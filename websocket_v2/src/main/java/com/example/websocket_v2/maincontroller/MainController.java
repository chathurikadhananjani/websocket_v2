package com.example.websocket_v2.maincontroller;

import com.example.websocket_v2.dto.MessageDto;
import com.example.websocket_v2.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MainController {


    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final MessageDto message) throws InterruptedException {

        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));

    }


    @SubscribeMapping("/subscribe")
    public String sendOneTimeMessage(){
        return "server one-time message via the application";
    }




}
