package br.com.f1rst.ada.crudClient.service;

import br.com.f1rst.ada.crudClient.converter.ClientConverter;
import br.com.f1rst.ada.crudClient.dto.ClientDTO;
import br.com.f1rst.ada.crudClient.dto.ResponseDTO;
import br.com.f1rst.ada.crudClient.model.Client;
import br.com.f1rst.ada.crudClient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ClientService {

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private ClientRepository clientRepository;

    public Mono<ResponseDTO> create(ClientDTO clientDTO) {

        //converter dto em model
        Client client = this.clientConverter.toClient(clientDTO);
        //salvar na base de dados o model
        Mono<Client> clientMono = this.clientRepository.save(client);
        //retornar o dado salvo como dto
        return clientMono
                .map((clientDocument) -> new ResponseDTO("Cliente cadastrado com sucesso!",
                        this.clientConverter.toClientDTO(clientDocument),
                        LocalDateTime.now()))
                .onErrorReturn(new ResponseDTO("Erro ao buscar cliente! email não possui cadastro",
                        new ClientDTO(),
                        LocalDateTime.now()));


    }

    public Flux<ResponseDTO<ClientDTO>> getAll() {
        Flux<Client> clientFlux = this.clientRepository.findAll();
        return clientFlux
                .map(client -> new ResponseDTO("Lista de clientes retornada com sucesso!",
                        this.clientConverter.toClientDTO(client),
                        LocalDateTime.now()
                ));
    }

    public Mono<ResponseDTO> findByEmail(String email) {
        Mono<Client> clientMono = this.clientRepository.findByEmail(email);
        return clientMono
                .map(client -> new ResponseDTO("Busca por email retornada com sucesso!",
                        this.clientConverter.toClientDTO(client),
                        LocalDateTime.now()
                )).onErrorReturn(new ResponseDTO("Erro ao buscar cliente! email não possui cadastro",
                        new ClientDTO(),LocalDateTime.now()));
    }
    
}
