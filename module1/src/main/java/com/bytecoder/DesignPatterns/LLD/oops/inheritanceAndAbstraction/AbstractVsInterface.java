package com.bytecoder.DesignPatterns.LLD.oops.inheritanceAndAbstraction;

import java.util.ArrayList;
import java.util.List;

/**
 * This example demonstrates the key differences between abstract classes and interfaces,
 * and when to use each one.
 */
public class AbstractVsInterface {

    // Abstract class example - used when we need to share state and implementation
    abstract static class DatabaseConnection {
        // Can have instance fields
        protected String url;
        protected String username;
        protected boolean isConnected;
        
        // Can have constructor
        public DatabaseConnection(String url, String username) {
            this.url = url;
            this.username = username;
            this.isConnected = false;
        }
        
        // Can have concrete methods
        public final boolean isConnected() {  // Can have final methods
            return isConnected;
        }
        
        // Can have protected methods
        protected void validateConnection() {
            if (!isConnected) {
                throw new IllegalStateException("Not connected to database");
            }
        }
        
        // Abstract methods to be implemented
        abstract void connect();
        abstract void disconnect();
        abstract void executeQuery(String query);
    }

    // Interface example - used to define a contract/capability
    interface Loggable {
        // All fields are public static final
        String LOG_PREFIX = "[LOG] ";
        
        // Abstract method
        void log(String message);
        
        // Default method
        default void logError(String error) {
            log(LOG_PREFIX + "ERROR: " + error);
        }
        
        // Static method
        static String formatMessage(String message) {
            return LOG_PREFIX + message;
        }
    }

    // Interface extending multiple interfaces
    interface AdvancedLogging extends Loggable {
        void setLogLevel(String level);
        default void logDebug(String message) {
            log(LOG_PREFIX + "DEBUG: " + message);
        }
    }

    // Concrete implementation of abstract class
    static class PostgresConnection extends DatabaseConnection {
        public PostgresConnection(String url, String username) {
            super(url, username);
        }

        @Override
        void connect() {
            // Implementation
            isConnected = true;
            System.out.println("Connected to Postgres");
        }

        @Override
        void disconnect() {
            isConnected = false;
            System.out.println("Disconnected from Postgres");
        }

        @Override
        void executeQuery(String query) {
            validateConnection();
            System.out.println("Executing query: " + query);
        }
    }

    // Class implementing multiple interfaces
    static class DatabaseLogger implements AdvancedLogging {
        private String logLevel = "INFO";
        private List<String> logs = new ArrayList<>();

        @Override
        public void log(String message) {
            logs.add(message);
            System.out.println(message);
        }

        @Override
        public void setLogLevel(String level) {
            this.logLevel = level;
            log("Log level set to: " + level);
        }
    }

    // Class using both abstract class and interfaces
    static class MonitoredPostgresConnection extends PostgresConnection implements AdvancedLogging {
        private final DatabaseLogger logger;

        public MonitoredPostgresConnection(String url, String username) {
            super(url, username);
            this.logger = new DatabaseLogger();
        }

        @Override
        public void log(String message) {
            logger.log(message);
        }

        @Override
        public void setLogLevel(String level) {
            logger.setLogLevel(level);
        }

        @Override
        void connect() {
            super.connect();
            log("Database connected");
        }

        @Override
        void disconnect() {
            super.disconnect();
            log("Database disconnected");
        }

        @Override
        void executeQuery(String query) {
            logDebug("Executing query: " + query);  // Using default method from interface
            super.executeQuery(query);
        }
    }

    public static void main(String[] args) {
        MonitoredPostgresConnection db = new MonitoredPostgresConnection("localhost:5432", "admin");
        db.setLogLevel("DEBUG");
        db.connect();
        db.executeQuery("SELECT * FROM users");
        db.disconnect();
    }
}
