import java.io.File;
/*
 * Author: LJ Antoniazzi
 * version : 1.1
 * Comments:
 *   This programs decrypts or encrypts a file from EAB .
 *   It can be run on her as run->configuration with parameters 
 *    d or e <input file> <output file>
 *    i.e decrypt encbck1.bck to encbck1_enc input parameters:
 *    d c:\\temp\\encbck1.bck c:\\temp\\encbck1_enc.bck 
 *    
 *    Run as an executable jar ( be exporting has executable jar to edf.jar) and line
 *    command is :
 *    > java -jar edf.jar d c:\\temp\\encbck1.bck c:\\temp\\encbck1_enc.bck
 */

public class Main {
	static String AES128_KEY = "eab is a good ap";
	
    public static void main(String args[]){  
    	CryptoUtils cu = new CryptoUtils();
    	File fin = new File(args[1]);
    	File fout = new File(args[2]);
    	try
    	{
    	   if ( args[0].equals("e")){
    		   cu.encrypt(AES128_KEY,fin,fout);
    	    } else if ( args[0].equals("d")){
    		    cu.decrypt(AES128_KEY, fin,fout);
    	         } 
    	          else {}
    	} catch (CryptoException e){}
    	
   // just confirm line command for checking below
    	for(int i=0;i<args.length;i++)  
    	    System.out.println(args[i]);  
    		

    }	
}
