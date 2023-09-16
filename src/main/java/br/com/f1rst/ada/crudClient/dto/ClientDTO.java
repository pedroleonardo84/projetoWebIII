package br.com.f1rst.ada.crudClient.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public class ClientDTO {
    private String name;
    private Integer age;
    private String email;

    public ClientDTO() {
    }
    public ClientDTO(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
