CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `author` varchar(80) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
); 