public class AdminAccount {
    private final String accountHolder = "Admin";
    private final String code = "1234";

    public boolean checkLogin(String name, String code){
        // Jag gjorde denna case-sensitive med flit eftersom det är en admin och man vill att det ska vara lite mer strikt.
        return name.equals(accountHolder) && code.equals(this.code);
    }
}
