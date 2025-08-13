package go_phone.common.service;

import java.util.List;

public interface BaseService<REQ, RES, I> {

    int create(REQ dto);

    int update(I id, REQ dto);

    RES findById(I id);

    List<RES> findAll();

    List<RES> findAllPageable(int offset, int limit);

    int delete(I id);

}
