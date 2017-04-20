PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE users(username varchar(10), password varchar(10));
INSERT INTO "users" VALUES('admin','123');
COMMIT;
