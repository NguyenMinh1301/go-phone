package go_phone.common.response;

import lombok.*;

import java.util.List;

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
