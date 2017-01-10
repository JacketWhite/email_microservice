package email_sender_microservice.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * This is a model for the Email table.
 * Uses ORMLite to create and define a table's name and fields.
 */

@DatabaseTable(tableName = "email")
    public class Email {

        @DatabaseField(generatedId = true, columnDefinition = "VARCHAR(100) DEFAULT 'EMAIL' NOT NULL")
        private Integer id;

        @DatabaseField
        private String status;

        @DatabaseField
        private String to;

        @DatabaseField
        private String from;

        @DatabaseField
        private String subject;

        @DatabaseField
        private String message;

        public Email() {
            // ORMLite needs a no-arg constructor
        }

        public Email(String to, String from, String subject, String message) {
            this.status = "new";
            this.to = to;
            this.from = from;
            this.subject = subject;
            this.message = message;
        }

    /**
     * Validates the argument email if its valid, in other words, looks like this: "xyz@gmail.com" and returns true.
     * Returns false if the email is not in a valid format.
     *
     * @param email an email String which is needs to be validated.
     * @return Boolean.
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    }
