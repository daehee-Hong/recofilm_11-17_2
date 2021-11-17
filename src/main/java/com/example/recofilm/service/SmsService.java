package com.example.recofilm.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@RequiredArgsConstructor
@Service
public class SmsService {

    private String api_key = "NCSSMMPV0L6N8WZJ";
    private String api_secret = "ATGWUBX0JGGRFSCORP7A6B42AC1QQTVO";

    public void makeParams(String phoneNumber, String cerNum){
        Message message = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    //수신번호
        params.put("from", "01023346394");//발신번호
        params.put("type", "SMS");
        params.put("text", "recofilm 문자인증 : 인증번호는" + "["+cerNum+"]" + "입니다.");

        try {
            JSONObject obj = (JSONObject) message.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }


}
