/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Container;

import Model.Choice;
import Model.Link;
import Model.Survey;
import Model.User;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author jcatt
 */
public class SurveyContainer implements Serializable {
    private Survey              survey;
    private User                user;
    private Collection<Choice>  choiceList;
    
    public SurveyContainer()
    {
    }

    /**
     * @return the survey
     */
    public Survey getSurvey() {
        return survey;
    }

    /**
     * @param survey the survey to set
     */
    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the choiceList
     */
    public Collection<Choice> getChoiceList() {
        return choiceList;
    }

    /**
     * @param choiceList the choiceList to set
     */
    public void setChoiceList(Collection<Choice> choiceList) {
        this.choiceList = choiceList;
    }

}
