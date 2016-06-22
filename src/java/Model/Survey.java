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
@Table(name = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Survey.findAll", query = "SELECT s FROM Survey s"),
    @NamedQuery(name = "Survey.findBySuIdsurvey", query = "SELECT s FROM Survey s WHERE s.suIdsurvey = :suIdsurvey"),
    @NamedQuery(name = "Survey.findBySuSurveytype", query = "SELECT s FROM Survey s WHERE s.suSurveytype = :suSurveytype"),
    @NamedQuery(name = "Survey.findBySuIsvoteeditable", query = "SELECT s FROM Survey s WHERE s.suIsvoteeditable = :suIsvoteeditable"),
    @NamedQuery(name = "Survey.findBySuEmailonparticipation", query = "SELECT s FROM Survey s WHERE s.suEmailonparticipation = :suEmailonparticipation"),
    @NamedQuery(name = "Survey.findBySuEmailoncomment", query = "SELECT s FROM Survey s WHERE s.suEmailoncomment = :suEmailoncomment"),
    @NamedQuery(name = "Survey.findBySuIsresultpublic", query = "SELECT s FROM Survey s WHERE s.suIsresultpublic = :suIsresultpublic")
})
public class Survey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SU_IDSURVEY")
    private Integer suIdsurvey;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "SU_TITLE")
    private String suTitle;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "SU_DESCRIPTION")
    private String suDescription;
    @Column(name = "SU_SURVEYTYPE")
    private Integer suSurveytype;
    @Column(name = "SU_ISVOTEEDITABLE")
    private Integer suIsvoteeditable;
    @Column(name = "SU_EMAILONPARTICIPATION")
    private Integer suEmailonparticipation;
    @Column(name = "SU_EMAILONCOMMENT")
    private Integer suEmailoncomment;
    @Column(name = "SU_ISRESULTPUBLIC")
    private Integer suIsresultpublic;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "SU_EXPIRATIONDATE")
    private String suExpirationdate;
    @OneToMany(mappedBy = "suIdsurvey")
    private Collection<Choice> choiceCollection;
    @JoinColumn(name = "US_IDUSER", referencedColumnName = "US_IDUSER")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User usIduser;

    public Survey() {
    }

    public Survey(Integer suIdsurvey) {
        this.suIdsurvey = suIdsurvey;
    }

    public Integer getSuIdsurvey() {
        return suIdsurvey;
    }

    public void setSuIdsurvey(Integer suIdsurvey) {
        this.suIdsurvey = suIdsurvey;
    }

    public String getSuTitle() {
        return suTitle;
    }

    public void setSuTitle(String suTitle) {
        this.suTitle = suTitle;
    }

    public String getSuDescription() {
        return suDescription;
    }

    public void setSuDescription(String suDescription) {
        this.suDescription = suDescription;
    }

    public Integer getSuSurveytype() {
        return suSurveytype;
    }

    public void setSuSurveytype(Integer suSurveytype) {
        this.suSurveytype = suSurveytype;
    }

    public Integer getSuIsvoteeditable() {
        return suIsvoteeditable;
    }

    public void setSuIsvoteeditable(Integer suIsvoteeditable) {
        this.suIsvoteeditable = suIsvoteeditable;
    }

    public Integer getSuEmailonparticipation() {
        return suEmailonparticipation;
    }

    public void setSuEmailonparticipation(Integer suEmailonparticipation) {
        this.suEmailonparticipation = suEmailonparticipation;
    }

    public Integer getSuEmailoncomment() {
        return suEmailoncomment;
    }

    public void setSuEmailoncomment(Integer suEmailoncomment) {
        this.suEmailoncomment = suEmailoncomment;
    }

    public Integer getSuIsresultpublic() {
        return suIsresultpublic;
    }

    public void setSuIsresultpublic(Integer suIsresultpublic) {
        this.suIsresultpublic = suIsresultpublic;
    }

    public String getSuExpirationdate() {
        return suExpirationdate;
    }

    public void setSuExpirationdate(String suExpirationdate) {
        this.suExpirationdate = suExpirationdate;
    }

    @XmlTransient
    public Collection<Choice> getChoiceCollection() {
        return choiceCollection;
    }

    public void setChoiceCollection(Collection<Choice> choiceCollection) {
        this.choiceCollection = choiceCollection;
    }

    public User getUsIduser() {
        return usIduser;
    }

    public void setUsIduser(User usIduser) {
        this.usIduser = usIduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suIdsurvey != null ? suIdsurvey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Survey)) {
            return false;
        }
        Survey other = (Survey) object;
        if ((this.suIdsurvey == null && other.suIdsurvey != null) || (this.suIdsurvey != null && !this.suIdsurvey.equals(other.suIdsurvey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Survey[ suIdsurvey=" + suIdsurvey + " ]";
    }
    
    public Choice getChoiceById(int id)
    {
        Choice choice = null;
        for(Choice c : choiceCollection)
        {
            if (c.getChIdchoice().equals(id)) {
                choice = c;
            }
        }
        return choice;
    }
    
}
