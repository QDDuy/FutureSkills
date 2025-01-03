package com.example.futureskills.configuation;

import com.example.futureskills.dto.request.IntrospectRequest;
import com.example.futureskills.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
public class CustomeJwtDecoder implements JwtDecoder {
    @Value("${jwtSignerKey}")
    private String jwtSecret;

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder=null;

    @Override
    public Jwt decode(String token) throws JwtException {
        try{
           var respone= authenticationService.introspect(IntrospectRequest.builder().token(token).build());
           if(!respone.isValid())
               throw new JwtException("invalid token");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec=new SecretKeySpec(jwtSecret.getBytes(),"HS512");
            nimbusJwtDecoder=NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();

        }
        return nimbusJwtDecoder.decode(token);
    }
}
