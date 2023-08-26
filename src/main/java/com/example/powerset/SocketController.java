package com.example.powerset;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/update")
    @SendTo("/topic/items")
    public String updateItems(String message) {
        // Process the message and update your data
        return message;
    }
}
