package go_phone.feature.category.mapper;

import go_phone.common.mapper.BaseMapper;
import go_phone.feature.category.entity.Brand;
import go_phone.feature.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

    List<Product> searchByName(String keyword);

}
