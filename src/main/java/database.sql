-- 1. Nettoyage de la base de données
DROP TABLE IF EXISTS donnations CASCADE;
DROP TABLE IF EXISTS expenses CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS expense_frequency CASCADE;

-- 2. Énumération PostgreSQL pour la fréquence
CREATE TYPE expense_frequency AS ENUM ('NONE', 'MONTHLY', 'WEEKLY', 'YEARLY');

-- 3. Table pour la classe User
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    ref VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(50)
);

-- 4. Table pour la classe concrète Donnation (contient les attributs hérités de CashFlow + comment)
CREATE TABLE donnations (
    id VARCHAR(50) PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(15, 2) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    comment TEXT,
    CONSTRAINT fk_donnations_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 5. Table pour la classe concrète Expense (contient les attributs hérités de CashFlow + reason + frequency)
CREATE TABLE expenses (
    id VARCHAR(50) PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(15, 2) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    reason TEXT NOT NULL,
    frequency expense_frequency DEFAULT 'NONE',
    CONSTRAINT fk_expenses_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);