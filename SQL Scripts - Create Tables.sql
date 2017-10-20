use CS_480_HOM;
create table `User`
(
	`UserId` varchar (256) NOT NULL,	-- make PK
    `UserName` varchar (256) NOT NULL	-- make UC
);
create table `EventId`
(
	`EventId` varchar(256) NOT NULL, 	-- make PK
	`Planner` varchar(256) NOT NULL,	-- make FK_EventUser
    `Organization` varchar(256),			-- make FK_EventOrg
    `Date` datetime NOT NULL,
    `IsPublic` bool NOT NULL
);
