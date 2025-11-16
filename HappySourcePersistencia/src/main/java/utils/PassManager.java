package utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PassManager {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256"; 
    private static final int ITERATIONS = 10000; // Número de iteraciones
    private static final int KEY_LENGTH = 256; // Longitud de la clave en bits
    private static final int SALT_LENGTH = 16; // Longitud de la sal en bytes

    /**
     * Generar un salt de forma aleatoria para cada contraseña
     * @return arreglo de bytes con el salt generado automaticamente
     * @throws NoSuchAlgorithmException 
     */
    public static byte[] generarSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Genera un password hashed a partir de un password original y un salt aleatorio
     * @param password que se desea convertir a hash
     * @return password hashed
     * @throws NoSuchAlgorithmException si los password no cumplen con el algoritmo seleccionado
     * @throws InvalidKeySpecException si los password no se pueden comparar
     */
    public static String hashPassword(String password) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generarSalt();
        return hashPassword(password, salt);
    }
    
    /**
     * Genera un password hashed a partir de un password original y el salt proporcionado
     * @param password que se desea convertir a hash
     * @param salt arreglo de bytes con el salt a utilizar para generar el hash
     * @return password hashed
     * @throws NoSuchAlgorithmException si los password no cumplen con el algoritmo seleccionado
     * @throws InvalidKeySpecException si los password no se pueden comparar
     */
    public static String hashPassword(String password, byte[] salt) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        // Combinar la sal y el hash para almacenarlos juntos
        // Se codifican en Base64 para facilitar su almacenamiento como String
        return Base64.getEncoder().encodeToString(salt) + ":" + 
               Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Verifica una contraseñia comparandola con el hash almacenado
     * @param password que se desea evaluar
     * @param passwordHashed correcto que se usa como como comprobación
     * @return true si los passwords coinciden o false en caso contrario
     * @throws NoSuchAlgorithmException si los password no cumplen con el algoritmo seleccionado
     * @throws InvalidKeySpecException si los password no se pueden comparar
     */
    public static boolean verificarPassword(String password, String passwordHashed) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = passwordHashed.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de hash inválido");
        }
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        String originalHash = parts[1];
        String newHash = hashPassword(password, salt).split(":")[1];
        return newHash.equals(originalHash);
    }
}
