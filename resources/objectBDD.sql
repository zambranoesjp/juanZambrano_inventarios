CREATE TABLE `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `id` varchar(16) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `store` (
  `id_store` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id_store`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `orders` (
  `id_order` int NOT NULL AUTO_INCREMENT,
  `id_client` int NOT NULL,
  `date_order` datetime NOT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_detail` (
  `id_order_detail` int NOT NULL AUTO_INCREMENT,
  `id_store` int NOT NULL,
  `id_product` int NOT NULL,
  `quantity` int NOT NULL,
  `id_order` int NOT NULL,
  PRIMARY KEY (`id_order_detail`),
  KEY `fk2_idx` (`id_store`),
  KEY `fk3_idx` (`id_product`),
  CONSTRAINT `fk2` FOREIGN KEY (`id_store`) REFERENCES `store` (`id_store`),
  CONSTRAINT `fk3` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `store_product` (
  `id_store_product` int NOT NULL AUTO_INCREMENT,
  `id_store` int NOT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id_store_product`),
  KEY `store_fk_idx` (`id_store`),
  KEY `product_fk_idx` (`id_product`),
  CONSTRAINT `product_fk` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  CONSTRAINT `store_fk` FOREIGN KEY (`id_store`) REFERENCES `store` (`id_store`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci