-- MySQL dump 10.13  Distrib 5.5.54, for Win32 (AMD64)
--
-- Host: localhost    Database: bbs
-- ------------------------------------------------------
-- Server version	5.5.54

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection` (
  `collection_id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL,
  `post_id` int(32) NOT NULL,
  `time` varchar(20) NOT NULL,
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (1,18,13,'2017-04-11 15:08'),(3,1,23,'2017-05-04 19:44');
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(32) NOT NULL AUTO_INCREMENT,
  `post_id` int(32) NOT NULL,
  `user_id` int(32) NOT NULL,
  `content` varchar(20000) NOT NULL,
  `time` varchar(20) NOT NULL,
  `agree` int(32) NOT NULL DEFAULT '0',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,4,2,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;的撒付三</p>','2017-03-02 21:52',0),(2,7,1,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;谢谢了！么么哒<img src=\"http://img.baidu.com/hi/face/i_f03.gif\"/></p>','2017-03-02 21:57',0),(3,9,10,'<p>&nbsp; &nbsp; &nbsp; &nbsp;是不错</p>','2017-03-02 23:31',0),(4,8,13,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 天才</p>','2017-03-03 00:16',1),(5,16,1,'<p>厉害了，我的哥<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/></p>','2017-03-03 14:08',1),(6,15,15,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;111</p>','2017-03-03 14:10',0),(7,17,1,'<p>这是谁，上来就发这种<img src=\"http://img.baidu.com/hi/jx2/j_0007.gif\"/></p>','2017-03-03 14:32',1),(8,18,1,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;666<img src=\"http://img.baidu.com/hi/jx2/j_0020.gif\"/></p>','2017-03-03 14:34',0),(10,32,18,'<p>选择自己选喜欢做的事情就对了，有兴趣，才能激发你的潜力，加油！<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/> &nbsp; &nbsp; &nbsp; &nbsp;</p>','2017-03-03 20:00',0),(11,11,18,'<p>&nbsp;音乐很好听！<img src=\"http://img.baidu.com/hi/jx2/j_0067.gif\"/></p>','2017-03-28 11:17',0),(12,13,18,'<p><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0035.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0047.gif\" style=\"width: 58px; height: 62px;\"/></p>','2017-04-11 15:09',0),(13,23,1,'<p>好看啊！<img src=\"http://img.baidu.com/hi/face/i_f48.gif\"/></p>','2017-05-04 19:44',0),(14,24,18,'<p>好听啊！<img src=\"http://img.baidu.com/hi/jx2/j_0017.gif\"/></p>','2017-05-16 21:41',16),(15,24,1,'<p>成都带不走只有你！</p>','2017-05-16 21:51',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `post_id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL,
  `post_time` varchar(20) NOT NULL,
  `hot` int(11) NOT NULL DEFAULT '0',
  `src` varchar(120) NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT '学习',
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (8,10,'2017-03-02 23:11',3,'/f6d4730b-b493-42b6-8f0b-2997962507af','学习','上天派来的李长青'),(9,12,'2017-03-02 23:27',1,'/67e2bafb-ead7-4e13-a224-2efb225572c6','学习','加油'),(10,1,'2017-03-02 23:31',0,'/4873d40c-ae92-42b1-8d78-d9ee59b807b3','学习','谢谢了，大家了'),(11,1,'2017-03-02 23:37',1,'/abfd4677-0297-4c6f-8958-d886b6de951c','学习','来自网易云音乐的一份歌单'),(12,1,'2017-03-03 00:05',0,'/24e48511-0915-4de2-b89c-9df2a5f559c6','分享','如何发表帖子'),(13,1,'2017-03-03 00:31',1,'/1f3e1f9c-b21d-44f7-8880-f3ca59cac84a','学习','关于论坛的名字'),(14,1,'2017-03-03 01:11',0,'/20def65b-a29e-44b3-9a41-b404ee485b6a','学习','关于网站是怎么写'),(15,14,'2017-03-03 14:04',0,'/e3b603c3-e1b9-4e81-8818-9b593d4e5a90','学习','12321'),(16,14,'2017-03-03 14:06',1,'/fa6bee6d-42a6-4515-9f43-76235abeb2a5','学习','30个不可不知的容器技术工具和资源'),(17,16,'2017-03-03 14:31',0,'/3f5a20e6-1eb7-4339-af42-31c129e3e192','分享','老司机快开车'),(18,16,'2017-03-03 14:32',0,'/4f4b01e5-c63b-4884-99b4-18ba7724159e','学习','论身体的重要性'),(22,18,'2017-03-03 17:58',0,'/7938aa1f-f3e3-4e02-99db-15f49e9b2863','视频','【混剪】千与千寻，龙猫 久石譲 - あの夏へ'),(23,18,'2017-03-03 18:00',1,'/40adc562-0740-458d-a9a4-5b01261ee5c5','搞笑','暴走大事件'),(24,20,'2017-03-03 18:31',0,'/cf285b89-f4bf-492a-9154-322c3e9fa1fc','音乐','和我在成都的街头走一走'),(25,20,'2017-03-03 18:33',0,'/bd1d0163-0b86-4b76-ab44-489d08860317','身边','SQL语句大全'),(27,20,'2017-03-03 18:44',1,'/5c5de419-034c-4395-b535-4aee55938da8','学习','CSS样式的书写'),(28,21,'2017-03-03 19:15',0,'/0ddc371a-41c5-4ec6-8da9-69037e90172b','分享','一个星期总有那么几天不想工作'),(29,21,'2017-03-03 19:17',0,'/f55bbdf2-551b-4c26-8403-9e9a0379397b','身边','听两个老前辈在唠嗑，感觉很不好，因为我一句也没听懂'),(30,21,'2017-03-03 19:18',1,'/948be7ca-37a1-4b03-8355-01c27f18edd9','学习','未来的日子里学好电脑和英语就够了'),(31,21,'2017-03-03 19:20',1,'/4abb69f5-c500-4d0e-b5a7-087048c8df28','学习','感觉这个飞光社区的设计颜色搭配还是挺不错的'),(32,22,'2017-03-03 19:56',1,'/34a93cba-aa54-484a-84bb-1bfd9c4cf43a','学习','不要盲目努力'),(33,22,'2017-03-03 19:59',0,'/d6211cdf-d2bb-4187-8aec-1c93180e7b58','学习','年轻人就要出去闯');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(32) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(10) NOT NULL,
  `nickname` varchar(256) NOT NULL,
  `sex` varchar(10) NOT NULL DEFAULT '未知',
  `birthday` varchar(20) NOT NULL DEFAULT '1990-1-1',
  `user_icon` varchar(120) NOT NULL DEFAULT 'default.ico',
  `email` varchar(120) NOT NULL,
  `reg_time` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'tyq123','123456','我是Admin','男','1993-6-2','face/1.png','1441071540@qq.com','2017-03-02 20:12'),(10,'lichangqing','123456','hello','未知','1990-1-1','face/12.png','wefsdhv','2017-03-02 22:34'),(11,'111111','111111','111111','未知','1990-1-1','face/13.png','12@ee.com','2017-03-02 23:22'),(12,'dengbaoling','d147258','deng','未知','1990-1-1','face/15.png','2239146847@qq.com','2017-03-02 23:27'),(13,'fghfghfgh','123qwe','rgtuj','未知','1990-1-1','face/15.png','dfghh@qq.com','2017-03-03 00:16'),(14,'123456','123456','clanceRen','未知','1990-1-1','face/12.png','18804501916@163.com','2017-03-03 13:58'),(15,'xxssa11','1234567_','4588','未知','1990-1-1','face/8.png','1647650260@qq.com','2017-03-03 14:09'),(16,'a123456','123456','老司机12a','未知','1990-1-1','face/15.png','2691544795@qq.com','2017-03-03 14:25'),(17,'ttt123','123456','成都的街头','未知','1990-1-1','face/11.png','1441071540@qq.com','2017-03-03 14:53'),(18,'tyqinjava','123456','千与千寻','女','1993-6-2','face/3.png','144107150@qq.com','2017-03-03 17:08'),(19,'12345678','MA123456','不跟你多BB','未知','1990-1-1','face/5.png','1750674245@qq.com','2017-03-03 17:18'),(20,'tyq12345','123456','我为代码狂','男','1990-1-1','face/8.png','TinyQ@163.com','2017-03-03 18:20'),(21,'bigbiggirl','12345678','bigbiggril','未知','1990-1-1','face/10.png','1047385692@qq.com','2017-03-03 19:07'),(22,'lyh15779310553','lyh150553','星源','未知','1990-1-1','face/6.png','2957979961@qq.com','2017-03-03 19:42'),(23,'saraband','123456','sara','未知','1990-1-1','face/4.png','saraband@126.com','2017-03-03 22:04'),(24,'123ghhg','ASD123','hh445','未知','1990-1-1','default.ico','788979879@qq.com','2017-04-07 15:58'),(26,'admin66','123456','你好明天d','未知','1990-1-1','default.ico','changqing@qq.com','2017-04-16 11:16'),(27,'root12','tyq123','tyq123','未知','1990-1-1','default.ico','1441071540@qq.com','2017-04-16 11:20'),(28,'admin123','tyq123','青青子衿','女','1999-1-1','face/9.png','1441071540@qq.com','2017-05-04 19:31');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-27 14:44:02
