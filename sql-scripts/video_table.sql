USE `video_library`;

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `entity_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `video_source` varchar(100) DEFAULT NULL,
  `thumbnail_source` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`entity_id`),
  FOREIGN KEY (entity_id) REFERENCES entity(entity_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


INSERT INTO `video` (`entity_id`,`name`,`creator`,`date`,`video_source`,`thumbnail_source`) VALUES (1,'Couple on the Beach','Oscar Due Wang','11/12/13','https://s3-us-west-1.amazonaws.com/vidlib.io/Couple+Walking+on+a+Beach+Filmed+with+a+Drone.mp4','https://s3-us-west-1.amazonaws.com/vidlib.io/couple-walking-on-beach-with-drone.jpg');
INSERT INTO `video` (`entity_id`,`name`,`creator`,`date`,`video_source`,`thumbnail_source`) VALUES (2,'City Views','Matthew Lee Moore','10/11/12','https://s3-us-west-1.amazonaws.com/vidlib.io/Drone+Footage+of+High+Rises+in+a+City.mp4','https://s3-us-west-1.amazonaws.com/vidlib.io/drone-video-of-city.jpg');
INSERT INTO `video` (`entity_id`,`name`,`creator`,`date`,`video_source`,`thumbnail_source`) VALUES (3,'Beautiful Beach','Matthew Lee Moore','09/08/07','https://s3-us-west-1.amazonaws.com/vidlib.io/Beach+Aerial+Footage+Taken+by+a+Drone.mp4','https://s3-us-west-1.amazonaws.com/vidlib.io/drone-shoots-beautiful-beach.jpg');
INSERT INTO `video` (`entity_id`,`name`,`creator`,`date`,`video_source`,`thumbnail_source`) VALUES (4,'Sunset Timelapse','Felix Mittermeier','08/07/06','https://s3-us-west-1.amazonaws.com/vidlib.io/Pexels+Videos+1110140.mp4','https://s3-us-west-1.amazonaws.com/vidlib.io/timelapse-of-moving-clouds-and-sunset.jpg');
