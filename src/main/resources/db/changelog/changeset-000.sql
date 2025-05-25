-- liquibase formatted sql

-- changeset create-employee:1
CREATE TYPE category_type AS ENUM ('take', 'give');
CREATE TYPE status_type AS ENUM ('paid', 'unpaid');

CREATE TABLE transactions (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  amount INTEGER NOT NULL CHECK (amount > 0),
  category CATEGORY_TYPE NOT NULL,
  created_at DATE DEFAULT CURRENT_DATE,
  due_date DATE,
  status STATUS_TYPE NOT NULL,
  remarks TEXT,
  CONSTRAINT valid_due_date CHECK (due_date >= created_at)
);