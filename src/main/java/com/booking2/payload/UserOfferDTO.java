package com.booking2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data


public class UserOfferDTO {
    private Long userId;
    private Long offerId;
}