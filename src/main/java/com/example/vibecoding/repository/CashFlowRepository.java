package com.example.vibe_coding.repository; // Déclaration du package repository

import com.example.vibe_coding.config.DatabaseConnection; // Import de la classe de connexion DatabaseConnection
import com.example.vibe_coding.model.CashFlow; // Import de la classe POJO CashFlow
import com.example.vibe_coding.model.Donation; // Import de la classe POJO Donation
import com.example.vibe_coding.model.Expense; // Import de la classe POJO Expense
import com.example.vibe_coding.model.ExpenseFrequency; // Import de l'énumération ExpenseFrequency
import org.springframework.stereotype.Repository; // Import de l'annotation Spring @Repository

import java.math.BigDecimal; // Import du type BigDecimal pour le montant
import java.sql.*; // Import des interfaces JDBC (Connection, PreparedStatement, ResultSet, Timestamp)
import java.time.Instant; // Import de la classe Instant pour la gestion du temps
import java.util.ArrayList; // Import de la classe ArrayList
import java.util.List; // Import de l'interface List

@Repository // Déclare la classe comme composant Repository auprès du conteneur Spring
public class CashFlowRepository { // Déclaration de la classe de dépôt JDBC

    public Expense saveExpense(Expense expense) { // Méthode de sauvegarde d'une dépense en BDD
        String sql = "INSERT INTO cash_flows (id, created_at, amount, user_id, type, reason, frequency) VALUES (?, ?, ?, ?, 'EXPENSE', ?, ?)"; // Requête SQL paramétrée (variable local String)
        try (Connection conn = DatabaseConnection.getConnection(); // Obtention de la connexion via DatabaseConnection
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Préparation de la requête JDBC via la variable conn
            stmt.setString(1, expense.getId()); // Définit l'identifiant à partir du getter de la variable expense
            stmt.setTimestamp(2, Timestamp.from(expense.getCreatedAt())); // Convertit l'Instant en Timestamp depuis le getter de expense
            stmt.setBigDecimal(3, expense.getAmount()); // Définit le montant à partir du getter de expense
            stmt.setString(4, expense.getUserId()); // Définit l'ID utilisateur à partir du getter de expense
            stmt.setString(5, expense.getReason()); // Définit le motif à partir du getter de expense
            stmt.setString(6, expense.getFrequency() != null ? expense.getFrequency().name() : ExpenseFrequency.NONE.name()); // Convertit l'enum en String depuis expense
            stmt.executeUpdate(); // Exécute la requête d'insertion SQL dans la base de données
            return expense; // Retourne l'objet expense passé en paramètre
        } catch (SQLException e) { // Capture des erreurs JDBC (variable e)
            throw new RuntimeException("Erreur lors de l'insertion de la dépense: " + e.getMessage(), e); // Relance l'exception JDBC sous forme de RuntimeException
        }
    }

    public Donnation saveDonnation(Donnation donnation) { // Méthode de sauvegarde d'une donation en BDD
        String sql = "INSERT INTO cash_flows (id, created_at, amount, user_id, type, comment) VALUES (?, ?, ?, ?, 'DONNATION', ?)"; // Requête SQL d'insertion (variable locale String)
        try (Connection conn = DatabaseConnection.getConnection(); // Connexion BDD via DatabaseConnection
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Préparation de la commande SQL via conn
            stmt.setString(1, donnation.getId()); // Attribue l'ID depuis la variable donnation
            stmt.setTimestamp(2, Timestamp.from(donnation.getCreatedAt())); // Attribue la date depuis la variable donnation
            stmt.setBigDecimal(3, donnation.getAmount()); // Attribue le montant depuis la variable donnation
            stmt.setString(4, donnation.getUserId()); // Attribue l'ID utilisateur depuis la variable donnation
            stmt.setString(5, donnation.getComment()); // Attribue le commentaire depuis la variable donnation
            stmt.executeUpdate(); // Exécute la requête d'écriture en BDD
            return donnation; // Retourne l'instance donnation reçue en paramètre
        } catch (SQLException e) { // Capture des erreurs JDBC (variable e)
            throw new RuntimeException("Erreur lors de l'insertion de la donation: " + e.getMessage(), e); // Relance l'erreur technique
        }
    }

    public List<CashFlow> findByType(String type) { // Méthode de recherche par type de flux
        List<CashFlow> list = new ArrayList<>(); // Instanciation d'une liste vide (variable locale list)
        String sql = "SELECT * FROM cash_flows WHERE LOWER(type) = LOWER(?)"; // Requête SELECT filtrée par le paramètre type
        try (Connection conn = DatabaseConnection.getConnection(); // Obtention de la connexion via DatabaseConnection
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Préparation de la requête
            stmt.setString(1, type); // Positionne le paramètre type dans la requête SQL
            ResultSet rs = stmt.executeQuery(); // Exécute la lecture SQL et renvoie le ResultSet rs
            while (rs.next()) { // Parcourt chaque ligne de résultat contenue dans rs
                list.add(mapResultSetToCashFlow(rs)); // Ajoute le flux extrait de rs à la variable list
            }
            return list; // Retourne la liste de flux remplie
        } catch (SQLException e) { // Capture des erreurs JDBC (variable e)
            throw new RuntimeException("Erreur lors de la récupération des flux par type: " + e.getMessage(), e); // Lève une exception
        }
    }

