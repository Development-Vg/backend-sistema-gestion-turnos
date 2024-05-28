package edu.uptc.swii.UserQueryService.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="usuarios")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String typeDocument;
    @Getter @Setter
    private String document;
    @Getter @Setter
    private String addres;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String celphone;
    @Getter @Setter
    @JsonIgnore
    private Set<String> roles;



    public User(String name, String lastName, String typeDocument, String document, String addres,
                String email, String celphone) {
        this.name = name;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.document = document;
        this.addres = addres;
        this.email = email;
        this.celphone = celphone;
        roles = new HashSet<>();
    }


}
