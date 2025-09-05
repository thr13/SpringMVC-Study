package com.advanced.app.proxy.v3;

import org.springframework.stereotype.Service;

@Service
public class ProxyOrderServiceV3 {
    private final ProxyOrderRepositoryV3 orderRepository;

    public ProxyOrderServiceV3(ProxyOrderRepositoryV3 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
