/*package model.dao;

public class sql {
    USE linkedin;
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT primary key,
  email VARCHAR(255) NOT NULL UNIQUE,
  job VARCHAR(255) ,
  password VARCHAR(255),
  firstName VARCHAR(255),
  lastName VARCHAR(255) , 
  additionalName VARCHAR(255) ,
  aducation VARCHAR(255) ,
  professtion VARCHAR(255) ,
  profilePhoto VARCHAR(255) ,
  backgroundPhoto VARCHAR(255) ,
  header VARCHAR(255) ,
  token  VARCHAR(255),
  location VARCHAR(255),
  followers VARCHAR(511) ,
  followings VARCHAR(511) ,
  connects VARCHAR(511) ,
  posts VARCHAR(511) ,
  intentionToHire VARCHAR(255) ,
  blocks VARCHAR(255) ,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE posts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  text VARCHAR(5000) ,
  hashtaks VARCHAR(1000),
  numLikes INT,
  numComments iNT,
  comments VARCHAR(1000),
  date VARCHAR(255) ,
  writer VARCHAR(1000) NOT NULL,
  token VARCHAR(255) ,
  image VARCHAR(255),
  likers VARCHAR(1000),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 ) ;

CREATE TABLE messages (

  id INT PRIMARY KEY AUTO_INCREMENT,
  sender  VARCHAR(1000) NOT NULL,
  receiver VARCHAR(1000) NOT NULL,
  content  VARCHAR(1000) NOT NULL,
  text  VARCHAR(1000),
  date  VARCHAR(1000),
  replyToMassage  VARCHAR(1000),
  image  VARCHAR(1000),
  token  VARCHAR(1000),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- ساخت جدول information
CREATE TABLE informationToCalls (
  id INT PRIMARY KEY AUTO_INCREMENT,
  userLink  VARCHAR(1000),
  email  VARCHAR(1000),
  phoneNumber VARCHAR(1000),
  communicationChannel VARCHAR(1000),
  typePhone  VARCHAR(1000),
  token  VARCHAR(1000),
  address  VARCHAR(1000)
);

-- ساخت جدول location
CREATE TABLE locations (
  id INT PRIMARY KEY AUTO_INCREMENT,
  country VARCHAR(100) NOT NULL,
  city VARCHAR(100)
);




}*/