    public List<CashFlow> findAll() { // Méthode de récupération de tous les flux
        List<CashFlow> list = new ArrayList<>(); // Instanciation de la liste de retour (variable locale list)
        String sql = "SELECT * FROM cash_flows"; // Requête SQL de sélection globale
        try (Connection conn = DatabaseConnection.getConnection(); // Connexion JDBC via DatabaseConnection
             Statement stmt = conn.createStatement(); // Création d'un Statement simple depuis conn
             ResultSet rs = stmt.executeQuery(sql)) { // Exécution du SELECT SQL
            while (rs.next()) { // Boucle sur le curseur JDBC rs
                list.add(mapResultSetToCashFlow(rs)); // Transformation de la ligne rs en objet et ajout dans list
            }
            return list; // Renvoie la liste globale
        } catch (SQLException e) { // Capture d'exception SQL
            throw new RuntimeException("Erreur lors de la récupération de tous les flux: " + e.getMessage(), e); // Message d'erreur
        }
    }

    public List<CashFlow> findByUserId(String userId) { // Méthode de récupération des flux d'un utilisateur
        List<CashFlow> list = new ArrayList<>(); // Création de la liste locale list
        String sql = "SELECT * FROM cash_flows WHERE user_id = ?"; // Requête SQL filtrée par identifiant utilisateur
        try (Connection conn = DatabaseConnection.getConnection(); // Récupération de la connexion conn
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Préparation de la requête SQL
            stmt.setString(1, userId); // Injecte le paramètre userId dans la requête JDBC
            ResultSet rs = stmt.executeQuery(); // Exécute le SELECT
            while (rs.next()) { // Parcourt le résultat rs
                list.add(mapResultSetToCashFlow(rs)); // Mappe et ajoute l'élément dans list
            }
            return list; // Renvoie la liste filtrée
        } catch (SQLException e) { // Capture des erreurs SQL
            throw new RuntimeException("Erreur lors de la recherche des flux de l'utilisateur: " + e.getMessage(), e); // Lève une exception
        }
    }

    public BigDecimal sumAmountByType(String type) { // Méthode de calcul de la somme par type de flux
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM cash_flows WHERE LOWER(type) = LOWER(?)"; // Requête d'agrégation SQL
        try (Connection conn = DatabaseConnection.getConnection(); // Obtention de la connexion conn
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Préparation du Statement
            stmt.setString(1, type); // Attribue le paramètre type
            ResultSet rs = stmt.executeQuery(); // Exécute la requête d'agrégation
            if (rs.next()) { // Vérifie la présence de la ligne de résultat
                return rs.getBigDecimal(1); // Retourne la première colonne sous forme de BigDecimal depuis rs
            }
            return BigDecimal.ZERO; // Valeur par défaut de type BigDecimal
        } catch (SQLException e) { // Capture d'erreur JDBC
            throw new RuntimeException("Erreur lors du calcul de la somme par type: " + e.getMessage(), e); // Relance en RuntimeException
        }
    }

    private CashFlow mapResultSetToCashFlow(ResultSet rs) throws SQLException { // Méthode privée utilitaire de mapping du ResultSet rs
        String type = rs.getString("type"); // Extrait le type (String) depuis rs
        String id = rs.getString("id"); // Extrait l'id (String) depuis rs
        Instant createdAt = rs.getTimestamp("created_at").toInstant(); // Extrait le Timestamp et le convertit en Instant depuis rs
        BigDecimal amount = rs.getBigDecimal("amount"); // Extrait le montant (BigDecimal) depuis rs
        String userId = rs.getString("user_id"); // Extrait l'ID utilisateur (String) depuis rs

        if ("EXPENSE".equalsIgnoreCase(type)) { // Condition de vérification si le type extrait est EXPENSE
            String reason = rs.getString("reason"); // Extrait le motif (String) depuis rs
            String freqStr = rs.getString("frequency"); // Extrait la fréquence (String) depuis rs
            ExpenseFrequency frequency = freqStr != null ? ExpenseFrequency.valueOf(freqStr) : ExpenseFrequency.NONE; // Convertit la chaîne String en enum ExpenseFrequency
            return new Expense(id, createdAt, amount, userId, reason, frequency); // Instancie et retourne la classe Expense
        } else { // Si le type est DONNATION
            String comment = rs.getString("comment"); // Extrait le commentaire (String) depuis rs
            return new Donnation(id, createdAt, amount, userId, comment); // Instancie et retourne la classe Donnation
        }
    }
}