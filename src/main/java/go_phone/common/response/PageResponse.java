package go_phone.common.response;

import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {

    private int offset;

    private int limit;

    private long total;

    private List<T> data;
}
