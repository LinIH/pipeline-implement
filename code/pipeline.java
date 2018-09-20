package ce3001.pipeline.s103502004;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/***Implement Pipeline***/
public class pipeline {
	static ArrayList<String> instructions=new ArrayList<String>();
	int[] mem={5,5,6,8,8};
	int[] reg={0,8,7,6,3,9,5,2,7};

	static IF_ID if_id=null;
	static ID_EX id_ex=null;
	static EX_MEM ex_mem=null;
	static MEM_WB mem_wb=null;
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		if_id=new IF_ID();
		id_ex=new ID_EX();
		ex_mem=new EX_MEM();
		mem_wb=new MEM_WB();

		Scanner cin=new Scanner(new File("Lwhazard.txt"));
		
		FileInputStream instream=new FileInputStream("Lwhazard.txt");
		PrintStream outstream=new PrintStream(new FileOutputStream("loadResult.txt"));
		System.setIn(instream);
		System.setOut(outstream);
		Register regis=new Register();
		while(cin.hasNext()){
			instructions.add(cin.nextLine());
		}
		while(regis.ExecutePipeline()){
		}
	}

}





