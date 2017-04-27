CREATE DATABASE DetailService;
USE DetailService;
CREATE TABLE `Cell` (
  `cell_id` int(11) NOT NULL,
  `ctn` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Cell_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567890);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567891);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567892);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567893);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567894);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567895);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567896);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567897);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (11111, 1234567898);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);
INSERT INTO DetailService.Cell (cell_id, ctn) VALUES (22222, 2147483647);