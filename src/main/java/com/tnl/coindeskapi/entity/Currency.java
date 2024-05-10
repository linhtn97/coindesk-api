package com.tnl.coindeskapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Entity
@Table(name = "currency")
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    private int id;
    private String code;
    private String name;
}
