package edu.uptc.swii.shiftmgmt.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name ="usuarios")
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
    private String typeUser;
    @Getter @Setter
    private Set<String> roles;

    @OneToOne
    @JoinColumn(name = "id_credencial")
    private Credentials credentials;

    public User(String name, String lastName, String typeDocument, String document, String addres,
            String email, String celphone, String typeUser, Credentials credentials) {
        this.name = name;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.document = document;
        this.addres = addres;
        this.email = email;
        this.celphone = celphone;
        this.typeUser = typeUser;
        this.credentials = credentials;
    }

    
}
