package infoRepository;

import java.util.HashMap;

class AuthService {
    // Static variable reference of single_instance
    // of type Singleton
    private static AuthService auth = null;
    private static HashMap<String, String> userPasses;
  
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private AuthService()
    {
    	userPasses = new HashMap<String, String>();
    }
  
    // Static method
    // Static method to create instance of Singleton class
    public static synchronized AuthService getInstance()
    {
        if (auth == null)
        	auth = new AuthService();
  
        return auth;
    }
}