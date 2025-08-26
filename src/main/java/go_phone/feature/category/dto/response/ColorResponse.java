package go_phone.feature.category.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ColorResponse {

    Integer colorId;
    String name;
    String hexCode;

    String createdAt;
    String createdBy;
    String updatedAt;
    String updatedBy;
    Integer isActive;
    Integer isDeleted;
}
