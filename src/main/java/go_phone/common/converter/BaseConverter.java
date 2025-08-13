package go_phone.common.converter;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @param <REQ>  Request DTO (create/update)
 * @param <RES>  Response DTO
 * @param <E>    Entity
 */
public interface BaseConverter<REQ, RES, E> {

    // Dùng khi CREATE
    E toEntity(REQ request);

    // Dùng khi UPDATE
    void updateEntity(REQ request, @MappingTarget E entity);

    // Convert 1 entity sang response
    RES toResponse(E entity);

    // Convert list entity sang list response
    List<RES> toResponseList(List<E> entities);

    // Convert list request sang list entity
    List<E> toEntityList(List<REQ> requests);
}
