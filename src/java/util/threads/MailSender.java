/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.threads;

import util.Mail;

/**
 *
 * @author yasithsandesh
 */
public class MailSender extends Thread {
    
    private String email;
    private String subject;
    private String content;

    public MailSender() {
    }
    
    
    
    public MailSender(String email, String subject, String content) {
        
        this.email = email;
        this.content = content;
        this.subject = subject;
        
    }
    
    @Override
    public void run() {
        Mail.sendMail(email, subject, 
                 "<!DOCTYPE html>"
                                    + "<html lang=\"en\">"
                                    + "<head>"
                                    + "    <meta charset=\"UTF-8\">"
                                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                                    + "    <title>MyFreshGoods Account Verification</title>"
                                    + "</head>"
                                    + "<body style=\"font-family: Arial, sans-serif; color: #333; line-height: 1.6;\">"
                                    + "    <div style=\"max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; border-radius: 10px;\">"
                                    + "        <h1 style=\"color:#6482AD; text-align: center;\">MyFreshGoods Account Verification</h1>"
                                    + "        <p style=\"text-align: center; font-size: 18px;\">"
                                    + "            Your verification code is:"
                                    + "        </p>"
                                    + "        <h1 style=\"color:#6482AD; text-align: center;\">" + this.content + "</h1>"
                                    + "        <p style=\"text-align: center; font-size: 16px;\">"
                                    + "            Please use this code to verify your account. If you did not request this verification, please ignore this email."
                                    + "        </p>"
                                    + "        <p style=\"text-align: center; font-size: 16px;\">Thank you for my fresh goods!</p>"
                                    + "    </div>"
                                    + "</body>"
                                    + "</html>"
                
                );
    }
    
}
