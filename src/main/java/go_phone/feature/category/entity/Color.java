package go_phone.feature.category.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Color {

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
