package config;

import dao.*;
import controllerUnderService.IdeasRequestToDB;
import org.springframework.web.bind.annotation.*;

/**
 * Edited by Nikolia on 9.12.2016(at night)-10.12.2016.
 * More detailed in "json requests and responses" file (Google Drive)
**/

@RestController
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/getIdeasList4Auth", method = RequestMethod.GET)
    public IdeasListDAO getIdeasList(@RequestParam ("userId") int id){
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        IdeasListDAO obj = new IdeasListDAO();
        obj.setData(objIdeas.getIdeasListUsingID(id));
        return obj;
    }

    @RequestMapping(value = "/getIdeasList", method = RequestMethod.GET)
    public IdeasListDAO getIdeasList(){
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        IdeasListDAO obj = new IdeasListDAO();
        obj.setData(objIdeas.getIdeasList());
        return obj;
    }

    @RequestMapping(value = "/getIdea", method = RequestMethod.GET)
    public IdeaDAO getIdea(@RequestParam ("ideaId") int id) {
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        return objIdeas.getIdeaByID(id);
    }

    @RequestMapping(value = "/getIdea4Auth", method = RequestMethod.GET)
    public IdeaDAO getIdea(@RequestParam ("ideaId") int id, @RequestParam ("userId") int UserId) {
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        return objIdeas.getIdeaByID4Auth(id, UserId);
    }

    @RequestMapping(value = "/postIdea", method = RequestMethod.POST)
    public void postIdea(@RequestBody IdeaDAO idea) {
        /* ДАТУ И ВРЕМЯ НЕ ДОБАВЛЯЕТ (СКОРЕЕ ВСЕГО, НЕ ДЕСЕРИАЛИЗИРУЕТ)!
        ЮНИТ ТЕСТЫ ПРОХОДИТ, ВСЁ ДОБАВЛЯЕТ git config ЧЕРЕЗ ТЕСТЫ, НО НЕ ЧЕРЕЗ ПОСТМЭН */
        // Come on, fix it please!
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        objIdeas.InsertData(idea);
    }


    // NIKOLIA 13/12/2016: STRING, BECAUSE WE NEED TO SAY, IF IT REGISTERED
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public String registerUser(@RequestBody RegisterRequest auth){
        // Didn't test
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        return String.valueOf(objIdeas.Registration(auth));
    }


    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public LoginResponseObj loginUser(@RequestParam ("login") String login, @RequestParam ("password") String password) {
        // Unknown bug
        LoginRequest loginRequest = new LoginRequest(login,password);
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        return new IdeasRequestToDB().ReturnIDByLogin(loginRequest);
    }
}

