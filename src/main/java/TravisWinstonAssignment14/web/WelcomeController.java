package TravisWinstonAssignment14.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import TravisWinstonAssignment14.repository.ChannelRepository;
import TravisWinstonAssignment14.repository.MessageRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class WelcomeController {
	
	private ChannelRepository channelRepo;
	private MessageRepository messageRepo;
	
	public WelcomeController() {
		this.channelRepo = new ChannelRepository();
		this.messageRepo = new MessageRepository();
	}
	
	//catch generic page and redirect to the welcome
	@GetMapping
    public String genericRedirect() {
        return "redirect:/welcome";
    }
	
    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        model.addAttribute("channels", channelRepo.getAllChannels());
        return "welcome";
    }
	
    @PostMapping("/setUser")
    @ResponseBody
    public String setUser(@RequestParam String name, HttpSession session) {
    	session.setAttribute("name", name);
        return name;
    }
    
    @GetMapping("/getUser")
    @ResponseBody
    public String getUser(HttpSession session) {
    	String name = (String) session.getAttribute("name");
    	return (name != null) ? name : "";
    }
    
    @PostMapping("/addChannel")
    @ResponseBody
    public String addChannel(@RequestParam String channelName) {
    	channelRepo.addChannel(channelName);
    	return channelName;
    }

    @GetMapping("/channels/{channelId}")
    public String channelPage(@PathVariable String channelId, Model model, HttpSession session) {
    	String name = (String) session.getAttribute("name");
    	if (name == null || name.trim().isEmpty()) {
    		System.out.println("Name Null");
    		System.out.println((String) session.getAttribute("name"));
            return "redirect:/welcome";
        }
        model.addAttribute("channel", channelRepo.getChannel(channelId));
        model.addAttribute("messages", messageRepo.getMessagesByChannel(channelId));
        return "channel";
    }
    
    @PostMapping("/channels/{channelId}/send")
    @ResponseBody
    public void sendMessage(@PathVariable String channelId, @RequestParam String name, @RequestParam String message) {
    	messageRepo.addMessage(channelId, "<b>" + name + "</b>: " + message);
    }

    @GetMapping("/channels/{channelId}/messages")
    @ResponseBody
    public List<String> getMessages(@PathVariable String channelId) {
        return messageRepo.getMessagesByChannel(channelId);
    }

}
