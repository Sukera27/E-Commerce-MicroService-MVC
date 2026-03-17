package com.sukera.notification_server.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.sukera.notification_server.events.OrderEvent;
import com.sukera.notification_server.utils.JsonUtils;

@Component
public class OrderEventsListener {

    @KafkaListener(topics = "orders-topics", groupId = "notification-server")
    public void handleOrderEvent(String message){
        var event = JsonUtils.fromJson(message, OrderEvent.class);
        System.out.println("Received message: " + message);
        System.out.println("Event details: " + event);
    }
}
