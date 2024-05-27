package edu.uptc.swii.UserCommandService.domain.model;

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

    @Column(unique = true)
    @Getter @Setter
    private String document;

    @Getter @Setter
    private String addres;

    @Column(unique = true)
    @Getter @Setter
    private String email;

    @Getter @Setter
    private String celphone;

    @Getter @Setter
    @JsonIgnore
    private Set<String> roles;

    @OneToOne
    @JoinColumn(name = "id_credencial")
    private Credentials credentials;

    public User(String name, String lastName, String typeDocument, String document, String addres,
                String email, String celphone, Credentials credentials) {
        this.name = name;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.document = document;
        this.addres = addres;
        this.email = email;
        this.celphone = celphone;
        this.credentials = credentials;
        roles = new HashSet<>();
    }


}
