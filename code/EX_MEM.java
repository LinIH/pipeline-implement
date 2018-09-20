package ce3001.pipeline.s103502004;

public class EX_MEM {
	boolean in;
	int ALUout;
	int WriteData;
	int rt;
	int pc;
	String ControlSignal;
	String ALUop;
	
	EX_MEM(){
		in=false;
		ALUout=0;
		WriteData=0;
		rt=0;
		pc=0;
		ControlSignal="00000";
	}
	
	void CalculateMemoryAddress(ID_EX id_ex){
		in=id_ex.in;
		ControlSignal=id_ex.ControlSignal.substring(4);
		WriteData=id_ex.ReadData2;
		if(id_ex.ControlSignal.charAt(0)=='1'){
			rt=id_ex.rd;
		}
		else{
			rt=id_ex.rt;
		}
		ALUop=id_ex.ControlSignal.substring(1, 3);
		if(ALUop.equals("10")){							//r-type
			if(id_ex.func.equals("100000")){				//add
				ALUout=id_ex.ReadData1+id_ex.ReadData2;
			}
			else if(id_ex.func.equals("100010")){			//sub
				ALUout=id_ex.ReadData1-id_ex.ReadData2;
			}
			else if(id_ex.func.equals("100100")){			//and
				ALUout=id_ex.ReadData1&id_ex.ReadData2;
			}
			else if(id_ex.func.equals("100101")){			//or
				ALUout=id_ex.ReadData1|id_ex.ReadData2;
			}
		}
		else if(ALUop.equals("00")){						//lw,sw
			ALUout=id_ex.ReadData1+id_ex.sign_extend;
		}
		else if(ALUop.equals("01")){						//beq
			ALUout=id_ex.ReadData1-id_ex.ReadData2;
		}

		pc=id_ex.pc+id_ex.sign_extend<<2;
	}
	
	void clear(){
		in=true;
		ALUout=0;
		WriteData=0;
		rt=0;
		pc=0;
		ControlSignal="00000";
		System.out.println("exmem clear happen");
	}
	
	
	void print(){
		System.out.println("EX/MEM:");
		System.out.println ( String.format("%-10s\t%-5s", "ALUout",ALUout) );
		System.out.println ( String.format("%-10s\t%-5s", "WriteData",WriteData) );
		System.out.println ( String.format("%-10s\t%-5s", "Rt",rt) );
		System.out.println ( String.format("%-10s\t%-5s", "Control Signals",ControlSignal) );
		System.out.println();
		
	}
	
	
	
	

}
