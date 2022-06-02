package com.company.car.inventory.respository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.company.car.inventory.model.Car;

import org.springframework.stereotype.Repository;

@Repository
public class InventoryCarRepository {

    Map<Integer, Car> inventoryCars;

    public InventoryCarRepository() {
        inventoryCars = buildInventoryCarList();
    }

    private Map<Integer, Car> buildInventoryCarList() {
        Car car1 = new Car();
        car1.setId(1);
        car1.setCarColor("red");
        car1.setCarType("sedan");
        car1.setYear(2015);
        car1.setMake("infiniti");
        car1.setModel("g35");

        Car car2 = new Car();
        car2.setId(2);
        car2.setCarColor("blue");
        car2.setCarType("suv");
        car2.setYear(2022);
        car2.setMake("toyota");
        car2.setModel("forerunner");

        Car car3 = new Car();
        car3.setId(3);
        car3.setCarColor("white");
        car3.setCarType("truck");
        car3.setYear(2001);
        car3.setMake("ford");
        car3.setModel("f150");

        return new HashMap<Integer, Car>() {{
            put(car1.getId(), car1);
            put(car2.getId(), car2);
            put(car3.getId(), car3);
        }};
    }

    public List<Car> getAll() {
        return inventoryCars.values().stream().collect(Collectors.toList());
    }

    public Car get(Integer id) {
        return inventoryCars.get(id);
    }

    public void save(Car car) {
        inventoryCars.put(car.getId(), car);
    }
}
