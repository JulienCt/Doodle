package Model;

import Model.Survey;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-12T11:12:35")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, Survey> surveyCollection;
    public static volatile SingularAttribute<User, String> usName;
    public static volatile SingularAttribute<User, Integer> usIduser;
    public static volatile SingularAttribute<User, String> usEmail;

}