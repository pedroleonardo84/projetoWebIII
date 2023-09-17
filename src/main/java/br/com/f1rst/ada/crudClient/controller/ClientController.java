package br.com.f1rst.ada.crudClient.controller;

import br.com.f1rst.ada.crudClient.dto.ClientDTO;
import br.com.f1rst.ada.crudClient.dto.ResponseDTO;
import br.com.f1rst.ada.crudClient.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a client",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono<ResponseDTO> create(@RequestBody ClientDTO clientDTO) {
        return this.clientService.create(clientDTO);
    }

    @GetMapping("/findAll")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "find all clients")
    public Flux<ResponseDTO<ClientDTO>> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("{email}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find by Client email")
    public Mono<ResponseDTO> findByEmail(@PathVariable("email") String email) {
        return this.clientService.findByEmail(email);
    }

    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update a Client",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono update(@RequestBody ClientDTO clientDTO) {
        return this.clientService.update(clientDTO);
    }

    @DeleteMapping("{email}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Delete by Client email")
    public Mono<ResponseDTO> delete(@PathVariable("email") String email) {
        return this.clientService.delete(email);
    }

}
