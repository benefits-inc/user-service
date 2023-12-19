CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `user_uuid` VARCHAR(200) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `role` ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER' COMMENT 'ADMIN, USER',
  `status` ENUM('REGISTERED', 'UNREGISTERED') NOT NULL COMMENT 'REGISTERED, UNREGISTERED',
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `last_login_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `INDEX_USER_ID` (`user_uuid` ASC) VISIBLE,
  UNIQUE INDEX `INDEX_EMAIL` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `profiles` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(32) NOT NULL,
  `user_uuid` VARCHAR(200) NOT NULL COMMENT '유저 프로필은 1개 제한 unique',
  `profile_image_url` VARCHAR(200) NULL,
  `nick_name` VARCHAR(45) NULL,
  `grade` ENUM('BRONZE', 'SILVER', 'GOLD') NOT NULL DEFAULT 'BRONZE' COMMENT 'BRONZE, SILVER, GOLD',
  PRIMARY KEY (`id`),
  INDEX `INDEX_USER_ID` (`user_uuid` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `address` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(32) NOT NULL,
  `user_uuid` VARCHAR(200) NOT NULL COMMENT '주소 3개까지 등록가능 제한 not unique',
  `receiver` VARCHAR(45) NOT NULL,
  `address1` VARCHAR(200) NOT NULL,
  `address2` VARCHAR(200) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `receive_type` ENUM('DOOR', 'SELF', 'OFFICE', 'POST', 'ETC') NOT NULL COMMENT 'DOOR(문 앞), SELF(직접 받고 부재 시 문 앞), OFFICE(경비실), POST(우편함), ETC',
  `receive_message` VARCHAR(100) NOT NULL COMMENT 'receive_type이 ETC인 경우 직접 입력\n그게 아니면 타입의 description 내용 자동 입력',
  PRIMARY KEY (`id`),
  INDEX `INDEX_USER_ID` (`user_uuid` ASC) VISIBLE)
ENGINE = InnoDB;