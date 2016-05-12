/*{
	"survey": {
		"suTitle": "Je suis un sondage test",
		"suDescription": "Je ne suis qu'un test",
		"suSurveytype": "1",
		"suIsvoteeditable": "1",
		"suEmailonparticipation": "1",
		"suEmailoncomment": "1",
		"suIsresultpublic": "1",
		"suExpirationdate": "28-05-2016",
		"usIduser": "1"
	},
	"user": {
		"usName": "BIpBIP",
		"usEmail": "EMAIL@BIPBiP"
	}
}*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.service;

import Model.Choice;
import Model.Container.SurveyContainer;
import Model.Link;
import Model.Link.TypeLien;
import Model.service.LinkFacadeREST;
import Model.service.ChoiceFacadeREST;
import Model.service.UserFacadeREST;
import Model.Survey;
import Model.User;
import Utils.MailSender;
import java.util.Collection;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jcatt
 */
@Stateless
@Path("survey")
public class SurveyFacadeREST extends AbstractFacade<Survey> {
    
    @PersistenceContext(unitName = "DoodleWebServicePU")
    private EntityManager em;
    
    @EJB
    private UserFacadeREST userFacade;
    @EJB
    private ChoiceFacadeREST choicefacade;
    @EJB
    private LinkFacadeREST linkFacade;

    public SurveyFacadeREST() {
        super(Survey.class);
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(SurveyContainer surveyContainer) throws MessagingException {
        MailSender mailsender = new MailSender();
        mailsender.sendMessage();
        Survey survey = surveyContainer.getSurvey();
        User user = surveyContainer.getUser();
        Collection<Choice> choiceList = surveyContainer.getChoiceList();
        
        //Creation du sondage en base
        super.create(survey);
        
        //Création des liens associés au sondage
        CreateLink(user.getUsEmail(), 1, "MD5ReadWrite", TypeLien.ReadWrite.getTypeLien(), 0);
        CreateLink(user.getUsEmail(), 1, "MD5Admin", TypeLien.Admin.getTypeLien(), 0);
        CreateLink(user.getUsEmail(), 1, "MD5Result", TypeLien.Result.getTypeLien(), 0);
        
        //Création des choix
        /*for(Choice choice: choiceList)
        {
            CreateChoice(choice.getChTitle(), survey);
        }*/
        
        //Création de l'utilisateur si il n'éxiste pas
        if(true)
        {
            CreateUser(user);
        }
    }

    private void CreateUser(User user) {
        userFacade.create(user);
    }

    private void CreateChoice(String titre, Survey survey) {
        Choice choice = new Choice(titre, survey);
        choicefacade.create(choice);
    }

    private void CreateLink(String email, int idSurvey, String key, int type, int idReponse) {
        Link link = new Link(email, idSurvey, key, type, idReponse);
        linkFacade.create(link);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Survey entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Survey find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Survey> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Survey> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
