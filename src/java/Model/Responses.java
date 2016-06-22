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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcatt
 */
@Entity
@Table(name = "RESPONSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responses.findAll", query = "SELECT r FROM Responses r"),
    @NamedQuery(name = "Responses.findByReIdresponse", query = "SELECT r FROM Responses r WHERE r.reIdresponse = :reIdresponse"),
    @NamedQuery(name = "Responses.findByReAnswertype", query = "SELECT r FROM Responses r WHERE r.reAnswertype = :reAnswertype")})
public class Responses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RE_IDRESPONSE")
    private Integer reIdresponse;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "RE_NAMEPARTICIPANT")
    private String reNameparticipant;
    @Column(name = "RE_ANSWERTYPE")
    private Integer reAnswertype;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "RE_ANSWERLINK")
    private String reAnswerlink;
    @JoinColumn(name = "CH_IDCHOICE", referencedColumnName = "CH_IDCHOICE")
    @ManyToOne
    private Choice chIdchoice;

    public Responses() {
    }
    
    public Responses(String nameParticipant, Integer idReponse) {
        reIdresponse = idReponse;
        reNameparticipant = nameParticipant;
    }

    public Responses(Integer reIdresponse) {
        this.reIdresponse = reIdresponse;
    }

    public Integer getReIdresponse() {
        return reIdresponse;
    }

    public void setReIdresponse(Integer reIdresponse) {
        this.reIdresponse = reIdresponse;
    }

    public String getReNameparticipant() {
        return reNameparticipant;
    }

    public void setReNameparticipant(String reNameparticipant) {
        this.reNameparticipant = reNameparticipant;
    }

    public Integer getReAnswertype() {
        return reAnswertype;
    }

    public void setReAnswertype(Integer reAnswertype) {
        this.reAnswertype = reAnswertype;
    }

    public String getReAnswerlink() {
        return reAnswerlink;
    }

    public void setReAnswerlink(String reAnswerlink) {
        this.reAnswerlink = reAnswerlink;
    }

    public Choice getChIdchoice() {
        return chIdchoice;
    }

    public void setChIdchoice(Choice chIdchoice) {
        this.chIdchoice = chIdchoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reIdresponse != null ? reIdresponse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responses)) {
            return false;
        }
        Responses other = (Responses) object;
        if ((this.reIdresponse == null && other.reIdresponse != null) || (this.reIdresponse != null && !this.reIdresponse.equals(other.reIdresponse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Responses[ reIdresponse=" + reIdresponse + " ]";
    }
    
}
