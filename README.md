# Go‑Phone — Backend Thương mại điện tử (Spring Boot/MyBatis)

<p align="center">
  <a href="./">
    <img src="logo/GO.svg" alt="Go-Phone Logo" width="160">
  </a>
</p>

Dự án Go‑Phone hiện thực hoá các nghiệp vụ cốt lõi của một sàn bán điện thoại/phụ kiện: quản lý sản phẩm, phân loại, xác thực người dùng bằng JWT, đơn hàng và tích hợp cổng thanh toán PayOS. Mã nguồn tổ chức theo lớp (controller → service → mapper → domain), ưu tiên chuẩn hoá response và xử lý lỗi thống nhất.

[![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.7-6DB33F?logo=springboot&logoColor=white)]()
[![MyBatis](https://img.shields.io/badge/MyBatis-Mapper-3E3E3E)]()
[![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)]()
[![Redis](https://img.shields.io/badge/Redis-DC382D?logo=redis&logoColor=white)]()
[![JWT](https://img.shields.io/badge/JWT-Security-000000?logo=jsonwebtokens&logoColor=white)]()
[![Spotless](https://img.shields.io/badge/Code%20Style-Spotless-1f6feb)]()
[![JaCoCo](https://img.shields.io/badge/Coverage-JaCoCo-orange)]()
[![SonarQube](https://img.shields.io/badge/SonarQube-4E9BCD?logo=sonarqube&logoColor=white)]()
[![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)]()
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)]()

> Tất cả ví dụ và đường dẫn dưới đây dùng tiền tố API mặc định là `/api/v1`.

## Công nghệ sử dụng

* **Ngôn ngữ/Runtime**: Java **21**
* **Framework**: Spring Boot **3.4.7** (Web, Validation, Security, Mail, Actuator)
* **ORM/Mapper**: **MyBatis** (XML mapper, tránh `SELECT *`)
* **CSDL chính**: **MySQL 8**
* **Bộ nhớ nhanh**: **Redis** (OTP, rate limit, cache ngắn hạn)
* **Bảo mật**: **JWT** (jjwt) + Spring Security
* **Định dạng mã**: **Spotless**
* **Độ phủ kiểm thử**: **JaCoCo**
* **Chất lượng mã**: **SonarQube** (local/server)
* **Đóng gói & hạ tầng**: Maven, Docker/Docker Compose

---

## Cấu trúc thư mục

Cấu trúc tổng quan (rút gọn):

```
go_phone/
 ├─ common/
 │   ├─ constants/ApiConstants.java
 │   ├─ exception/{ErrorCode.java, AppException.java, GlobalExceptionHandler.java}
 │   ├─ mapper/BaseMapper.java
 │   ├─ response/{ApiResponse.java, PageResponse.java, ResponseHandler.java, ResponseWriter.java}
 │   └─ util/{CalculateOffset.java, OrderNumberGenerator.java, HashingUtils.java}
 ├─ feature/
 │   ├─ auth/ ...
 │   ├─ product/ ...
 │   ├─ category/ (brand, color, made-from) ...
 │   ├─ orders/ ...
 │   └─ payment/ (payos) ...
 ├─ docker-compose.yml
 ├─ pom.xml
 └─ docs/
     ├─ Hướng dẫn cấu hình môi trường.docx/        # Hướng dẫn .env
     ├─ Hướng dẫn setup Docker.docx/             # Hướng dẫn Docker/Compose
     └─ Hướng dẫn setup PayOS.docx/              # Hướng dẫn tích hợp PayOS
```

---

## Chuẩn hoá API & xử lý lỗi

### Mẫu response

```json
{
  "success": true,
  "message": "SUCCESS",
  "code": null,
  "data": { "..." },
  "timestamp": "2025-09-10T12:34:56.789"
}
```

* `success`: trạng thái xử lý
* `message`: thông điệp tổng quát/chi tiết ngắn
* `code`: mã lỗi nghiệp vụ (khi `success=false`)
* `data`: payload trả về
* `timestamp`: thời điểm phát sinh response

### Quy ước mã lỗi (ví dụ)

* `5000`: Lỗi hệ thống hoặc xác thực
* `4000`: Lỗi dịch vụ ngoài
* `3000`: Dữ liệu/đầu vào không hợp lệ
* `2000`: Không tìm thấy/đã tồn tại

Tất cả exception được thu gom tại `GlobalExceptionHandler` và ánh xạ về schema ở trên.

## Đặc tả xác thực

* Cơ chế: JWT Bearer token.
* Header: `Authorization: Bearer <token>` cho các endpoint cần bảo vệ.
* Token được phát hành sau khi đăng nhập thành công và có hạn sử dụng theo cấu hình.

## Danh sách endpoint

> Tiền tố mặc định: `/api/v1`. Tham số phân trang: `page` (bắt đầu từ 1), `size`. Offset tính bởi `CalculateOffset`.

### Auth

* `POST  /auth/register` – đăng ký tài khoản
* `POST  /auth/login` – đăng nhập, trả JWT
* `POST  /auth/logout` – đăng xuất
* `POST  /auth/introspect` – kiểm tra hiệu lực token
* `POST  /auth/forgot/request` – gửi OTP (email) yêu cầu xác nhận bằng mã 6 chữ số
* `POST  /auth/forgot/reset` – hoàn tất đặt lại mật khẩu

### Product

* `GET   /product/get` – danh sách sản phẩm
* `GET   /product/page?page=&size=` – phân trang
* `GET   /product/search?keyword=&page=&size=` – tìm kiếm có phân trang
* `GET   /product/get/{id}` – chi tiết theo id
* `POST  /product/create` – tạo mới
* `PUT   /product/update/{id}` – cập nhật
* `DELETE /product/delete/{id}` – xoá mềm

### Category (brand/color/made-from)

* `GET   /category/{brand|color|made-from}/get`
* `GET   /category/{brand|color|made-from}/page?page=&size=`
* `GET   /category/{brand|color|made-from}/search?keyword=&page=&size=`
* `GET   /category/{brand|color|made-from}/get/{id}`
* `POST  /category/{brand|color|made-from}/create`
* `PUT   /category/{brand|color|made-from}/update/{id}`
* `DELETE /category/{brand|color|made-from}/delete/{id}`

### Orders

* `POST  /orders/create` – tạo đơn; `OrderNumberGenerator` sinh mã 10 chữ số \[1\_000\_000\_000, 9\_999\_999\_999]
* `GET   /orders/get/{orderCode}` – tra cứu đơn theo mã

### Payments (PayOS)

* `POST  /payments/payos/create` – tạo phiên thanh toán PayOS (trả link thanh toán)
* `POST  /payments/payos/webhooks/payos` – webhook nhận thông báo trạng thái thanh toán

### Hệ thống/Actuator

* `GET   /actuator/health` – tình trạng ứng dụng

## Cấu hình & vận hành

### Tài liệu cấu hình môi trường (.env)

Xem chi tiết tại thư mục: `docs/Hướng dẫn cấu hình môi trường.docx/`
(bao gồm mô tả biến môi trường MySQL, Redis, JWT, Mail, PayOS, v.v.)

### Tài liệu setup Docker

Xem chi tiết tại thư mục: `docs/Hướng dẫn setup Docker.docx/`
(bao gồm hướng dẫn khởi tạo hạ tầng bằng Docker Compose, mapping cổng, khởi tạo schema ban đầu bằng `init.sql`)

### Tài liệu setup PayOS

Xem chi tiết tại thư mục: `docs/Hướng dẫn setup PayOS.docx/`
(bao gồm cấu hình `PAYOS_CLIENT_ID`, `PAYOS_API_KEY`, `PAYOS_CHECKSUM_KEY`, quy trình tạo payment và webhook)

### Build & chạy ứng dụng

**Yêu cầu**: JDK 21, Maven 3.9+

Build:

```bash
mvn clean package -DskipTests
```

Chạy bằng JAR (đảm bảo biến môi trường đã cấu hình theo tài liệu `docs/Hướng dẫn setup Docker.docx/`):

```bash
java -jar target/gophone-0.1.jar
```

Chạy trực tiếp qua Maven:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## Kiểm thử & chất lượng mã

* **JaCoCo**: thu thập và hiển thị độ phủ kiểm thử

  ```bash
  mvn test
  mvn jacoco:report
  # Báo cáo HTML ở: target/site/jacoco/index.html
  ```
* **Spotless**: định dạng mã theo chuẩn

  ```bash
  mvn spotless:apply
  mvn spotless:check
  ```
* **SonarQube**: phân tích tĩnh

  ```bash
  mvn -DskipTests sonar:sonar \
    -Dsonar.host.url=<SONAR_URL> \
    -Dsonar.login=<SONAR_TOKEN>
  ```

## Ghi chú bảo mật

* Không ghi log thông tin nhạy cảm (mật khẩu, token, khoá bí mật).
* JWT secret và các credential phải quản lý qua biến môi trường/secret manager.
* Ràng buộc quyền truy cập endpoint nhạy cảm ở lớp Security.

## Phụ lục: cURL mẫu

Đăng nhập:

```bash
curl -X POST http://localhost:8888/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user@example.com","password":"your-password"}'
```

Lấy danh sách sản phẩm (phân trang):

```bash
curl "http://localhost:8888/api/v1/product/page?page=1&size=20"
```

Tạo đơn hàng:

```bash
curl -X POST http://localhost:8888/api/v1/orders/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <JWT>" \
  -d '{"items":[{"productId":1,"qty":2}], "note":"..."}'
```

Tạo phiên thanh toán PayOS:

```bash
curl -X POST http://localhost:8888/api/v1/payments/payos/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <JWT>" \
  -d '{"orderCode":1234567890, "amount": 1000000, "description":"Thanh toan don hang"}'
```
