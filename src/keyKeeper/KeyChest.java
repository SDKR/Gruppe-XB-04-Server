package keyKeeper;

import java.util.ArrayList;

public class KeyChest {
	
	keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
	
	private String sqlUser;
	private String sqlPasswd;
	private String sqlUrl;
	private String encryptionKey;
	
	public void keyImporter()
	{
		GK.getConfigInfo();
		sqlUser = GK.getCompleteFile().get(1);
		sqlPasswd = GK.getCompleteFile().get(2);
		sqlUrl = GK.getCompleteFile().get(0);
		encryptionKey = GK.getCompleteFile().get(3);
	}

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

}
