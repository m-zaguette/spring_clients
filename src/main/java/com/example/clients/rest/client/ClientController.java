package com.example.clients.rest.client;

import java.util.List;

import javax.validation.Valid;

import com.example.clients.model.entity.ClientEntity;
import com.example.clients.model.repository.ClientRepository;

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


@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientRepository _repository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClientEntity saveFromJson( @RequestBody @Valid ClientEntity client ){
        return _repository.save(client);
    }

    @GetMapping
    public List<ClientEntity> getAll(){
        return _repository.findAll();
    }

    @GetMapping("{id}")
    public ClientEntity getById(@PathVariable Integer id){
        return _repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{id.not_found}"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Integer id){
        _repository
        .findById(id)
        .map( client -> {
            _repository.delete(client);
            return Void.TYPE;   
        })
        .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{id.not_found}"));
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable Integer id, @RequestBody @Valid ClientEntity updatedClient){
        _repository
        .findById(id)
        .map( client -> {
            updatedClient.setId(client.getId());
            return _repository.save(updatedClient);
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{id.not_found}"));
    }
}
