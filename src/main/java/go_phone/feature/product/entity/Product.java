package go_phone.feature.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    Integer productId;
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
