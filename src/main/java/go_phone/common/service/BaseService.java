package go_phone.common.service;

import go_phone.common.response.PageResponse;

import java.util.List;

/**
 * @param <REQ>  Request DTO (create/update)
 * @param <RES>  Response DTO
 * @param <I>    Id
 */
public interface BaseService<REQ, RES, I> {

    int create(REQ dto);

    int update(I id, REQ dto);

    RES findById(I id);

    List<RES> findAll();

    PageResponse<RES> findAllPageable(int page, int size);

    PageResponse<RES> searchPageable(String keyword, int page, int size);

    int delete(I id);

}
