package test;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendingEmail{


    static String message;
    static final String CONFIGSET = "ConfigSet";
    static final int PORT = 465;
    static final String SUBJECT = "Test Report on Automation";

    public static void sendMail() throws Exception{
        String FROM = "naveenramesh17@yahoo.com";
        String FROMNAME = "Naveen Ramesh";
        String SMTP_USERNAME = "************";
        String SMTP_PASSWORD = "************";
        String TO = "naveenramesh17@yahoo.com";
        String HOST = "smtp.mail.yahoo.com";

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setText("Please find the attached test report");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = "D:\\D back up\\naveen\\Selenium\\AutomationProject\\email_Report.html";
        String fileName = "email_Report.html";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);

        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        Transport transport = session.getTransport();
        try
        {
            System.out.println("Sending Report via Email ...");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            transport.close();
        }
    }

}
