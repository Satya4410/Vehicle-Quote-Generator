package com.example.dxc.Service;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dxc.model.User;
import com.example.dxc.repo.UserRepository;

@Controller
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showForm(User user) {
        return "home";
    }

    @GetMapping("/about")
    public String showForm1(User user) {
        return "about";
    }

    @GetMapping("/form")
    public String showForm3(User user) {
        return "form";
    }

    @PostMapping("/calculate")
    public String calculateQuote(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }

        double vehiclePrice;
        if (user.getVehicleName().equals("Honda") && user.getVehicleModel().equals("Honda City")) {
            vehiclePrice = 500000;
        } else if (user.getVehicleName().equals("Honda") && user.getVehicleModel().equals("Honda Civic")) {
            vehiclePrice = 700000;

        } else if (user.getVehicleName().equals("Honda") && user.getVehicleModel().equals("Honda Amaz")) {
            vehiclePrice = 110000;

        } else if (user.getVehicleName().equals("Toyota") && user.getVehicleModel().equals("Corolla")) {
            vehiclePrice = 950000;
        } else if (user.getVehicleName().equals("Toyota") && user.getVehicleModel().equals("Toyota Yaris")) {
            vehiclePrice = 800000;
        } else if (user.getVehicleName().equals("Toyota") && user.getVehicleModel().equals("Toyota Fortuner")) {
            vehiclePrice = 4000000;
        }
        

   else if (user.getVehicleName().equals("BMW") && user.getVehicleModel().equals("X7")) {
        vehiclePrice = 12000000;
    } else if (user.getVehicleName().equals("BMW") && user.getVehicleModel().equals("XM")) {
        vehiclePrice = 26000000;
    } else if (user.getVehicleName().equals("BMW") && user.getVehicleModel().equals("7-Series")) {
        vehiclePrice = 17000000;
    }
    
        
        

    else if (user.getVehicleName().equals("Audi") && user.getVehicleModel().equals("RS5")) {
         vehiclePrice = 11300000;
     } else if (user.getVehicleName().equals("Audi") && user.getVehicleModel().equals("RS Q8")) {
         vehiclePrice = 22000000;
     } else if (user.getVehicleName().equals("Audi") && user.getVehicleModel().equals("Q7")) {
         vehiclePrice = 8400000;
     }
     
        
        
        
        
       
        else {
            // Handle other vehicle name and model combinations
            vehiclePrice = 0; // Set a default value or throw an exception
        }

        double quote = quoteService.calculateQuote(vehiclePrice, user.getVehicleAge());

        // Display the popup message
        String popupMessage = "Dear " + user.getUserName() + ", for your vehicle name " + user.getVehicleName()
                + ", vehicle model- " + user.getVehicleModel() + ", the total quote is " + quote;

        user.setPopupMessage(popupMessage);

        // Save user data in the database
        userRepository.save(user);

        // Send popup message to the user's email
        sendEmail(user.getUserEmail(), popupMessage);

        model.addAttribute("popupMessage", popupMessage);
        return "result";
    }

    private void sendEmail(String userEmail, String message) {
        // Sender's email and password
        final String senderEmail = "dhakad7845@gmail.com";
        final String password = "lttlsshsnoufjxyr";

        // SMTP server information
        final String smtpServer = "smtp.gmail.com";
        final int smtpPort = 587;

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", smtpPort);

        // Create a Session object with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            // Create a new MimeMessage object
            Message mimeMessage = new MimeMessage(session);

            // Set the sender and recipient addresses
            mimeMessage.setFrom(new InternetAddress(senderEmail));

            if (userEmail != null) {
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            } else {
                // Handle the case when userEmail is null
                System.out.println("Failed to send email. User email is null.");
                return;
            }

            // Set the email subject and content
            mimeMessage.setSubject("Quote Details");
            mimeMessage.setText(message + "\n\nThanks & Regards,\n Vehicle Generator Team.");

            // Send the email
            Transport.send(mimeMessage);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error message: " + e.getMessage());
        }
    }

    @GetMapping("/extract-quote")
    public String showExtractQuoteForm(User user) {
        return "home";
    }

    @GetMapping("/quote")
    public ResponseEntity<String> getQuoteData(@RequestParam("email") String email) {
        List<User> users = userRepository.findAllByUserEmail(email);

        if (users.isEmpty()) {
            return ResponseEntity.ok("No matching data found.");
        } else {
            StringBuilder tableData = new StringBuilder();
            tableData.append("<table style=\"border-collapse: collapse; width: 100%;\">");
            tableData.append("<thead><tr>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px; \">User Name</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">User Email</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">Vehicle Name</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">Vehicle Model</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">Vehicle Coverage</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">Vehicle Type</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">Vehicle Age</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">Quote Generate Date</th>");
            tableData.append("<th style=\"border: 3px solid #ddd; padding: 8px;\">Popup Message</th>");
            tableData.append("</tr></thead><tbody>");

            for (User user : users) {
                tableData.append("<tr>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getUserName()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getUserEmail()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getVehicleName()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getVehicleModel()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getVehicleCoverage()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getVehicleType()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getVehicleAge()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getQuoteGenerateDate()).append("</td>");
                tableData.append("<td style=\"border: 2px solid #ddd; padding: 8px;\">").append(user.getPopupMessage()).append("</td>");
                tableData.append("</tr>");
            }

            tableData.append("</tbody></table>");

            StringBuilder response = new StringBuilder();
            response.append(tableData.toString());

            return ResponseEntity.ok(response.toString());
        }
    }
}
