package com.exercise.stationentryapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ports_and_plugs")
public class Port {

    @Id
    @Column(name = "port_number")
    private int portNumber;

    @Column(name = "port_type")
    private String portType;

    @Column(name = "plug_type")
    private String plugType;

    @OneToMany(mappedBy = "port", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ChargingEvent> chargingEvents;

}
