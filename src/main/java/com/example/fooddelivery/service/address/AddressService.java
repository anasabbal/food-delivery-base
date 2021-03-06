package com.example.fooddelivery.service.address;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface AddressService {

    Page<AddressDto> getAll(Pageable pageable);
    Address update(String addressId, AddressCommand addressCommand);

    void deleteAddress(String addressId);

    Address findAddressById(String addressId);
    public Set<Address> findById(Set<String> id);
}
