package com.example.pfi;

import com.example.pfi.Classes.Category;
import com.example.pfi.Classes.PanierArticle;

import java.util.ArrayList;

public class Client {
    private static Client Instance = null;

    // region Nom
    String username;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
    // endregion
    // region Panier
    public String getPassword(){
        return "Password";
    }
    private final PanierArticle panier = new PanierArticle();

    public static ArrayList<Category> categories;

    // endregion

    private Client(String username) {
        setUsername(username);
        categories = Category.loadCategories();
    }
    public Client(String username, String password){
        setUsername(username);

    }

    // region Static
    public static void setClient(String nom) {
        Instance = new Client(nom);
    }

    public static Client getInstance() {
        return Instance;
    }



    /**
     * @return Is the given password valid for this username.
     */
    public static boolean isPasswordValid(String username, String password){
        return password.equals("Password");
    }


    public static PanierArticle getPanier() { return getInstance().panier; }
    // endregion
}
