package io.github.xisyn.spring.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PurchasedBook {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    private Double cost;
}
