package com.tcs.aggregator.controller;

import com.tcs.aggregator.dto.Aggregator;
import com.tcs.aggregator.dto.Order;
import com.tcs.aggregator.dto.Product;
import com.tcs.aggregator.feign.OrderClient;
import com.tcs.aggregator.feign.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregatorController
{
    @Autowired
     ProductClient productClient;

    @Autowired
     OrderClient orderClient;

    @GetMapping("/aggregator/{id}")
    public ResponseEntity<Aggregator> aggregator(@PathVariable Long id)
    {
        List<Order> orders=this.orderClient.getOrderByProduct(id).getBody();
        Product product=this.productClient.getProduct(id).getBody();

        return ResponseEntity.ok(new Aggregator(product,orders));
    }
}
