package com.example.example.controller;

import com.example.example.payload.RegistrationDto;
import com.example.example.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    //http://localhost:8080/api/v1/registration
    @PostMapping
    public ResponseEntity<?> addRegistration(@Valid @RequestBody RegistrationDto registrationDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }
        RegistrationDto registrationDto1=registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(registrationDto1, HttpStatus.CREATED);
    }
//http://localhost:8080/api/v1/registration?id=
    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam long id){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Registration deleted successfully", HttpStatus.OK);

    }
//http://localhost:8080/api/v1/registration?id=
    @PutMapping
    public ResponseEntity<RegistrationDto> updateRegistration(@RequestParam long id, @RequestBody RegistrationDto registrationDto){
        RegistrationDto registrationDto2=registrationService.updateRegistration(id, registrationDto);
        return new ResponseEntity<>(registrationDto2,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/registration?pageNo=1&pageSize=3&sortBy=name&sortDir=asc
    //http://localhost:8080/api/v1/registration?pageNo=1&pageSize=3&sortBy=name&sortDir=desc

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getRegistration(
            @RequestParam(name="pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue = "3", required = false)int pageSize,
            @RequestParam(name="sortBy", defaultValue ="name",required = false) String sortBy,
            @RequestParam(name="sortDir", defaultValue ="name", required = false)String sortDir
    ){
        List<RegistrationDto> registrationDtos=registrationService.getRegistration(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(registrationDtos, HttpStatus.OK);

    }
//http://localhost:8080/api/v1/registration/ById?id=
    @GetMapping("/ById")
    public ResponseEntity<RegistrationDto> GetRegistrationById(@RequestParam long id){
        RegistrationDto registrationDto =registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);

    }
}
