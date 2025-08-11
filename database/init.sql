DROP DATABASE IF EXISTS go_phone_master_db;

CREATE DATABASE go_phone_master_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE go_phone_master_db;

-- Create table product
CREATE TABLE `product` (
    `product_id` INT AUTO_INCREMENT PRIMARY KEY,
    `brand_id` INT,
    `color_id` INT,
    `made_from_id` INT,

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
    `is_deleted` TINYINT DEFAULT 0
) ENGINE=InnoDB 
  DEFAULT CHARSET=utf8mb4 
  COLLATE=utf8mb4_unicode_ci;
