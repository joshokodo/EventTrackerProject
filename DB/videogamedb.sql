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
-- Table `Rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rating` ;

CREATE TABLE IF NOT EXISTS `Rating` (
  `id` INT NOT NULL,
  `rated` ENUM('E', 'E10UP', 'T', 'M', 'A', 'TBD') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `videogame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videogame` ;

CREATE TABLE IF NOT EXISTS `videogame` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `features` VARCHAR(500) NULL,
  `release_date` DATE NULL,
  `category_id` INT NOT NULL,
  `own` TINYINT NOT NULL,
  `Rating_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_videogame_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_videogame_Rating1`
    FOREIGN KEY (`Rating_id`)
    REFERENCES `Rating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform` ;

CREATE TABLE IF NOT EXISTS `platform` (
  `id` INT NOT NULL,
  `system` ENUM('PS4', 'XBOX1', 'PC', 'SWITCH', 'THREE_DS') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform_videogame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform_videogame` ;

CREATE TABLE IF NOT EXISTS `platform_videogame` (
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

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'gamer';

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
-- Data for table `Rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `VideoGameDB`;
INSERT INTO `Rating` (`id`, `rated`) VALUES (1, 'E');
INSERT INTO `Rating` (`id`, `rated`) VALUES (2, 'E10UP');
INSERT INTO `Rating` (`id`, `rated`) VALUES (3, 'T');
INSERT INTO `Rating` (`id`, `rated`) VALUES (4, 'M');
INSERT INTO `Rating` (`id`, `rated`) VALUES (5, 'A');
INSERT INTO `Rating` (`id`, `rated`) VALUES (6, 'TBD');

COMMIT;


-- -----------------------------------------------------
-- Data for table `videogame`
-- -----------------------------------------------------
START TRANSACTION;
USE `VideoGameDB`;
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (1, 'Red Dead Redemption 2', 'gang of outlaws looking to score in one final heist', 60.00, 'open world, single player, multiplayer coming soon', '2018-10-26', 1, 0, 4);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (2, 'Hollow Knight', 'metroidvania with atmosphere', 15.00, 'single player, free DLC', '2018-02-24', 2, 1, 3);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (3, 'Overwatch', 'hero shooter moba style', 60.00, 'multiplayer, free DLC', '2016-05-24', 4, 1, 3);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (4, 'Brawlhalla', 'ssb style fighting game', 0.00, 'multiplayer free to play', '2015-11-30', 7, 1, 3);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (5, 'Skyrim', 'open world rpg', 60.00, 'paid dlc, lots of versions', '2011-11-11', 5, 1, 4);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (6, 'Bioshock', 'a mystery under the sea', 60.00, 'paid DLC', '2007-08-21', 4, 1, 4);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (7, 'Fallout 76', 'post apocalyptic rpg shooter', 60.00, 'First time Multiplayer', '2018-11-14', 4, 0, 4);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (8, 'Super Smash Bros. Ultimate', 'fighting game with characters from across nintendo', 60.00, 'multiplayer', '2018-12-6', 7, 0, 2);
INSERT INTO `videogame` (`id`, `title`, `description`, `price`, `features`, `release_date`, `category_id`, `own`, `Rating_id`) VALUES (9, 'Kingdom Hearts 3', 'disney characters meets FF rpg', 60.00, 'N/A', '2019-12-30', 5, 0, 3);

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
INSERT INTO `platform` (`id`, `system`) VALUES (5, 'THREE_DS');

COMMIT;


-- -----------------------------------------------------
-- Data for table `platform_videogame`
-- -----------------------------------------------------
START TRANSACTION;
USE `VideoGameDB`;
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (1, 1);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (2, 1);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (3, 1);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (3, 2);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (1, 3);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (2, 3);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (3, 3);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (3, 4);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (1, 5);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (2, 5);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (3, 5);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (4, 5);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (1, 6);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (2, 6);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (3, 6);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (1, 7);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (2, 7);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (3, 7);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (4, 8);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (1, 9);
INSERT INTO `platform_videogame` (`platform_id`, `videogame_id`) VALUES (4, 9);

COMMIT;

