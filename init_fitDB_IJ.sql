-- -----------------------------------------------------------------
-- Author: Jackson O'Donnell
--
-- Purpose: Set up the initial database for the fitness.AIO project
--
-- -----------------------------------------------------------------

-- -----------------------------------------------------------------
-- Use this to create and connect to the database
-- -----------------------------------------------------------------
-- DROP DATABASE IF EXISTS fitDB;
-- CREATE DATABASE  fitDB;

-- USE fitDB;

-- -----------------------------------------------------------------
-- Use this to connect to the database rather than creating it
-- -----------------------------------------------------------------
connect 'jdbc:derby:.\fitDB;create=true';

-- -----------------------------------------------------------------
-- Table: users
-- 
-- Description: Holds user info for logging in
-- 
-- Attributes:
--              username - The user's screen name
--              name     - The user's given first and last name
--              password - The user's password
-- -----------------------------------------------------------------
-- DROP TABLE IF EXISTS users;
CREATE TABLE users(
   username VARCHAR(25) NOT NULL,
   name     VARCHAR(25) NOT NULL,
   password VARCHAR(25) NOT NULL,
   PRIMARY KEY (username));

-- -----------------------------------------------------------------
-- Table: diary
-- 
-- Description: Holds information to gather data on each diary entry
-- 
-- Attributes:
--              username     - The user's screen name
--              dat          - The date of the diary entry
--              sleepID      - The ID of the sleep entry for that date
--              exerciseID   - The ID of the exercise entry for that date
--              foodID       - The ID of the food list entry for that date
-- -----------------------------------------------------------------
-- Make or remake diary relationship
-- DROP TABLE IF EXISTS diary;
CREATE TABLE diary(
   username  VARCHAR(25) NOT NULL,
   dat       VARCHAR(25) NOT NULL,
   PRIMARY KEY (dat, username));

-- -----------------------------------------------------------------
-- Table: food_list
-- 
-- Description: Holds lists of food names
-- 
-- Attributes:
--              foodID       - The ID of the food list
--              food_name    - The name of one food in the list
-- -----------------------------------------------------------------
-- Make or remake food list relationship
-- DROP TABLE IF EXISTS food_list;
CREATE TABLE food_list(
   username  VARCHAR(25) NOT NULL,
   dat       VARCHAR(25) NOT NULL,
   food_name VARCHAR(25) NOT NULL,
   quantity  INT NOT NULL,
   PRIMARY KEY (username, dat, food_name));
   
-- -----------------------------------------------------------------
-- Table: food_notes
-- 
-- Description: Holds notes corresponding to food lists
-- 
-- Attributes:
--              foodID       - The ID of the food list
--              note         - A note about the food list
-- -----------------------------------------------------------------
CREATE TABLE food_notes(
   username  VARCHAR(25) NOT NULL,
   dat       VARCHAR(25) NOT NULL,
   note       VARCHAR(255),
   PRIMARY KEY(username, dat));

-- -----------------------------------------------------------------
-- Table: sleep
-- 
-- Description: Holds lists of food names
-- 
-- Attributes:
--              sleepID       - The ID of the sleep entry
--              hours         - The number of hours slept
--              note          - A note about the sleep entry
-- -----------------------------------------------------------------
-- Make or remake sleep relationship
-- DROP TABLE IF EXISTS sleep;
CREATE TABLE sleep(
   username  VARCHAR(25) NOT NULL,
   dat       VARCHAR(25) NOT NULL,
   hours   INT NOT NULL,
   note    VARCHAR(255),
   PRIMARY KEY (username, dat));

-- -----------------------------------------------------------------
-- Table: food
-- 
-- Description: Holds lists of food names
-- 
-- Attributes:
--              food_name    - The name of the food
--              calories     - The number of calories in the food
--              protein      - The grams of protein in the food
--              fat          - The grams of fat in the food
--              carbs        - The grams of carbs in the food
-- -----------------------------------------------------------------
-- Make or remake food relationship
-- DROP TABLE IF EXISTS food;
CREATE TABLE food(
   food_name VARCHAR(25) NOT NULL,
   calories  int NOT NULL,
   protien   int NOT NULL,
   fat       int NOT NULL,
   carbs     int NOT NULL,
   PRIMARY KEY (food_name));
-- -----------------------------------------------------------------
-- Table: work_out
-- 
-- Description: Holds exercise entries for the diary
-- 
-- Attributes:
--              exerciseID   - The ID of the exercise entry
--              hours        - The number of hours exercising
-- -----------------------------------------------------------------
-- Make or remake work_out relationship
-- DROP TABLE IF EXISTS work_out;
CREATE TABLE work_out(
   username  VARCHAR(25) NOT NULL,
   dat       VARCHAR(25) NOT NULL,
   hours     INT NOT NULL,
   note      VARCHAR(255),
   PRIMARY KEY (username, dat));
   
-- -----------------------------------------------------------------
-- Table: oneRep
-- 
-- Description: Holds data on the one rep maxes for each user
-- 
-- Attributes:
--              deadlift    - The number of pounds a user can deadlift
--              squat       - The number of pounds a user can squat
--              bench       - The number of pounds a user can bench
--              username    - The user's screen name
-- -----------------------------------------------------------------
-- DROP TABLE IF EXISTS users;
CREATE TABLE oneRep(
   deadlift VARCHAR(25),
   squat    VARCHAR(25),
   bench    VARCHAR(25),
   username VARCHAR(25) NOT NULL,
   PRIMARY KEY (username));
   
-- userProfile:
CREATE TABLE userProfile(
    protien  VARCHAR(25),
    carb     VARCHAR(25),
    fat      VARCHAR(25),
    calories VARCHAR(25),
    height   VARCHAR(25),
    weight   VARCHAR(25),
    username VARCHAR(25) NOT NULL,
    PRIMARY KEY (username));


