package com.sukera.orders_service.events;

import com.sukera.orders_service.persistance.enums.OrderStatus;

public record OrderEvent(String orderId, int itemCount, OrderStatus status) {

}
