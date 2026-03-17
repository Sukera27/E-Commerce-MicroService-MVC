package com.sukera.orders_service.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.sukera.orders_service.controller.converter.Converter;
import com.sukera.orders_service.controller.dto.InventoryResponse;
import com.sukera.orders_service.controller.dto.OrderDTO;
import com.sukera.orders_service.events.OrderEvent;
import com.sukera.orders_service.persistance.enums.OrderStatus;
import com.sukera.orders_service.persistance.model.Order;
import com.sukera.orders_service.service.OrderService;
import com.sukera.orders_service.utils.JsonUtils;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/api/v1/orders/")
@CrossOrigin
@AllArgsConstructor
public class OrderController {

     OrderService orderService;

     Converter converter;

     WebClient.Builder webClientBuilder;
     KafkaTemplate<String, String> kafkaTemplate;
     ObservationRegistry observationRegistry;

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO){
        
        Observation inventoryObservation = Observation.createNotStarted("inventory-service", observationRegistry);
        return inventoryObservation.observe(() -> {
        
        
        List<InventoryResponse> inventoryResponse = this.webClientBuilder.build()
            .post()
            .uri("http://localhost:8080/api/v1/inventory/in-stock")
            .bodyValue(orderDTO.getItems())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<InventoryResponse>>() {})
            .block();

        
        if (inventoryResponse == null || inventoryResponse.stream().anyMatch(ir -> !ir.inStock())) {
            throw new RuntimeException("Stock no disponible");
        }

        Order order = converter.toOrder(orderDTO);
        Order saved = orderService.createOrder(order);

        this.kafkaTemplate.send("orders-topics", saved.getOrderNumber(), 
            JsonUtils.toJson(new OrderEvent(saved.getOrderNumber(), saved.getItems().size(), OrderStatus.PLACED)));
        
        return ResponseEntity.ok().body(converter.toOrderDTO(saved));
        });
    }
    
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
       return ResponseEntity.ok().body(orderService.getAllOrders().stream().map(converter::toOrderDTO).toList());
    }


    
}
