package com.api.service;

import com.api.entity.Registration;
import com.api.entity.User;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationrepository;
    private ModelMapper modelMapper;


    public RegistrationService(RegistrationRepository registrationrepository, ModelMapper modelMapper) {
        this.registrationrepository = registrationrepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getAll(){
        List<Registration>registrations = registrationrepository.findAll();
        return registrations.stream().map(this::mapToDto).collect(Collectors.toList());
    }



    public RegistrationDto createRegistration(RegistrationDto registrationDto){
        Registration reg=mapToEntity(registrationDto);
        Registration savedReg=registrationrepository.save(reg);
        return mapToDto(savedReg);
    }

    public void deleteRegistration(long id) {
        registrationrepository.deleteById(id);
    }

    public RegistrationDto updateReg(long id, RegistrationDto registrationDto) {
        Registration r=registrationrepository.findById(id).get();
          r.setName(registrationDto.getName());
          r.setEmail(registrationDto.getEmail());
          r.setMobile(registrationDto.getMobile());
          Registration reg=registrationrepository.save(r);
          RegistrationDto savedReg=mapToDto(reg);
          return savedReg;
    }

    Registration mapToEntity(RegistrationDto registrationDto){
        Registration reg =modelMapper.map(registrationDto,Registration.class);
        /*Registration reg=new Registration();
        reg.setName(registrationDto.getName());
        reg.setEmail(registrationDto.getEmail());
        reg.setMobile(registrationDto.getMobile());*/
        return reg;
    }

    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto=modelMapper.map(registration,RegistrationDto.class);
        /*RegistrationDto dto= new RegistrationDto();
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());*/
        return dto;
    }

    public RegistrationDto getRegistrationById(long id){
        Registration registration=registrationrepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Record not Found"));

        return mapToDto(registration);
    }
}
