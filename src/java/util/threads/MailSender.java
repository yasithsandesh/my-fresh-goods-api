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
        Mail.sendMail(email, subject, content);
    }
    
}
