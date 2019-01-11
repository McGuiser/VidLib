USE `video_library`;

DROP TABLE IF EXISTS `entity_comment`;

CREATE TABLE `entity_comment` (
  `entity_id` int(11) NOT NULL,
  `comment_no` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL, 
  `comment` varchar(1000) NOT NULL,
  
  PRIMARY KEY (`comment_no`, `entity_id`),
  FOREIGN KEY (entity_id) REFERENCES entity(entity_id),
  FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


INSERT INTO `entity_comment` (`entity_id`,`comment_no`,`user_id`,`comment`) VALUES (1, 1, 4, 'Hello world!');