package com.maloef.cdnd.helloworld.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;

@Service
@Slf4j
public class JwtVerifier {

    private PublicKey publicKey = readPublicKey();

    public void verify(String jwt) {
        JwtParser parser = Jwts.parser().setSigningKey(publicKey);
        Jws<Claims> jws = parser.parseClaimsJws(jwt);
        log.info("jws: {}", jws);
    }

    @SneakyThrows
    private PublicKey readPublicKey() {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream inputStream = getClass().getResourceAsStream("/auth0Certificate.pem");
        Certificate cert = cf.generateCertificate(inputStream);
        return cert.getPublicKey();
    }
}
