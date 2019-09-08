package io.github.xisyn.spring.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "residence_address")
    private String residenceAddress;
}
