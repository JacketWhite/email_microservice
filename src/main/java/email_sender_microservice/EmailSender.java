package email_sender_microservice;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;

/**
 * Implements the EmailSender that reads the emailProperties from a connection.properties file
 * Uses gmail's smtp to send emails.
 *
 */
public class EmailSender {
    static ConnectionPropertyValues configReader = new ConnectionPropertyValues();
    static HashMap EmailProperties = configReader.getPropValuesOfEmail();

    public static String SENDER_EMAIL = EmailProperties.get("sender_email").toString();
    public static String SENDER_PASSWORD = EmailProperties.get("sender_password").toString();
    public static String HOST = EmailProperties.get("host").toString();
    public static String SMTP_HOST = "smtp.gmail.com";
    public static String SMTP_PORT = "587";


    /**
     * Sends an email to userEmail using gmail's smtp.
     * <p>
     * Sets the properties and details for the smtp service in props.
     * <p>
     * Authenticates the password for the SENDER_EMAIL, the email account that the email will be sent from.
     * <p>
     * Sets the content and subject and reply address for the email and then sends it.
     *
     * @param clientEmail The reply for an email goes to this email account.
     * @param userEmail The email will be sent to this.
     * @param subject
     * @param messagetext
     */
    public static void send(String clientEmail, String userEmail, String subject, String messagetext) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST); //SMTP Host
            props.put("mail.smtp.port", SMTP_PORT); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setReplyTo(new Address[]{new InternetAddress(clientEmail)});

            message.setSubject(subject);
            message.setContent(messagetext, "text/html");

            Transport.send(message);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}
