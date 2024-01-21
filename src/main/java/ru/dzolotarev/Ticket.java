package ru.dzolotarev;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@ToString
public class Ticket implements Serializable {
    private String origin;

    @JsonProperty("origin_name")
    private String originName;

    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("departure_date")
    private LocalDate departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonProperty("departure_time")
    private LocalTime departureTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("arrival_date")
    private LocalDate arrivalDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonProperty("arrival_time")
    private LocalTime arrivalTime;

    private String carrier;
    private Byte stops;
    private Double price;
}
