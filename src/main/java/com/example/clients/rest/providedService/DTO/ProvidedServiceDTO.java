package com.example.clients.rest.providedService.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProvidedServiceDTO {
    private String description;
    private String value;
    private String date;
    private Integer idClient;
}
