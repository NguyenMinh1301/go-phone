package go_phone.feature.category.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandResponse {

    Integer brandId;
    String name;
    String description;

    String createdAt;
    String createdBy;
    String updatedAt;
    String updatedBy;
    Integer isActive;
    Integer isDeleted;

}
