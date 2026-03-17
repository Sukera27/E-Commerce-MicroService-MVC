package com.sukera.notification_server.events;

import com.sukera.notification_server.enums.OrderStatus;

public record OrderEvent(String orderId, int itemCount, OrderStatus status) {

}
