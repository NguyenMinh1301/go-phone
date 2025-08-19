USE go_phone_master_db;

INSERT INTO `brand` (`name`, `description`, `created_by`, `is_active`, `is_deleted`) VALUES
					('Apple', 'Thương hiệu điện thoại iPhone nổi tiếng của Mỹ', 'admin', 1, 0),
					('Samsung', 'Hãng điện thoại và thiết bị điện tử Hàn Quốc', 'admin', 1, 0),
					('Xiaomi', 'Thương hiệu điện thoại và thiết bị thông minh của Trung Quốc', 'admin', 1, 0),
					('Oppo', 'Nhà sản xuất smartphone đến từ Trung Quốc', 'admin', 1, 0),
					('Google', 'Hãng điện thoại Pixel của Mỹ', 'admin', 1, 0);
                    
INSERT INTO `color` (`name`, `hex_code`, `created_by`, `is_active`, `is_deleted`) VALUES
					('Đen', '#000000', 'admin', 1, 0),
					('Trắng', '#FFFFFF', 'admin', 1, 0),
					('Xanh dương', '#0000FF', 'admin', 1, 0),
					('Đỏ', '#FF0000', 'admin', 1, 0),
					('Bạc', '#C0C0C0', 'admin', 1, 0);
                    
INSERT INTO `made_from` (`country_name`, `description`, `created_by`, `is_active`, `is_deleted`) VALUES
						('Mỹ', 'Sản xuất hoặc lắp ráp tại Hoa Kỳ', 'admin', 1, 0),
						('Hàn Quốc', 'Sản xuất hoặc lắp ráp tại Hàn Quốc', 'admin', 1, 0),
						('Trung Quốc', 'Sản xuất hoặc lắp ráp tại Trung Quốc', 'admin', 1, 0),
						('Việt Nam', 'Sản xuất hoặc lắp ráp tại Việt Nam', 'admin', 1, 0);
                        
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

INSERT INTO `role` (`role_code`, `role_name`, `priority`) VALUES
('GO_STARTER','Go-Starter',1),
('GO_USER','Go-User',2),
('GO_PLUS','Go-Plus',3),
('GO_PRO','Go-Pro',4),
('GO_ELITE','Go-Elite',5);
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    