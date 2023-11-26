package com.example.pfi.Classes;

import com.example.pfi.R;

public class Client {
    private static Client Instance = null;

    // region Nom
    String username;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
    // endregion
    // region Panier

    private final PanierArticle panier = new PanierArticle();

    // endregion


    private Client(String username) {
        setUsername(username);

        Article a = new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix, 2);

        panier.addItem(a);
        panier.addItem(new Article(R.string.article_name_banane, R.string.article_banane_desc, "article_banane", R.string.article_banane_prix, 4));
        panier.addItem(new Article(R.string.article_name_carotte, R.string.article_carotte_desc, "article_carotte", R.string.article_carotte_prix, 6));
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
