package ce3001.pipeline.s103502004;

public class Register {
	pipeline pipe=new pipeline();
	IF_ID if_id=new IF_ID();
	ID_EX id_ex=new ID_EX();
	EX_MEM ex_mem=new EX_MEM();
	MEM_WB mem_wb=new MEM_WB();
	int cycle=0;
	
	void WriteRegister(MEM_WB mem_wb){
		if(mem_wb.ControlSignal.charAt(0)=='1'){
			if(mem_wb.ControlSignal.charAt(1)=='0'){	//r-type
				pipe.reg[mem_wb.rt]=mem_wb.ALUout;
			}
			else{										//lw
				pipe.reg[mem_wb.rt]=mem_wb.ReadData;
			}
		}
	}
	
	void DataHazard(){
		if(ex_mem.ControlSignal.charAt(3)=='1'&&ex_mem.rt!=0){	//EX stage
			if(ex_mem.rt==id_ex.rs){							//source data hazard
				if(ex_mem.ControlSignal.charAt(4)=='0'){		//r-type	**else lw
					id_ex.ReadData1=ex_mem.ALUout;
				}
			}
			if(ex_mem.rt==id_ex.rt){							//target data hazard
				if(ex_mem.ControlSignal.charAt(4)=='0'){		//r-type	**else lw
					id_ex.ReadData2=ex_mem.ALUout;
				}
			}
				
		}
		if(mem_wb.ControlSignal.charAt(0)=='1'){				//MEM stage
			if(mem_wb.rt==id_ex.rs){							//source data hazard
				if(mem_wb.ControlSignal.charAt(1)=='0'){		//r-type
					id_ex.ReadData1=mem_wb.ReadData;
				}
				else{											//lw
					id_ex.ReadData1=mem_wb.ALUout;
				}
			}
			if(mem_wb.rt==id_ex.rt){							//source data hazard
				if(mem_wb.ControlSignal.charAt(1)=='0'){		//r-type
					id_ex.ReadData2=mem_wb.ReadData;
				}
				else{											//lw
					id_ex.ReadData2=mem_wb.ALUout;
				}
			}
		}
	}
	
	void LwHazard(){
		if((id_ex.ControlSignal.charAt(5)=='1')&&(id_ex.rt==Integer.parseInt(if_id.instruction.substring(6, 11), 2))||id_ex.rt==Integer.parseInt(if_id.instruction.substring(11, 16), 2)){
			if_id.in=true;
			if_id.pc=if_id.pc-4;
			if_id.instruction="00000000000000000000000000000000";
		}
	}
	
	void BranchHazard(){
		if(mem_wb.branch){
			if_id.clear();
			id_ex.clear();
			ex_mem.clear();
			
		}
	}
	
	boolean ExecutePipeline(){
		WriteRegister(mem_wb);
		mem_wb.ReadDataFromMem(ex_mem, if_id);
		ex_mem.CalculateMemoryAddress(id_ex);
		id_ex.Decode(if_id);
		if_id.fetch();
		DataHazard();
		//LwHazard();
		BranchHazard();
		cycle++;
		print();
		if(if_id.in||id_ex.in||ex_mem.in||mem_wb.in){
			return true;
		}
		else return false;
	}
	
	
	
	void print(){
		System.out.printf("CC %d:",cycle);
		System.out.println();
		System.out.println();
		System.out.println("Registers:");
		for(int i=0;i<9;i++){
			System.out.printf("$%d: %d  ", i,pipe.reg[i]);
			if(i==2||i==5||i==8){
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("Data memory:");
		for(int i=0;i<5;i++){
			System.out.printf("%02d:	%d",i*4,pipe.mem[i]);
			System.out.println();
		}
		System.out.println();
		if_id.print();
		id_ex.print();
		ex_mem.print();
		mem_wb.print();
		System.out.println("==========================");
		System.out.println();
	}
}
