package app.connect.com.connect.Objetos;

/**
 * Created by aluno on 28/06/16.
 */
public class RedeSocial {

    private int id;
    private String link;
    private  int icone;
    private int perfil_id;

    public RedeSocial(){

    }
    public RedeSocial(int id, String link, int icone, int perfil_id) {
        this.id = id;
        this.link = link;
        this.icone = icone;
        this.perfil_id = perfil_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    public int getPerfilID() {
        return perfil_id;
    }

    public void setPerfilID(int perfil_id) {
        this.perfil_id = perfil_id;
    }
}
