CREATE DATABASE test;
USE test;
CREATE TABLE `Cell` (
  `cell_id` int(11) NOT NULL,
  `ctn` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Cell_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
