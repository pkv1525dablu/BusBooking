package com.booking2.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfferId implements Serializable {
    private Long user;
    private Long offer; // constructors, equals and hashCode methods
 }