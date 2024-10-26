package com.example.FreshFarm.Delivery.service.impl;

import com.example.FreshFarm.Delivery.config.JwtService;
import com.example.FreshFarm.Delivery.mapper.OrderMapper;
import com.example.FreshFarm.Delivery.model.domain.Basket;
import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.order.OrderResponse;
import com.example.FreshFarm.Delivery.repository.BasketRepository;
import com.example.FreshFarm.Delivery.repository.OrderRepository;
import com.example.FreshFarm.Delivery.service.BasketService;
import com.example.FreshFarm.Delivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;
    private final OrderMapper orderMapper;
    private final JwtService jwtService;

    @Override
    public List<OrderResponse> customersOrder(String token) {
        User customer = jwtService.getUserFromToken(token);
        return orderMapper.toResponses(orderRepository.findAllByCustomer(customer));
    }

    @Override
    public void placeOrder(String token) {
        User user = jwtService.getUserFromToken(token);
        List<Order> orderList = new ArrayList<>();
        double totalPrice = 0.0;
        for (Basket basket : basketRepository.findAllByUser(user)) {
            totalPrice += basket.getKilo() * basket.getProduct().getPrice();
        }
        basketRepository.deleteAllByUser(user);
        orderRepository.save(orderMapper.toOrder(totalPrice, user));
    }
}
