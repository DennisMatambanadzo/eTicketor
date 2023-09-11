package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.exceptions.EmailFailureException;
//import online.epochsolutions.eticketor.models.TicketToken;
import online.epochsolutions.eticketor.models.user.VerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${email.from}")
    private String fromMessage;

    @Value("${app.frontend.url}")
    private String url;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private SimpleMailMessage makeMailMessage(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMessage);
        return simpleMailMessage;
    }

    public void sendVerificationEmail(VerificationToken verificationToken) throws EmailFailureException {
        SimpleMailMessage message = makeMailMessage();
        message.setTo(verificationToken.getUser().getEmail());
        message.setSubject("Verify your mail to activate your account,");
        message.setText("Please follow the link below to verify your email to activate your account.\n" +
                url + "/auth/verify?token=" + verificationToken.getToken());
        try{
            javaMailSender.send(message);
        }catch(MailException exception){
            throw new EmailFailureException();
        }
    }

//    public void sendTicketPurchaseEmail(TicketToken token) throws EmailFailureException{
//        SimpleMailMessage message = makeMailMessage();
//        message.setTo(token.getUser().getEmail());
//        message.setSubject("Your ticket purchase was successful,");
//        message.setText("Ticket details.....");
//        try{
//            javaMailSender.send(message);
//        }catch(MailException exception){
//            throw new EmailFailureException();
//        }
//    }
}
