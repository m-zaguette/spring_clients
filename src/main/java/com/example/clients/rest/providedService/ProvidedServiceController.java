package com.example.clients.rest.providedService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.clients.core.utils.BigDecimalConverter;
import com.example.clients.model.entity.ClientEntity;
import com.example.clients.model.entity.ProvidedServiceEntity;
import com.example.clients.model.repository.ClientRepository;
import com.example.clients.model.repository.ProvidedServiceRepository;
import com.example.clients.rest.providedService.DTO.ProvidedServiceDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/provided-services")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProvidedServiceController {
    @Autowired
    private final ProvidedServiceRepository _repository;

    @Autowired
    private final ClientRepository _clientRepository;

    @Autowired
    private final BigDecimalConverter _bigDecimalConverter;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProvidedServiceEntity saveFromJson( @RequestBody ProvidedServiceDTO dto){
        ProvidedServiceEntity providedService = new ProvidedServiceEntity();
        LocalDate date = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ClientEntity client = _clientRepository
        .findById(
            dto.getIdClient()
            ).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "{clientId.not_found}"
            )
        );
        providedService.setDescription(dto.getDescription());
        providedService.setDate(date);
        providedService.setValue(_bigDecimalConverter.fromString(dto.getValue()));
        providedService.setClient(client);
        return _repository.save(providedService);
    }

    @GetMapping
    public List<ProvidedServiceEntity> getAll(){
        return _repository.findAll();
    }

    @GetMapping("{id}")
    public ProvidedServiceEntity getById(@PathVariable Integer id){
        return _repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        _repository.findById(id)
        .map( providedService -> {
            _repository.delete(providedService);
            return Void.TYPE;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable Integer id, @RequestBody ProvidedServiceEntity updateProvidedService){
        _repository.findById(id).map( (providedService) -> {
            updateProvidedService.setId(providedService.getId());
            return _repository.save(updateProvidedService);
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{id.not_found}"));
    }
}
