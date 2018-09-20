package ce3001.pipeline.s103502004;

public class MEM_WB {
	boolean in;
	boolean branch;
	int ReadData;
	int ALUout;
	int rt;
	String ControlSignal;
	
	pipeline pipe=new pipeline();
	
	MEM_WB(){
		ReadData=0;
		ALUout=0;
		rt=0;
		in=false;
		branch=false;
		ControlSignal="00";
	}
	
	void ReadDataFromMem(EX_MEM ex_mem,IF_ID if_id){
		in=ex_mem.in;
		branch=false;
		ALUout=ex_mem.ALUout;
		rt=ex_mem.rt;
		ControlSignal=ex_mem.ControlSignal.substring(3);
		ReadData=0;
		if(ex_mem.ControlSignal.charAt(1)=='1'){	//lw
			ReadData=pipe.mem[ALUout/4];
		}
		else if(ex_mem.ControlSignal.charAt(2)=='1'){//sw
			pipe.mem[ALUout/4]=ex_mem.WriteData;
		}
		else if(ex_mem.ControlSignal.charAt(0)=='1'){//beq
			branch=true;
			if_id.pc=ex_mem.pc;
		}
	}
	
	void print(){
		System.out.println("MEM/WB:");
		System.out.println ( String.format("%-10s\t%-5s", "ReadData",ReadData) );
		System.out.println ( String.format("%-10s\t%-5s", "ALUout",ALUout) );
		System.out.println ( String.format("%-10s\t%-5s", "Control Signals",ControlSignal) );
		System.out.println();
	}

}
