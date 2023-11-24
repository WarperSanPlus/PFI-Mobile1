package com.example.pfi.Classes;

public class Client {
    public static Client Instance = null;

    // region Nom
    String username;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
    // endregion

    private Client(String username) {
        setUsername(username);
    }

    // region Static
    public static void setClient(String nom) {
        Instance = new Client(nom);
    }

    /**
     * @return Is the given password valid for this username.
     */
    public static boolean isPasswordValid(String username, String password){
        return password.equals("Password");
    }
    // endregion
}
