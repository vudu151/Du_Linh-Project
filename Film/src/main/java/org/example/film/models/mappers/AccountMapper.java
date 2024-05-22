//package org.example.film.models.mappers;
//
//import org.example.film.models.dtos.AccountDto;
//import org.example.film.models.entities.Account;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AccountMapper {
//    private final ModelMapper modelMapper;
//    public AccountMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    public AccountDto convertToDto(Account account){
//        return modelMapper.map(account, AccountDto.class);
//    }
//
//    public Account convertToEntity(AccountDto accountDto){
//        return modelMapper.map(accountDto, Account.class);
//    }
//}
