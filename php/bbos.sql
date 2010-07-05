-- phpMyAdmin SQL Dump
-- version OVH
-- http://www.phpmyadmin.net
--
-- Serveur: mysql5-29.90
-- Généré le : Mer 03 Mars 2010 à 14:20
-- Version du serveur: 5.0.90
-- Version de PHP: 5.2.6-1+lenny4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `ainpactebbos`
--

-- --------------------------------------------------------

--
-- Structure de la table `bbos_action`
--

DROP TABLE IF EXISTS `bbos_action`;
CREATE TABLE IF NOT EXISTS `bbos_action` (
  `idAction` int(10) unsigned NOT NULL auto_increment,
  `Match_idMatch` int(10) unsigned NOT NULL,
  `Player_idPlayer` int(10) unsigned default NULL,
  `Action_type_idAction_type` int(10) unsigned NOT NULL,
  `Opponent_idPlayer` int(10) unsigned default NULL,
  `Team_idTeam` int(10) unsigned NOT NULL,
  `Turn` int(11) default NULL,
  `Divers` text collate latin1_bin,
  PRIMARY KEY  (`idAction`),
  KEY `Match_idMatch` (`Match_idMatch`),
  KEY `Player_idPlayer` (`Player_idPlayer`),
  KEY `Team_idTeam` (`Team_idTeam`),
  KEY `Action_type_idAction_type` (`Action_type_idAction_type`),
  KEY `Opponent_idPlayer` (`Opponent_idPlayer`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=2 ;

--
-- Contenu de la table `bbos_action`
--

INSERT INTO `bbos_action` (`idAction`, `Match_idMatch`, `Player_idPlayer`, `Action_type_idAction_type`, `Opponent_idPlayer`, `Team_idTeam`, `Turn`, `Divers`) VALUES
(1, 2, 1, 1, 2, 1, 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_action_type`
--

DROP TABLE IF EXISTS `bbos_action_type`;
CREATE TABLE IF NOT EXISTS `bbos_action_type` (
  `idAction_type` int(10) unsigned NOT NULL auto_increment,
  `Name` text character set latin1 NOT NULL,
  PRIMARY KEY  (`idAction_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=9 ;

--
-- Contenu de la table `bbos_action_type`
--

INSERT INTO `bbos_action_type` (`idAction_type`, `Name`) VALUES
(1, 'Pass'),
(2, 'Catch'),
(3, 'Touchdown'),
(4, 'Casualty'),
(5, 'Aggression'),
(6, 'Interception'),
(7, 'MVP'),
(8, 'Public');

-- --------------------------------------------------------

--
-- Structure de la table `bbos_card`
--

DROP TABLE IF EXISTS `bbos_card`;
CREATE TABLE IF NOT EXISTS `bbos_card` (
  `idCard` int(10) unsigned NOT NULL auto_increment,
  `Card_type_idCard_type` int(10) unsigned NOT NULL,
  `Name` text collate latin1_bin NOT NULL,
  PRIMARY KEY  (`idCard`),
  KEY `Card_type_idCard_type` (`Card_type_idCard_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=1 ;

--
-- Contenu de la table `bbos_card`
--


-- --------------------------------------------------------

--
-- Structure de la table `bbos_card_type`
--

DROP TABLE IF EXISTS `bbos_card_type`;
CREATE TABLE IF NOT EXISTS `bbos_card_type` (
  `idCardType` int(10) unsigned NOT NULL auto_increment,
  `Name` text character set latin1 NOT NULL,
  `Cost` int(10) NOT NULL,
  PRIMARY KEY  (`idCardType`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=16 ;

--
-- Contenu de la table `bbos_card_type`
--

INSERT INTO `bbos_card_type` (`idCardType`, `Name`, `Cost`) VALUES
(9, 'Miscellaneous mayhem', 50000),
(10, 'Special play', 50000),
(11, 'Magic item', 50000),
(12, 'Dirty trick', 50000),
(13, 'Good karma', 100000),
(14, 'Random event', 200000),
(15, 'Desperate measure', 400000);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_coach`
--

DROP TABLE IF EXISTS `bbos_coach`;
CREATE TABLE IF NOT EXISTS `bbos_coach` (
  `idCoach` int(10) unsigned NOT NULL auto_increment,
  `Name` text character set latin1,
  `NickName` text character set latin1,
  `PWD` text character set latin1,
  PRIMARY KEY  (`idCoach`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=6 ;

--
-- Contenu de la table `bbos_coach`
--

INSERT INTO `bbos_coach` (`idCoach`, `Name`, `NickName`, `PWD`) VALUES
(1, 'Frederic Berger', 'lord bojo', 'lancie'),
(2, 'Frederic Poupet', 'poups', 'poups'),
(3, 'test_poups', 'test_poups', 'test'),
(4, 'test_bojo', 'test_bojo', 'test'),
(5, 'Yann', 'Yanno', 'yanno');

-- --------------------------------------------------------

--
-- Structure de la table `bbos_competence`
--

DROP TABLE IF EXISTS `bbos_competence`;
CREATE TABLE IF NOT EXISTS `bbos_competence` (
  `idCompetence` int(10) unsigned NOT NULL auto_increment,
  `Competence_Type_idCompetence_Type` tinyint(3) unsigned NOT NULL,
  `Name` text,
  PRIMARY KEY  (`idCompetence`),
  KEY `Competence_FKIndex1` (`Competence_Type_idCompetence_Type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=80 ;

--
-- Contenu de la table `bbos_competence`
--

INSERT INTO `bbos_competence` (`idCompetence`, `Competence_Type_idCompetence_Type`, `Name`) VALUES
(1, 1, 'Jump up'),
(2, 1, 'Sure feet'),
(3, 1, 'Dodge'),
(4, 1, 'Side step'),
(5, 1, 'Catch'),
(6, 1, 'Leap'),
(7, 1, 'Sneaky gits'),
(8, 1, 'Sprint'),
(9, 1, 'Diving tackle'),
(10, 1, 'Diving catch'),
(11, 2, 'Multiple block'),
(12, 2, 'Mighty blow'),
(13, 2, 'Strong arm'),
(14, 2, 'Thick skull'),
(15, 2, 'Piling on'),
(16, 2, 'Break tackle'),
(17, 2, 'Guard'),
(18, 2, 'Juggernaut'),
(19, 2, 'Grab'),
(20, 2, 'Stand firm'),
(21, 3, 'Kick-off return'),
(22, 3, 'Strip ball'),
(23, 3, 'Block'),
(24, 3, 'Pass block'),
(25, 3, 'Sure hands'),
(26, 3, 'Kick'),
(27, 3, 'Frenzy'),
(28, 3, 'Dauntless'),
(29, 3, 'Dirty player'),
(30, 3, 'Wrestle'),
(31, 3, 'Fend'),
(32, 3, 'Shadowing'),
(33, 3, 'Pro'),
(34, 3, 'Tackle'),
(35, 4, 'Leader'),
(36, 4, 'Hail Mary pass'),
(37, 4, 'Safe throw'),
(38, 4, 'Nerves of steel'),
(39, 4, 'Pass'),
(40, 4, 'Dump off'),
(41, 4, 'Accurate'),
(42, 5, 'Extra arm'),
(43, 5, 'Horns'),
(44, 5, 'Two heads'),
(45, 5, 'Big hand'),
(46, 5, 'Claws'),
(47, 5, 'Disturbing presence'),
(48, 5, 'Prehensile tail'),
(49, 5, 'Foul appearance'),
(50, 5, 'Tentacles'),
(51, 5, 'Very long legs'),
(52, 6, 'Secret weapon'),
(53, 6, 'Wild animal'),
(54, 6, 'Bombardier'),
(55, 6, 'Bone head'),
(56, 6, 'Ball and chain'),
(57, 6, 'Decay'),
(58, 6, 'Really stupid'),
(59, 6, 'Fan favorite'),
(60, 6, 'Throw a team mate'),
(61, 6, 'No hand'),
(62, 6, 'Titchy'),
(63, 6, 'Stunty'),
(64, 6, 'Loner'),
(65, 6, 'Right stuff'),
(66, 6, 'Stab'),
(67, 6, 'Nurgle''s rot'),
(68, 6, 'Take root'),
(69, 6, 'Hypnotic gaze'),
(70, 6, 'Regeneration'),
(71, 6, 'Blood lust'),
(72, 6, 'Always hungry'),
(73, 6, 'Chainsaw'),
(74, 6, 'Stakes'),
(75, 6, 'Animosity'),
(76, 7, '+1 Strength'),
(77, 7, '+1 Agility'),
(78, 7, '+1 Armor'),
(79, 7, '+1 Movement');

-- --------------------------------------------------------

--
-- Structure de la table `bbos_competence_type`
--

DROP TABLE IF EXISTS `bbos_competence_type`;
CREATE TABLE IF NOT EXISTS `bbos_competence_type` (
  `idCompetence_Type` tinyint(3) unsigned NOT NULL auto_increment,
  `Name` text,
  PRIMARY KEY  (`idCompetence_Type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `bbos_competence_type`
--

INSERT INTO `bbos_competence_type` (`idCompetence_Type`, `Name`) VALUES
(1, 'Agility'),
(2, 'Strength'),
(3, 'General'),
(4, 'Pass'),
(5, 'Mutation'),
(6, 'Extraordinary'),
(7, 'Attributes');

-- --------------------------------------------------------

--
-- Structure de la table `bbos_inducement_type`
--

DROP TABLE IF EXISTS `bbos_inducement_type`;
CREATE TABLE IF NOT EXISTS `bbos_inducement_type` (
  `idInducement_type` int(10) unsigned NOT NULL auto_increment,
  `Name` text character set latin1 NOT NULL,
  `Limit` tinyint(4) NOT NULL,
  PRIMARY KEY  (`idInducement_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=11 ;

--
-- Contenu de la table `bbos_inducement_type`
--

INSERT INTO `bbos_inducement_type` (`idInducement_type`, `Name`, `Limit`) VALUES
(1, 'Bloodweiser babes', 2),
(2, 'Bribe the ref', 3),
(3, 'Extra team training', 4),
(4, 'Halfling Master Chef', 1),
(5, 'Igor', 1),
(6, 'Mercenaries', -1),
(7, 'Star player', 2),
(8, 'Wandering apothecary', 2),
(9, 'Wizard', 1),
(10, 'Card', -1);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_injuries`
--

DROP TABLE IF EXISTS `bbos_injuries`;
CREATE TABLE IF NOT EXISTS `bbos_injuries` (
  `idInjuries` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(255) default NULL,
  `Code` tinyint(3) unsigned default NULL,
  PRIMARY KEY  (`idInjuries`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `bbos_injuries`
--

INSERT INTO `bbos_injuries` (`idInjuries`, `Name`, `Code`) VALUES
(3, '-1 Movement', 3),
(4, '-1 Strength', 4),
(5, '-1 Agility', 5),
(6, '-1 Armor', 6);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_league`
--

DROP TABLE IF EXISTS `bbos_league`;
CREATE TABLE IF NOT EXISTS `bbos_league` (
  `idLeague` int(10) unsigned NOT NULL auto_increment,
  `idCoach` int(10) unsigned NOT NULL,
  `idLeague_type` int(10) unsigned NOT NULL,
  `Name` varchar(45) character set latin1 default NULL,
  `Season` int(10) unsigned default NULL,
  `TeamNumber` int(10) unsigned default NULL,
  `StartingPrince` int(10) default NULL,
  `Opened` tinyint(1) default NULL,
  PRIMARY KEY  (`idLeague`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=4 ;

--
-- Contenu de la table `bbos_league`
--

INSERT INTO `bbos_league` (`idLeague`, `idCoach`, `idLeague_type`, `Name`, `Season`, `TeamNumber`, `StartingPrince`, `Opened`) VALUES
(1, 1, 1, 'Test Cup', 1, 4, 1100000, NULL),
(2, 1, 2, 'Test League', 1, 4, 1100000, NULL),
(3, 1, 4, 'Eternal', 1, 0, 1000000, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_league_type`
--

DROP TABLE IF EXISTS `bbos_league_type`;
CREATE TABLE IF NOT EXISTS `bbos_league_type` (
  `idLeague_type` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(20) default NULL,
  PRIMARY KEY  (`idLeague_type`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `bbos_league_type`
--

INSERT INTO `bbos_league_type` (`idLeague_type`, `Name`) VALUES
(1, 'Cup'),
(2, 'Pool'),
(3, 'Mixed'),
(4, 'Eternal');

-- --------------------------------------------------------

--
-- Structure de la table `bbos_match`
--

DROP TABLE IF EXISTS `bbos_match`;
CREATE TABLE IF NOT EXISTS `bbos_match` (
  `idMatch` int(10) unsigned NOT NULL auto_increment,
  `date` datetime NOT NULL,
  `League_idLeague` int(10) unsigned NOT NULL,
  `Team_idTeam_1` int(10) unsigned NOT NULL,
  `Team_idTeam_2` int(10) unsigned NOT NULL,
  `State` tinyint(3) unsigned default NULL,
  `Score_1` tinyint(3) unsigned default NULL,
  `Score_2` tinyint(3) unsigned default NULL,
  `Winner_idTeam` mediumint(8) default NULL,
  `Round` tinyint(3) unsigned default NULL,
  `ExtraTime` tinyint(1) default NULL,
  `Public` int(10) unsigned default NULL,
  `FAME_1` tinyint(3) unsigned default NULL,
  `FAME_2` tinyint(3) unsigned default NULL,
  `Winnings_1` int(10) unsigned default NULL,
  `Winnings_2` int(10) unsigned default NULL,
  `challengerId` int(10) NOT NULL,
  `Data` blob,
  PRIMARY KEY  (`idMatch`),
  KEY `Team_FKIndex1` (`League_idLeague`),
  KEY `Team_FKIndex2` (`Team_idTeam_1`),
  KEY `Team_FKIndex3` (`Team_idTeam_2`),
  KEY `challengerId` (`challengerId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=52 ;

--
-- Contenu de la table `bbos_match`
--

INSERT INTO `bbos_match` (`idMatch`, `date`, `League_idLeague`, `Team_idTeam_1`, `Team_idTeam_2`, `State`, `Score_1`, `Score_2`, `Winner_idTeam`, `Round`, `ExtraTime`, `Public`, `FAME_1`, `FAME_2`, `Winnings_1`, `Winnings_2`, `challengerId`, `Data`) VALUES
(2, '2008-08-04 10:28:16', 3, 1, 25, 3, 1, 0, 1, 1, 0, 12000, 2, 0, 50000, 20000, 25, NULL),
(3, '2008-07-08 10:30:43', 3, 1, 26, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
(4, '2008-08-10 10:31:52', 3, 1, 19, 3, 0, 1, 19, 1, 1, 25000, 1, 0, 50000, 60000, 1, NULL),
(5, '2008-07-22 10:32:46', 3, 1, 25, 3, 2, 2, 0, 1, 0, 10000, 0, 1, 20000, 30000, 5, NULL),
(49, '2008-08-20 15:39:46', 3, 20, 23, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20, NULL),
(50, '2008-08-20 16:06:41', 3, 19, 23, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 19, NULL),
(51, '2008-08-28 17:13:28', 3, 22, 20, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_match_has_inducement`
--

DROP TABLE IF EXISTS `bbos_match_has_inducement`;
CREATE TABLE IF NOT EXISTS `bbos_match_has_inducement` (
  `idMatch_has_inducement` int(10) unsigned NOT NULL auto_increment,
  `Match_idMatch` int(10) unsigned NOT NULL,
  `Team_idTeam` int(10) unsigned NOT NULL,
  `Inducement_type_idInducementType` int(10) unsigned NOT NULL,
  `Cost` int(10) NOT NULL,
  `Card_idCard` int(10) NOT NULL,
  `Player_type_idPlayer_type` int(10) NOT NULL,
  `Data` text collate latin1_bin NOT NULL,
  PRIMARY KEY  (`idMatch_has_inducement`),
  KEY `match_has_inducement_FKIndex1` (`Match_idMatch`),
  KEY `match_has_inducement_FKIndex2` (`Team_idTeam`),
  KEY `match_has_inducement_FKIndex3` (`Inducement_type_idInducementType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=1 ;

--
-- Contenu de la table `bbos_match_has_inducement`
--


-- --------------------------------------------------------

--
-- Structure de la table `bbos_player`
--

DROP TABLE IF EXISTS `bbos_player`;
CREATE TABLE IF NOT EXISTS `bbos_player` (
  `idPlayer` int(10) unsigned NOT NULL auto_increment,
  `Player_Type_idPlayer_Type` mediumint(8) unsigned NOT NULL,
  `Team_idTeam` int(10) unsigned NOT NULL,
  `Name` text,
  `Ranking` text,
  `MissNextGame` tinyint(1) default NULL,
  `Completion` int(10) unsigned default NULL,
  `Touchdowns` int(10) unsigned default NULL,
  `Casualties` int(10) unsigned default NULL,
  `Interceptions` int(10) unsigned default NULL,
  `MVP` int(10) unsigned default NULL,
  `Persistant` int(11) NOT NULL,
  `Number` tinyint(4) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `Retired` tinyint(1) NOT NULL default '0',
  `Dead` tinyint(1) NOT NULL default '0',
  `icon_address` text,
  PRIMARY KEY  (`idPlayer`),
  KEY `Player_FKIndex1` (`Team_idTeam`),
  KEY `Player_FKIndex2` (`Player_Type_idPlayer_Type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=350 ;

--
-- Contenu de la table `bbos_player`
--

INSERT INTO `bbos_player` (`idPlayer`, `Player_Type_idPlayer_Type`, `Team_idTeam`, `Name`, `Ranking`, `MissNextGame`, `Completion`, `Touchdowns`, `Casualties`, `Interceptions`, `MVP`, `Persistant`, `Number`, `Status`, `Retired`, `Dead`, `icon_address`) VALUES
(1, 52, 1, 'Teigneux', 'Teigneux', 0, 1, 2, 0, 0, 0, 1, 1, 'Active', 0, 0, ''),
(2, 52, 1, 'Morveux', 'Morveux', 0, 0, 0, 2, 0, 0, 0, 2, 'Active', 0, 0, ''),
(3, 51, 1, 'Cogneur', 'Cogneur', 1, 1, 1, 3, 4, 2, 1, 3, 'Active', 0, 0, ''),
(4, 51, 1, 'Têtu', 'Têtu', 0, 0, 0, 0, 2, 0, 0, 16, 'Active', 0, 0, ''),
(5, 50, 1, 'Hurleur', 'Hurleur', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(6, 50, 1, 'Râleur', 'Râleur', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(7, 49, 1, 'A', 'A', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(8, 49, 1, 'B', 'B', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(9, 49, 1, 'C', 'C', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(10, 49, 1, 'D', 'D', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(11, 49, 1, 'E', 'E', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(12, 49, 1, 'F', 'F', 0, 0, 0, 0, 0, 0, 0, 12, 'Active', 0, 0, ''),
(13, 52, 1, 'Morveux Sr', 'Morveux', 0, 2, 1, 2, 0, 0, 0, 4, 'Retired', 1, 0, ''),
(14, 51, 1, 'H', 'H', 1, 1, 0, 0, 0, 0, 0, 7, 'Dead', 0, 1, ''),
(184, 16, 19, 'A', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, 'http://bbos.ainpacte.org/images/players/Dark_elves/left/active/witch.gif;http://bbos.ainpacte.org/images/players/Dark_elves/right/active/witch.gif;http://bbos.ainpacte.org/images/players/Dark_elves/left/passive/witch.gif;http://bbos.ainpacte.org/images/players/Dark_elves/right/passive/witch.gif'),
(185, 15, 19, 'B', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(186, 15, 19, 'C', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(187, 15, 19, 'D', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, 'http://bbos.ainpacte.org/images/players/Dark_elves/left/active/blitzer.gif;http://bbos.ainpacte.org/images/players/Dark_elves/right/active/blitzer.gif'),
(188, 15, 19, 'E', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(189, 13, 19, 'F', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(190, 13, 19, 'G', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(191, 12, 19, 'H', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(192, 12, 19, 'I', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(193, 12, 19, 'J', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(194, 12, 19, 'K', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 12, 'Active', 0, 0, ''),
(195, 20, 20, 'A', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(196, 20, 20, 'B', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(197, 18, 20, 'C', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(198, 18, 20, 'D', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(199, 18, 20, 'E', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(200, 18, 20, 'F', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(201, 17, 20, 'G', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(202, 17, 20, 'H', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(203, 17, 20, 'I', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(204, 17, 20, 'J', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 12, 'Active', 0, 0, ''),
(205, 21, 20, 'K', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 16, 'Active', 0, 0, ''),
(206, 27, 21, 'Al', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(207, 27, 21, 'Bob', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(208, 23, 21, 'Dick', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(209, 24, 21, 'Rick', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(210, 25, 21, 'Bill', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(211, 26, 21, 'Bo', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(212, 22, 21, 'Pat', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 1, 0, ''),
(213, 22, 21, 'Fred', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(214, 22, 21, 'Ed', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(215, 22, 21, 'Ben', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(216, 22, 21, 'Sam', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 1, 0, ''),
(217, 22, 21, 'Co', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 12, 'Active', 0, 0, ''),
(218, 22, 21, 'Ted', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 13, 'Active', 1, 0, ''),
(219, 22, 21, 'Mac', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 14, 'Active', 0, 0, ''),
(220, 22, 21, 'Max', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 15, 'Active', 0, 0, ''),
(221, 22, 21, 'Rob', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 16, 'Active', 0, 0, ''),
(222, 7, 22, 'Groucho', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(223, 6, 22, 'Chico', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(224, 6, 22, 'Harpo', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(225, 6, 22, 'Zappo', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(226, 6, 22, 'Groucho', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(227, 5, 22, 'B', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(228, 5, 22, 'C', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(229, 5, 22, 'D', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(230, 5, 22, 'E', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(231, 5, 22, 'F', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(232, 5, 22, 'G', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(233, 34, 23, 'Courgette', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(234, 34, 23, 'Oignon', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(235, 34, 23, 'Ail', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(236, 34, 23, 'Echalotte', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(237, 34, 23, 'Tomate', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(238, 34, 23, 'Aubergine', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(239, 33, 23, 'Haricot', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(240, 33, 23, 'Pois', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(241, 33, 23, 'Betterave', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(242, 33, 23, 'Celeri', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(243, 33, 23, 'Carotte', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(244, 35, 23, 'Topinambour', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 16, 'Active', 0, 0, ''),
(245, 17, 24, 'toto', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(246, 17, 24, 'titi', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(247, 17, 24, 'tata', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(248, 17, 24, 'zozo', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(249, 20, 24, 'zeze', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(250, 20, 24, 'aezrouze', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(251, 18, 24, 'lamzeur', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(252, 18, 24, 'amlzrjap', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(253, 18, 24, 'arezp*^ùioa', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(254, 18, 24, 'aporiu', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(255, 19, 24, 'azpio$', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(256, 19, 24, 'aepopo', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 12, 'Active', 0, 0, ''),
(257, 17, 24, 'azr', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 13, 'Active', 0, 0, ''),
(258, 17, 24, 'a', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 14, 'Active', 0, 0, ''),
(259, 17, 24, 'g', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 15, 'Active', 0, 0, ''),
(260, 17, 25, '1', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(261, 17, 25, '2', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(262, 17, 25, '3', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(263, 17, 25, '4', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(264, 17, 25, '5', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(265, 21, 25, '6', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(266, 17, 25, '7', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(267, 20, 25, '8', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(268, 18, 25, '9', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(269, 18, 25, '10', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(270, 18, 25, '11', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(271, 1, 26, 'a', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(272, 1, 26, 'b', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(273, 1, 26, 'z', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(274, 1, 26, 'z', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(275, 2, 26, 'e', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(276, 2, 26, 'r', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(277, 3, 26, 't', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(278, 3, 26, 'y', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(279, 4, 26, 'u', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(280, 4, 26, 'i', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(281, 4, 26, 'p', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(282, 4, 26, 'l', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 12, 'Active', 0, 0, ''),
(283, 11, 27, 'Abc', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(284, 11, 27, 'Bcd', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 1, 0, ''),
(285, 10, 27, 'Cde', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 1, 0, ''),
(286, 10, 27, 'Def', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(287, 10, 27, 'Efg', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(288, 10, 27, 'Fgh', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(289, 9, 27, 'Ghi', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(290, 9, 27, 'Hij', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(291, 8, 27, 'Ijk', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 1, 0, ''),
(292, 8, 27, 'Jkl', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(293, 8, 27, 'Klm', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(294, 21, 28, 'lmiu', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, 'Active', 0, 0, ''),
(295, 21, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(296, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, 'Active', 0, 0, ''),
(297, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(298, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, 'Active', 0, 0, ''),
(299, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, 'Active', 0, 0, ''),
(300, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, 'Active', 0, 0, ''),
(301, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, 'Active', 0, 0, ''),
(302, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, 'Active', 0, 0, ''),
(303, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, 'Active', 0, 0, ''),
(304, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, 'Active', 0, 0, ''),
(305, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 12, 'Active', 0, 0, ''),
(306, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 13, 'Active', 0, 0, ''),
(307, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 14, 'Active', 0, 0, ''),
(308, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 15, 'Active', 0, 0, ''),
(309, 28, 28, 'oipy', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 16, 'Active', 0, 0, ''),
(311, 53, 1, '53', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 13, 'Active', 1, 0, ''),
(312, 49, 1, '49', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 14, 'Active', 1, 0, ''),
(314, 53, 1, 'Atchoum', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 15, 'Active', 1, 0, ''),
(315, 93, 29, 'A', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, '', 0, 0, ''),
(316, 86, 29, 'B', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, '', 0, 0, ''),
(317, 86, 29, 'C', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, '', 0, 0, ''),
(318, 87, 29, 'D', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, '', 0, 0, ''),
(319, 87, 29, 'E', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, '', 0, 0, ''),
(320, 83, 29, 'F', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, '', 1, 0, ''),
(321, 83, 29, 'G', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, '', 0, 0, ''),
(322, 83, 29, 'H', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, '', 0, 0, ''),
(323, 83, 29, 'I', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, '', 0, 0, ''),
(324, 83, 29, 'J', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, '', 0, 0, ''),
(325, 83, 29, 'K', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, '', 0, 0, ''),
(326, 83, 29, 'L', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 12, '', 0, 0, ''),
(327, 83, 29, 'M', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 13, '', 0, 0, ''),
(328, 83, 29, 'N', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 14, '', 0, 0, ''),
(329, 83, 29, 'O', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 15, '', 0, 0, ''),
(330, 49, 1, 'TOTO', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 13, 'Active', 0, 0, ''),
(332, 92, 30, 'A', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 1, '', 0, 0, ''),
(333, 150, 30, 'B', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, '', 0, 0, ''),
(334, 91, 30, 'C', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 3, '', 1, 0, ''),
(335, 85, 30, 'D', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, '', 0, 0, ''),
(336, 83, 30, 'E', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 5, '', 0, 0, ''),
(337, 84, 30, 'F', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 6, '', 0, 0, ''),
(338, 82, 30, 'G', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 7, '', 0, 0, ''),
(339, 82, 30, 'H', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 8, '', 0, 0, ''),
(340, 82, 30, 'I', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 9, '', 0, 0, ''),
(341, 82, 30, 'J', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 10, '', 0, 0, ''),
(342, 82, 30, 'K', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 11, '', 0, 0, ''),
(343, 16, 19, 'Az', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 2, 'Active', 0, 0, ''),
(344, 14, 19, '8', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 13, 'Active', 0, 0, ''),
(345, 12, 19, 'R4', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 14, 'Active', 0, 0, ''),
(346, 49, 1, 'Alf', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 4, 'Active', 0, 0, ''),
(347, 49, 1, 'RT', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 14, 'Active', 0, 0, ''),
(348, 53, 1, 'Atchoum', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 15, 'Active', 0, 0, ''),
(349, 53, 1, 'Atchoum', 'Rookie', 0, 0, 0, 0, 0, 0, 0, 15, 'Active', 0, 0, '');

-- --------------------------------------------------------

--
-- Structure de la table `bbos_player_has_competence`
--

DROP TABLE IF EXISTS `bbos_player_has_competence`;
CREATE TABLE IF NOT EXISTS `bbos_player_has_competence` (
  `Player_idPlayer` int(10) unsigned NOT NULL,
  `Competence_idCompetence` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Player_idPlayer`,`Competence_idCompetence`),
  KEY `Player_has_Competence_FKIndex1` (`Player_idPlayer`),
  KEY `Player_has_Competence_FKIndex2` (`Competence_idCompetence`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bbos_player_has_competence`
--

INSERT INTO `bbos_player_has_competence` (`Player_idPlayer`, `Competence_idCompetence`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 16),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(4, 23),
(4, 24),
(4, 25),
(4, 26),
(4, 27),
(4, 28),
(4, 29),
(4, 30),
(5, 31),
(5, 32),
(5, 33),
(5, 34),
(5, 35),
(5, 36),
(5, 37),
(5, 38),
(5, 39),
(5, 40),
(6, 41),
(6, 42),
(6, 43),
(6, 44),
(6, 45),
(6, 47),
(6, 48),
(6, 49),
(6, 50),
(7, 15),
(7, 51),
(7, 52),
(7, 53),
(7, 54),
(7, 55),
(7, 56),
(7, 57),
(7, 58),
(7, 59),
(7, 60),
(8, 61),
(8, 62),
(8, 63),
(8, 64),
(8, 65),
(8, 67),
(8, 68),
(8, 69),
(8, 70),
(8, 76),
(9, 71),
(9, 77),
(10, 76),
(10, 77),
(10, 78),
(10, 79),
(11, 66),
(11, 72),
(11, 73),
(11, 74),
(11, 75),
(11, 79),
(12, 30);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_player_has_injuries`
--

DROP TABLE IF EXISTS `bbos_player_has_injuries`;
CREATE TABLE IF NOT EXISTS `bbos_player_has_injuries` (
  `Injuries_idInjuries` int(10) unsigned NOT NULL,
  `Player_idPlayer` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Injuries_idInjuries`,`Player_idPlayer`),
  KEY `Injuries_has_Player_FKIndex1` (`Injuries_idInjuries`),
  KEY `Injuries_has_Player_FKIndex2` (`Player_idPlayer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bbos_player_has_injuries`
--

INSERT INTO `bbos_player_has_injuries` (`Injuries_idInjuries`, `Player_idPlayer`) VALUES
(3, 3),
(4, 4),
(5, 5),
(6, 6);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_player_type`
--

DROP TABLE IF EXISTS `bbos_player_type`;
CREATE TABLE IF NOT EXISTS `bbos_player_type` (
  `idPlayer_Type` mediumint(8) unsigned NOT NULL auto_increment,
  `Name` text,
  `Ma` tinyint(4) default NULL,
  `St` tinyint(4) default NULL,
  `Ag` tinyint(4) default NULL,
  `Ar` tinyint(4) default NULL,
  `Cost` int(11) default NULL,
  `Position` text,
  `isStar` tinyint(1) default '0',
  PRIMARY KEY  (`idPlayer_Type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=151 ;

--
-- Contenu de la table `bbos_player_type`
--

INSERT INTO `bbos_player_type` (`idPlayer_Type`, `Name`, `Ma`, `St`, `Ag`, `Ar`, `Cost`, `Position`, `isStar`) VALUES
(1, 'Amazon Linewoman', 6, 3, 3, 7, 50000, 'Linewoman', 0),
(2, 'Amazon Thrower', 6, 3, 3, 7, 70000, 'Thrower', 0),
(3, 'Amazon Catcher', 6, 3, 3, 7, 70000, 'Catcher', 0),
(4, 'Amazon Blitzer', 6, 3, 3, 7, 90000, 'Blitzer', 0),
(5, 'Beastman', 6, 3, 3, 8, 60000, 'Lineman', 0),
(6, 'Chaos Warrior', 5, 4, 3, 9, 100000, 'Chaos Warrior', 0),
(7, 'Mutable Minotaur', 5, 5, 2, 8, 150000, 'Minotaur', 0),
(8, 'Elf Lineman', 6, 3, 4, 7, 60000, 'Lineman', 0),
(9, 'Elf Thrower', 6, 3, 4, 7, 70000, 'Thrower', 0),
(10, 'Elf Catcher', 8, 3, 4, 7, 100000, 'Catcher', 0),
(11, 'Elf Blitzer', 7, 3, 4, 8, 110000, 'Blitzer', 0),
(12, 'Dark Elf Lineman', 6, 3, 4, 8, 70000, 'Lineman', 0),
(13, 'Dark Elf Runner', 7, 3, 4, 7, 80000, 'Runner', 0),
(14, 'Dark Elf Assassin', 6, 3, 4, 7, 90000, 'Assassin', 0),
(15, 'Dark Elf Blitzer', 7, 3, 4, 8, 100000, 'Blitzer', 0),
(16, 'Dark Elf Witch', 7, 3, 4, 7, 110000, 'Witch', 0),
(17, 'Wood Elf Lineman', 7, 3, 4, 7, 70000, 'Lineman', 0),
(18, 'Wood Elf Catcher', 9, 2, 4, 7, 90000, 'Catcher', 0),
(19, 'Wood Elf Thrower', 7, 3, 4, 7, 90000, 'Thrower', 0),
(20, 'Wardancer', 8, 3, 4, 7, 120000, 'Wardancer', 0),
(21, 'Treeman', 2, 6, 1, 10, 120000, 'Treeman', 0),
(22, 'Goblin', 6, 2, 3, 7, 40000, 'Goblin', 0),
(23, 'Goblin Bomber', 6, 2, 3, 7, 40000, 'Bomber', 0),
(24, 'Goblin Pogo Stick', 7, 2, 3, 7, 40000, 'Pogo stick', 0),
(25, 'Goblin Looney', 6, 2, 3, 7, 40000, 'Chainsaw Looney', 0),
(26, 'Goblin Fanatic', 3, 7, 3, 7, 70000, 'Fanatic', 0),
(27, 'Troll', 4, 5, 1, 9, 110000, 'Troll', 0),
(28, 'Halfling', 5, 2, 3, 6, 30000, 'Halfling', 0),
(29, 'High Elf Lineman', 6, 3, 4, 8, 70000, 'Lineman', 0),
(30, 'High Elf Thrower', 6, 3, 4, 8, 90000, 'Thrower', 0),
(31, 'High Elf Catcher', 8, 3, 4, 7, 90000, 'Catcher', 0),
(32, 'High Elf Blitzer', 7, 3, 4, 8, 100000, 'Blitzer', 0),
(33, 'Skink', 8, 2, 3, 7, 60000, 'Skink', 0),
(34, 'Saurus', 6, 4, 1, 9, 80000, 'Saurus', 0),
(35, 'Kroxigor', 6, 5, 1, 9, 140000, 'Kroxigor', 0),
(36, 'Human Lineman', 6, 3, 3, 8, 50000, 'Lineman', 0),
(37, 'Human Catcher', 8, 2, 3, 7, 70000, 'Catcher', 0),
(38, 'Human Thrower', 6, 3, 3, 8, 70000, 'Thrower', 0),
(39, 'Human Blitzer', 7, 3, 3, 8, 90000, 'Blitzer', 0),
(40, 'Loner Ogre', 5, 5, 2, 9, 140000, 'Ogre', 0),
(41, 'Skeleton', 5, 3, 2, 7, 40000, 'Skeleton', 0),
(42, 'Thro-Ra', 6, 3, 2, 7, 70000, 'Thro-Ra', 0),
(43, 'Blitz-Ra', 6, 3, 2, 8, 90000, 'Blitz-Ra', 0),
(44, 'Mummy', 3, 5, 1, 9, 110000, 'Mummy', 0),
(45, 'Tomb Guardian', 3, 5, 1, 8, 100000, 'Tomb Guardian', 0),
(46, 'Zombie', 4, 3, 2, 8, 40000, 'Zombie', 0),
(47, 'Ghoul', 7, 3, 3, 7, 70000, 'Ghoul', 0),
(48, 'Wight', 6, 3, 3, 8, 90000, 'Wight', 0),
(49, 'Dwarf Blocker', 4, 3, 2, 9, 70000, 'Blocker', 0),
(50, 'Dwarf Runner', 6, 3, 3, 8, 80000, 'Runner', 0),
(51, 'Dwarf Blitzer', 5, 3, 3, 9, 80000, 'Blitzer', 0),
(52, 'Troll Slayer', 5, 3, 2, 8, 90000, 'Troll Slayer', 0),
(53, 'Deathroller', 4, 7, 1, 10, 160000, 'Deathroller', 0),
(54, 'Hobgoblin', 6, 3, 3, 7, 40000, 'Hobgoblin', 0),
(55, 'Chaos Dwarf', 4, 3, 2, 8, 70000, 'Chaos Dwarf', 0),
(56, 'Bullcentaur', 6, 4, 2, 9, 130000, 'Bullcentaur', 0),
(57, 'Flesh Golem', 4, 4, 2, 9, 100000, 'Flesh Golem', 0),
(58, 'Werewolf', 8, 3, 3, 8, 120000, 'Werewolf', 0),
(59, 'Norse Lineman', 6, 3, 3, 7, 50000, 'Lineman', 0),
(60, 'Norse Thrower', 6, 3, 3, 7, 70000, 'Thrower', 0),
(61, 'Norse Runner', 8, 3, 3, 7, 90000, 'Runner', 0),
(62, 'Berserker', 6, 3, 3, 7, 90000, 'Berserker', 0),
(63, 'Ulfwerner', 6, 4, 2, 8, 110000, 'Ulfwerner', 0),
(64, 'Snowtroll', 5, 5, 1, 8, 140000, 'Snowtroll', 0),
(65, 'Rotter', 5, 3, 3, 8, 40000, 'Rotter', 0),
(66, 'Pestigor', 6, 3, 3, 8, 80000, 'Pestigor', 0),
(67, 'Nurgle''s Warrior', 4, 4, 2, 9, 110000, 'Nurgle''s Warrior', 0),
(68, 'Nurgle''s Beast', 4, 5, 1, 9, 140000, 'Nurgle''s Beast', 0),
(69, 'Snotling', 5, 1, 3, 5, 20000, 'Snotling', 0),
(70, 'Ogre', 5, 5, 2, 9, 140000, 'Ogre', 0),
(71, 'Orc Lineman', 5, 3, 3, 9, 50000, 'Lineman', 0),
(72, 'Orc Thrower', 5, 3, 3, 8, 70000, 'Thrower', 0),
(73, 'Black Orc Blocker', 4, 4, 2, 9, 80000, 'Black Orc Blocker', 0),
(74, 'Orc Blitzer', 6, 3, 3, 9, 80000, 'Blitzer', 0),
(75, 'Skaven Lineman', 7, 3, 3, 7, 50000, 'Lineman', 0),
(76, 'Skaven Thrower', 7, 3, 3, 7, 70000, 'Thrower', 0),
(77, 'Skaven Blitzer', 7, 3, 3, 8, 90000, 'Blitzer', 0),
(78, 'Gutter Runer', 9, 2, 4, 7, 80000, 'Gutter Runner', 0),
(79, 'Rat Ogre', 6, 5, 2, 8, 160000, 'Rat Ogre', 0),
(80, 'Thrall', 6, 3, 3, 7, 40000, 'Thrall', 0),
(81, 'Vampire', 6, 4, 4, 8, 110000, 'Vampire', 0),
(82, 'Marauder', 6, 3, 3, 8, 50000, 'Marauder', 0),
(83, 'Anim Goblin', 6, 2, 3, 7, 40000, 'Goblin', 0),
(84, 'Anim Skaven', 7, 3, 3, 7, 50000, 'Skaven', 0),
(85, 'Anim Dark Elf', 6, 3, 4, 8, 70000, 'Dark Elf', 0),
(86, 'Anim Skaven Thrower', 7, 3, 3, 7, 70000, 'Skaven Thrower', 0),
(87, 'Anim Skaven Blitzer', 7, 3, 3, 8, 90000, 'Skaven Blitzer', 0),
(88, 'Slann Lineman', 6, 3, 3, 8, 80000, 'Lineman', 0),
(89, 'Slann Catcher', 7, 2, 4, 7, 80000, 'Catcher', 0),
(90, 'Slann Blitzer', 7, 3, 3, 8, 110000, 'Blitzer', 0),
(91, 'Chaos Ogre', 5, 5, 2, 9, 140000, 'Ogre', 0),
(92, 'Chaos Troll', 4, 5, 1, 9, 110000, 'Troll', 0),
(93, 'Warpstone Troll', 4, 5, 1, 9, 110000, 'Troll', 0),
(94, 'Barik Farblast', 6, 3, 3, 8, 60000, 'Star Bazooka', 1),
(95, 'Boomer Eziasson', 4, 3, 2, 9, 60000, 'Star Bomber', 1),
(96, 'Brick Far''th', 5, 5, 2, 9, 290000, 'Star Ogre', 1),
(97, 'Grotty', 6, 2, 4, 7, 0, 'Star Goblin', 1),
(98, 'Bomber Dribblesnot', 6, 2, 3, 7, 60000, 'Star Bomber', 1),
(99, 'Count Luthor Von Drakenborg', 6, 5, 4, 9, 390000, 'Star Vampire', 1),
(100, 'Deeproot Strongbranch', 2, 7, 1, 10, 250000, 'Star Treeman', 1),
(101, 'Eldril Sidewinder', 8, 3, 4, 7, 170000, 'Star Elf', 1),
(102, 'Flint Churnblade', 5, 3, 2, 8, 130000, 'Star Chainsaw', 1),
(103, 'Fungus the Loon', 4, 7, 3, 7, 80000, 'Star Fanatic', 1),
(104, 'Grashnak Blackhoof', 6, 6, 2, 8, 310000, 'Star Minotaur', 1),
(105, 'Griff Oberwald', 7, 4, 4, 8, 320000, 'Star Human', 1),
(106, 'Grim Ironjaw', 5, 4, 3, 8, 220000, 'Star Troll slayer', 1),
(107, 'Hack Enslash', 6, 3, 2, 7, 120000, 'Star Chainsaw', 1),
(108, 'Hakflem Skuttlespike', 9, 3, 4, 7, 200000, 'Star Gutter Runner', 1),
(109, 'Headsplitter', 6, 6, 3, 8, 340000, 'Star Rat Ogre', 1),
(110, 'Helmut Wulf', 6, 3, 3, 8, 110000, 'Star Chainsaw', 1),
(111, 'Hemlock', 8, 2, 3, 7, 170000, 'Star Assassin', 1),
(112, 'Horkon Heatripper', 7, 3, 4, 7, 210000, 'Star Assassin', 1),
(113, 'Htark the Unstoppable', 6, 5, 2, 9, 310000, 'Star Bullcentaur', 1),
(114, 'Hubris Rakarth', 7, 4, 4, 8, 260000, 'Star Elf', 1),
(115, 'Icepelt Hammerblow', 5, 6, 1, 8, 330000, 'Star Snowtroll', 1),
(116, 'Jordell Freshbreeze', 8, 3, 5, 7, 230000, 'Star Wardancer', 1),
(117, 'Lord Borak the Despoiler', 5, 5, 3, 9, 270000, 'Star Chaos Warrior', 1),
(118, 'Max Spleenripper', 5, 4, 3, 8, 130000, 'Star Chainsaw', 1),
(119, 'Mighty Zug', 4, 5, 2, 9, 230000, 'Star Human', 1),
(120, 'Morg''N''Thorg', 6, 6, 3, 10, 430000, 'Star Ogre', 1),
(121, 'Nobbla Blackwart', 6, 2, 3, 7, 100000, 'Star Chainsaw', 1),
(122, 'Prince Moranion', 7, 4, 4, 8, 230000, 'Star Dragon Prince', 1),
(123, 'Puggy Baconbreath', 5, 3, 3, 6, 140000, 'Star Halfling', 1),
(124, 'Ratmut III', 5, 6, 1, 9, 350000, 'Star Mummy', 1),
(125, 'Rashnak Backstabber', 7, 3, 3, 7, 200000, 'Star Assassin', 1),
(126, 'Ripper', 4, 6, 1, 9, 270000, 'Star Troll', 1),
(127, 'Scrappa Sorehead', 7, 2, 3, 7, 50000, 'Star Pogo', 1),
(128, 'Setekh', 6, 4, 2, 8, 220000, 'Star Wight', 1),
(129, 'Slibli', 7, 4, 1, 9, 250000, 'Star Saurus', 1),
(130, 'Skitter Stab-Stab', 9, 2, 4, 7, 160000, 'Star Assassin', 1),
(131, 'Ugroth Bolgrot', 5, 3, 3, 9, 100000, 'Star Chainsaw', 1),
(132, 'Varag Ghoul-Chewer', 6, 4, 3, 9, 260000, 'Star Orc', 1),
(133, 'Wilhelm Chaney', 8, 4, 3, 8, 240000, 'Star Werewolf', 1),
(134, 'Zara the Slayer', 6, 4, 3, 8, 270000, 'Star Amazon', 1),
(135, 'Zzargh Madeye', 4, 4, 3, 9, 60000, 'Star Blunderbuss', 1),
(136, 'Bertha Bigfist', 6, 5, 2, 9, 230000, 'Star Ogress', 1),
(137, 'Dolfar Longstrike', 7, 3, 4, 7, 170000, 'Star Elf', 1),
(138, 'Fezglitch', 4, 7, 3, 7, 80000, 'Star Fanatic', 1),
(139, 'Glart Smashrip Jr.', 7, 4, 3, 8, 200000, 'Star Stormvermin', 1),
(140, 'Humerus Carpal', 7, 2, 3, 7, 130000, 'Star Skeleton', 1),
(141, 'Ithaca Benoin', 7, 3, 3, 7, 220000, 'Star Thro-Ra', 1),
(142, 'J Earlice', 8, 3, 3, 7, 180000, 'Star Ghoul', 1),
(143, 'Lottabottol', 8, 3, 3, 8, 220000, 'Star Skink', 1),
(144, 'Mad Max', 6, 3, 3, 8, 130000, 'Star Thrall', 1),
(145, 'Quetzal Leap', 8, 2, 4, 7, 250000, 'Star Skink', 1),
(146, 'Roxanna Darknail', 8, 3, 5, 7, 250000, 'Star Witch', 1),
(147, 'Sinnedbad', 6, 3, 2, 7, 80000, 'Star Assassion', 1),
(148, 'Soaren Hightower', 6, 3, 4, 8, 180000, 'Star Phoenix Warrior', 1),
(149, 'Willow Rosebark', 5, 4, 3, 8, 150000, 'Star Halfling', 1),
(150, 'Minotaur', 5, 5, 2, 8, 150000, 'Minautor', 0);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_player_type_has_competence`
--

DROP TABLE IF EXISTS `bbos_player_type_has_competence`;
CREATE TABLE IF NOT EXISTS `bbos_player_type_has_competence` (
  `Player_Type_idPlayer_Type` mediumint(8) unsigned NOT NULL,
  `Competence_idCompetence` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Player_Type_idPlayer_Type`,`Competence_idCompetence`),
  KEY `Player_Type_has_Competence_FKIndex1` (`Player_Type_idPlayer_Type`),
  KEY `Player_Type_has_Competence_FKIndex2` (`Competence_idCompetence`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bbos_player_type_has_competence`
--

INSERT INTO `bbos_player_type_has_competence` (`Player_Type_idPlayer_Type`, `Competence_idCompetence`) VALUES
(1, 3),
(2, 3),
(2, 39),
(3, 3),
(3, 5),
(4, 3),
(4, 23),
(5, 43),
(7, 12),
(7, 14),
(7, 27),
(7, 43),
(7, 53),
(7, 64),
(9, 39),
(10, 5),
(10, 38),
(11, 4),
(11, 23),
(13, 40),
(14, 32),
(14, 66),
(15, 23),
(16, 1),
(16, 3),
(16, 27),
(18, 3),
(18, 5),
(19, 39),
(20, 3),
(20, 6),
(20, 23),
(21, 12),
(21, 13),
(21, 14),
(21, 20),
(21, 60),
(21, 64),
(21, 68),
(22, 3),
(22, 63),
(22, 65),
(23, 3),
(23, 52),
(23, 54),
(23, 61),
(23, 63),
(24, 3),
(24, 6),
(24, 29),
(24, 51),
(24, 52),
(24, 63),
(25, 52),
(25, 61),
(25, 63),
(25, 73),
(26, 52),
(26, 56),
(26, 61),
(26, 63),
(27, 12),
(27, 58),
(27, 60),
(27, 64),
(27, 70),
(27, 72),
(28, 3),
(28, 28),
(28, 65),
(30, 37),
(30, 39),
(31, 5),
(32, 23),
(33, 3),
(33, 63),
(35, 12),
(35, 14),
(35, 48),
(35, 55),
(35, 64),
(37, 3),
(37, 5),
(38, 25),
(38, 39),
(39, 23),
(40, 12),
(40, 14),
(40, 55),
(40, 60),
(40, 64),
(41, 70),
(42, 25),
(42, 39),
(42, 70),
(43, 23),
(43, 70),
(44, 12),
(44, 70),
(45, 16),
(45, 70),
(46, 70),
(47, 3),
(48, 23),
(48, 70),
(49, 14),
(49, 23),
(49, 34),
(50, 14),
(50, 25),
(51, 14),
(51, 23),
(52, 14),
(52, 23),
(52, 27),
(52, 28),
(53, 12),
(53, 16),
(53, 18),
(53, 20),
(53, 29),
(53, 52),
(53, 61),
(55, 14),
(55, 23),
(55, 34),
(56, 2),
(56, 8),
(56, 14),
(57, 14),
(57, 20),
(57, 70),
(58, 27),
(58, 46),
(58, 70),
(59, 23),
(60, 23),
(60, 39),
(61, 23),
(61, 28),
(62, 1),
(62, 23),
(62, 27),
(63, 27),
(64, 27),
(64, 46),
(64, 47),
(64, 53),
(64, 64),
(65, 57),
(65, 67),
(66, 43),
(66, 67),
(67, 47),
(67, 49),
(67, 67),
(67, 70),
(68, 12),
(68, 47),
(68, 49),
(68, 50),
(68, 58),
(68, 64),
(68, 67),
(68, 70),
(69, 3),
(69, 4),
(69, 62),
(69, 63),
(69, 65),
(70, 12),
(70, 14),
(70, 55),
(70, 60),
(72, 25),
(72, 39),
(74, 23),
(76, 25),
(76, 39),
(77, 23),
(78, 3),
(79, 12),
(79, 27),
(79, 48),
(79, 53),
(79, 64),
(81, 14),
(81, 69),
(81, 70),
(81, 71),
(83, 3),
(83, 63),
(83, 65),
(83, 75),
(84, 75),
(85, 75),
(86, 25),
(86, 39),
(86, 75),
(87, 23),
(87, 75),
(88, 6),
(88, 51),
(89, 6),
(89, 10),
(89, 51),
(90, 1),
(90, 6),
(90, 9),
(90, 51),
(91, 12),
(91, 14),
(91, 55),
(91, 60),
(91, 64),
(92, 12),
(92, 58),
(92, 60),
(92, 64),
(92, 70),
(92, 72),
(93, 12),
(93, 58),
(93, 60),
(93, 64),
(93, 70),
(93, 72),
(94, 14),
(94, 23),
(94, 25),
(94, 36),
(94, 52),
(94, 64),
(95, 14),
(95, 23),
(95, 41),
(95, 52),
(95, 54),
(95, 61),
(95, 64),
(96, 12),
(96, 13),
(96, 14),
(96, 38),
(96, 55),
(96, 60),
(96, 64),
(97, 3),
(97, 63),
(97, 64),
(97, 65),
(98, 3),
(98, 41),
(98, 52),
(98, 54),
(98, 61),
(98, 63),
(98, 64),
(98, 65),
(99, 3),
(99, 23),
(99, 64),
(99, 69),
(99, 70),
(100, 12),
(100, 13),
(100, 14),
(100, 20),
(100, 23),
(100, 60),
(100, 64),
(101, 3),
(101, 5),
(101, 24),
(101, 38),
(101, 64),
(101, 69),
(102, 14),
(102, 23),
(102, 52),
(102, 61),
(102, 64),
(102, 73),
(103, 12),
(103, 52),
(103, 56),
(103, 61),
(103, 63),
(103, 64),
(104, 12),
(104, 14),
(104, 27),
(104, 43),
(104, 64),
(105, 2),
(105, 3),
(105, 8),
(105, 23),
(105, 31),
(105, 64),
(106, 12),
(106, 14),
(106, 23),
(106, 27),
(106, 28),
(106, 64),
(107, 4),
(107, 52),
(107, 61),
(107, 64),
(107, 70),
(107, 73),
(108, 3),
(108, 42),
(108, 44),
(108, 48),
(108, 64),
(109, 12),
(109, 27),
(109, 48),
(109, 64),
(110, 20),
(110, 52),
(110, 61),
(110, 64),
(110, 73),
(111, 1),
(111, 3),
(111, 4),
(111, 23),
(111, 63),
(111, 64),
(111, 66),
(112, 3),
(112, 6),
(112, 11),
(112, 32),
(112, 64),
(112, 66),
(113, 2),
(113, 8),
(113, 14),
(113, 16),
(113, 18),
(113, 23),
(113, 64),
(114, 1),
(114, 12),
(114, 22),
(114, 23),
(114, 29),
(114, 64),
(115, 12),
(115, 27),
(115, 46),
(115, 47),
(115, 64),
(115, 70),
(116, 3),
(116, 4),
(116, 6),
(116, 10),
(116, 23),
(116, 64),
(117, 12),
(117, 23),
(117, 29),
(117, 64),
(118, 52),
(118, 61),
(118, 64),
(118, 73),
(119, 12),
(119, 23),
(119, 64),
(120, 12),
(120, 14),
(120, 23),
(120, 60),
(120, 64),
(121, 3),
(121, 23),
(121, 52),
(121, 61),
(121, 63),
(121, 64),
(121, 73),
(122, 23),
(122, 28),
(122, 30),
(122, 34),
(122, 64),
(123, 3),
(123, 23),
(123, 38),
(123, 63),
(123, 64),
(123, 65),
(124, 12),
(124, 16),
(124, 30),
(124, 64),
(124, 70),
(125, 3),
(125, 4),
(125, 7),
(125, 64),
(125, 66),
(126, 12),
(126, 19),
(126, 60),
(126, 64),
(126, 70),
(127, 2),
(127, 3),
(127, 6),
(127, 8),
(127, 29),
(127, 51),
(127, 52),
(127, 63),
(127, 64),
(127, 65),
(128, 16),
(128, 18),
(128, 22),
(128, 23),
(128, 64),
(128, 70),
(129, 17),
(129, 19),
(129, 20),
(129, 64),
(130, 3),
(130, 32),
(130, 48),
(130, 64),
(130, 66),
(131, 52),
(131, 61),
(131, 64),
(131, 73),
(132, 1),
(132, 12),
(132, 14),
(132, 23),
(132, 64),
(133, 5),
(133, 27),
(133, 30),
(133, 46),
(133, 64),
(133, 70),
(134, 1),
(134, 3),
(134, 23),
(134, 28),
(134, 64),
(134, 66),
(134, 74),
(135, 13),
(135, 14),
(135, 25),
(135, 34),
(135, 36),
(135, 39),
(135, 52),
(135, 64),
(136, 3),
(136, 12),
(136, 14),
(136, 16),
(136, 55),
(136, 60),
(136, 64),
(137, 10),
(137, 21),
(137, 24),
(137, 26),
(137, 36),
(137, 64),
(138, 47),
(138, 49),
(138, 52),
(138, 56),
(138, 61),
(138, 64),
(139, 18),
(139, 46),
(139, 64),
(140, 3),
(140, 5),
(140, 38),
(140, 64),
(140, 70),
(141, 25),
(141, 38),
(141, 39),
(141, 40),
(141, 41),
(141, 64),
(141, 70),
(142, 3),
(142, 5),
(142, 8),
(142, 10),
(142, 64),
(143, 1),
(143, 5),
(143, 6),
(143, 9),
(143, 24),
(143, 32),
(143, 51),
(143, 64),
(144, 14),
(144, 28),
(144, 64),
(144, 70),
(145, 5),
(145, 6),
(145, 10),
(145, 21),
(145, 31),
(145, 38),
(145, 51),
(145, 64),
(146, 1),
(146, 3),
(146, 6),
(146, 18),
(146, 27),
(146, 64),
(147, 1),
(147, 4),
(147, 7),
(147, 23),
(147, 24),
(147, 52),
(147, 64),
(147, 66),
(148, 13),
(148, 21),
(148, 25),
(148, 31),
(148, 37),
(148, 39),
(148, 64),
(149, 4),
(149, 14),
(149, 28),
(149, 64),
(150, 12),
(150, 14),
(150, 27),
(150, 43),
(150, 53),
(150, 64);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_player_type_has_doublecompetence_type`
--

DROP TABLE IF EXISTS `bbos_player_type_has_doublecompetence_type`;
CREATE TABLE IF NOT EXISTS `bbos_player_type_has_doublecompetence_type` (
  `Player_Type_idPlayer_Type` mediumint(8) unsigned NOT NULL,
  `Competence_Type_idCompetence_Type` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY  (`Player_Type_idPlayer_Type`,`Competence_Type_idCompetence_Type`),
  KEY `Player_Type_has_Competence_Type_FKIndex1` (`Player_Type_idPlayer_Type`),
  KEY `Player_Type_has_Competence_Type_FKIndex2` (`Competence_Type_idCompetence_Type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bbos_player_type_has_doublecompetence_type`
--

INSERT INTO `bbos_player_type_has_doublecompetence_type` (`Player_Type_idPlayer_Type`, `Competence_Type_idCompetence_Type`) VALUES
(1, 1),
(2, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(21, 1),
(26, 1),
(27, 1),
(34, 1),
(35, 1),
(36, 1),
(38, 1),
(39, 1),
(40, 1),
(41, 1),
(42, 1),
(43, 1),
(44, 1),
(45, 1),
(46, 1),
(48, 1),
(49, 1),
(50, 1),
(51, 1),
(52, 1),
(53, 1),
(54, 1),
(55, 1),
(56, 1),
(57, 1),
(59, 1),
(60, 1),
(62, 1),
(63, 1),
(64, 1),
(65, 1),
(66, 1),
(67, 1),
(68, 1),
(70, 1),
(71, 1),
(72, 1),
(73, 1),
(74, 1),
(75, 1),
(76, 1),
(77, 1),
(79, 1),
(80, 1),
(82, 1),
(84, 1),
(86, 1),
(87, 1),
(88, 1),
(91, 1),
(92, 1),
(93, 1),
(150, 1),
(1, 2),
(2, 2),
(3, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2),
(16, 2),
(17, 2),
(18, 2),
(19, 2),
(20, 2),
(22, 2),
(23, 2),
(24, 2),
(25, 2),
(28, 2),
(30, 2),
(31, 2),
(32, 2),
(33, 2),
(36, 2),
(37, 2),
(38, 2),
(41, 2),
(42, 2),
(46, 2),
(47, 2),
(50, 2),
(54, 2),
(58, 2),
(59, 2),
(60, 2),
(61, 2),
(65, 2),
(69, 2),
(71, 2),
(72, 2),
(75, 2),
(76, 2),
(78, 2),
(80, 2),
(83, 2),
(84, 2),
(85, 2),
(86, 2),
(88, 2),
(89, 2),
(7, 3),
(21, 3),
(22, 3),
(23, 3),
(24, 3),
(25, 3),
(26, 3),
(27, 3),
(28, 3),
(29, 3),
(33, 3),
(35, 3),
(40, 3),
(44, 3),
(45, 3),
(53, 3),
(64, 3),
(68, 3),
(69, 3),
(70, 3),
(79, 3),
(83, 3),
(91, 3),
(92, 3),
(93, 3),
(150, 3),
(1, 4),
(3, 4),
(4, 4),
(5, 4),
(6, 4),
(7, 4),
(8, 4),
(10, 4),
(11, 4),
(12, 4),
(14, 4),
(15, 4),
(16, 4),
(17, 4),
(18, 4),
(20, 4),
(21, 4),
(22, 4),
(23, 4),
(24, 4),
(25, 4),
(26, 4),
(27, 4),
(28, 4),
(29, 4),
(31, 4),
(32, 4),
(33, 4),
(34, 4),
(35, 4),
(36, 4),
(37, 4),
(39, 4),
(40, 4),
(41, 4),
(43, 4),
(44, 4),
(45, 4),
(46, 4),
(47, 4),
(48, 4),
(49, 4),
(51, 4),
(52, 4),
(53, 4),
(54, 4),
(55, 4),
(56, 4),
(57, 4),
(58, 4),
(59, 4),
(61, 4),
(62, 4),
(63, 4),
(64, 4),
(65, 4),
(66, 4),
(67, 4),
(68, 4),
(69, 4),
(70, 4),
(71, 4),
(73, 4),
(74, 4),
(75, 4),
(77, 4),
(78, 4),
(79, 4),
(80, 4),
(81, 4),
(83, 4),
(84, 4),
(85, 4),
(87, 4),
(88, 4),
(89, 4),
(90, 4),
(91, 4),
(92, 4),
(93, 4),
(150, 4),
(55, 5),
(68, 5),
(75, 5),
(76, 5),
(77, 5),
(78, 5),
(79, 5),
(91, 5),
(92, 5),
(150, 5);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_player_type_has_simplecompetence_type`
--

DROP TABLE IF EXISTS `bbos_player_type_has_simplecompetence_type`;
CREATE TABLE IF NOT EXISTS `bbos_player_type_has_simplecompetence_type` (
  `Player_Type_idPlayer_Type` mediumint(8) unsigned NOT NULL,
  `Competence_Type_idCompetence_Type` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY  (`Player_Type_idPlayer_Type`,`Competence_Type_idCompetence_Type`),
  KEY `Player_Type_has_Competence_Type_FKIndex1` (`Player_Type_idPlayer_Type`),
  KEY `Player_Type_has_Competence_Type_FKIndex2` (`Competence_Type_idCompetence_Type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bbos_player_type_has_simplecompetence_type`
--

INSERT INTO `bbos_player_type_has_simplecompetence_type` (`Player_Type_idPlayer_Type`, `Competence_Type_idCompetence_Type`) VALUES
(3, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1),
(20, 1),
(22, 1),
(23, 1),
(24, 1),
(25, 1),
(28, 1),
(29, 1),
(30, 1),
(31, 1),
(32, 1),
(33, 1),
(37, 1),
(38, 1),
(47, 1),
(58, 1),
(61, 1),
(69, 1),
(78, 1),
(81, 1),
(83, 1),
(85, 1),
(89, 1),
(90, 1),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(21, 2),
(26, 2),
(27, 2),
(34, 2),
(35, 2),
(39, 2),
(40, 2),
(43, 2),
(44, 2),
(45, 2),
(48, 2),
(49, 2),
(51, 2),
(52, 2),
(53, 2),
(55, 2),
(56, 2),
(57, 2),
(62, 2),
(63, 2),
(64, 2),
(66, 2),
(67, 2),
(68, 2),
(70, 2),
(73, 2),
(74, 2),
(77, 2),
(79, 2),
(81, 2),
(82, 2),
(87, 2),
(90, 2),
(91, 2),
(92, 2),
(93, 2),
(150, 2),
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 3),
(6, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 3),
(17, 3),
(18, 3),
(19, 3),
(20, 3),
(29, 3),
(30, 3),
(31, 3),
(32, 3),
(34, 3),
(36, 3),
(37, 3),
(39, 3),
(41, 3),
(42, 3),
(43, 3),
(46, 3),
(47, 3),
(48, 3),
(49, 3),
(50, 3),
(51, 3),
(52, 3),
(54, 3),
(55, 3),
(56, 3),
(57, 3),
(58, 3),
(59, 3),
(60, 3),
(61, 3),
(62, 3),
(63, 3),
(65, 3),
(66, 3),
(67, 3),
(71, 3),
(72, 3),
(73, 3),
(74, 3),
(75, 3),
(76, 3),
(77, 3),
(78, 3),
(80, 3),
(81, 3),
(82, 3),
(84, 3),
(85, 3),
(86, 3),
(87, 3),
(88, 3),
(89, 3),
(90, 3),
(2, 4),
(9, 4),
(13, 4),
(19, 4),
(30, 4),
(38, 4),
(42, 4),
(50, 4),
(60, 4),
(72, 4),
(76, 4),
(82, 4),
(86, 4),
(5, 5),
(6, 5),
(7, 5),
(65, 5),
(66, 5),
(67, 5),
(82, 5),
(83, 5),
(84, 5),
(85, 5),
(86, 5),
(87, 5),
(93, 5);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_team`
--

DROP TABLE IF EXISTS `bbos_team`;
CREATE TABLE IF NOT EXISTS `bbos_team` (
  `idTeam` int(10) unsigned NOT NULL auto_increment,
  `idLeague` int(10) unsigned NOT NULL,
  `Team_Type_idTeam_Type` mediumint(8) unsigned NOT NULL,
  `Coach_idCoach` int(10) unsigned NOT NULL,
  `Name` text,
  `Popularity` tinyint(3) unsigned default NULL,
  `Assists` tinyint(3) unsigned default NULL,
  `Cheerleaders` tinyint(3) unsigned default NULL,
  `Apothecary` tinyint(1) default NULL,
  `Treasury` int(10) unsigned default NULL,
  `Reroll` int(11) NOT NULL,
  `Active` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`idTeam`),
  KEY `Team_FKIndex1` (`Coach_idCoach`),
  KEY `Team_FKIndex2` (`Team_Type_idTeam_Type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Contenu de la table `bbos_team`
--

INSERT INTO `bbos_team` (`idTeam`, `idLeague`, `Team_Type_idTeam_Type`, `Coach_idCoach`, `Name`, `Popularity`, `Assists`, `Cheerleaders`, `Apothecary`, `Treasury`, `Reroll`, `Active`) VALUES
(1, 3, 13, 1, 'Dwarfs punks', 3, 10, 10, 1, 500000, 10, 1),
(19, 3, 4, 1, 'Ombres', 0, 2, 2, 1, 4330000, 5, 1),
(20, 3, 5, 1, 'A poil dans la forêt', 0, 0, 0, 0, 0, 2, 1),
(21, 3, 6, 1, 'Verts et méchants', 5, 0, 0, 0, 0, 4, 1),
(22, 3, 2, 1, 'Blood Brothers', 1, 0, 0, 0, 1000000, 3, 1),
(23, 3, 9, 1, 'Sacs à mains', 0, 0, 0, 0, 0, 3, 1),
(24, 3, 5, 2, 'sylvester stallions', 10, 2, 2, 1, 0, 4, 1),
(25, 3, 5, 2, 'elfes sylvains', 2, 0, 0, 0, 0, 3, 1),
(26, 3, 1, 2, 'les groupies de xena', 2, 2, 2, 0, 0, 4, 1),
(27, 3, 3, 4, 'Pro mais pas trop', 1, 0, 0, 0, 0, 3, 1),
(28, 3, 7, 2, 'les rase bitume', 5, 2, 2, 1, 0, 5, 1),
(29, 3, 24, 5, 'Les vermines du mitard', 1, 0, 0, 1, 0, 3, 1),
(30, 3, 22, 1, 'BAF (Bojo Air Force)', 1, 0, 0, 0, 0, 4, 0);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_team_type`
--

DROP TABLE IF EXISTS `bbos_team_type`;
CREATE TABLE IF NOT EXISTS `bbos_team_type` (
  `idTeam_Type` mediumint(8) unsigned NOT NULL auto_increment,
  `Name` text,
  `RerollCost` int(10) unsigned default NULL,
  `Apothecary` tinyint(1) default NULL,
  `ApothecaryCost` int(10) unsigned default NULL,
  `WizardCost` int(10) unsigned default NULL,
  `CanRaise` tinyint(1) default NULL,
  `BribeCost` int(10) unsigned default NULL,
  `ChefCost` int(10) unsigned default NULL,
  `LogoURL` text,
  `Description` varchar(100) default NULL,
  PRIMARY KEY  (`idTeam_Type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Contenu de la table `bbos_team_type`
--

INSERT INTO `bbos_team_type` (`idTeam_Type`, `Name`, `RerollCost`, `Apothecary`, `ApothecaryCost`, `WizardCost`, `CanRaise`, `BribeCost`, `ChefCost`, `LogoURL`, `Description`) VALUES
(1, 'Amazon', 50000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/amazon.png', NULL),
(2, 'Chaos', 60000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/chaos.png', NULL),
(3, 'Elf', 50000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/elf.png', NULL),
(4, 'Dark elf', 50000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/dark_elf.png', NULL),
(5, 'Wood elf', 50000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/wood_elf.png', NULL),
(6, 'Goblin', 60000, 1, 50000, 150000, 0, 50000, 300000, 'http://bbos.ainpacte.org/images/team_logos/goblin.png', NULL),
(7, 'Halfing', 60000, 1, 50000, 150000, 0, 100000, 50000, 'http://bbos.ainpacte.org/images/team_logos/halfling.png', NULL),
(8, 'High elf', 50000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/high_elf.png', NULL),
(9, 'Lizardman', 60000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/lizardman.png', NULL),
(10, 'Human', 50000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/human.png', NULL),
(11, 'Khemri', 70000, 0, 0, 150000, 1, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/khemri.png', NULL),
(12, 'Undead', 70000, 0, 0, 150000, 1, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/undead.png', NULL),
(13, 'Dwarf', 40000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/dwarf.png', NULL),
(14, 'Chaos dwarf', 70000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/chaos_dwarf.png', NULL),
(15, 'Necromantic', 70000, 0, 0, 150000, 1, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/necromantic.png', NULL),
(16, 'Norse', 60000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/norse.png', NULL),
(17, 'Nurgle', 70000, 0, 0, 150000, 1, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/nurgle.png', NULL),
(18, 'Ogre', 70000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/ogre.png', NULL),
(19, 'Orc', 60000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/orc.png', NULL),
(20, 'Skaven', 60000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/skaven.png', NULL),
(21, 'Vampire', 70000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/vampire.png', NULL),
(22, 'Chaos pact', 70000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/chaos_pact.png', NULL),
(23, 'Slann', 50000, 1, 50000, 150000, 0, 100000, 300000, 'http://bbos.ainpacte.org/images/team_logos/slann.png', NULL),
(24, 'Underworld', 70000, 1, 50000, 150000, 0, 50000, 300000, 'http://bbos.ainpacte.org/images/team_logos/underworld.png', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `bbos_team_type_has_player_type`
--

DROP TABLE IF EXISTS `bbos_team_type_has_player_type`;
CREATE TABLE IF NOT EXISTS `bbos_team_type_has_player_type` (
  `Team_Type_idTeam_Type` mediumint(8) unsigned NOT NULL,
  `Player_Type_idPlayer_Type` mediumint(8) unsigned NOT NULL,
  `MaxNumber` tinyint(3) unsigned default NULL,
  PRIMARY KEY  (`Team_Type_idTeam_Type`,`Player_Type_idPlayer_Type`),
  KEY `Team_Type_has_Player_Type_FKIndex1` (`Team_Type_idTeam_Type`),
  KEY `Team_Type_has_Player_Type_FKIndex2` (`Player_Type_idPlayer_Type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bbos_team_type_has_player_type`
--

INSERT INTO `bbos_team_type_has_player_type` (`Team_Type_idTeam_Type`, `Player_Type_idPlayer_Type`, `MaxNumber`) VALUES
(1, 1, 16),
(1, 2, 2),
(1, 3, 2),
(1, 4, 4),
(1, 110, 0),
(1, 120, 0),
(1, 134, 0),
(1, 136, 0),
(1, 146, 0),
(1, 149, 0),
(2, 5, 16),
(2, 6, 4),
(2, 7, 1),
(2, 96, 0),
(2, 97, 0),
(2, 104, 0),
(2, 117, 0),
(2, 118, 0),
(2, 120, 0),
(2, 126, 0),
(3, 8, 16),
(3, 9, 2),
(3, 10, 4),
(3, 11, 2),
(3, 101, 0),
(3, 114, 0),
(3, 116, 0),
(3, 120, 0),
(3, 122, 0),
(3, 137, 0),
(4, 12, 16),
(4, 13, 2),
(4, 14, 2),
(4, 15, 4),
(4, 16, 2),
(4, 101, 0),
(4, 112, 0),
(4, 114, 0),
(4, 120, 0),
(4, 141, 0),
(4, 146, 0),
(5, 17, 16),
(5, 18, 4),
(5, 19, 2),
(5, 20, 2),
(5, 21, 1),
(5, 101, 0),
(5, 116, 0),
(5, 120, 0),
(5, 134, 0),
(5, 137, 0),
(5, 149, 0),
(6, 22, 16),
(6, 23, 1),
(6, 24, 1),
(6, 25, 1),
(6, 26, 1),
(6, 27, 2),
(6, 98, 0),
(6, 103, 0),
(6, 120, 0),
(6, 121, 0),
(6, 126, 0),
(6, 127, 0),
(7, 21, 2),
(7, 28, 16),
(7, 100, 0),
(7, 120, 0),
(7, 123, 0),
(7, 134, 0),
(7, 136, 0),
(7, 149, 0),
(8, 29, 16),
(8, 30, 2),
(8, 31, 4),
(8, 32, 2),
(8, 101, 0),
(8, 120, 0),
(8, 122, 0),
(8, 134, 0),
(8, 137, 0),
(8, 148, 0),
(9, 33, 16),
(9, 34, 6),
(9, 35, 1),
(9, 110, 0),
(9, 111, 0),
(9, 120, 0),
(9, 129, 0),
(9, 143, 0),
(9, 145, 0),
(10, 36, 16),
(10, 37, 4),
(10, 38, 2),
(10, 39, 4),
(10, 40, 1),
(10, 105, 0),
(10, 110, 0),
(10, 119, 0),
(10, 120, 0),
(10, 123, 0),
(10, 134, 0),
(11, 41, 16),
(11, 42, 2),
(11, 43, 2),
(11, 45, 4),
(11, 107, 0),
(11, 124, 0),
(11, 128, 0),
(11, 140, 0),
(11, 141, 0),
(11, 147, 0),
(12, 41, 16),
(12, 44, 2),
(12, 46, 16),
(12, 47, 4),
(12, 48, 2),
(12, 99, 0),
(12, 107, 0),
(12, 124, 0),
(12, 128, 0),
(12, 142, 0),
(12, 147, 0),
(13, 49, 16),
(13, 50, 2),
(13, 51, 2),
(13, 52, 2),
(13, 53, 1),
(13, 94, 0),
(13, 95, 0),
(13, 102, 0),
(13, 106, 0),
(13, 120, 0),
(13, 134, 0),
(14, 54, 16),
(14, 55, 6),
(14, 56, 2),
(14, 104, 0),
(14, 113, 0),
(14, 120, 0),
(14, 121, 0),
(14, 125, 0),
(14, 135, 0),
(14, 150, 1),
(15, 46, 16),
(15, 47, 2),
(15, 57, 2),
(15, 58, 2),
(15, 99, 0),
(15, 107, 0),
(15, 124, 0),
(15, 128, 0),
(15, 133, 0),
(15, 142, 0),
(16, 59, 16),
(16, 60, 2),
(16, 61, 2),
(16, 62, 2),
(16, 63, 2),
(16, 64, 1),
(16, 95, 0),
(16, 110, 0),
(16, 115, 0),
(16, 120, 0),
(16, 133, 0),
(16, 134, 0),
(17, 65, 16),
(17, 66, 4),
(17, 67, 4),
(17, 68, 1),
(17, 96, 0),
(17, 97, 0),
(17, 104, 0),
(17, 117, 0),
(17, 118, 0),
(17, 120, 0),
(17, 126, 0),
(18, 69, 16),
(18, 70, 6),
(18, 96, 0),
(18, 97, 0),
(18, 98, 0),
(18, 120, 0),
(18, 121, 0),
(18, 127, 0),
(18, 136, 0),
(19, 22, 4),
(19, 27, 1),
(19, 71, 16),
(19, 72, 2),
(19, 73, 4),
(19, 74, 4),
(19, 98, 0),
(19, 120, 0),
(19, 126, 0),
(19, 127, 0),
(19, 131, 0),
(19, 132, 0),
(20, 75, 16),
(20, 76, 2),
(20, 77, 2),
(20, 78, 4),
(20, 79, 1),
(20, 108, 0),
(20, 109, 0),
(20, 120, 0),
(20, 130, 0),
(20, 138, 0),
(20, 139, 0),
(21, 80, 16),
(21, 81, 6),
(21, 99, 0),
(21, 110, 0),
(21, 120, 0),
(21, 133, 0),
(21, 142, 0),
(21, 144, 0),
(22, 82, 12),
(22, 83, 1),
(22, 84, 1),
(22, 85, 1),
(22, 91, 1),
(22, 92, 1),
(22, 98, 0),
(22, 120, 0),
(22, 131, 0),
(22, 135, 0),
(22, 138, 0),
(22, 144, 0),
(22, 150, 1),
(23, 35, 1),
(23, 88, 16),
(23, 89, 4),
(23, 90, 4),
(23, 110, 0),
(23, 111, 0),
(23, 120, 0),
(23, 129, 0),
(23, 143, 0),
(23, 145, 0),
(24, 83, 12),
(24, 84, 2),
(24, 86, 2),
(24, 87, 2),
(24, 93, 1),
(24, 98, 0),
(24, 120, 0),
(24, 121, 0),
(24, 130, 0),
(24, 138, 0),
(24, 139, 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `bbos_action`
--
ALTER TABLE `bbos_action`
  ADD CONSTRAINT `action_ibfk_1` FOREIGN KEY (`Match_idMatch`) REFERENCES `bbos_match` (`idMatch`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `action_ibfk_2` FOREIGN KEY (`Player_idPlayer`) REFERENCES `bbos_player` (`idPlayer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `action_ibfk_4` FOREIGN KEY (`Opponent_idPlayer`) REFERENCES `bbos_player` (`idPlayer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `action_ibfk_5` FOREIGN KEY (`Team_idTeam`) REFERENCES `bbos_team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `action_ibfk_6` FOREIGN KEY (`Action_type_idAction_type`) REFERENCES `bbos_action_type` (`idAction_type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_card`
--
ALTER TABLE `bbos_card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`Card_type_idCard_type`) REFERENCES `bbos_card_type` (`idCardType`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_competence`
--
ALTER TABLE `bbos_competence`
  ADD CONSTRAINT `competence_ibfk_1` FOREIGN KEY (`Competence_Type_idCompetence_Type`) REFERENCES `bbos_competence_type` (`idCompetence_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_match`
--
ALTER TABLE `bbos_match`
  ADD CONSTRAINT `match_ibfk_2` FOREIGN KEY (`Team_idTeam_1`) REFERENCES `bbos_team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `match_ibfk_3` FOREIGN KEY (`Team_idTeam_2`) REFERENCES `bbos_team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `match_ibfk_4` FOREIGN KEY (`League_idLeague`) REFERENCES `bbos_league` (`idLeague`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_match_has_inducement`
--
ALTER TABLE `bbos_match_has_inducement`
  ADD CONSTRAINT `match_has_inducement_ibfk_1` FOREIGN KEY (`Match_idMatch`) REFERENCES `bbos_match` (`idMatch`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `match_has_inducement_ibfk_2` FOREIGN KEY (`Team_idTeam`) REFERENCES `bbos_team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `match_has_inducement_ibfk_3` FOREIGN KEY (`Inducement_type_idInducementType`) REFERENCES `bbos_inducement_type` (`idInducement_type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_player`
--
ALTER TABLE `bbos_player`
  ADD CONSTRAINT `player_ibfk_1` FOREIGN KEY (`Team_idTeam`) REFERENCES `bbos_team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_ibfk_2` FOREIGN KEY (`Player_Type_idPlayer_Type`) REFERENCES `bbos_player_type` (`idPlayer_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_player_has_competence`
--
ALTER TABLE `bbos_player_has_competence`
  ADD CONSTRAINT `player_has_competence_ibfk_1` FOREIGN KEY (`Player_idPlayer`) REFERENCES `bbos_player` (`idPlayer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_has_competence_ibfk_2` FOREIGN KEY (`Competence_idCompetence`) REFERENCES `bbos_competence` (`idCompetence`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_player_has_injuries`
--
ALTER TABLE `bbos_player_has_injuries`
  ADD CONSTRAINT `player_has_injuries_ibfk_1` FOREIGN KEY (`Injuries_idInjuries`) REFERENCES `bbos_injuries` (`idInjuries`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_has_injuries_ibfk_2` FOREIGN KEY (`Player_idPlayer`) REFERENCES `bbos_player` (`idPlayer`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_player_type_has_competence`
--
ALTER TABLE `bbos_player_type_has_competence`
  ADD CONSTRAINT `player_type_has_competence_ibfk_1` FOREIGN KEY (`Player_Type_idPlayer_Type`) REFERENCES `bbos_player_type` (`idPlayer_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_type_has_competence_ibfk_2` FOREIGN KEY (`Competence_idCompetence`) REFERENCES `bbos_competence` (`idCompetence`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_player_type_has_doublecompetence_type`
--
ALTER TABLE `bbos_player_type_has_doublecompetence_type`
  ADD CONSTRAINT `player_type_has_doublecompetence_type_ibfk_1` FOREIGN KEY (`Player_Type_idPlayer_Type`) REFERENCES `bbos_player_type` (`idPlayer_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_type_has_doublecompetence_type_ibfk_2` FOREIGN KEY (`Competence_Type_idCompetence_Type`) REFERENCES `bbos_competence_type` (`idCompetence_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_player_type_has_simplecompetence_type`
--
ALTER TABLE `bbos_player_type_has_simplecompetence_type`
  ADD CONSTRAINT `player_type_has_simplecompetence_type_ibfk_1` FOREIGN KEY (`Player_Type_idPlayer_Type`) REFERENCES `bbos_player_type` (`idPlayer_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_type_has_simplecompetence_type_ibfk_2` FOREIGN KEY (`Competence_Type_idCompetence_Type`) REFERENCES `bbos_competence_type` (`idCompetence_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_team`
--
ALTER TABLE `bbos_team`
  ADD CONSTRAINT `team_ibfk_1` FOREIGN KEY (`Coach_idCoach`) REFERENCES `bbos_coach` (`idCoach`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `team_ibfk_2` FOREIGN KEY (`Team_Type_idTeam_Type`) REFERENCES `bbos_team_type` (`idTeam_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bbos_team_type_has_player_type`
--
ALTER TABLE `bbos_team_type_has_player_type`
  ADD CONSTRAINT `team_type_has_player_type_ibfk_1` FOREIGN KEY (`Team_Type_idTeam_Type`) REFERENCES `bbos_team_type` (`idTeam_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `team_type_has_player_type_ibfk_2` FOREIGN KEY (`Player_Type_idPlayer_Type`) REFERENCES `bbos_player_type` (`idPlayer_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION;
