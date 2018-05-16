/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.entities;

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
 * @author jiahao pan
 */
@Entity
@Table(name = "LOGINTEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logintest.findAll", query = "SELECT l FROM Logintest l")
    , @NamedQuery(name = "Logintest.findById", query = "SELECT l FROM Logintest l WHERE l.id = :id")
    , @NamedQuery(name = "Logintest.findByUsername", query = "SELECT l FROM Logintest l WHERE l.username = :username")
    , @NamedQuery(name = "Logintest.control", query = "SELECT l FROM Logintest l WHERE l.username = :username and l.password = :password")
    , @NamedQuery(name = "Logintest.findByPassword", query = "SELECT l FROM Logintest l WHERE l.password = :password")})
public class Logintest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;

    public Logintest() {
    }

    public Logintest(Long id) {
        this.id = id;
    }

    public Logintest(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logintest)) {
            return false;
        }
        Logintest other = (Logintest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "goOffer.entities.Logintest[ id=" + id + " ]";
    }
    
}
