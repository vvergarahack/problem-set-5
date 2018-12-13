import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */

public class Database {
	/*Scanner in = new Scanner(System.in);
	private static long genActNum = 100000001L;
	private long actNum;
	private double balance;
	private User user;
	*/
	private String path;
	private String[] acts;
	public Database(String path) throws FileNotFoundException, IOException{
		this.path = path;
		this.acts = getAllActs();
	}
	
	///////////////////////GET ALL ACCOUNTS//////////////////////////
	
	public String[] getAllActs() throws FileNotFoundException, IOException{
		int i = 0;
		String[] acts = new String[10];
		FileReader alt = null;
		InputStreamReader org = null;
		try {
			alt = new FileReader(System.getProperty("user.dir") + File.separator + path);
		}
		catch(FileNotFoundException e){
			org = new InputStreamReader(getClass().getResourceAsStream(path));
		}
		
		try(BufferedReader br = new BufferedReader(org!=null ? org : alt)) {
			String line;
			while((line = br.readLine())!=null) {
				if(i>= acts.length) {
					acts = Arrays.copyOf(acts,  acts.length + 10);
				}
				acts[i++] = line;
			}
		}
		return Arrays.copyOf(acts, i);
	}
	
	///////////////////////READ ACCOUNT//////////////////////////
	
	public String readAct (long actNum) {
		for(String act : acts) {
			if(act.startsWith(String.valueOf(actNum)) && act.endsWith("Y")) {
				return act;
			}
		}
		return null;
		//read
	}
	
	///////////////////////WRITE ACCOUNT//////////////////////////
	
	public void writeAct (BankAccount act, BankAccount dest) throws IOException {
		boolean newAct = true;
		for(int i = 0; i< acts.length; i++) {
			if(acts[i].startsWith(String.valueOf(act.getActNum()))){
				acts[i] = act.toString();
				newAct = false;
			}
			if(dest != null){
				if(acts[i].startsWith(String.valueOf(dest.getActNum()))) {
					acts[i]=act.toString();
				}
			}
		}
		if(newAct) {
			acts = Arrays.copyOf(acts, acts.length+1);
			acts[acts.length-1] = act.toString();
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+File.separator+path))) {
			for(String acct:acts) {
				bw.write(acct);
				bw.newLine();
			}
		}
	}
	
	///////////////////////MAX ACCOUNT NUM//////////////////////////
	
	public long getMaxAccountNumber() {
		long max = -1L;
		
		for (String account : acts) {
			long accountNumber = Long.parseLong(account.substring(0, 9));
			
			if (accountNumber > max) {
				max = accountNumber;
			}
		}
		
		return max;
	}
	
}
