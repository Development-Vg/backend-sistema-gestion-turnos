package edu.uptc.swii.UserQueryService.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "credenciales")
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String token;

    @OneToOne(mappedBy = "credentials", cascade = CascadeType.ALL)
    private User user;
}
