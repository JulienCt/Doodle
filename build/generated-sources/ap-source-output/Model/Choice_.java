package Model;

import Model.Responses;
import Model.Survey;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-12T11:12:35")
@StaticMetamodel(Choice.class)
public class Choice_ { 

    public static volatile SingularAttribute<Choice, String> chTitle;
    public static volatile CollectionAttribute<Choice, Responses> responsesCollection;
    public static volatile SingularAttribute<Choice, byte[]> chRessourceimage;
    public static volatile SingularAttribute<Choice, Integer> chIdchoice;
    public static volatile SingularAttribute<Choice, String> chRessourcelink;
    public static volatile SingularAttribute<Choice, Survey> suIdsurvey;

}