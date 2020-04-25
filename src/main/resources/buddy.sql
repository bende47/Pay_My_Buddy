-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 25 Avril 2020 à 14:17
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `buddy`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `idcpte` bigint(20) NOT NULL AUTO_INCREMENT,
  `datecreation` datetime DEFAULT NULL,
  `solde` double DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idcpte`),
  KEY `FK2vtonjuktoy7p8jh6mlxdsa8u` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `connections`
--

CREATE TABLE IF NOT EXISTS `connections` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datecreation` datetime DEFAULT NULL,
  `iduser_con` bigint(20) DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbw2gfrwjb34bisq5glmgq3lkj` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
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
  KEY `FKgdsktfhqx9yvbomuc2jg3t23` (`idcpte`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `iduser` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) DEFAULT NULL,
  `datederniereconnexion` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenoms` varchar(255) DEFAULT NULL,
  `statut` bit(1) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`iduser`, `contact`, `datederniereconnexion`, `email`, `nom`, `password`, `prenoms`, `statut`) VALUES
(1, '0789654123', NULL, 'messou@gmail.com', 'Messou', '$2a$10$TBMAzdS2wNWV.0N0BdUUS.f/upxb9i6JnhmFwiJNGIgv2izjWg/7e', 'Arsene', b'1');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FK2vtonjuktoy7p8jh6mlxdsa8u` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Contraintes pour la table `connections`
--
ALTER TABLE `connections`
  ADD CONSTRAINT `FKbw2gfrwjb34bisq5glmgq3lkj` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Contraintes pour la table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `FKgdsktfhqx9yvbomuc2jg3t23` FOREIGN KEY (`idcpte`) REFERENCES `compte` (`idcpte`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
