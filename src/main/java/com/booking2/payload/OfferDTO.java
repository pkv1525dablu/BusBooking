package com.booking2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Data

public class OfferDTO {
    private Long id;
    @NotEmpty(message = "Offer name cannot be empty") @Size(min = 2, max = 100, message = "Offer name must be between 2 and 100characters")
    private String offerName; @NotEmpty(message = "Promo code cannot be empty")
    @Size(min = 2, max = 50, message = "Promo code must be between 2and 50characters")
    private String promoCode;
    @NotEmpty(message = "Discount type cannot be empty")
    private String discountType;
    @NotNull(message = "Discount value cannot be empty")
    private Double discountValue;
    @NotNull(message = "Start date cannot be empty")

    private Date startDate;
    @NotNull(message = "End date cannot be empty")
    private Date endDate;
}