package email_sender_microservice;

import java.util.Timer;
import java.util.TimerTask;

public class ScheduledEmailController {

    /**
     * Periodically sends the subject and message using the sender as an email, every 10 seconds,
     *
     * @param sender String email from where you want to send your email.
     * @param receiver String email, the address where you want to send your email.
     * @param subject the title that will be sent.
     * @param message the content that will be sent.
     * @see Timer
     * @see EmailSender
     */
    public void scheduleEmails(final String sender, final String receiver, final String subject, final String message){
        final EmailSender email1 = new EmailSender();
        Timer t = new Timer();
        t.scheduleAtFixedRate(
            new TimerTask(){
                public void run() {
                    email1.send(sender, receiver, subject, message);
                }
            },
            0,
            10000);
    }

}
