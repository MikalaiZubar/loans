package com.micro.loans.service;

import com.micro.loans.dto.LoanDto;

public interface LoansService {

    void createLoan(String mobileNumber, long loanAmount);

    LoanDto getLoan(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    void deleteLoan(String mobileNumber);
}
