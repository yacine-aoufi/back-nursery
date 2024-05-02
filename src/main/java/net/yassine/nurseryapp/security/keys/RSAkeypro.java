package net.yassine.nurseryapp.security.keys;

import lombok.Getter;
import lombok.Setter;
import net.yassine.nurseryapp.security.keys.Keygenerator;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Getter
@Setter
public class RSAkeypro {

    private RSAPublicKey publickey;
    private RSAPrivateKey privatekey;

    public RSAkeypro() {
        KeyPair pair = Keygenerator.generateRsaKey();
        this.publickey = (RSAPublicKey) pair.getPublic();
        this.privatekey = (RSAPrivateKey) pair.getPrivate();
    }

}