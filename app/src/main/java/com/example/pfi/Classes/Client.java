package com.example.pfi.Classes;

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
