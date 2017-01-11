-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 11 Janvier 2017 à 22:55
-- Version du serveur :  5.6.15-log
-- Version de PHP :  5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `projet_android`
--

-- --------------------------------------------------------

--
-- Structure de la table `scores`
--

CREATE TABLE IF NOT EXISTS `scores` (
  `id_score` int(11) NOT NULL AUTO_INCREMENT,
  `jeu` varchar(50) NOT NULL,
  `score` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`id_score`),
  KEY `id_utilisateur` (`id_utilisateur`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;

--
-- Contenu de la table `scores`
--

INSERT INTO `scores` (`id_score`, `jeu`, `score`, `id_utilisateur`) VALUES
(1, 'Arkanoid', 145000, 1),
(2, 'Arkanoid', 456156431, 1),
(3, 'Arkanoid', 2147483647, 1),
(4, 'Arkanoid', 53155347, 1),
(5, 'Arkanoid', 1531516, 1),
(6, 'Arkanoid', 153566233, 1),
(7, 'Arkanoid', 1231235, 1),
(8, 'Arkanoid', 123123, 1),
(9, 'Arkanoid', 123123153, 1),
(10, 'Arkanoid', 2147483647, 1),
(11, 'Arkanoid', 2147483647, 1),
(12, 'Arkanoid', 456645342, 1),
(13, 'Arkanoid', 64532645, 1),
(14, 'Arkanoid', 2147483647, 1),
(15, 'Arkanoid', 453454355, 1),
(16, 'Arkanoid', 45355332, 1),
(17, 'Arkanoid', 2147483647, 1),
(18, 'Super Mario Bros', 1500000, 3),
(19, 'Metroid', 111111543, 3),
(20, 'Castlevania', 54345500, 3),
(21, 'ゼルダの伝説', 543280, 3),
(22, 'Dark Castle', 1, 3),
(23, 'Duck Hunt', 1212002800, 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(50) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id_utilisateur`, `pseudo`, `mdp`) VALUES
(1, 'Xavier', '123456789'),
(2, 'Test1', '12345678'),
(3, 'X', '1'),
(4, 'Roger', '12345678'),
(5, 'Michel', '12345678'),
(6, 'Fabrice', '12345678'),
(7, 'Louis', '12345678'),
(8, 'François', '12345678'),
(9, 'Sebastien', '12345678'),
(10, 'Fabio', '12345678'),
(11, 'Guy', '12345678'),
(12, 'Emile', '12345678'),
(13, 'Charles', '12345678'),
(14, 'Anne', '12345678'),
(15, 'Antoine', '12345678'),
(16, 'Luc', '12345678'),
(17, 'Didier', '12345678'),
(18, 'Christian', '12345678'),
(19, 'Nathalie', '12345678');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
