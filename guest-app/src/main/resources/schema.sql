CREATE TABLE USER
(
    ID        BIGINT AUTO_INCREMENT PRIMARY KEY,
    USER_NAME VARCHAR(128) NOT NULL UNIQUE,
    PASSWORD  VARCHAR(256) NOT NULL
);