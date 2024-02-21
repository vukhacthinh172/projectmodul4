package ra.demoprojectmodul4.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demoprojectmodul4.model.Catalog;
import ra.demoprojectmodul4.reponsitory.CatalogRepository;
import ra.demoprojectmodul4.service.ICatalogService;

import java.util.List;
@Service
public class ICatalogServiceImpl implements ICatalogService {
    @Autowired
    private CatalogRepository catalogRepository;
    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public void save(Catalog catalog) {
      catalogRepository.save(catalog);
    }

//    @Override
//    public void delete(Integer id) {
//   catalogReponsitory.delete(findById(id));
//    }

    @Override
    public Catalog findById(Integer id) {
        return catalogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Catalog> findByCatalogNameContains(String name) {
        return catalogRepository.findCatalogByNameContaining(name);
    }
    public void hideShowCatalogByCatalogId(Integer id){
        Catalog catalog = catalogRepository.findById(id).orElse(null);
        if (catalog!=null){
            boolean currentCatalog = catalog.isStatus();
            catalog.setStatus(!currentCatalog);
            catalogRepository.save(catalog);
        }else {
            throw new RuntimeException("Không tìm thấy sản phẩm với mã sản phẩm: " + id);
        }
    }
}
