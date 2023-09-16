package br.com.f1rst.ada.crudClient.converter;

import br.com.f1rst.ada.crudClient.dto.ClientDTO;
import br.com.f1rst.ada.crudClient.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public ClientDTO toClientDTO(Client client) {
        return new ClientDTO(client.getName(), client.getAge(), client.getEmail());
    }

    public Client toClient(ClientDTO clientDTO) {
        return new Client(clientDTO.getName(), clientDTO.getAge() , clientDTO.getEmail());
    }
}
