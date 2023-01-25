-- -----------------------------------------------------
-- Table `certificate`.`gift_certificate`
-- -----------------------------------------------------
CREATE TABLE `gift_certificate` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(120) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    `duration` INT NOT NULL,
    `create_date` TIMESTAMP NOT NULL,
    `last_update_date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `certificate`.`tag`
-- -----------------------------------------------------
CREATE TABLE `tag` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `certificate`.`gift_certificate_has_tag`
-- -----------------------------------------------------
CREATE TABLE `gift_certificate_has_tag` (
    `gift_certificate_id` INT NOT NULL references gift_certificate (id),
    `tag_id` INT NOT NULL references tag (id));
