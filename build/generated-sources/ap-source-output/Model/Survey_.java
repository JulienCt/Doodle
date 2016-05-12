package Model;

import Model.Choice;
import Model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-12T15:31:45")
@StaticMetamodel(Survey.class)
public class Survey_ { 

    public static volatile SingularAttribute<Survey, Integer> suIsresultpublic;
    public static volatile SingularAttribute<Survey, String> suDescription;
    public static volatile SingularAttribute<Survey, Integer> suSurveytype;
    public static volatile SingularAttribute<Survey, String> suTitle;
    public static volatile SingularAttribute<Survey, Integer> suIsvoteeditable;
    public static volatile CollectionAttribute<Survey, Choice> choiceCollection;
    public static volatile SingularAttribute<Survey, String> suExpirationdate;
    public static volatile SingularAttribute<Survey, Integer> suIdsurvey;
    public static volatile SingularAttribute<Survey, Integer> suEmailonparticipation;
    public static volatile SingularAttribute<Survey, User> usIduser;
    public static volatile SingularAttribute<Survey, Integer> suEmailoncomment;

}