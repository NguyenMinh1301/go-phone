package go_phone.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    private LocalDateTime createdAt;

    private Integer createdBy;

    private LocalDateTime updatedAt;

    private Integer updatedBy;

    private Boolean isDeleted;

}
