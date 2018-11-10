-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema VideoGameDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `VideoGameDB` ;

-- -----------------------------------------------------
-- Schema VideoGameDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `VideoGameDB` DEFAULT CHARACTER SET utf8 ;
USE `VideoGameDB` ;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL,
  `name` ENUM('ACTION', 'ADVENTURE', 'PUZZLE', 'RPG', 'FIGHTING', 'SPORTS', 'SHOOTER') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `videogame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videogame` ;

CREATE TABLE IF NOT EXISTS `videogame` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `rating` ENUM('E', 'T', 'M', 'A') NOT NULL,
  `description` VARCHAR(500) NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `features` VARCHAR(500) NULL,
  `release_date` DATE NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_videogame_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform` ;

CREATE TABLE IF NOT EXISTS `platform` (
  `id` INT NOT NULL,
  `system` ENUM('PS4', 'XBOX1', 'PC', 'SWITCH', '3DS') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform_has_videogame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform_has_videogame` ;

CREATE TABLE IF NOT EXISTS `platform_has_videogame` (
  `platform_id` INT NOT NULL,
  `videogame_id` INT NOT NULL,
  PRIMARY KEY (`platform_id`, `videogame_id`),
  CONSTRAINT `fk_platform_has_videogame_platform`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_platform_has_videogame_videogame1`
    FOREIGN KEY (`videogame_id`)
    REFERENCES `videogame` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS gamer;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'gamer' IDENTIFIED BY 'gamer';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `VideoGameDB`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'ACTION');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'ADVENTURE');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'PUZZLE');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'SHOOTER');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'RPG');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'SPORTS');
INSERT INTO `category` (`id`, `name`) VALUES (7, 'FIGHTING');

COMMIT;


-- -----------------------------------------------------
-- Data for table `videogame`
-- -----------------------------------------------------
START TRANSACTION;
USE `VideoGameDB`;
INSERT INTO `videogame` (`id`, `title`, `rating`, `description`, `price`, `features`, `release_date`, `category_id`) VALUES (1, 'Outerspace', 'M', 'Partake in space battles in space', 60.00, 'Online multiplayer, single player campaign, up to 4 players on one screen', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `platform`
-- -----------------------------------------------------
START TRANSACTION;
USE `VideoGameDB`;
INSERT INTO `platform` (`id`, `system`) VALUES (1, 'PS4');
INSERT INTO `platform` (`id`, `system`) VALUES (2, 'XBOX1');
INSERT INTO `platform` (`id`, `system`) VALUES (3, 'PC');
INSERT INTO `platform` (`id`, `system`) VALUES (4, 'SWITCH');
INSERT INTO `platform` (`id`, `system`) VALUES (5, '3DS');

COMMIT;


-- -----------------------------------------------------
-- Data for table `platform_has_videogame`
-- -----------------------------------------------------
START TRANSACTION;
USE `VideoGameDB`;
INSERT INTO `platform_has_videogame` (`platform_id`, `videogame_id`) VALUES (1, 1);
INSERT INTO `platform_has_videogame` (`platform_id`, `videogame_id`) VALUES (2, 1);
INSERT INTO `platform_has_videogame` (`platform_id`, `videogame_id`) VALUES (3, 1);

COMMIT;

