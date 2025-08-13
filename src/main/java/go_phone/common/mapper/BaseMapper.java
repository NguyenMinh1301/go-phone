package go_phone.common.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {
    T findById(@Param("id") Integer id);

    List<T> findAll();

    List<T> findAllPageable(@Param("offset") int offset,
                            @Param("limit") int limit);

    List<T> searchPageable(@Param("keyword") String keyword,
                           @Param("offset") int offset,
                           @Param("limit") int limit);

    int insert(T entity);

    int update(T entity);

    int deleteById(@Param("id") Integer id);

    int softDeleteById(@Param("id") Integer id);

    boolean existsById(@Param("id") Integer id);

    boolean existsByName(@Param("name") String name);

    int countAll();

    int countPageable();

    int countSearch(String keyword);

}
