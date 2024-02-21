package ra.demoprojectmodul4.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.demoprojectmodul4.dto.ProductDto;
import ra.demoprojectmodul4.model.Product;
import ra.demoprojectmodul4.reponsitory.ProductRepository;
import ra.demoprojectmodul4.service.IProductService;
import ra.demoprojectmodul4.service.UploadService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private  final ModelMapper modelMapper;
    private  final UploadService uploadService;

    @Override
    public List<Product> findAllOrderBy() {
        return productRepository.findAllOrderBy();
    }

    @Override
    public void save(ProductDto productdto) {
         String imageUrl = null;
         if (productdto.getProductId()!=null){
             imageUrl = productRepository.findById(productdto.getProductId()).get().getImgUrl();
         }if (productdto.getFile().getSize()!=0){
             imageUrl = uploadService.uploadFileToServer(productdto.getFile());
        }
         Product newProduct = productdto.getProduct();
                  newProduct.setImgUrl(imageUrl);
                       productRepository.save(newProduct);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findProductByProductNameContaining(String name) {
        return productRepository.findProductByProductNameContaining(name);
    }

    @Override
    public void hideShowProduct(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product!=null){
           boolean currentStatus = product.isStatus();
           product.setStatus(!currentStatus);
           productRepository.save(product);
        }else {
            throw new RuntimeException("Không tìm thấy sản phẩm với mã sản phẩm: " + id);
        }
    }
}
