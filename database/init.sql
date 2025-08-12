DROP DATABASE IF EXISTS go_phone_master_db;

CREATE DATABASE go_phone_master_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE go_phone_master_db;

--   -- Create user
-- CREATE USER 'go_phone_user'@'%' IDENTIFIED BY 'your_password';
-- GRANT ALL PRIVILEGES ON go_phone_master_db.* TO 'go_phone_user'@'%';
-- FLUSH PRIVILEGES;

  -- Create table brand
CREATE TABLE `brand` (
    `brand_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT,

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;
  
INSERT INTO `brand` (`name`, `description`, `created_by`, `is_active`, `is_deleted`) VALUES
					('Apple', 'Thương hiệu điện thoại iPhone nổi tiếng của Mỹ', 'admin', 1, 0),
					('Samsung', 'Hãng điện thoại và thiết bị điện tử Hàn Quốc', 'admin', 1, 0),
					('Xiaomi', 'Thương hiệu điện thoại và thiết bị thông minh của Trung Quốc', 'admin', 1, 0),
					('Oppo', 'Nhà sản xuất smartphone đến từ Trung Quốc', 'admin', 1, 0),
					('Google', 'Hãng điện thoại Pixel của Mỹ', 'admin', 1, 0);

  -- Create table color
CREATE TABLE `color` (
    `color_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `hex_code` VARCHAR(7),

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;
  
INSERT INTO `color` (`name`, `hex_code`, `created_by`, `is_active`, `is_deleted`) VALUES
					('Đen', '#000000', 'admin', 1, 0),
					('Trắng', '#FFFFFF', 'admin', 1, 0),
					('Xanh dương', '#0000FF', 'admin', 1, 0),
					('Đỏ', '#FF0000', 'admin', 1, 0),
					('Bạc', '#C0C0C0', 'admin', 1, 0);
  
  -- Create table made_from
CREATE TABLE `made_from` (
    `made_from_id` INT AUTO_INCREMENT PRIMARY KEY,
    `country_name` VARCHAR(255) NOT NULL,
    `description` TEXT,

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;
  
INSERT INTO `made_from` (`country_name`, `description`, `created_by`, `is_active`, `is_deleted`) VALUES
						('Mỹ', 'Sản xuất hoặc lắp ráp tại Hoa Kỳ', 'admin', 1, 0),
						('Hàn Quốc', 'Sản xuất hoặc lắp ráp tại Hàn Quốc', 'admin', 1, 0),
						('Trung Quốc', 'Sản xuất hoặc lắp ráp tại Trung Quốc', 'admin', 1, 0),
						('Việt Nam', 'Sản xuất hoặc lắp ráp tại Việt Nam', 'admin', 1, 0);

-- Create table product
CREATE TABLE `product` (
    `product_id` INT AUTO_INCREMENT PRIMARY KEY,
    `brand_id` INT,
    `color_id` INT,
    `made_from_id` INT,
    
    `product_name` VARCHAR(255),

    `screen_size` VARCHAR(255),
    `screen_details` TEXT,
    `screen_resolution` VARCHAR(255),
    `screen_refresh_rate` VARCHAR(255),

    `camera_front` VARCHAR(255),
    `camera_front_details` TEXT,
    `camera_rear` VARCHAR(255),
    `camera_rear_details` TEXT,

    `chipset` VARCHAR(255),
    `gpu` VARCHAR(255),
    `nfc` VARCHAR(255),
    `sim` VARCHAR(255),
    `audio_port` VARCHAR(255),
    `gps` VARCHAR(255),

    `ram` VARCHAR(255),
    `storage_capacity` VARCHAR(255),
    `operating_system` VARCHAR(255),

    `size` VARCHAR(255),
    `weight` VARCHAR(255),
    `back_material` VARCHAR(255),
    `frame_material` VARCHAR(255),
    `wifi` VARCHAR(255),
    `bluetooth` VARCHAR(255),

    `note` TEXT,

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0,
    
    CONSTRAINT `fk_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand`(`brand_id`),
    CONSTRAINT `fk_product_color` FOREIGN KEY (`color_id`) REFERENCES `color`(`color_id`),
    CONSTRAINT `fk_product_made_from` FOREIGN KEY (`made_from_id`) REFERENCES `made_from`(`made_from_id`)
) ENGINE=InnoDB 
  DEFAULT CHARSET=utf8mb4 
  COLLATE=utf8mb4_unicode_ci;
  
INSERT INTO `product` (
    `brand_id`, `color_id`, `made_from_id`, `product_name`,
    `screen_size`, `screen_details`, `screen_resolution`, `screen_refresh_rate`,
    `camera_front`, `camera_front_details`, `camera_rear`, `camera_rear_details`,
    `chipset`, `gpu`, `nfc`, `sim`, `audio_port`, `gps`,
    `ram`, `storage_capacity`, `operating_system`,
    `size`, `weight`, `back_material`, `frame_material`, `wifi`, `bluetooth`,
    `note`, `created_by`, `is_active`, `is_deleted`
) VALUES
(1, 2, 3, 'iPhone 14 Pro Max',
 '6.7 inch', 'Super Retina XDR OLED', '2796x1290', '120Hz',
 '12MP', 'Camera TrueDepth hỗ trợ Face ID', '48MP + 12MP + 12MP', 'Chụp ảnh ban đêm, chống rung quang học',
 'Apple A16 Bionic', 'Apple GPU 5-core', 'Có', '1 Nano SIM + 1 eSIM', 'Không', 'Có',
 '6GB', '128GB', 'iOS 16',
 '160.7 x 77.6 x 7.85 mm', '240g', 'Kính cường lực', 'Thép không gỉ', 'Wi-Fi 6', 'Bluetooth 5.3',
 'Hỗ trợ Dynamic Island', 'admin', 1, 0),

(2, 1, 4, 'Samsung Galaxy S23 Ultra',
 '6.8 inch', 'Dynamic AMOLED 2X', '3088x1440', '120Hz',
 '12MP', 'Camera selfie góc rộng', '200MP + 12MP + 10MP + 10MP', 'Zoom quang học 10x, chống rung OIS',
 'Snapdragon 8 Gen 2', 'Adreno 740', 'Có', '2 Nano SIM', 'USB-C', 'Có',
 '12GB', '256GB', 'Android 13',
 '163.4 x 78.1 x 8.9 mm', '234g', 'Kính Gorilla Glass Victus 2', 'Nhôm Armor', 'Wi-Fi 6E', 'Bluetooth 5.3',
 'Hỗ trợ S-Pen', 'admin', 1, 0),

(3, 3, 3, 'Xiaomi 13 Pro',
 '6.73 inch', 'AMOLED', '3200x1440', '120Hz',
 '32MP', 'Selfie HDR', '50MP + 50MP + 50MP', 'Hợp tác ống kính Leica',
 'Snapdragon 8 Gen 2', 'Adreno 740', 'Có', '2 Nano SIM', 'USB-C', 'Có',
 '12GB', '256GB', 'Android 13',
 '162.9 x 74.6 x 8.38 mm', '229g', 'Gốm', 'Nhôm', 'Wi-Fi 6E', 'Bluetooth 5.3',
 'Sạc nhanh 120W', 'admin', 1, 0),

(4, 4, 3, 'Oppo Find X6 Pro',
 '6.82 inch', 'AMOLED LTPO3', '3168x1440', '120Hz',
 '32MP', 'Camera HDR', '50MP + 50MP + 50MP', 'Hợp tác ống kính Hasselblad',
 'Snapdragon 8 Gen 2', 'Adreno 740', 'Có', '2 Nano SIM', 'USB-C', 'Có',
 '12GB', '512GB', 'Android 13',
 '164.8 x 76.2 x 9.1 mm', '218g', 'Kính', 'Nhôm', 'Wi-Fi 7', 'Bluetooth 5.3',
 'Sạc nhanh 100W', 'admin', 1, 0),

(5, 5, 1, 'Google Pixel 7 Pro',
 '6.7 inch', 'LTPO AMOLED', '3120x1440', '120Hz',
 '10.8MP', 'Camera góc rộng', '50MP + 48MP + 12MP', 'Chụp ảnh đêm Night Sight',
 'Google Tensor G2', 'Mali-G710', 'Có', '1 Nano SIM + 1 eSIM', 'USB-C', 'Có',
 '12GB', '128GB', 'Android 13',
 '162.9 x 76.6 x 8.9 mm', '212g', 'Kính Gorilla Glass Victus', 'Nhôm', 'Wi-Fi 6E', 'Bluetooth 5.2',
 'Hỗ trợ AI xử lý ảnh nâng cao', 'admin', 1, 0);

  -- Create table user
CREATE TABLE `user` (
    `user_id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(100) NOT NULL UNIQUE,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `full_name` VARCHAR(255),
    `phone` VARCHAR(20),
    `address` TEXT,

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

  -- Create table role
CREATE TABLE `role` (
    `role_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL UNIQUE, -- ROLE_ADMIN, ROLE_USER
    `description` TEXT,

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

  -- Create table user_role
CREATE TABLE `user_role` (
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),

    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    FOREIGN KEY (`role_id`) REFERENCES `role`(`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

  -- Create table order
CREATE TABLE `order` (
    `order_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT NOT NULL,
    `total_amount` DECIMAL(15,2) NOT NULL,
    `status` VARCHAR(50) DEFAULT 'PENDING', -- PENDING, CONFIRMED, SHIPPED, COMPLETED, CANCELED
    `shipping_address` VARCHAR(255),

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0,

    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

  -- Create table order_detail
CREATE TABLE `order_detail` (
    `order_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL,
    `price` DECIMAL(15,2) NOT NULL,
    PRIMARY KEY (`order_id`, `product_id`),

    FOREIGN KEY (`order_id`) REFERENCES `order`(`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `product`(`product_id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

  -- Create table order cart
CREATE TABLE `cart` (
    `cart_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT NOT NULL,

    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100),
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100),

    `is_active` TINYINT DEFAULT 0,
    `is_deleted` TINYINT DEFAULT 0,

    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

  -- Create table order cart_item
CREATE TABLE `cart_item` (
    `cart_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`cart_id`, `product_id`),

    FOREIGN KEY (`cart_id`) REFERENCES `cart`(`cart_id`) ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `product`(`product_id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
