package ra.demoprojectmodul4.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ra.demoprojectmodul4.dto.CustomerDto;
import ra.demoprojectmodul4.model.Customer;
import ra.demoprojectmodul4.model.Role;
import ra.demoprojectmodul4.reponsitory.CustomerRepository;
import ra.demoprojectmodul4.reponsitory.RoleReponsitory;
import ra.demoprojectmodul4.security.AuthencationUser;
import ra.demoprojectmodul4.service.ICustomerService;
import ra.demoprojectmodul4.service.UploadService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleReponsitory roleReponsitory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByKeyword(String name) {
        return customerRepository.findByKeyword(name);
    }
    public void blockUnblockCustomer(Integer id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer!=null){
            customer.setStatusAccount(!customer.getStatusAccount());
            customerRepository.save(customer);
        }
    }

    @Override
    public Customer save(CustomerDto customerDto) {
        String imageUrl = null;
        if (customerDto.getCustomerId()!=null){
            imageUrl = customerRepository.findById(customerDto.getCustomerId()).get().getAvatarUrl();
        }if (customerDto.getFile().getSize()!=0){
            imageUrl = uploadService.uploadFileToServer(customerDto.getFile());
        }
       Customer customer = modelMapper.map(customerDto,Customer.class);
       customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
       //lay role tu DB
        Role roleCustomer = roleReponsitory.findRolesByName("CUSTOMER");
        customer.setRole(roleCustomer);
        customer.setAvatarUrl(imageUrl);
        return  customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }
    @Override
    public UserDetails  loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> userOptional = customerRepository.findCustomerByUsername(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Customer customer = userOptional.get();

        return new AuthencationUser(
                customer.getEmailAddress(),
                customer.getPassword(),
                mapRolesToAuthorities(Arrays.asList(customer.getRole())),
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName()
        );
    }
    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findCustomerByUserName(String name) {
        return customerRepository.findCustomerByUsername(name);
    }
    public  boolean isPhoneExits(String phone){
        return customerRepository.existsCustomerByPhoneNumber(phone);
    }

    @Override
    public boolean findCustomerByStatusAccount(boolean account) {
        return customerRepository.findCustomerByStatusAccount(account);
    }

}
