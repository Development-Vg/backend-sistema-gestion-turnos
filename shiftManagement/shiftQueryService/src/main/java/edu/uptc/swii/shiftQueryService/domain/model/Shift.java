package edu.uptc.swii.shiftQueryService.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Enumerated;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z", locale = "es_CO", timezone = "GMT-0500")
    private ZonedDateTime date;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private ShifStatus status;


    public void changeStatus(){
        status = ShifStatus.INACTIVE;
    }

}
