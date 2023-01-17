package main;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public abstract class Main {
	
	public static void main(String[] args) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

	    Path caminhoArquivo = Path.of("C:\\miguelrosa\\ProjetoJca\\oslusiadas.txt");
	    String passagemOriginal = Files.readString(caminhoArquivo);
	    
	    System.out.println("Texto original:\n"+passagemOriginal);

	    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey desKey = keygenerator.generateKey();
	    

        byte[] respostaCriptografada = criptografar(desKey, passagemOriginal);
        String respostaDecriptografada = decriptografar(desKey, respostaCriptografada);
                		
        System.out.println("Texto criptografado:\n"+String.valueOf(respostaCriptografada));
        System.out.println("Texto decriptografado:\n"+ String.valueOf(respostaDecriptografada));

	}

	public static byte[] criptografar(SecretKey desKey, String passagemOriginal) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		    	
		byte[] txtByte1 = passagemOriginal.getBytes();
		Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		desCipher.init(Cipher.ENCRYPT_MODE, desKey);
		byte[] textoCriptografado = desCipher.doFinal(txtByte1); 
		        
		return textoCriptografado;
	}
			
	public static String decriptografar(SecretKey desKey, byte[] cipher) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			
		Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		desCipher.init(Cipher.DECRYPT_MODE, desKey);
		String textoDecriptografado = new String(desCipher.doFinal(cipher), StandardCharsets.UTF_8);
		     
		return textoDecriptografado;
	}
			
} 	
	    