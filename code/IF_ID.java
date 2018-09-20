package ce3001.pipeline.s103502004;

public class IF_ID {
	pipeline pipe=new pipeline();
	boolean in;
	int pc;
	String instruction;
	
	IF_ID(){
		in=false;
		pc=4;
		instruction="00000000000000000000000000000000";
	}
	
	void fetch(){
		if(pc/4<=pipe.instructions.size()){
			instruction=pipe.instructions.get((pc/4)-1);
		}
		pc=pc+4;
		in=true;
		if(pc/4>pipe.instructions.size()+1){
			instruction="00000000000000000000000000000000";
			in=false;
		}
	}
	
	void clear(){
		in=true;
		pc=pc-4;
		instruction="00000000000000000000000000000000";
	}
	
	void print(){
		System.out.println("IF/ID:");
		System.out.println ( String.format("%-10s\t%-5s", "PC",pc-4) );
		System.out.println ( String.format("%-10s\t%-5s", "Instruction",instruction) );
		System.out.println();
	}

}
