//package com.example.Starbucks.OAuth;
//
//import com.example.Starbucks.enums.Authority;
//import com.example.Starbucks.users.model.User;
//import lombok.Builder;
//import lombok.Getter;
//import org.apache.catalina.Role;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Getter
//public class OAuthAttributes {
//    private Map<String, Object> attributes;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//    }
//
//    public static OAuthAttributes of(String socialName, String userNameAttributeName, Map<String, Object> attributes){
//        // 카카오
//        if("kakao".equals(socialName)){
//            return ofKakao("id", attributes);
//        }
//
//        return null;
//    }
//
//    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
//        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
//        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
//
//        return OAuthAttributes.builder()
//                .name((String) kakaoProfile.get("nickname"))
//                .email((String) kakaoAccount.get("email"))
//                .nameAttributeKey(userNameAttributeName)
//                .attributes(attributes)
//                .build();
//    }
//
//    public User toEntity(){
//        return User.builder()
//                .name(name)
//                .email(email)
//                .roles(Collections.singletonList(Authority.ROLE_USER.name()))
//                .build();
//    }
//}
