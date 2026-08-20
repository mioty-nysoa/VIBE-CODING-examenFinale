
INSERT INTO users (id, ref, first_name, last_name, email, phone) VALUES
('usr-101', 'REF-2026-01', 'Alice', 'Martin', 'alice.martin@example.com', '+33611223344'),
('usr-102', 'REF-2026-02', 'Bob', 'Thomas', 'bob.thomas@example.com', '+33622334455'),
('usr-103', 'REF-2026-03', 'Chloé', 'Bernard', 'chloe.bernard@example.com', NULL);


INSERT INTO donnations (id, created_at, amount, user_id, comment) VALUES
('don-101', '2026-01-15 10:30:00+00', 250.00, 'usr-101', 'Don annuel pour l''association'),
('don-102', '2026-02-01 14:15:00+00', 100.00, 'usr-102', 'Campagne de financement participatif'),
('don-103', '2026-02-10 09:00:00+00', 500.00, 'usr-101', 'Mécénat ponctuel'),
('don-104', '2026-02-18 16:45:00+00', 50.00, 'usr-103', 'Soutien au projet');


INSERT INTO expenses (id, created_at, amount, user_id, reason, frequency) VALUES
('exp-101', '2026-01-05 08:00:00+00', 45.99, 'usr-101', 'Abonnement Internet fibre', 'MONTHLY'),
('exp-102', '2026-01-12 11:20:00+00', 1200.00, 'usr-102', 'Achat nouvel ordinateur portable', 'NONE'),
('exp-103', '2026-01-20 18:30:00+00', 25.00, 'usr-101', 'Recharge passe de transport', 'WEEKLY'),
('exp-104', '2026-02-02 10:00:00+00', 350.00, 'usr-103', 'Renouvellement licences logiciels', 'YEARLY'),
('exp-105', '2026-02-14 12:00:00+00', 85.50, 'usr-102', 'Achat fournitures de bureau', 'NONE');