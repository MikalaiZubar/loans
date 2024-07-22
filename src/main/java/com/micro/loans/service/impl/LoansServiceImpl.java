package com.micro.loans.service.impl;

import com.micro.loans.dto.LoanDto;
import com.micro.loans.entity.Loan;
import com.micro.loans.exception.ResourceAlreadyExistsException;
import com.micro.loans.exception.ResourceNotFoundException;
import com.micro.loans.mapper.CustomMapper;
import com.micro.loans.mapper.SimpleMapper;
import com.micro.loans.repository.LoansRepository;
import com.micro.loans.service.LoansService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.SplittableRandom;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements LoansService {

    private static final Logger log = LoggerFactory.getLogger(LoansServiceImpl.class);

    private final LoansRepository loansRepository;

    private final SimpleMapper simpleMapper;

    private final CustomMapper customMapper;

    @Override
    public void createLoan(String mobileNumber, long loanAmount) {
        loansRepository.findByMobileNumber(mobileNumber)
                .ifPresentOrElse(loan -> {
                            throw new ResourceAlreadyExistsException("Loan already exists for mobile number " + mobileNumber);
                        },
                        () -> {
                            Loan loan = getLoan(mobileNumber, loanAmount);
                            loansRepository.save(loan);
                        });
    }

    @Override
    public LoanDto getLoan(String mobileNumber) {
        Optional<Loan> loanOpt = loansRepository.findByMobileNumber(mobileNumber);
        if (loanOpt.isEmpty()) {
            throw new ResourceNotFoundException("Loan not found for mobile number " + mobileNumber);
        }
        return simpleMapper.modelToDto(loanOpt.get());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        boolean isUpdated = true;
        Optional<Loan> loanOpt = loansRepository.findByMobileNumber(loanDto.getMobileNumber());
        if (loanOpt.isEmpty()) {
            throw new ResourceNotFoundException("Loan not found for mobile number " + loanDto.getMobileNumber());
        }
        Loan loan = customMapper.dtoToModel(loanDto, loanOpt.get());
        try {
            loansRepository.save(loan);
        } catch (Throwable th) {
            log.error(th.getMessage());
            isUpdated = false;
        }
        return isUpdated;
    }

    @Override
    public void deleteLoan(String mobileNumber) {
        loansRepository.findByMobileNumber(mobileNumber)
                .ifPresentOrElse(
                        loansRepository::delete,
                () -> {
                    throw new ResourceNotFoundException("Loan not found for mobile number " + mobileNumber);
                });
    }

    private Loan getLoan(String mobileNumber, long loanAmount) {
        Loan loan = new Loan();
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType("PRIVATE");
        loan.setTotalLoan(loanAmount);
        loan.setOutstandingAmount(loanAmount);
        SplittableRandom splittableRandom = new SplittableRandom();
        long loanNumber = splittableRandom.nextLong(10000000000000L, 99999999999999L);
        loan.setLoanNumber(String.valueOf(loanNumber));
        return loan;
    }
}
