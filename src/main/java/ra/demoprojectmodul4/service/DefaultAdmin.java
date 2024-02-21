package ra.demoprojectmodul4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ra.demoprojectmodul4.model.Customer;
import ra.demoprojectmodul4.model.Role;
import ra.demoprojectmodul4.reponsitory.CustomerRepository;
import ra.demoprojectmodul4.reponsitory.RoleReponsitory;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultAdmin {
    private final CustomerRepository customerRepository;
    private final IRoleService iRoleService;
    private  final BCryptPasswordEncoder passwordEncoder;
    @EventListener(ApplicationReadyEvent.class)
    public void defaultAdmin(){
        if (customerRepository.findCustomerByUsername("admin").isEmpty()){
            Role roleCustomer = new Role("CUSTOMER");
            Role roleAdmin = new Role("ADMIN");
            iRoleService.save(roleAdmin);
            iRoleService.save(roleCustomer);
            Customer admin = new Customer();
            admin.setUsername("admin");
            admin.setEmailAddress("admin@gmail.com");
            admin.setPhoneNumber("012345678");
            admin.setPassword(passwordEncoder.encode("admin"));
           Role adminRole = iRoleService.findRolesByName("ADMIN");
           admin.setRole(adminRole);
           customerRepository.save(admin);
        }
    }
}
