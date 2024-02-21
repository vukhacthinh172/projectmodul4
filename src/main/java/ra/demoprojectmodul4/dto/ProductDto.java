package ra.demoprojectmodul4.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import ra.demoprojectmodul4.model.Catalog;
import ra.demoprojectmodul4.model.Product;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Product product;
    private MultipartFile file;
    private Integer productId;
    private String productName;
    private Catalog catalog;
    private String description;
    private double unitPrice;
    private int stock;
    private Date createdAt;
    private Date updatedAt;
    private boolean status = true;

    public ProductDto(Product product, MultipartFile file) {
        this.product = product;
        this.file = file;
    }
}
