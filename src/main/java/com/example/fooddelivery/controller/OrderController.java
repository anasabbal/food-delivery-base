package com.example.fooddelivery.controller;



import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.mapper.AddressMapper;
import com.example.fooddelivery.mapper.OrderMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;
    private final AddressMapper addressMapper;


    @GetMapping("/all")
    public ResponseEntity<Page<OrderDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(orderService.getAll(pageable));
    }
    @PostMapping("/{orderId}/billingAddress")
    public ResponseEntity<AddressDto> addBillingAddressToOrder(@PathVariable("orderId") final String orderId, @RequestBody final AddressCommand addressCommand){
        final Address address = orderService.addBillingAddressToOrder(orderId, addressCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(addressMapper.toAddressDto(address));
    }
    @PostMapping("/{orderId}/shippingAddress")
    public ResponseEntity<AddressDto> addShippingAddressToOrder(@PathVariable("orderId") String orderId, @RequestBody final AddressCommand addressCommand){
        final Address address = orderService.addShippingAddressToOrder(orderId, addressCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(addressMapper.toAddressDto(address));
    }
}