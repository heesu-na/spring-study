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
    public String viewLogout(HttpSession session) throws URISyntaxException {
        //System.out.println(session.getAttribute("id"));
        session.invalidate();

//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + accessToken);
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> userAPIParams = new LinkedMultiValueMap<>();
//        HttpEntity<MultiValueMap<String, String>> userAPIRequest = new HttpEntity<MultiValueMap<String, String>>(userAPIParams, headers);
//        URI userAPIUri = new URI("https://kapi.kakao.com/v2/user/logout");
//        restTemplate.postForObject(userAPIUri, userAPIRequest, HashMap.class);
        return "redirect:/";
    }

    @GetMapping(value="/oauth/kakao")
    public String kakaoAccessToken(@RequestParam("code") String code, HttpSession session) throws Exception {
        Map<String, Object> accessTokenInfo = getAccessToken(code);
        String accessToken = (String) accessTokenInfo.get("access_token");
        KakaoUserInfo userInfo = getUserInfo(accessToken);
        KakaoUserInfo.Properties properties = userInfo.getProperties();
        KakaoUserInfo.KakaoAccount kakaoAccount = userInfo.getKakaoAccount();

        session.setAttribute("id", properties.getNickname());
        session.setAttribute("email", kakaoAccount.getEmail());
        return "redirect:/";
    }

    private KakaoUserInfo getUserInfo(String accessToken) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> userAPIParams = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> userAPIRequest = new HttpEntity<MultiValueMap<String, String>>(userAPIParams, headers);
        URI userAPIUri = new URI("https://kapi.kakao.com/v2/user/me");
        Map<String, Object> userInfo = restTemplate.postForObject(userAPIUri, userAPIRequest, Map.class);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE); // 네이밍 규칙 설정
        return modelMapper.map(userInfo, KakaoUserInfo.class);
    }

    private Map<String, Object> getAccessToken(String code) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "88335c81da72b820becceb1966c4c0d9");
        params.add("redirect_uri", "http://localhost:8080/oauth/kakao");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        URI uri = new URI("https://kauth.kakao.com/oauth/token");

        Map<String, Object> response = restTemplate.postForObject(uri, request, HashMap.class);

        return response;
    }

}
