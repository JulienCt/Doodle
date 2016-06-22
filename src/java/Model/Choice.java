/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "CHOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choice.findAll", query = "SELECT c FROM Choice c"),
    @NamedQuery(name = "Choice.findByChIdchoice", query = "SELECT c FROM Choice c WHERE c.chIdchoice = :chIdchoice")})
public class Choice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CH_IDCHOICE")
    private Integer chIdchoice;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "CH_TITLE")
    private String chTitle;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "CH_RESSOURCELINK")
    private String chRessourcelink;
    @Lob
    @Column(name = "CH_RESSOURCEIMAGE")
    private byte[] chRessourceimage;
    @JoinColumn(name = "SU_IDSURVEY", referencedColumnName = "SU_IDSURVEY")
    @ManyToOne
    private Survey suIdsurvey;
    @OneToMany(mappedBy = "chIdchoice")
    private Collection<Responses> responsesCollection;

    public Choice() {
    }
    
    public Choice(Integer chIdchoice) {
        this.chIdchoice = chIdchoice;
    }
    
    public Choice(String title, Survey survey) {
        this.chTitle = title;
        this.suIdsurvey = survey;
    }

    public Integer getChIdchoice() {
        return chIdchoice;
    }

    public void setChIdchoice(Integer chIdchoice) {
        this.chIdchoice = chIdchoice;
    }

    public String getChTitle() {
        return chTitle;
    }

    public void setChTitle(String chTitle) {
        this.chTitle = chTitle;
    }

    public String getChRessourcelink() {
        return chRessourcelink;
    }

    public void setChRessourcelink(String chRessourcelink) {
        this.chRessourcelink = chRessourcelink;
    }

    public byte[] getChRessourceimage() {
        return chRessourceimage;
    }

    public void setChRessourceimage(byte[] chRessourceimage) {
        this.chRessourceimage = chRessourceimage;
    }

    public Survey getSuIdsurvey() {
        return suIdsurvey;
    }

    public void setSuIdsurvey(Survey suIdsurvey) {
        this.suIdsurvey = suIdsurvey;
    }

    @XmlTransient
    public Collection<Responses> getResponsesCollection() {
        return responsesCollection;
    }

    public void setResponsesCollection(Collection<Responses> responsesCollection) {
        this.responsesCollection = responsesCollection;
    }
    
    public void addReponseToListReponse(Responses reponse)
    {
        responsesCollection.add(reponse);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chIdchoice != null ? chIdchoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choice)) {
            return false;
        }
        Choice other = (Choice) object;
        if ((this.chIdchoice == null && other.chIdchoice != null) || (this.chIdchoice != null && !this.chIdchoice.equals(other.chIdchoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Choice[ chIdchoice=" + chIdchoice + " ]";
    }
    
}
