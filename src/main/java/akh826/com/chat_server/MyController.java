package akh826.com.chat_server;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import org.springframework.http.*;

// import org.springframework.boot.web.servlet.error.ErrorController;

@Controller

// public class MyController implements ErrorController {

public class MyController {
    @GetMapping("/")
    public String index(Model model, HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        model.addAttribute("ModelName", "Deepseek");
        return "index";
    }

    @GetMapping("/chat")
    public String chat(Model model, HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        model.addAttribute("ModelName", "Deepseek");
        return "chat";
    }

    @GetMapping("/aboutus")
    public String contactus(Model model, HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "aboutus";
    }

    @GetMapping("/settings")
    public String settings(Model model, HttpServletResponse response) {
        // Remove X-Frame-Options header to prevent Firefox blocking
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "settings";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }

    // raw json body example:
    // { "history":[ { "role": "user", "content": "hello" }]}
    @PostMapping("/postchat")
    @ResponseBody
    public Map<String, String> postChat(@RequestBody Map<String, Object> payload) {
        // post to deepseek
        String apiKey = (String) payload.get("deepseek_api_key");
        if (apiKey == null || apiKey.isEmpty()) {
            return Map.of("reply", "API key is not set. Please set the DeepSeek API Key in settings.");
        }

        String apiUrl = "https://api.deepseek.com/chat/completions";

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // Prepare request body
        Map<String, Object> requestPayload = Map.of(
                "model", "deepseek-chat",
                "messages", payload.get("history"),
                "max_tokens", 1000,
                "frequency_penalty", 0.0,
                "presence_penalty", 1.0,
                "temperature", 1.0);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestPayload,
                headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity,
                    String.class);

            // Parse JSON and extract the reply
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String reply = root.path("choices").get(0).path("message").path("content").asText();

            return Map.of("reply", reply);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("reply", "Error contacting DeepSeek API. " + e.getMessage());
        }
    }

    // @RequestMapping("/error")
    // public String handleError(HttpServletRequest request) {
    // Integer statusCode = (Integer)
    // request.getAttribute("jakarta.servlet.error.status_code");
    // if (statusCode != null && statusCode == 404) {
    // return "redirect:/chat";
    // }
    // // Optionally handle other errors
    // return "error";
    // }

}