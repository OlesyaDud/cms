CREATE SCHEMA IF NOT EXISTS cmg;

SET NAMES 'utf8mb4';
USE cmg;

-- Drop tables in the correct order to avoid foreign key constraint issues
DROP TABLE IF EXISTS User_Events;
DROP TABLE IF EXISTS Twofactor_Verifications;
DROP TABLE IF EXISTS Passwordreset_Verifications;
DROP TABLE IF EXISTS Account_Verifications;
DROP TABLE IF EXISTS User_Roles;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Users;

-- Create Users table
CREATE TABLE Users (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(250) DEFAULT NULL,
    address VARCHAR(250) DEFAULT NULL,
    phone VARCHAR(30) DEFAULT NULL,
    title VARCHAR(50) DEFAULT NULL,
    bio VARCHAR(250) DEFAULT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    non_locked BOOLEAN DEFAULT FALSE,
    using_mfa BOOLEAN DEFAULT FALSE,
    image_url VARCHAR(250) DEFAULT 'https://cdn-icons-png.flaticon.com/512/149/149071.png',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UQ_Users_Email UNIQUE (email)
);

-- Create Roles table
CREATE TABLE Roles (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    permission VARCHAR(250) NOT NULL,
    CONSTRAINT UQ_Roles_Name UNIQUE (name)
);

-- Create User_Roles table
CREATE TABLE User_Roles (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT UQ_User_Roles_User_Id UNIQUE (user_id)
);

-- Create Events table
CREATE TABLE Events (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(150) NOT NULL,
    description VARCHAR(250) NOT NULL,
    CONSTRAINT UQ_Events_Type UNIQUE (type)
);

-- Create User_Events table
CREATE TABLE User_Events (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    event_id BIGINT UNSIGNED NOT NULL,
    device VARCHAR(100) DEFAULT NULL,
    id_address VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Events (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Create Account_Verifications table
CREATE TABLE Account_Verifications (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    url VARCHAR(250) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_Account_Verifications_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_Account_Verifications_Url UNIQUE (url)
);

-- Create Passwordreset_Verifications table
CREATE TABLE Passwordreset_Verifications (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    url VARCHAR(250) NOT NULL,
    expiration_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_Passwordreset_Verifications_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_Passwordreset_Verifications_Url UNIQUE (url)
);

-- Create Twofactor_Verifications table
CREATE TABLE Twofactor_Verifications (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    code VARCHAR(10) NOT NULL,
    expiration_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_Twofactor_Verifications_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_Twofactor_Verifications_Code UNIQUE (code)
);
