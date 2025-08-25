package go_phone.feature.category.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.common.handler.CalculateOffset;
import go_phone.common.response.PageResponse;
import go_phone.feature.category.converter.MadeFromConverter;
import go_phone.feature.category.dto.request.MadeFromRequest;
import go_phone.feature.category.dto.response.MadeFromResponse;
import go_phone.feature.category.entity.MadeFrom;
import go_phone.feature.category.mapper.MadeFromMapper;
import go_phone.feature.category.service.MadeFromService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MadeFromServiceImpl implements MadeFromService {

    private final MadeFromMapper madeFromMapper;
    private final MadeFromConverter madeFromConverter;
    private final CalculateOffset calculateOffset = new CalculateOffset();

    @Override
    public int create(MadeFromRequest dto) {
        if (madeFromMapper.existsByName(dto.getCountryName())) {
            throw new AppException(ErrorCode.MADE_FROM_EXISTED);
        }
        MadeFrom madeFrom = madeFromConverter.toEntity(dto);
        return madeFromMapper.insert(madeFrom);
    }

    @Override
    public int update(Integer id, MadeFromRequest dto) {
        MadeFrom madeFrom = madeFromMapper.findById(id);
        if (madeFrom == null) {
            throw new AppException(ErrorCode.MADE_FROM_NOT_FOUND);
        }
        madeFromConverter.updateEntity(dto, madeFrom);
        return madeFromMapper.update(madeFrom);
    }

    @Override
    public MadeFromResponse findById(Integer id) {
        MadeFrom madeFrom = madeFromMapper.findById(id);
        if (madeFrom != null) {
            return madeFromConverter.toResponse(madeFrom);
        } else {
            throw new AppException(ErrorCode.MADE_FROM_NOT_FOUND);
        }
    }

    @Override
    public List<MadeFromResponse> findAll() {
        List<MadeFrom> madeFroms = madeFromMapper.findAll();
        if (madeFroms == null || madeFroms.isEmpty()) {
            throw new AppException(ErrorCode.MADE_FROM_EMPTY);
        }
        return madeFromConverter.toResponseList(madeFroms);
    }

    @Override
    public PageResponse<MadeFromResponse> findAllPageable(int page, int size) {

        int offset = calculateOffset.calculateOffset(page, size);

        List<MadeFrom> madeFroms = madeFromMapper.findAllPageable(offset, size);
        int totalMadeFrom = madeFromMapper.countAll();
        return madeFromConverter.toResponsePage(madeFroms, page, size, totalMadeFrom);
    }

    @Override
    public PageResponse<MadeFromResponse> searchPageable(String keyword, int page, int size) {

        int offset = calculateOffset.calculateOffset(page, size);

        List<MadeFrom> madeFroms = madeFromMapper.searchPageable(keyword, offset, size);
        int totalMadeFrom = madeFromMapper.countSearch(keyword);
        return madeFromConverter.toResponsePage(madeFroms, page, size, totalMadeFrom);
    }

    @Override
    public int delete(Integer id) {
        MadeFrom madeFrom = madeFromMapper.findById(id);
        if (madeFrom == null) {
            throw new AppException(ErrorCode.MADE_FROM_NOT_FOUND);
        }
        madeFromMapper.softDeleteById(id);
        return madeFromMapper.softDeleteById(id);
    }
}
