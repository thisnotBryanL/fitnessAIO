-- --------------------------------------------
-- Author: Jackson O'Donnell
--
-- Purpose: To remove all data from database
-- --------------------------------------------

connect 'jdbc:derby:.\fitDB';

DELETE FROM users;
DELETE FROM diary;
DELETE FROM food_list;
DELETE FROM food_notes;
DELETE FROM sleep;
DELETE FROM food;
DELETE FROM work_out;
DELETE FROM oneRep;