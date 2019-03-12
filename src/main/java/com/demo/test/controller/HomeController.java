package com.demo.test.controller;

import com.demo.test.Application;
import com.demo.test.bean.KakaoUserInfo;
import com.demo.test.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "/")
    public String main() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLogin() {
        return "loginForm";
    }


    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public Object viewLoginVerify(@ModelAttribute() @Valid User user, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        //model.addAttribute("message", "Hello");
        // TODO : 폼 검증
        User db = new User();
        db.setId("hee_su");
        db.setPwd("111111");
        Map<String, Object> err = new HashMap<>();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for(int i=0; i < list.size(); i++) {
                ObjectError e = list.get(i);
                FieldError fe = (FieldError) e;
                err.put(fe.getField(), fe.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("errMsg", err);
        }
        if(db.getId().equals(user.getId()) && db.getPwd().equals(user.getPwd())){
            session.setAttribute("id", user.getId());
            redirectAttributes.addFlashAttribute("id", user.getId());
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout")
    public String viewLogout(HttpSession session) {
        //System.out.println(session.getAttribute("id"));
        session.invalidate();

//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + accessToken);
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        URI userAPIUri = new URI("https://kapi.kakao.com/v2/user/logout");
        return "redirect:/";
    }



    @GetMapping(value="/oauth/kakao")
    public String kakaoAccessToken(@RequestParam("code") String code, HttpSession session) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://kauth.kakao.com/oauth/token";
        MultiValueMap<String, String> urlParams = new LinkedMultiValueMap<>();
        urlParams.add("grant_type", "authorization_code");
        urlParams.add("client_id", "88335c81da72b820becceb1966c4c0d9");
        urlParams.add("redirect_uri", "http://localhost:8080/oauth/kakao");
        urlParams.add("code", code);

        Map<String, String> accessTokenResponse = restTemplate.postForObject(url, urlParams, Map.class);

        String accessToken = accessTokenResponse.get("access_token");

        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer "+accessToken);
        MultiValueMap<String, String> userInfoParams = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(userInfoParams, headers);
        url= "https://kapi.kakao.com/v2/user/me";
        //HttpEntity<String> request = new HttpEntity<String>(headers);
        Map<String, Object> userInfo = restTemplate.postForObject(url, entity, Map.class);
        Map<String, Object> properties = (Map<String, Object>)userInfo.get("properties");
        Map<String, Object> kakaoAccount = (Map<String, Object>)userInfo.get("kakao_account");


        session.setAttribute("id", properties.get("nickname"));
        session.setAttribute("email", kakaoAccount.get("email"));
       return "redirect:/";
    }
}
