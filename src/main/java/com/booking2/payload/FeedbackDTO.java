package com.booking2.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class FeedbackDTO {
    private Long id;
    private Long userId;
    @NotNull(message = "Rating cannot be empty")
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;
    private String comments;
}