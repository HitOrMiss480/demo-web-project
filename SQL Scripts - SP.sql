use cs_480_hom;
-- USER STORED PROCEDURES
DELIMITER //
CREATE PROCEDURE GetUser(uID varchar(256))
BEGIN
    SELECT UserId, UserName, `Name`
    FROM User
    Where UserId = uID;
    
    SELECT e.EventId,e.EventName, e.IsPublic, e.Org, e.Planner, e.Date
    FROM `Event` AS e
    JOIN `UserEvent` AS ue
    ON e.EventId = ue.EventId
    WHERE ue.UserId = uID;
    
    SELECT *
    FROM `Organization` as o
    JOIN `UserOrg` AS uo
    ON o.OrgId = uo.OrgId
    WHERE uo.UserId = uID;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CreateUser(uID varchar(256),n varchar(256), un varchar(256))
BEGIN
	INSERT INTO `User`(UserId,`Name`,UserName) 
    VALUES 			  (   uID,     n,      un);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE AlterUser(uID varchar(256),n varchar(256),un varchar(256))
BEGIN
	UPDATE `user`
    SET UserName = un,`Name` = n
    WHERE UserId = uID;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteUser(uID varchar(256))
BEGIN
	DELETE FROM `user`
    WHERE UserId = uID;
END//
DELIMITER ;
-- END USER STORED PROCEDURES

-- EVENT STORED PROCEDURES
DELIMITER //
CREATE PROCEDURE GetEvents()
BEGIN
	SELECT EventId, EventName, `Date`, IsPublic, Org, Planner 
    FROM `event`;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CreateEvent(eID varchar(256),en varchar(256), d date, isP bool, p varchar(256))
BEGIN
	INSERT INTO `event` (EventID, EventName, `Date`, IsPublic, Planner)
    VALUES 				(    eID,        en,      d,      isP,       p);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE AlterEvent(eID varchar(256),en varchar(256), d date, isP bool, p varchar(256))
BEGIN
	UPDATE `event`
    SET EventName = en,`Date` = d, IsPublic = isP, Planner = p
    WHERE EventId = eID;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteEvent(eID varchar(256))
BEGIN
	DELETE FROM `event`
    WHERE EventId = eID;
END//
DELIMITER ;
-- END EVENT STORED PROCS

-- ORGANIZATION STORED PROCS
DELIMITER //
CREATE PROCEDURE GetOrgs()
BEGIN
	SELECT OrgId,OrgName,Head
    FROM Organization;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CreateOrg(oID varchar(256), `on` varchar(256), h varchar(256))
BEGIN 
	INSERT INTO `Organization` (OrgId, OrgName, Head)
    VALUES 					   (  oID,    `on`,    h);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE AlterOrg(oID varchar(256), `on` varchar(256), h varchar(256))
BEGIN
	UPDATE `Organization`
    SET OrgName = `on`, Head = h
    WHERE OrgId = oID;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteOrg(oID varchar(256))
BEGIN
	DELETE FROM `Organization`
    WHERE OrgId = oID;
END//
DELIMITER ;
-- END ORG STORED PROC

-- USEREVENT STORED PROC
DELIMITER //
CREATE PROCEDURE AddUserEvent()
BEGIN

END//
DELIMITER ;