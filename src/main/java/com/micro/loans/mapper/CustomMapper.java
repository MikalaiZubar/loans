package com.micro.loans.mapper;

import com.micro.loans.dto.LoanDto;
import com.micro.loans.entity.Loan;
import org.springframework.stereotype.Component;

@Component
public class CustomMapper {

    public Loan dtoToModel(LoanDto loanDto, Loan loan){
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loan.getOutstandingAmount());
        return loan;
    }
}
