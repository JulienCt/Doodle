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
	},
        "choiceList": [
                {"choice": { "chTitle": "Oui"}},
                {"choice": { "chTitle": "NON"}}
        ]
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
import Model.Responses;
import Model.service.LinkFacadeREST;
import Model.service.ChoiceFacadeREST;
import Model.service.UserFacadeREST;
import Model.Survey;
import Model.User;
import Utils.MailSender;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
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
    @EJB
    private ResponsesFacadeREST reponseFacade;

    public SurveyFacadeREST() {
        super(Survey.class);
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(SurveyContainer surveyContainer) throws MessagingException {
        Survey survey = surveyContainer.getSurvey();
        User user = surveyContainer.getUser();
        List<Choice> choiceList = surveyContainer.getChoiceList();
        
        Query qr = em.createNamedQuery("User.findByEmail");
        qr.setParameter("usEmail", user.getUsEmail());
        List<User> users = (List<User>) qr.getResultList();
        
        if(users.isEmpty())
        {
            CreateUser(user);
        }
        
        //Creation du sondage en base
        super.create(survey);
        
        //Création des liens associés au sondage
        String linkReadWrite = CreateLink(user.getUsEmail(), 1, TypeLien.ReadWrite.getTypeLien(), 0);
        String linkAdmin = CreateLink(user.getUsEmail(), 1, TypeLien.Admin.getTypeLien(), 0);
        String linkResult = CreateLink(user.getUsEmail(), 1, TypeLien.Result.getTypeLien(), 0);
        
        //Création des choix
        for(Choice choice: choiceList)
        {
            CreateChoice(choice.getChTitle(), survey);
        }
        
        //Formatage du mail et envoi
        String mailContent = FormatCreateMailContent(user, survey, linkReadWrite, linkAdmin, linkResult);
        sendMail(mailContent, user.getUsEmail());
    }
    
    private void sendMail(String mailContent, String mailReceiver) throws MessagingException
    {
        MailSender mailsender = new MailSender();
        mailsender.sendMessage(mailContent, mailReceiver);
    }
    
    private String FormatCreateMailContent(User user, Survey survey, String linkReadWrite, String linkAdmin, String linkResult) {
        //Envoie de l'email avec les liens
        String content = "Bonjour "+user.getUsName()+",\n\n" +
                "Bravo ! Vous venez de créer votre sondage Doodle\n" +
                "\""+survey.getSuTitle()+"\"\n\n" +
                "Voici le lien vers votre sondage :\n" +
                "      "+linkReadWrite+" \n" +
                "\n" +
                "Partagez ce lien avec tous ceux qui devraient voter. N'oubliez pas de\n" +
                "voter aussi.\n\n" +
                "Pour administrer le sondage vous pouvez vous rendre à l'adresse suivante : \n " +
                "      "+linkAdmin+"\n\n" +
                "Si vous voulez consulter les resultats associés à votre sondage c'est ici : \n " +
                "      "+linkResult+"\n\n" +
                "Pensez à conserver ce courriel au cas où vous souhaiteriez modifier le sondage ou consulter les résultats par la suite.";
        return content;
    }
    
    private String FormatVoteMailContent(String nameParticipant, Survey survey, String link) {
        //Envoie de l'email avec les liens
        String content = "Bonjour "+nameParticipant+",\n\n" +
                "Bravo ! Vous venez de répondre à un sondage Doodle\n" +
                "\""+survey.getSuTitle()+"\"\n\n" +
                "Voici le lien pour éditer votre réponse :\n" +
                "      "+link+" \n" +
                "\n" +
                "Pensez à conserver ce courriel au cas où vous souhaiteriez modifier votre réponse par la suite.";
        return content;
    }

    private void CreateUser(User user) {
        userFacade.create(user);
    }

    private void CreateChoice(String titre, Survey survey) {
        Choice choice = new Choice(titre, survey);
        choicefacade.create(choice);
    }

    private String CreateLink(String email, int idSurvey, int type, int idReponse) {
        String key = GetMD5(email, idSurvey, type);
        Link link = new Link(email, idSurvey, key, type, idReponse);
        linkFacade.create(link);
        return "http://doodle.com/survey/" + key;
    }
    
    private String GetMD5(String email, int idSurvey, int type)
    {
        String md5 = new String();
        String data = email+idSurvey+type;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(data.getBytes());
            byte[] messageDigestMD5 = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte bytes : messageDigestMD5) {
                stringBuffer.append(String.format("%02x", bytes & 0xff));
            }
            md5 = stringBuffer.toString();
        } 
        catch (NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return md5;
    }
    
    @GET
    @Path("getSurvey/{md5}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Survey findFromMd5(@PathParam("md5") String md5) {
        Query query = em.createNamedQuery("Link.findByLiKey");
        query.setParameter("liKey", md5);
        List<Link> links = (List<Link>)query.getResultList();
        
        Link link = links.get(0);
        Survey survey = super.find(link.getLiIdsurvey());
        
        return survey;
    }
    
    @GET
    @Path("addReponseToSurvey/{choiceId}/{participantName}/{participantMail}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addReponseToSurvey(@PathParam("choiceId") String choiceId, @PathParam("participantMail") String participantMail, @PathParam("participantName") String participantName ) throws MessagingException {
        Responses reponse = new Responses(participantName, Integer.parseInt(choiceId));
        reponseFacade.create(reponse);
        Choice choice = choicefacade.find(choiceId);
        Survey survey = super.find(choice.getSuIdsurvey());
        String linkVote = CreateLink(participantMail, 1, TypeLien.ReadWrite.getTypeLien(), 0);
        String content = FormatVoteMailContent(participantName, survey,linkVote);
        sendMail(content, participantMail);
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
