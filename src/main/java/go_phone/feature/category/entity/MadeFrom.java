package go_phone.feature.category.entity;

import go_phone.common.model.BaseEntity;
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
public class MadeFrom extends BaseEntity {

    Integer madeFromId;
    String countryName;
    String description;

    Integer isActive;
}
