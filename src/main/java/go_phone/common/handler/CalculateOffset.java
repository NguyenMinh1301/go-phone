package go_phone.common.handler;

public class CalculateOffset {

    // Tính offset cho Pageable
    public int calculateOffset(int page, int size) {

        // int offset = (page - 1) * size;

        return (page - 1) * size;
    }
}
