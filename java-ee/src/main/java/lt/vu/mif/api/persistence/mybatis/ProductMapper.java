package lt.vu.mif.api.persistence.mybatis;

import lt.vu.mif.api.entities.Product;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ProductMapper {
    void insert(Product record);
}
