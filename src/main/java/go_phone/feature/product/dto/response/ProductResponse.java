package go_phone.feature.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Integer brandId;
    Integer colorId;
    Integer madeFromId;
    String productName;

    String screenSize;
    String screenDetails;
    String screenResolution;
    String screenRefreshRate;

    String cameraFront;
    String cameraFrontDetails;
    String cameraRear;
    String cameraRearDetails;

    String chipset;
    String gpu;
    String nfc;
    String sim;
    String audioPort;
    String gps;

    String ram;
    String storageCapacity;
    String operatingSystem;

    String size;
    String weight;
    String backMaterial;
    String frameMaterial;
    String wifi;
    String bluetooth;

    String note;

    String createdAt;
    String createdBy;
    String updatedAt;
    String updatedBy;
    Integer isActive;
    Integer isDeleted;

}
