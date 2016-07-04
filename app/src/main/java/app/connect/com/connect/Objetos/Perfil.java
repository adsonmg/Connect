package app.connect.com.connect.Objetos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by adson on 7/2/2016.
 */
public class Perfil implements Serializable{

    private int id;
    private String nome;
    private String bio;
    private String email;
    private String telefone;
    private int tipo;
    private String facebook;
    private String instagram;
    private String snapchat;
    private String twitter;
    private String whatsApp;


    public Perfil() {
    }

    public Perfil(int id,
                  String nome,
                  String email,
                  String bio,
                  String telefone,
                  int tipo,
                  String facebook,
                  String instagram,
                  String snapchat,
                  String twitter,
                  String whatsApp) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.bio = bio;
        this.telefone = telefone;
        this.tipo = tipo;
        this.facebook = facebook;
        this.instagram = instagram;
        this.snapchat = snapchat;
        this.twitter = twitter;
        this.whatsApp = whatsApp;
    }

    public Perfil(String nome,
                  String email,
                  String bio,
                  String telefone,
                  int tipo,
                  String facebook,
                  String instagram,
                  String snapchat,
                  String twitter,
                  String whatsApp) {
        this.nome = nome;
        this.email = email;
        this.bio = bio;
        this.telefone = telefone;
        this.tipo = tipo;
        this.facebook = facebook;
        this.instagram = instagram;
        this.snapchat = snapchat;
        this.twitter = twitter;
        this.whatsApp = whatsApp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getSnapchat() {
        return snapchat;
    }

    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }
}
