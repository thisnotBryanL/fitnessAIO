-- ----------------------------------------------------
-- Author: Jackson O'Donnell
--
-- Purpose: Delete all tables in the database
-- ----------------------------------------------------

connect 'jdbc:derby:.\fitDB;';

DROP TABLE users;
DROP TABLE diary;
DROP TABLE food_list;
DROP TABLE food_notes;
DROP TABLE sleep;
DROP TABLE food;
DROP TABLE work_out;
DROP TABLE oneRep;
DROP TABLE userProfile;