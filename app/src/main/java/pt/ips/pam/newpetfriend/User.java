package pt.ips.pam.newpetfriend;

public class User {
    public String email;
    public String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(){
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }


}
