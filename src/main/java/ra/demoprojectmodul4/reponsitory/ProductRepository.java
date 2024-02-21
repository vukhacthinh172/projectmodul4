package ra.demoprojectmodul4.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.demoprojectmodul4.model.Product;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product , Integer> {
    List<Product> findProductByProductNameContaining(String name);
    @Query("select P from Product P order by P.createdAt")
    List<Product>  findAllOrderBy();
}
