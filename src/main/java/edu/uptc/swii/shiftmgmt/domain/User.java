package edu.uptc.swii.shiftmgmt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="usuario")
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

}
