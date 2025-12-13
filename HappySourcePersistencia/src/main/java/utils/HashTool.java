package utils;

public class HashTool {

    public static void main(String[] args) throws Exception {
        String pass = "Admin1234";
        System.out.println(PassManager.hashPassword(pass));
    }
}
