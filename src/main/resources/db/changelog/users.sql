--liquibase formatted sql

--changeset brocodex:1-create-users-table

CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  telegramId BIGINT NOT NULL UNIQUE,
  userName VARCHAR(255) NOT NULL,
  firstName VARCHAR(255),
  lastName VARCHAR(255),
  createdAt DATE DEFAULT CURRENT_DATE,
  isActive BOOLEAN DEFAULT TRUE
);