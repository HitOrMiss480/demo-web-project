create database cs_480_hom;

use cs_480_hom;

CREATE TABLE IF NOT EXISTS `User`
(
	`UserId` varchar (256) PRIMARY KEY,
	`Name` varchar(256) NOT NULL,
	`UserName` varchar (256) NOT NULL,	
	CONSTRAINT UC_UserName UNIQUE (`UserName`)
);

CREATE TABLE IF NOT EXISTS `Event`
(
	`EventId` varchar(256) PRIMARY KEY,
	`EventName` varchar(256) NOT NULL,
	`Planner` varchar(256) NOT NULL,	
    `Org` varchar(256),					
    `Date` datetime NOT NULL,
    `IsPublic` bool NOT NULL
);

CREATE TABLE IF NOT EXISTS `Organization`
(
	`OrgId` varchar(256) PRIMARY KEY,
    `OrgName` varchar(256) NOT NULL,
    `Head` varchar(256) NOT NULL 		
);

CREATE TABLE IF NOT EXISTS `UserEvent`
(
	`UserId` varchar(256) NOT NULL,		
    `EventId` varchar(256) NOT NULL		    
);

CREATE TABLE IF NOT EXISTS `UserOrg`
(
	`UserId` varchar(256) NOT NULL,		
    `OrgId` varchar(256) NOT NULL		-- make FK_UserOrgOrg
);

ALTER TABLE `Event`
ADD CONSTRAINT FK_EventUser FOREIGN KEY (`Planner`)
 REFERENCES `User`(`UserID`);
 
ALTER TABLE `Event`
ADD CONSTRAINT FK_EventOrg FOREIGN KEY (`Org`)
 REFERENCES `Organization`(`OrgId`);

ALTER TABLE `Organization`
ADD CONSTRAINT FK_OrgUser FOREIGN KEY (`Head`)
 REFERENCES `User`(`UserId`);

ALTER TABLE `UserEvent`
ADD CONSTRAINT FK_UserEventUser FOREIGN KEY(`UserId`)
 REFERENCES `User`(`UserId`);
 
ALTER TABLE `UserEvent`
ADD CONSTRAINT FK_UserEventEvent FOREIGN KEY (`EventId`)
 REFERENCES `Event`(`EventId`);
 
ALTER TABLE `UserOrg`
ADD CONSTRAINT FK_UserOrgUser FOREIGN KEY (`UserId`)
 REFERENCES `User`(`UserId`);
 
ALTER TABLE `UserOrg`
ADD CONSTRAINT FK_UserOrgOrg FOREIGN KEY (`OrgId`)
 REFERENCES `Organization`(`OrgId`);