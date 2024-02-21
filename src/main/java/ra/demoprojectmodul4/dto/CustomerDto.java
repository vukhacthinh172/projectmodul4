package ra.demoprojectmodul4.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ra.demoprojectmodul4.model.Customer;
import ra.demoprojectmodul4.validate.PhoneUnique;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    private Integer customerId;
    @NotBlank
    @Size(min = 3 , message = "Toi thieu 3 ki tu")
    private String lastName;
    @NotBlank
    @Size(min = 3 , message = "Toi thieu 3 ki tu")
    private String firstName;
    @NotBlank
    @Size(min = 3 , message = "Toi thieu 3 ki tu")
    private String username;
    @NotBlank
    @Size(min = 3 , message = "Toi thieu 3 ki tu")
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String emailAddress;
    @Pattern(regexp = "^\\d{10,11}$", message = "so dien thoai khong dung dinh dang")
    @PhoneUnique
    private String phoneNumber;
    @NotBlank
    @Size(min = 5 , message = "Toi thieu 5 ki tu")
    private String address;
    private MultipartFile file ;

    public CustomerDto(Customer customer, MultipartFile file) {
    }
}
