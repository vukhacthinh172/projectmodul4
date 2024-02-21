package ra.demoprojectmodul4.service;

import ra.demoprojectmodul4.model.Catalog;

import java.util.List;

public interface ICatalogService {
    List<Catalog> findAll();
    void save(Catalog catalog);
   // void delete(Integer id);
    Catalog findById(Integer id);
    List<Catalog> findByCatalogNameContains(String name);
}
