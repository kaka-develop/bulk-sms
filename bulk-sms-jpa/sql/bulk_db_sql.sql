-- MySQL Workbench Synchronization
-- Generated: 2016-07-28 15:04
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: tung

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `bulksms` DEFAULT CHARACTER SET latin1 ;

CREATE TABLE IF NOT EXISTS `bulksms`.`blacklist_general` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `keyword` VARCHAR(45) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`blacklist_numbers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` NVARCHAR(12) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `partner_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_blacklist_numbers_partner1_idx` (`partner_id` ASC),
  CONSTRAINT `fk_blacklist_numbers_partner1`
    FOREIGN KEY (`partner_id`)
    REFERENCES `bulksms`.`partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`blacklist_topic` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `key_word` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `partner_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `partner_id`),
  INDEX `fk_blacklist_topic_partner1_idx` (`partner_id` ASC),
  CONSTRAINT `fk_blacklist_topic_partner1`
    FOREIGN KEY (`partner_id`)
    REFERENCES `bulksms`.`partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`bundle` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `quantity` INT(11) NULL DEFAULT NULL,
  `renewal_date` INT(11) NULL DEFAULT NULL,
  `price` FLOAT(11) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`group_n_contact` (
  `created_date` DATE NULL DEFAULT NULL,
  `partner_group_contact_id` INT(11) NOT NULL,
  `partner_contact_id` INT(11) NOT NULL,
  PRIMARY KEY (`partner_group_contact_id`, `partner_contact_id`),
  INDEX `fk_group_n_contact_partner_group_contact1_idx` (`partner_group_contact_id` ASC),
  INDEX `fk_group_n_contact_partner_contact1_idx` (`partner_contact_id` ASC),
  CONSTRAINT `fk_group_n_contact_partner_group_contact1`
    FOREIGN KEY (`partner_group_contact_id`)
    REFERENCES `bulksms`.`partner_group_contact` (`id`)
   ON DELETE CASCADE,
  CONSTRAINT `fk_group_n_contact_partner_contact1`
    FOREIGN KEY (`partner_contact_id`)
    REFERENCES `bulksms`.`partner_contact` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `brand_name` VARCHAR(45) NULL DEFAULT NULL,
  `about` VARCHAR(255) NULL DEFAULT NULL,
  `trigger_deliver_api` VARCHAR(255) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner_token`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `token` VARCHAR(255) NULL DEFAULT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner_admin_account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `info` VARCHAR(45) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone_number` INT(11) NULL DEFAULT NULL,
  `avatar` VARCHAR(45) NULL DEFAULT NULL,
  `enable` BOOLEAN DEFAULT FALSE,
  `config_notify_by_email` BIT(1) NULL DEFAULT NULL,
  `config_notify_by_phone` BIT(1) NULL DEFAULT NULL,
  `config_notify_by_app` BIT(1) NULL DEFAULT NULL,
  `partner_id` INT(11) NULL DEFAULT NULL,
  `partner_admin_role_id` INT(11) NULL DEFAULT NULL,
  `partner_token_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partner_admin_account_partner1_idx` (`partner_id` ASC),
  INDEX `fk_partner_admin_account_partner_admin_role1_idx` (`partner_admin_role_id` ASC),
  CONSTRAINT `fk_partner_admin_account_partner1`
    FOREIGN KEY (`partner_id`)
    REFERENCES `bulksms`.`partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partner_admin_account_partner_admin_role1`
    FOREIGN KEY (`partner_admin_role_id`)
    REFERENCES `bulksms`.`partner_admin_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
	CONSTRAINT `fk_partner_admin_account_partner_token`
    FOREIGN KEY (`partner_token_id`)
    REFERENCES `bulksms`.`partner_token` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner_admin_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `role` INT(11) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner_contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `phone_number` VARCHAR(16) NULL DEFAULT NULL,
  `info` VARCHAR(255) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `partner_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_partner_contact_partner`
    FOREIGN KEY (`partner_id`)
    REFERENCES `bulksms`.`partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner_group_contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `info` VARCHAR(255) NULL DEFAULT NULL,
  `partner_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partner_group_contact_partner_idx` (`partner_id` ASC),
  CONSTRAINT `fk_partner_group_contact_partner`
    FOREIGN KEY (`partner_id`)
    REFERENCES `bulksms`.`partner` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`subscription` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `quantity` INT(11) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `expire_date` DATETIME NULL DEFAULT NULL,
  `partner_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_subscription_partner1_idx` (`partner_id` ASC),
  CONSTRAINT `fk_subscription_partner1`
    FOREIGN KEY (`partner_id`)
    REFERENCES `bulksms`.`partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`uploaded_files` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(255) NOT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `process_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner_scheduler` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `crontab_expression` VARCHAR(255) NULL DEFAULT NULL,
  `params` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `bulksms`.`partner_template` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(500) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


CREATE TABLE IF NOT EXISTS  `bulksms`.`bundle_history` (
	partner_id int(11),
  bundle_id int(11),
  created_date datetime,
  foreign key (partner_id) references partner(id) ON DELETE CASCADE,
  foreign key (bundle_id) references bundle(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS  `bulksms`.`bundle_extend`(
	partner_id int(11),
  bundle_id int(11),
  state varchar(15),
  updated_date date,
  foreign key(partner_id) references partner(id) ON DELETE CASCADE,
  foreign key(bundle_id) references bundle(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS  `bulksms`.`sms_history`(
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `content` varchar(255),
  `status` varchar(15),
  `created_date` datetime,
  `updated_date` datetime,
  `partner_id` int(11),
  foreign key(partner_id) references partner(id) ON DELETE CASCADE
);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
