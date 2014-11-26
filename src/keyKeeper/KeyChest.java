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

	public void keyImporter() {
		GK.getConfigInfo();
		encryption cryp = new encryption();

		sqlUrl = GK.getCompleteFile().get(0);
		sqlUser = cryp.xor_decrypt(GK.getCompleteFile().get(1),"458k");
		sqlPasswd = cryp.xor_decrypt(GK.getCompleteFile().get(2),"458k");
		encryptionKey = cryp.xor_decrypt(GK.getCompleteFile().get(3),"458k");
		portNr = cryp.xor_decrypt(GK.getCompleteFile().get(4),"458");

		keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
		
//		GK.getConfigInfo();
//		String stringToCryp = GK.getCompleteFile().get(1);
//		System.out.println(stringToCryp);
		
//		String emilCryp = cryp.xor_decrypt("wak40336", "458");
//		System.out.println(emilCryp);
//		System.out.println(cryp.xor_decrypt(emilCryp, "458"));

		
//		System.out.println(GK.getCompleteFile().get(0));
//		System.out.println(GK.getCompleteFile().get(1));
//		System.out.println(GK.getCompleteFile().get(2));
//		System.out.println(GK.getCompleteFile().get(3));
//		System.out.println(GK.getCompleteFile().get(4));
		
//		System.out.println(sqlUrl);
//		System.out.println(sqlUser);
//		System.out.println(sqlPasswd);
//		System.out.println(encryptionKey);
//		System.out.println(portNr);

	}
//
//	public static void main(String[] args) {
//		encryption cryp = new encryption();
//		keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
//
//		GK.getConfigInfo();
//		String stringToCryp = GK.getCompleteFile().get(2);
//		System.out.println(stringToCryp);
//
//		String emilCryp = cryp.xor_decrypt("w]JG", "458k");
//		System.out.println(emilCryp);
		// System.out.println(cryp.xor_decrypt(emilCryp, "458"));
//		String em = cryp.xor_decrypt(emilCryp, "458");
//		System.out.println(em);
//
//	}

	public ArrayList<String> getdecryptFile() {
		return decryptFile;
	}

	// GETTERS
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
