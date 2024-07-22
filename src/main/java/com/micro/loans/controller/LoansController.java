package com.micro.loans.controller;

import com.micro.loans.dto.ErrorResponseDto;
import com.micro.loans.dto.LoanDto;
import com.micro.loans.dto.ResponseDto;
import com.micro.loans.service.LoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.micro.loans.constants.LoansConstants.*;

@Tag(
        name = "CRUD REST API for Loans in Bank services.",
        description = "APIs to CREATE, UPDATE, GET and DELETE operations."
)
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/loans")
@Validated
public class LoansController {

    private final LoansService loansService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create a new Loan inside Bank application"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createLoan(@RequestBody @Valid LoanDto loanDto) {
        String mobileNumber = loanDto.getMobileNumber();
        long totalLoan = loanDto.getTotalLoan();
        loansService.createLoan(mobileNumber, totalLoan);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(), MESSAGE_201));
    }

    @Operation(
            summary = "GET Loan REST API",
            description = "REST API to fetch Loan details for Bank application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP status NOT_FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<LoanDto> getLoan(@RequestParam
                                               @Pattern(regexp = "[\\d]{12}", message = "mobileNumber should contain 12 digits") String mobileNumber) {
        LoanDto loanDto = loansService.getLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanDto);
    }


    @Operation(
            summary = "UPDATE Loan REST API",
            description = "REST API to update Loan inside Bank application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP status FAILED",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoanDto loanDto) {
        boolean isUpdated = loansService.updateLoan(loanDto);
        HttpStatus status = isUpdated ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        String message = isUpdated ? MESSAGE_200 : MESSAGE_417_UPDATE;
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(status.toString(), message));
    }

    @Operation(
            summary = "DELETE Loan REST API",
            description = "REST API to delete Loan inside Bank application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP status NOT_FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam
                                                      @Pattern(regexp = "[\\d]{12}", message = "mobileNumber should contain 12 digits") String mobileNumber) {
        loansService.deleteLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), MESSAGE_200));
    }
}
