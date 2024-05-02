package net.yassine.nurseryapp.security.keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Keygenerator {


    public static KeyPair generateRsaKey() {

        KeyPair keyPair;
        try {
            // KeyPairGenerator KeyPairGenerator=KeyPairGenerator.getInstance("RSA");
            KeyPairGenerator keygene = KeyPairGenerator.getInstance("RSA");
            keygene.initialize(2048);
            keyPair = keygene.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return keyPair;

    }




}
