package com.wagnersouzas.dsbcliente.services;

import com.wagnersouzas.dsbcliente.dto.ClientDTO;
import com.wagnersouzas.dsbcliente.entities.Client;
import com.wagnersouzas.dsbcliente.repositories.ClientRepository;
import com.wagnersouzas.dsbcliente.resources.ClientResource;
import com.wagnersouzas.dsbcliente.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    public static final String ID_NOT_FOUND = "Id not found: %d";

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(c -> new ClientDTO(c));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> clientOptional = repository.findById(id);

        return new ClientDTO(clientOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {

        Client client = new Client();

        copyDtoEntity(clientDTO, client);

        client = repository.save(client);

        return new ClientDTO(client);

    }

    @Transactional
    public ClientDTO uipdate(Long id, ClientDTO clientDTO) {

        try {
            Client client = repository.getOne(id);

            copyDtoEntity(clientDTO, client);

            client = repository.save(client);

            return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format(ID_NOT_FOUND, id));
        }
    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(String.format(ID_NOT_FOUND, id));
        }
    }

    private void copyDtoEntity(ClientDTO clientDTO, Client client) {
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }
}
