/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicol Farias
 */
@Entity
@Table(name = "ACCOUNTMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accountmodel.findAll", query = "SELECT a FROM Accountmodel a")
    , @NamedQuery(name = "Accountmodel.findByAccountid", query = "SELECT a FROM Accountmodel a WHERE a.accountid = :accountid")
    , @NamedQuery(name = "Accountmodel.findByAccountname", query = "SELECT a FROM Accountmodel a WHERE a.accountname = :accountname")
    , @NamedQuery(name = "Accountmodel.findByAccountemail", query = "SELECT a FROM Accountmodel a WHERE a.accountemail = :accountemail")
    , @NamedQuery(name = "Accountmodel.findByIsmember", query = "SELECT a FROM Accountmodel a WHERE a.ismember = :ismember")
    , @NamedQuery(name = "Accountmodel.findByNameAndEmail", query = "SELECT a FROM Accountmodel a WHERE a.accountname = :accountname "
            + "and a.accountemail = :accountemail")
    , @NamedQuery(name= "Accountmodel.findByAccountEmailContaining", query = "SELECT a FROM Accountmodel a WHERE a.accountemail LIKE CONCAT('%', :word, '%')") //with help from https://stackoverflow.com/questions/44373846/using-select-query-with-like-operator-and-parameters-in-java
    })

public class Accountmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ACCOUNTID")
    private Integer accountid;
    @Basic(optional = false)
    @Column(name = "ACCOUNTNAME")
    private String accountname;
    @Basic(optional = false)
    @Column(name = "ACCOUNTEMAIL")
    private String accountemail;
    @Basic(optional = false)
    @Column(name = "ISMEMBER")
    private Boolean ismember;

    public Accountmodel() {
    }

    public Accountmodel(Integer accountid) {
        this.accountid = accountid;
    }

    public Accountmodel(Integer accountid, String accountname, String accountemail, Boolean ismember) {
        this.accountid = accountid;
        this.accountname = accountname;
        this.accountemail = accountemail;
        this.ismember = ismember;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountemail() {
        return accountemail;
    }

    public void setAccountemail(String accountemail) {
        this.accountemail = accountemail;
    }

    public Boolean getIsmember() {
        return ismember;
    }

    public void setIsmember(Boolean ismember) {
        this.ismember = ismember;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountid != null ? accountid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accountmodel)) {
            return false;
        }
        Accountmodel other = (Accountmodel) object;
        if ((this.accountid == null && other.accountid != null) || (this.accountid != null && !this.accountid.equals(other.accountid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Accountmodel[ accountid=" + accountid + " ]";
    }
    
}
