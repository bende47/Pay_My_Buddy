
--
-- Base de données :  `buddy`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `idcpte` bigint(20) NOT NULL,
  `datecreation` datetime DEFAULT NULL,
  `solde` double DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idcpte`),
  KEY `FK2vtonjuktoy7p8jh6mlxdsa8u` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `connection`
--

CREATE TABLE IF NOT EXISTS `connection` (
  `id` bigint(20) NOT NULL,
  `datecreation` datetime DEFAULT NULL,
  `iduser_con` int(11) NOT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo730e5o1hk0e7rinfrsx8ul6b` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1),
(1),
(1);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `type_trans` varchar(2) NOT NULL,
  `idtrans` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `datecreation` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `idcompte_rec` int(11) NOT NULL,
  `idcpte` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idtrans`),
  KEY `FKfv7nq3kv2yl6kn2jd86gpbqij` (`idcpte`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `iduser` bigint(20) NOT NULL,
  `datederniereconnexion` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenoms` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FK2vtonjuktoy7p8jh6mlxdsa8u` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Contraintes pour la table `connection`
--
ALTER TABLE `connection`
  ADD CONSTRAINT `FKo730e5o1hk0e7rinfrsx8ul6b` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FKfv7nq3kv2yl6kn2jd86gpbqij` FOREIGN KEY (`idcpte`) REFERENCES `compte` (`idcpte`);

