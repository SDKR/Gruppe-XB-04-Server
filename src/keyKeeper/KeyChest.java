package keyKeeper;

import java.util.ArrayList;
import java.util.Iterator;

import Encryption.encryption;

public class KeyChest {

	keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
	
	
	private String sqlUser;
	private String sqlPasswd;
	private String sqlUrl;
	private String encryptionKey;
	private String portNr;
	
	private ArrayList<String> decryptFile = new ArrayList<String>();
	
	public void keyImporter()
	{
		GK.getConfigInfo();
		sqlUser = GK.getCompleteFile().get(1);
		sqlPasswd = GK.getCompleteFile().get(2);
		sqlUrl = GK.getCompleteFile().get(0);
		encryptionKey = GK.getCompleteFile().get(3);
		portNr = GK.getCompleteFile().get(4);
		
	}
	public static void main (String []args)
	{
		encryption cryp = new encryption();
		keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
		
		GK.getConfigInfo();
		String stringToCryp = GK.getCompleteFile().get(1);
		System.out.println(stringToCryp);
		
		String emilCryp = cryp.xor_decrypt("emil", "458");
		System.out.println(emilCryp);
		System.out.println(cryp.xor_decrypt(emilCryp, "458"));
		
		
	}
	
	
//	public void kryptering(){
//	JEG HADER LOOPS!?!#"¤! 
//		public void decrypt(byte[] b)
	
		/*public void decrypt()
		{
//		Størrelse på arraylist (5)
		int aSize = GK.getCompleteFile().size();
		int ii = 0;
//		encryption key 
		Byte ff = (byte) 458;
		
		byte[] b = GK.getCompleteFile().get(1).getBytes();
		System.out.println(GK.getCompleteFile().get(1));
//		byte[] b = null;
		
//		while (ii < aSize){

//		Generates for loop containing decryption value
		for(int i = 0 ; i<b.length ; i++)
		{
			b[i] = (byte)(b[i]^ff);
		}
//		Generates new String without any white spaces following or leading
		String encrypted = new String(b).trim();
		System.out.println(encrypted);
		decryptFile.add(encrypted);
//		Returns decrypted String
//	}
      System.out.println("\n"+"Printer indholdet i arrayet:"+"\n");
      Iterator printlist = decryptFile.iterator();
      while (printlist.hasNext()) {
      System.out.println(printlist.next());
      }
	  }*/
//	}
		
	public ArrayList<String> getdecryptFile() {
		return decryptFile;
	}

		
//		GETTERS
	public String getSqlUser() {
		return sqlUser;
	}

	public String getSqlPasswd() {
		return sqlPasswd;
	}

	public String getSqlUrl() {
		return sqlUrl;
	}

	public String getEncryption() {
		return encryptionKey;
	}

	public String getportNr() {
		return portNr;
	}

	
}
