SET NAMES utf8mb4;
SET character_set_client = utf8mb4;
SET collation_connection = utf8mb4_unicode_ci;

DROP DATABASE IF EXISTS go_phone_master_db;

CREATE DATABASE go_phone_master_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE go_phone_master_db;

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
  `role_id`     INT PRIMARY KEY AUTO_INCREMENT,
  `role_code`   VARCHAR(50) UNIQUE NOT NULL, -- GO_STARTER, GO_USER, GO_PLUS, GO_PRO, GO_ELITE
  `role_name`   VARCHAR(100) NOT NULL,
  `priority`    INT NOT NULL,                -- thứ bậc (1..5)
  `s_active`   TINYINT(1) DEFAULT 1
);

  -- Create table user_role
CREATE TABLE `user_role` (
    `user_id` INT,
    `role_id` INT,
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
  
CREATE TABLE `revoked_token` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `token` VARCHAR(512) NOT NULL UNIQUE,
  
  `expires_at` DATETIME NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  INDEX `idx_expires_at` (`expires_at`)
) ENGINE=InnoDB 
  DEFAULT CHARSET=utf8mb4 
  COLLATE=utf8mb4_unicode_ci;