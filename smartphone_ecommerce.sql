CREATE DATABASE IF NOT EXISTS project_md3_smartphone_ecommerce;
USE project_md3_smartphone_ecommerce;

# --Khách hàng khi muốn mua hàng => phải đăng ký tài khoản => bảng users
CREATE TABLE IF NOT EXISTS users
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    user_name         VARCHAR(100)        DEFAULT '',
    email             VARCHAR(255) UNIQUE            NOT NULL,
    role_id           VARCHAR(100)        DEFAULT '' NOT NULL,
    status            bit(1)                         NOT NULL COMMENT 'trạng thái còn hoạt động hay ko',
    address           VARCHAR(255)        DEFAULT '',
    password          VARCHAR(100) UNIQUE DEFAULT '' NOT NULL,
    avatar            VARCHAR(255),
    created_at        DATETIME,
    updated_at        DATETIME,
    is_deleted        TINYINT(1)          DEFAULT 0,
    date_of_birth     DATE,
    google_account_id INT                 DEFAULT 0
    );

# ----------------------------------------------------roles--------------------------

CREATE TABLE IF NOT EXISTS roles
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(20) NOT NULL DEFAULT ''
    );

# ----------------------------------------------------user_roles--------------------------


CREATE TABLE IF NOT EXISTS user_roles
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    role_id INT NOT NULL

);
ALTER TABLE user_roles
    ADD CONSTRAINT FOREIGN KEY (role_id) REFERENCES roles (id);
ALTER TABLE user_roles
    ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id);



# ----------------------------------------------------categories--------------------------

CREATE TABLE IF NOT EXISTS categories
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL DEFAULT '',
    description     TEXT                ,
    status      BIT                   DEFAULT 1
    );

# ----------------------------------------------------products--------------------------

CREATE TABLE IF NOT EXISTS products
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    sku            VARCHAR(255) UNIQUE NOT NULL        DEFAULT '',
    name           VARCHAR(100)        NOT NULL UNIQUE DEFAULT '',
    description    TEXT                                ,
    unit_price     DECIMAL(10, 2),
    stock_quantity INT                 NOT NULL        DEFAULT 1,
    image          VARCHAR(255),
    category_id    INT                 NOT NULL,
    created_at     DATETIME,
    updated_at     DATETIME
    );
ALTER TABLE products
    ADD CONSTRAINT FOREIGN KEY (category_id) REFERENCES categories (id);

# ----------------------------------------------------orders--------------------------

CREATE TABLE IF NOT EXISTS orders
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    serial_number  varchar(255) UNIQUE NOT NULL DEFAULT '' COMMENT 'UUID Auto_Increment',
    total_price    DECIMAL(10, 2),
    status         VARCHAR(20) COMMENT
    'WAITING : Đơn hàng mới chờ xác nhận
    CONFIRM : Đã xác nhận
    DELIVERY : Đang giao hàng
    SUCCESS : Đã giao hàng
    CANCEL : Đã hủy đơn
    DENIED: Bị từ chối',
    orders_name    VARCHAR(100)                 DEFAULT '',
    orders_address VARCHAR(255)        NOT NULL DEFAULT '',
    orders_phone   VARCHAR(20)         NOT NULL DEFAULT '',
    note           VARCHAR(100)                 DEFAULT '',
    created_at     DATETIME COMMENT 'Mac Dinh Ngay Hien Tai',
    received_at    DATETIME COMMENT 'Thoi Gian Nhan Hang',
    user_id        INT                 NOT NULL

    );

ALTER TABLE orders
    ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id);

# ----------------------------------------------------order_details--------------------------

CREATE TABLE IF NOT EXISTS order_details
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    product_name TEXT ,
    unit_price   DECIMAL(10, 2),
    category_id  INT NOT NULL,
    product_id   INT NOT NULL
    );
ALTER TABLE order_details
    ADD CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id),
    ADD CONSTRAINT FOREIGN KEY (category_id) REFERENCES categories (id);


# ----------------------------------------------------shopping_cart--------------------------

CREATE TABLE IF NOT EXISTS shopping_carts
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    order_quantity INT,
    user_id        INT NOT NULL,
    product_id     INT NOT NULL
);

ALTER TABLE shopping_carts
    ADD CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id),
    ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id);

# ----------------------------------------------------shopping_cart--------------------------

CREATE TABLE IF NOT EXISTS address
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    full_address VARCHAR(255),
    user_id      INT         NOT NULL,
    phone        VARCHAR(15) NOT NULL,
    receive_name VARCHAR(50) NOT NULL
    );


ALTER TABLE shopping_carts
    ADD CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id);

# ----------------------------------------------------wish_list--------------------------

CREATE TABLE IF NOT EXISTS wish_lists
(
    id           INT PRIMARY KEY AUTO_INCREMENT,

    user_id      INT         NOT NULL,
    product_id      INT         NOT NULL

);
ALTER TABLE wish_lists
    ADD CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id),
    ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id);
















