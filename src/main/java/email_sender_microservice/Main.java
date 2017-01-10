package email_sender_microservice;

public class Main {
    
    public static void main(String[] args) {

        // Data for the email sending, it will be getting from tha DB
        String sender = "randomemail@gmail.com";
        String receiver = "pindurpandurok.codecool@gmail.com";
        String subject = "Some test subject";
        String headerUrl = "https://media.licdn.com/media/AAEAAQAAAAAAAAQLAAAAJDk5MDJhZTU1LTU1NTEtNDllYy1iMWJkLTM3ZTNkNjQyNTA1ZQ.png";
        String footerUrl = "https://esr-divparty.netdna-ssl.com/images/email-signature-examples-1/minimalist-email-signature-example-12.jpg";
        String color = "red";
        String text = "<h2>Cukor, só és mindenmijó</h2></br></br><p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p></br></br>";
        String message = "<body style='text-align: justify; color: " + color + "'><img src=" + headerUrl + " style='width: 100%'>" + text + "<img src=" + footerUrl + "></body>";


        ScheduledEmailController testEmail = new ScheduledEmailController();
        new Thread(() -> { testEmail.scheduleEmails(sender, receiver, subject, message); }).start();

    }
}
