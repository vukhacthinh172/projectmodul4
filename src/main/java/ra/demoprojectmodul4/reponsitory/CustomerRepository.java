package ra.demoprojectmodul4.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.demoprojectmodul4.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.emailAddress) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR CAST(c.phoneNumber AS string) LIKE CONCAT('%', :keyword, '%')")
    List<Customer> findByKeyword(@Param("keyword") String keyword);
    Optional<Customer> findCustomerByUsername(String name);
    boolean existsCustomerByPhoneNumber(String phone);
    boolean findCustomerByStatusAccount(boolean accoun);

}
