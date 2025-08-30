SET
  NAMES utf8mb4;
SET
  character_set_client = utf8mb4;
SET
  collation_connection = utf8mb4_unicode_ci;
DROP
  DATABASE IF EXISTS go_phone_master_db;
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
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
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
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
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
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
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
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
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
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- Create table role
CREATE TABLE `role` (
  `role_id` INT PRIMARY KEY AUTO_INCREMENT,
  `role_code` VARCHAR(50) UNIQUE NOT NULL,
  -- GO_STARTER, GO_USER, GO_PLUS, GO_PRO, GO_ELITE
  `role_name` VARCHAR(100) NOT NULL,
  `priority` INT NOT NULL,
  -- thứ bậc (1..5)
  `s_active` TINYINT(1) DEFAULT 1
);
INSERT INTO `role` (
  `role_code`, `role_name`, `priority`
)
VALUES
  ('GO_STARTER', 'Go-Starter', 1),
  ('GO_USER', 'Go-User', 2),
  ('GO_PLUS', 'Go-Plus', 3),
  ('GO_PRO', 'Go-Pro', 4),
  ('GO_ELITE', 'Go-Elite', 5);
-- Create table user_role
CREATE TABLE `user_role` (
  `user_id` INT,
  `role_id` INT,
  PRIMARY KEY (`user_id`, `role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
  FOREIGN KEY (`role_id`) REFERENCES `role`(`role_id`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
CREATE TABLE `revoked_token` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `token` VARCHAR(512) NOT NULL UNIQUE,
  `expires_at` DATETIME NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  INDEX `idx_expires_at` (`expires_at`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- Create table carts
CREATE TABLE carts (
  cart_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NULL,
  guest_token VARCHAR(64) NULL,
  status VARCHAR(24) NOT NULL DEFAULT 'ACTIVE',
  -- ACTIVE / CHECKED_OUT / EXPIRED
  total_amount BIGINT NOT NULL DEFAULT 0,
  discount_amount BIGINT NOT NULL DEFAULT 0,
  shipping_fee BIGINT NOT NULL DEFAULT 0,
  tax_amount BIGINT NOT NULL DEFAULT 0,
  grand_total BIGINT NOT NULL DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(64),
  updated_at TIMESTAMP NULL,
  updated_by VARCHAR(64),
  is_deleted TINYINT(1) NOT NULL DEFAULT 0,
  KEY idx_carts_user (user_id),
  KEY idx_carts_guest (guest_token)
);
-- Create table cart_items
CREATE TABLE cart_items (
  cart_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  cart_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  sku_id BIGINT NULL,
  price_unit BIGINT NOT NULL,
  qty INT NOT NULL,
  subtotal BIGINT NOT NULL,
  snapshot_json LONGTEXT NULL,
  is_deleted TINYINT(1) NOT NULL DEFAULT 0,
  FOREIGN KEY (cart_id) REFERENCES carts(cart_id) ON DELETE CASCADE,
  KEY idx_cart_items_cart (cart_id)
);
-- Create table orders
CREATE TABLE orders (
  order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_code BIGINT NOT NULL UNIQUE,
  user_id BIGINT NULL,
  status VARCHAR(24) NOT NULL,
  -- PENDING_PAYMENT / PAID / CANCELLED / EXPIRED / REFUNDED
  amount_items BIGINT NOT NULL,
  discount_amount BIGINT NOT NULL DEFAULT 0,
  shipping_fee BIGINT NOT NULL DEFAULT 0,
  tax_amount BIGINT NOT NULL DEFAULT 0,
  grand_total BIGINT NOT NULL,
  currency VARCHAR(3) NOT NULL DEFAULT 'VND',
  customer_name VARCHAR(120),
  customer_phone VARCHAR(32),
  customer_email VARCHAR(120),
  shipping_address_json LONGTEXT NULL,
  pay_gateway VARCHAR(32) NOT NULL DEFAULT 'PAYOS',
  pay_ref VARCHAR(64) NULL,
  -- paymentLinkId/payOS id
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(64),
  updated_at TIMESTAMP NULL,
  updated_by VARCHAR(64),
  is_deleted TINYINT(1) NOT NULL DEFAULT 0,
  KEY idx_orders_code (order_code),
  KEY idx_orders_user (user_id)
);
-- Create table order_items
CREATE TABLE order_items (
  order_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  sku_id BIGINT NULL,
  name_snapshot VARCHAR(255),
  price_unit BIGINT NOT NULL,
  qty INT NOT NULL,
  subtotal BIGINT NOT NULL,
  vat_rate DECIMAL(5, 2) DEFAULT 0,
  is_deleted TINYINT(1) NOT NULL DEFAULT 0,
  FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
  KEY idx_order_items_order (order_id)
);
-- Create table inventory_reservations
CREATE TABLE inventory_reservations (
  resv_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  sku_id BIGINT NOT NULL,
  qty INT NOT NULL,
  expires_at TIMESTAMP NOT NULL,
  status VARCHAR(16) NOT NULL,
  -- HELD / RELEASED / APPLIED
  KEY idx_resv_order (order_id),
  KEY idx_resv_exp (expires_at)
);
-- Create table payment_attempts
CREATE TABLE payment_attempts (
  attempt_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  gateway VARCHAR(32) NOT NULL DEFAULT 'PAYOS',
  gateway_payment_id VARCHAR(64),
  checkout_url VARCHAR(512),
  status VARCHAR(24) NOT NULL,
  -- INITIATED / PENDING / SUCCEEDED / FAILED / CANCELLED
  amount BIGINT NOT NULL,
  currency VARCHAR(3) NOT NULL DEFAULT 'VND',
  idempotency_key VARCHAR(64),
  raw_payload LONGTEXT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NULL,
  UNIQUE KEY uk_attempt_order_gateway (order_id, gateway)
);
-- Create table payment_events
CREATE TABLE payment_events (
  event_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  gateway_payment_id VARCHAR(64) NOT NULL,
  payload_hash CHAR(64) NOT NULL,
  type VARCHAR(64),
  received_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_evt_unique (
    gateway_payment_id, payload_hash
  )
);
