package br.com.f1rst.ada.crudClient.repository;

import br.com.f1rst.ada.crudClient.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
    Mono<Client> findByEmail(String name);

}
