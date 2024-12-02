/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

import es.albarregas.beans.Usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jesus
 */
public class Utilities {

    public static String comprobarCampos(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";
        while (parametros.hasMoreElements() && error.equals("n")) {
            String nombre = parametros.nextElement();
            if (request.getParameter(nombre).length() == 0) {
                error = "v";
            }
        }
        if(error.equals("n")) {
            if(!request.getParameter("password").equals(request.getParameter("password2"))) {
                error = "c";
            }
        }
        return error;
    }
    
    public static Usuario.Genero string2Genero(String sexo) {
        Usuario.Genero genero;
        switch (sexo) {
            case "H":
                genero = Usuario.Genero.Hombre;
                break;
            case "M":
                genero = Usuario.Genero.Mujer;
                break;
            default:
                genero = Usuario.Genero.Otro;
        }
        return genero;
    }
    
    public static String genero2String(Usuario.Genero genero) {
        String sexo = null;
        
        switch (genero) {
            case Hombre:
                sexo = "H";
                break;
            case Mujer:
                sexo = "M";
                break;
            default:
                sexo = "O";
                break;
        }
        return sexo;
    }
    
    /**
     * Obtiene el valor de utilizar la función MD5 de una cadena
     * @param input Cadena pasada para convertir a MD5
     * @return Cadena tras la conversión
     */
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
