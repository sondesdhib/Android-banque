package model;

/**
 * Created by florian on 05/08/2015.
 */
public class Client {

    private int idClient;
    private String nom;
    private String prenom;
    private String login;
    private String password;

    public Client(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
        setLogin();
    }

    public Client(int idClient, String nom, String prenom) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        setLogin();

    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin() {

    this.login = nom.substring(0,1) + prenom.substring(0,1);

    }

    public void setLogin(String login){
        this.login = login;
    }

    @Override
    public String toString() {
        return "Nom : " + getNom() + " Prenom : " + getPrenom() + " Login :" + getLogin();
    }
}
