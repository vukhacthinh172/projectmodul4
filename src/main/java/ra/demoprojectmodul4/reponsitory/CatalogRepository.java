package ra.demoprojectmodul4.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.demoprojectmodul4.model.Catalog;

import java.util.List;
@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Integer> {
    List<Catalog> findCatalogByNameContaining(String name);
}
