
CREATE DATABASE Buddy CHARACTER SET 'utf8';


CREATE TABLE `compte` (
  `idcpte` bigint(20) NOT NULL AUTO_INCREMENT,
  `datecreation` datetime DEFAULT NULL,
  `solde` double DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idcpte`),
  KEY `iduser` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


CREATE TABLE `connections` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datecreation` datetime DEFAULT NULL,
  `iduser_con` bigint(20) DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `iduser` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


CREATE TABLE `transactions` (
  `type_trans` varchar(2) NOT NULL,
  `idtrans` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `amount_red` double DEFAULT NULL,
  `amount_vers` double DEFAULT NULL,
  `datecreation` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `idcpte_rec` bigint(20) DEFAULT NULL,
  `idcpte` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idtrans`),
  KEY `idcpte` (`idcpte`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


CREATE TABLE  `user` (
  `iduser` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) DEFAULT NULL,
  `datederniereconnexion` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenoms` varchar(255) DEFAULT NULL,
  `statut` bit(1) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

ALTER TABLE `compte`
  ADD CONSTRAINT `FK_iduser` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

ALTER TABLE `connections`
  ADD CONSTRAINT `FK_iduser` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

ALTER TABLE `transactions`
  ADD CONSTRAINT `FK_iduser` FOREIGN KEY (`idcpte`) REFERENCES `compte` (`idcpte`);

