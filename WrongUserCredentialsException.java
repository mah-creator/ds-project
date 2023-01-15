public class WrongUserCredentialsException extends IllegalStateException {
    WrongUserCredentialsException(){
        super("Wrong Email or Password");
    }
}
    
