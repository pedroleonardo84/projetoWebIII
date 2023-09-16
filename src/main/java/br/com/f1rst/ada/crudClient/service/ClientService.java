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
                .onErrorReturn(new ResponseDTO("Erro ao cadastrar produto",
                        new ClientDTO(),
                        LocalDateTime.now()));


    }
/*
    public Flux<ResponseDTO<ProductDTO>> getAll() {
        Flux<Product> productFlux = this.productRepository.findAll();
        return productFlux
                .map(product -> new ResponseDTO("Listagem de produtos retornada com sucesso!",
                        this.productConverter.toProductDTO(product),
                        LocalDateTime.now()
                ));
    }

    public Mono<ResponseDTO<ProductDTO>> findByCode(String code) {
        Mono<Product> productMono = this.productRepository.findByCode(code);
        return productMono
                .map(product -> new ResponseDTO("Busca por code retornada com sucesso!",
                        this.productConverter.toProductDTO(product),
                        LocalDateTime.now()
                ));

    }*/
}
