DROP DATABASE IF EXISTS HealthBlog;
CREATE DATABASE HealthBlog CHARSET 'utf8';
USE HealthBlog;

CREATE TABLE Articles(
  Id INTEGER NOT NULL AUTO_INCREMENT,
  Category VARCHAR(150) NOT NULL,
  Title TEXT NOT NULL,
  AuthorId INTEGER NOT NULL,
  Date DATE NOT NULL,

  PRIMARY KEY(Id),
  FOREIGN KEY (AuthorId) REFERENCES Users(Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Images(
  Id INTEGER NOT NULL AUTO_INCREMENT,
  Path TEXT NOT NULL,
  ArticleId INTEGER NOT NULL,

  PRIMARY KEY(Id),
  FOREIGN KEY (ArticleId) REFERENCES Articles(Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Articles_Tags(
  Id INTEGER NOT NULL AUTO_INCREMENT,
  ArticleId INTEGER NOT NULL,
  TagId INTEGER NOT NULL,

  PRIMARY KEY(Id),
  FOREIGN KEY(ArticleId) REFERENCES Articles(Id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY(TagId) REFERENCES Tags(Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Users(
  Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Email VARCHAR(100) UNIQUE NOT NULL,
  FullName VARCHAR(150) NOT NULL,
  Password VARCHAR(60) NOT NULL
);

CREATE TABLE Roles(
  Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(150) NOT NULL
);

CREATE TABLE Users_Roles(
  Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  UserId INTEGER NOT NULL,
  RoleId INTEGER NOT NULL,

  FOREIGN KEY(UserId) REFERENCES Users(Id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY(RoleId) REFERENCES Roles(Id) ON DELETE CASCADE ON UPDATE CASCADE
);