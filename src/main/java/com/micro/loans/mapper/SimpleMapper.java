package com.micro.loans.mapper;

import com.micro.loans.dto.LoanDto;
import com.micro.loans.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SimpleMapper {
    SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);

    Loan dtoToModel(LoanDto loanDto);

    LoanDto modelToDto(Loan loan);
}
