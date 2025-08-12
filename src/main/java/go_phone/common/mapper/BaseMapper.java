package go_phone.common.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {
    T findById(@Param("id") Integer id);

    List<T> findAll();

//    List<T> findAllPaged(@Param("offset") int offset,
//                         @Param("limit") int limit,
//                         @Param("paging") PageRequest pageRequest);

    int insert(T entity);

    int update(T entity);

    int deleteById(@Param("id") Integer id);

    int softDeleteById(@Param("id") Integer id);

    boolean existsById(@Param("id") Integer id);

    int countAll();
}
