package com.booking2.entities;

import javax.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bus_operators")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusOperator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "busOperator")
    private List<Bus> buses;
}