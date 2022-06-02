package com.company.car.inventory.service;

import java.util.List;

import com.company.car.inventory.model.Car;
import com.company.car.inventory.respository.InventoryCarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryCarService {
    @Autowired
    InventoryCarRepository repository;

    public List<Car> getAll() {
        return repository.getAll();
    }
    
    public Car get(Integer carId) {
        return repository.get(carId);
    }

    public void save(Car car) {
        repository.save(car);
    }

    public boolean delete(Integer carId) {
        return repository.delete(carId);
    }

}
