package com.micro.loans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Loan",
        description = "Schema to hold Loan data"
)
@Data
public class LoanDto {

    @Schema(
            description = "Mobile number of the Customer",
            example = "370987654321"
    )
    @NotBlank(message = "mobileNumber cannot be empty")
    @Pattern(regexp = "[\\d]{12}", message = "mobileNumber should contain 12 digits")
    private String mobileNumber;

    @Schema(
            description = "Loan Number",
            example = "12345678901122"
    )
    @Pattern(regexp = "[\\d]{14}", message = "LoanNumber should contain 14 digits")
    private String loanNumber;

    @Schema(
            description = "Loan type",
            example = "CREDIT"
    )
    private String loanType;

    @Schema(
            description = "Loan total amount",
            example = "100000"
    )
    private long totalLoan;

    @Schema(
            description = "Loan paid amount",
            example = "10000"
    )
    private long amountPaid;

    @Schema(
            description = "Loan amount to pay",
            example = "90000"
    )
    private long outstandingAmount;
}
