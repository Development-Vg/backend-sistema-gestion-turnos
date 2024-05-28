package edu.uptc.swii.shiftCommandService.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

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
    private ShifStatus status = ShifStatus.ACTIVE;

    public Shift(Integer userId, String dependence, ZonedDateTime  date) {
        this.userId = userId;
        this.dependence = dependence;
        this.date = date;
    }

    public void changeStatus(){
        status = ShifStatus.INACTIVE;
    }

    public void changeStatusCancel(){
        status = ShifStatus.CANCEL;
    }
}