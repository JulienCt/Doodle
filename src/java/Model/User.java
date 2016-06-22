/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jcatt
 */
@Entity
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUsIduser", query = "SELECT u FROM User u WHERE u.usIduser = :usIduser"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.usEmail = :usEmail")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "US_IDUSER")
    private Integer usIduser;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "US_EMAIL")
    private String usEmail;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "US_NAME")
    private String usName;
    @OneToMany(mappedBy = "usIduser", cascade = CascadeType.PERSIST)
    private Collection<Survey> surveyCollection;

    public User() {
    }
    
    public User(String nom, String email) {
        this.usEmail = email;
        this.usName = nom;
    }

    public User(Integer usIduser) {
        this.usIduser = usIduser;
    }

    public Integer getUsIduser() {
        return usIduser;
    }

    public void setUsIduser(Integer usIduser) {
        this.usIduser = usIduser;
    }

    public String getUsEmail() {
        return usEmail;
    }

    public void setUsEmail(String usEmail) {
        this.usEmail = usEmail;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    @XmlTransient
    public Collection<Survey> getSurveyCollection() {
        return surveyCollection;
    }

    public void setSurveyCollection(Collection<Survey> surveyCollection) {
        this.surveyCollection = surveyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usIduser != null ? usIduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.usIduser == null && other.usIduser != null) || (this.usIduser != null && !this.usIduser.equals(other.usIduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.User[ usIduser=" + usIduser + " ]";
    }
    
}
