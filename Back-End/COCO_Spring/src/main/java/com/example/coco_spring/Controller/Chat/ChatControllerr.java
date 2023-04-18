package com.example.coco_spring.Controller.Chat;


import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.websocketproject.ChatService;
import com.example.coco_spring.websocketproject.Chatroom;
import com.example.coco_spring.websocketproject.ChatroomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatControllerr {
    @Autowired
    ChatService cs ;
    @Autowired
    UserRepository ur ;

    @Autowired
    ChatroomRepo cr ;

    @GetMapping("/Chatroom/{Idsender}/{idreciver}")
    @ResponseBody
    public Chatroom chatfind(@PathVariable("Idsender") Long Idsender, @PathVariable("idreciver") Long idreciver) {
        return cs.findchat(Idsender, idreciver);
    }

	/*@PostMapping("/send/{idreciver}")
	@ResponseBody
	public void send(@RequestBody Message m,@PathVariable("idreciver") Long idreciver) {
	 cs.sendmessage(m, idreciver);
	}*/
//get
    @GetMapping ("/getc/{idreciver}")
    @ResponseBody
    public Chatroom getcon(@PathVariable("idreciver") Long idreciver) {
        return cs.getConv(idreciver);
    }

    @GetMapping("/ListUser")
    @ResponseBody
    public List<User> getListUser() {
        return ur.findAll();
    }

    @GetMapping("/allchat")
    @ResponseBody
    public List<Chatroom> getChat() {
        return cr.findAll();
    }

    @PostMapping("/color/{id}")
    @ResponseBody
    public void color(@PathVariable("id") Long id ,@RequestBody String c) {
        cs.changecolor(id, c);
    }

}
