package com.malskyi.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Items extends BaseEntity {

    int count;

    @OneToOne
    @JoinColumn(name = "commodities_id")
    private Commodity commodity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "orders_id", nullable = false, referencedColumnName = "id")
    private Orders orders;

    public String toStringLog() {
        return ("\n[ Count : " + count +
                " | Commodity.name : " + commodity.getName() +
                " | Commodity.Category.name : " + commodity.getCategory().getName() +
                " | Commodity.Producer.name : " + commodity.getProducer().getName() +
                " ]\n");
    }
}

