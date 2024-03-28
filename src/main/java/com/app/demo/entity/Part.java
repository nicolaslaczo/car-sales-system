package com.app.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String partNum;
    private String partName;
    private String partPrice;




}
