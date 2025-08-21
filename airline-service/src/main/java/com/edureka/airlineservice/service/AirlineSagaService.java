package com.edureka.airlineservice.service;

import com.edureka.airlineservice.domain.Order;
import com.edureka.airlineservice.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import jakarta.jms.Queue;

@Service
public class AirlineSagaService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private Queue sagaQueue;

    @JmsListener(destination = "airline-queue")
    public void listen(Order order) {
        System.out.println("Message Consumed: " + order);
        if (order.getOrderStatus().equals("NEW")) {
            order.setOrderStatus("AIRLINE_SUCCESS");
            airlineRepository.save(order);
        } else if (order.getOrderStatus().equals("HOTEL_FAILED")) {
            order.setOrderStatus("FAILED");
            airlineRepository.save(order);
        }
        jmsTemplate.convertAndSend(sagaQueue, order);
    }
}
