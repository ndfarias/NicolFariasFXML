/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Accountmodel;
import java.util.List;
import javax.persistence.Query;
import java.util.Scanner;

/**
 *
 * @author Nicol Farias
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    //the following code snippet is from the demo project
    // Database manager
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        manager = (EntityManager) Persistence.createEntityManagerFactory("NicolFariasFXMLPU").createEntityManager();
    }

    
    //the following code is from scene builder skeleton
    
    @FXML
    private Button buttonCreateAccount;

    @FXML
    private Button buttonReadAccounts;

    @FXML
    private Button buttonUpdateAccount;

    @FXML
    private Button buttonDeleteAccount;
    
    @FXML
    private Button buttonReadEmail;
    
    @FXML
    private Button buttonNameAndEmail;
    
    
    // The following code has been copied and modified from the demo project

    @FXML
    void createAccount(ActionEvent event) {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Enter Account ID:");
        int accountId = scn.nextInt();
        
        System.out.println("Enter Account Name:");
        String accountName = scn.nextLine();
        scn.next();
        
        System.out.println("Enter Account Email:");
        String accountEmail = scn.next();
        scn.next();
        
        System.out.println("Enter Whether or not the Account is a Member:");
        Boolean isMember = scn.nextBoolean();
        
        // create account instance
        Accountmodel account = new Accountmodel();
        
        account.setAccountid(accountId);
        account.setAccountname(accountName);
        account.setAccountemail(accountEmail);
        account.setIsmember(isMember);
        
        // save this account to the database        
        create(account);
    }

    @FXML
    void deleteAccount(ActionEvent event) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Account ID:");
        
        int accountId = scn.nextInt();
        
        Query q = manager.createNamedQuery("Accountmodel.findByAccountid");
        
        q.setParameter("accountid", accountId);
        Accountmodel a = (Accountmodel) q.getSingleResult();
        
        if (a != null) {
            System.out.println("We are deleting this Account: "+ a.toString());
            delete(a); 
        }
    }
    
    @FXML
    void readAccounts(ActionEvent event) {
        //read all
        List<Accountmodel> accounts = readAll();
        
    }

    @FXML
    void updateAccount(ActionEvent event) {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Enter Account ID:");
        int accountId = scn.nextInt();
        
        System.out.println("Enter Account Name:");
        String accountName = scn.nextLine();
        scn.next();
        
        System.out.println("Enter Account Email:");
        String accountEmail = scn.next();
        scn.next();
        
        System.out.println("Enter whether or not Account is a Member:");
        Boolean isMember = scn.nextBoolean();
        
        Accountmodel account = new Accountmodel();
        
        account.setAccountid(accountId);
        account.setAccountname(accountName);
        account.setAccountemail(accountEmail);
        account.setIsmember(isMember);
        
        // save account to database       
        update(account);
    }
    
    //new custom-named queries
    @FXML
    void readByNameAndEmail(ActionEvent e) {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Enter Account Name:");
        String accountName = scn.nextLine();
        //scn.next();
        
        System.out.println("Enter Account Email:");
        String accountEmail = scn.nextLine();
        //scn.next();
        
        List<Accountmodel> accounts = readByNameEmail(accountName, accountEmail);     
    }
    
    @FXML
    void readByEmailContaining (ActionEvent e) {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Enter word to search in Email:");
        String word = scn.next();
        
        List<Accountmodel> accounts = readByEmailContains(word);
        
    }
    
    //CRUD Operations based on demo project and modified for this instance
    
    public void create(Accountmodel account) {
        try {
            manager.getTransaction().begin();
            
            if (account.getAccountid() != null) {
                
                // create account
                manager.persist(account);
                
                manager.getTransaction().commit();
                
                System.out.println(account.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Accountmodel> readAll(){
        Query query = manager.createNamedQuery("Accountmodel.findAll");
        List<Accountmodel> accounts = query.getResultList();

        for (Accountmodel a : accounts) {
            System.out.println(a.getAccountid() + " " + a.getAccountname() + " " + a.getAccountemail() + " " + a.getIsmember());
        }
        
        return accounts;
    }
    
    // Update operation
    public void update(Accountmodel model) {
        try {

            Accountmodel existingAccount = manager.find(Accountmodel.class, model.getAccountid());

            if (existingAccount != null) {
                manager.getTransaction().begin();
                
                //update account
                existingAccount.setAccountname(model.getAccountname());
                existingAccount.setAccountemail(model.getAccountemail());
                existingAccount.setIsmember(model.getIsmember());
 
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Accountmodel account) {
        try {
            Accountmodel existingAccount = manager.find(Accountmodel.class, account.getAccountid());

            if (existingAccount != null) {
                
                manager.getTransaction().begin();
                
                //remove account
                manager.remove(existingAccount);
                
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Accountmodel> readByNameEmail (String n, String e) {
        Query q = manager.createNamedQuery("Accountmodel.findByNameAndEmail");
        
        q.setParameter("accountname", n);
        q.setParameter("accountemail", e);
        
        List<Accountmodel> accounts = q.getResultList();
        for (Accountmodel a : accounts) {
            System.out.println(a.getAccountid() + " " + a.getAccountname() + " " + a.getAccountemail() + " " + a.getIsmember());
        }
        return accounts;
    }
    
    public List<Accountmodel> readByEmailContains (String e) {
        Query q = manager.createNamedQuery("Accountmodel.findByAccountEmailContaining");
        
        q.setParameter("word", e);
        
        List <Accountmodel> accounts = q.getResultList();
        
        for (Accountmodel a : accounts) {
            System.out.println(a.getAccountid() + " " + a.getAccountname() + " " + a.getAccountemail() + " " + a.getIsmember());
        }
        return accounts;     
    }
}
