package com.company.car.inventory.api;

import com.company.car.inventory.model.Car;
import com.company.car.inventory.model.InventoryCarGet400Response;
import com.company.car.inventory.service.InventoryCarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-01T09:25:42.493746-05:00[America/Chicago]")
@Controller
@RequestMapping("${openapi.companyCarInventoryService.base-path:/v1}")
public class InventoryApiController implements InventoryApi {

    @Autowired
    private InventoryCarService service;

    private final NativeWebRequest request;

    @Autowired
    public InventoryApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<Car>> inventoryCarGet(Integer limit,Integer offset) {
        
        return Optional
            .ofNullable( service.getAll() )
            .map( cars -> ResponseEntity.ok().body(cars) )          //200 OK
            .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found
    }

    @Override
    public ResponseEntity<Car> inventoryCarIdGet(String carId) {
        return Optional
            .ofNullable( service.get(Integer.parseInt(carId)) )
            .map( car -> ResponseEntity.ok().body(car) )          //200 OK
            .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found
    }

    @Override
    public ResponseEntity<Void> inventoryCarPost(Car car) {
        service.save(car);
		return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> inventoryCarIdDelete(String carId) {
        if(service.delete(Integer.parseInt(carId))) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
