package com.advanced.app.proxy.v3;

import com.advanced.app.proxy.v2.ProxyOrderRepositoryV2;
import org.springframework.stereotype.Service;

@Service
public class ProxyOrderServiceV3 {
    private final ProxyOrderRepositoryV2 orderRepository;

    public ProxyOrderServiceV3(ProxyOrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
