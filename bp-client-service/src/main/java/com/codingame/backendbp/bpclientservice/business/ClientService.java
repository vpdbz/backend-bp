package com.codingame.backendbp.bpclientservice.business;

import com.codingame.backendbp.bpclientservice.dto.ClientResponse;
import com.codingame.backendbp.bpclientservice.exception.NotFoundException;
import com.codingame.backendbp.bpclientservice.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codingame.backendbp.bpclientservice.dao.ClientRepository;
import com.codingame.backendbp.bpclientservice.dao.entity.ClientEntity;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(entity -> mapToClientResponse(entity))
                .collect(Collectors.toList());
    }

    public ClientResponse getClientById(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        if (clientEntity.isPresent()) {
            return mapToClientResponse(clientEntity.get());
        } else {
            throw new NotFoundException("El cliente no existe con id:" + id);
        }
    }

    public ClientResponse getClientByName(String name) {
        List<ClientEntity> clientEntity = clientRepository.findByName(name);
        if (!clientEntity.isEmpty()) {
            return mapToClientResponse(clientEntity.get(0));
        } else {
            throw new NotFoundException("El cliente no existe con nombre: " + name);
        }
    }

    public ClientResponse saveClient(Client client) {
        ClientEntity clientEntity = clientRepository.save(new ClientEntity(client));
        return mapToClientResponse(clientEntity);
    }

    public ClientResponse updateClient(Long id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            ClientEntity clientEntity = clientRepository.save(new ClientEntity(client));
            return mapToClientResponse(clientEntity);
        } else {
            throw new NotFoundException("El cliente no existe con id: " + id);
        }
    }

    public ClientResponse patchClient(Long id, Client client) {
        return clientRepository.findById(id)
                .map(clientFound -> {
                    if (client.getName() != null) {
                        clientFound.setName(client.getName());
                    }
                    if (client.getGender() != null) {
                        clientFound.setGender(client.getGender());
                    }
                    if (client.getAge() != null && client.getAge() > 0 && client.getAge() < 150) {
                        clientFound.setAge(client.getAge());
                    }
                    if (client.getAddress() != null) {
                        clientFound.setAddress(client.getAddress());
                    }
                    if (client.getPhone() != null) {
                        clientFound.setPhone(client.getPhone());
                    }
                    if (client.getPassword() != null) {
                        clientFound.setPassword(client.getPassword());
                    }
                    if (client.isStatus() != null) {
                        clientFound.setStatus(client.isStatus());
                    }

                    ClientEntity clientEntity = clientRepository.save(clientFound);
                    return mapToClientResponse(clientEntity);
                })
                .orElseThrow(() -> new NotFoundException("El cliente no existe con id: " + id));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    private ClientResponse mapToClientResponse(ClientEntity clientEntity) {
        return new ClientResponse(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getGender(),
                clientEntity.getAge(),
                clientEntity.getAddress(),
                clientEntity.getPhone(),
                clientEntity.isStatus());
    }

}
