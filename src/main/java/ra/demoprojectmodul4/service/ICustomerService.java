package ra.demoprojectmodul4.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ra.demoprojectmodul4.dto.CustomerDto;
import ra.demoprojectmodul4.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService  extends UserDetailsService {
    List<Customer> findAll();
    List<Customer> findByKeyword(String name);
    Customer save (CustomerDto customerDto);
    Optional<Customer> findById(Integer id);
    Optional<Customer> findCustomerByUserName(String name);
boolean findCustomerByStatusAccount(boolean account);
}
