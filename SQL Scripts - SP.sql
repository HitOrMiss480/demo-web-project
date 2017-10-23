use cs_480_hom;

DELIMITER //

CREATE PROCEDURE GetUser(un varchar(256))
BEGIN
	SELECT @UserId := UserId 
    FROM User
    WHERE Username = un;
    
    SELECT e.EventId,e.EventName,e.Org,e.Date,e.IsPublic
    FROM userevent AS ue JOIN `event` AS e
    ON ue.EventId = e.EventId
    WHERE ue.UserId = @UserId;
    
    SELECT o.OrgId, o.OrgName, o.Head
    FROM userorg as uo JOIN `organization` AS o
    ON uo.OrgId = o.OrgId
    WHERE uo.UserId = @UserId;
END//

DELIMITER ;

