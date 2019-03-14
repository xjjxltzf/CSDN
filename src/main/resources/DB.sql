DROP TABLE IF EXISTS `demo_security_user`;

CREATE TABLE `demo_security_user` (
  `EID` int(11) NOT NULL AUTO_INCREMENT,
  `EITIME` date DEFAULT NULL,
  `EUTIME` date DEFAULT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `access_level` int(11) NOT NULL,
  `description` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `demo_security_user` */

insert  into `demo_security_user`(`EID`,`EITIME`,`EUTIME`,`username`,`password`,`access_level`,`description`) values (1,'2019-03-19','2019-03-20','username1','password1',0,'description1'),(2,'2019-03-27','2019-03-13','username2','password2',1,'description2'),(3,'2019-03-13','2019-03-12','username3','password3',1,'description3'),(4,'2019-03-13','2019-03-28','admin','admin',0,'admin'),(5,'2019-03-14','2019-03-20','user','user',1,'user');
