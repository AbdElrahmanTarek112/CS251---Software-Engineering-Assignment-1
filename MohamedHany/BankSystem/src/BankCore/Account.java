package BankCore;

public class Account  {
    //public String name;
    //public String email;
    private String phone;
    private String nationalId;
    private String securityQuestion;
    private String securityAnswer;
    private Card card;
    //public String password;

    Account(String name, String email, String phone, String nationalId, String securityQuestion, String securityAnswer) {
        //this.name = name;
        //this.email = email;
        this.phone = phone;
        this.nationalId = nationalId;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;

        //this.password = password;
    }


}
