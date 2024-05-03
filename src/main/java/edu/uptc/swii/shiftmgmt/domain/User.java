package edu.uptc.swii.shiftmgmt.domain;

import javax.persistence.Entity;

@Entity
public class User {
    private String id;
    private String name;
    private String lastName;
    private String typeDocument;
    private String document;
    private String addres;
    private String email;
    private String typeUser;

}
