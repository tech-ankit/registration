package com.api.controller;

import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
       List<RegistrationDto> registrations=registrationService.getAll();
       return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> createRegistration(
            @RequestBody RegistrationDto registrationDto
    ){
       RegistrationDto reg= registrationService.createRegistration(registrationDto);
       return new ResponseEntity<>(reg,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(
            @RequestParam long id
    ){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto> updateRegistration(
            @PathVariable long id,
            @RequestBody RegistrationDto registrationDto
    ){
        RegistrationDto savedReg= registrationService.updateReg(id,registrationDto);
        return new ResponseEntity<>(savedReg,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @PathVariable() long id
    ){
        RegistrationDto dto = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
