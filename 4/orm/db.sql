DROP TABLE IF EXISTS `haraldfw`.konto;
DROP TABLE IF EXISTS `haraldfw`.badkonto;

CREATE TABLE `haraldfw`.konto (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` DECIMAL NOT NULL,
  `version` INT,
  `owner` VARCHAR(256),
  PRIMARY KEY (`id`))
;

CREATE TABLE `haraldfw`.badkonto (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` DECIMAL NOT NULL,
  `owner` VARCHAR(256),
  PRIMARY KEY (`id`))
;

SELECT * FROM konto;
SELECT * FROM badkonto;