package edu.uptc.swii.shiftCommandService.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "turnos")
@NoArgsConstructor
public class Shift {
    @Id
    @Getter @Setter
    private int id;
    @Getter @Setter
    private Integer userId;
    @Getter @Setter
    private String dependence;
    @Getter @Setter
    private String date;
    @Getter @Setter
    private String shiftNumber;

}
