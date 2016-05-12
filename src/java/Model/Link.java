/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcatt
 */
@Entity
@Table(name = "LINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Link.findAll", query = "SELECT l FROM Link l"),
    @NamedQuery(name = "Link.findByLiIdlink", query = "SELECT l FROM Link l WHERE l.liIdlink = :liIdlink"),
    @NamedQuery(name = "Link.findByLiIdsurvey", query = "SELECT l FROM Link l WHERE l.liIdsurvey = :liIdsurvey"),
    @NamedQuery(name = "Link.findByLiIdresponse", query = "SELECT l FROM Link l WHERE l.liIdresponse = :liIdresponse"),
    @NamedQuery(name = "Link.findByLiType", query = "SELECT l FROM Link l WHERE l.liType = :liType"),
    @NamedQuery(name = "Link.findByLiKey", query = "SELECT l FROM Link l WHERE l.liKey = :liKey"),
    @NamedQuery(name = "Link.findByLiEmail", query = "SELECT l FROM Link l WHERE l.liEmail = :liEmail")})
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LI_IDLINK")
    private Integer liIdlink;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LI_IDSURVEY")
    private int liIdsurvey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LI_IDRESPONSE")
    private int liIdresponse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LI_TYPE")
    private int liType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 33)
    @Column(name = "LI_KEY")
    private String liKey;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "LI_EMAIL")
    private String liEmail;

    public Link() {
    }

    public Link(Integer liIdlink) {
        this.liIdlink = liIdlink;
    }
    
    public Link(String email, int idSurvey, String key, int type, int idReponse) {
        this.liEmail = email;
        this.liIdsurvey = idSurvey;
        this.liKey = key;
        this.liType = type;
        this.liIdresponse = idReponse;
    }
    
    public enum TypeLien
    {
        ReadWrite(1),
        Vote(2),
        Admin(3),
        Result(4);
        
        private int type;
        
        private TypeLien(int typ)
        {
            this.type = typ;
        }
        
        public int getTypeLien()
        {
            return type;
        }
        
    }

    public Link(Integer liIdlink, int liIdsurvey, int liIdresponse, int liType, String liKey, String liEmail) {
        this.liIdlink = liIdlink;
        this.liIdsurvey = liIdsurvey;
        this.liIdresponse = liIdresponse;
        this.liType = liType;
        this.liKey = liKey;
        this.liEmail = liEmail;
    }

    public Integer getLiIdlink() {
        return liIdlink;
    }

    public void setLiIdlink(Integer liIdlink) {
        this.liIdlink = liIdlink;
    }

    public int getLiIdsurvey() {
        return liIdsurvey;
    }

    public void setLiIdsurvey(int liIdsurvey) {
        this.liIdsurvey = liIdsurvey;
    }

    public int getLiIdresponse() {
        return liIdresponse;
    }

    public void setLiIdresponse(int liIdresponse) {
        this.liIdresponse = liIdresponse;
    }

    public int getLiType() {
        return liType;
    }

    public void setLiType(int liType) {
        this.liType = liType;
    }

    public String getLiKey() {
        return liKey;
    }

    public void setLiKey(String liKey) {
        this.liKey = liKey;
    }

    public String getLiEmail() {
        return liEmail;
    }

    public void setLiEmail(String liEmail) {
        this.liEmail = liEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liIdlink != null ? liIdlink.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.liIdlink == null && other.liIdlink != null) || (this.liIdlink != null && !this.liIdlink.equals(other.liIdlink))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Link[ liIdlink=" + liIdlink + " ]";
    }
    
}
