# Setting Up SQL Server On Your Local

## Getting Started

Go to the MySQL website: [MySQL Installer](https://dev.mysql.com/downloads/installer/)

### Installing

Once the package is downloaded, run the installer on the computer.

Install the following packages: MySQL Server and MySQL Workbench.

Once the packages are downloaded, it will request a 'root' user and password. 
```
NOTE: Make the password something easy to remember you will be using it later.
```
Once the packages are finished downloading, you will need to build the database on your local.

```
Open your task manager and check if the following task is running in the background on your computer: mysqld

If not, follow the instructions here: [Making a MySQL Server On Your Local](https://dev.mysql.com/doc/refman/5.7/en/windows-start-service.html) 

Open MySQL Workbench and create a connection. Leave all the settings for your new DB connection and only give it a connection name. Open the connection.

Open the two SQL script files: SQL Scripts - Create Tables.sql; SQL Scripts - SP.sql

Build them in order of tables then 'sp's. If all builds well the database should be up on your local.

The connection string should be: server=127.0.0.1;uid=root;pwd=[you password];database=CS_480_HOM
```
