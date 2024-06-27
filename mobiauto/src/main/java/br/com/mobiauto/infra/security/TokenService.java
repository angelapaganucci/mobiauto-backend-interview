package br.com.mobiauto.infra.security;

import br.com.mobiauto.domain.model.Usuario;
import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTSigner.Options;
import com.auth0.jwt.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${mobiauto.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            JWTSigner signer = new JWTSigner(secret);
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("issuer", "auth-api");
            claims.put("subject", usuario.getEmail());
            claims.put("expiresAt", getExpirationDate());
            Options options = new JWTSigner.Options().setAlgorithm(Algorithm.HS512);
            return signer.sign(claims, options);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    public String verificaToken(String token) {
        try {
            JWTVerifier verifier = new JWTVerifier(token);
            Map<String, Object> verify = verifier.verify(token);
            Object subject = verify.get("subject");
            return subject.toString();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido", e);
        }
    }


    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}