package br.edu.ifpb.sistema_academico.services;

import br.edu.ifpb.sistema_academico.cdi.Transactional;
import br.edu.ifpb.sistema_academico.dao.UserDAO;
import br.edu.ifpb.sistema_academico.models.User;

import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class LoginService implements Serializable {

    @Inject
    private UserDAO userDAO;

    public User verificarCredenciais(String username, String senha) {
        String senhaHash = generateMD5Hash(senha).toUpperCase(Locale.ROOT);
        System.out.println(senhaHash);
        return userDAO.encontrarUsuario(username, senhaHash);
    }

    private String generateMD5Hash(String senha) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(senha.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();

            return DatatypeConverter.printHexBinary(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
