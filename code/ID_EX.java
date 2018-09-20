package ce3001.pipeline.s103502004;

public class ID_EX {
	boolean in;
	int ReadData1;
	int ReadData2;
	int sign_extend;
	int rs;
	int rt;
	int rd;
	int pc;
	String func;
	String opcode;
	String ControlSignal;
	
	pipeline pipe=new pipeline();
	
	ID_EX(){
		in=false;
		ReadData1=0;
		ReadData2=0;
		sign_extend=0;
		rs=0;
		rt=0;
		rd=0;
		pc=0;
		ControlSignal="000000000";
	}

	void Decode(IF_ID if_id){
		in=if_id.in;
		opcode=if_id.instruction.substring(0, 6);
		func=if_id.instruction.substring(26);
		rs=Integer.parseInt(if_id.instruction.substring(6, 11),2);
		rt=Integer.parseInt(if_id.instruction.substring(11, 16),2);
		pc=if_id.pc;
		ReadData1=pipe.reg[rs];
		ReadData2=pipe.reg[rt];
		if(opcode.equals("000000")){						//r-type
			rd=Integer.parseInt(if_id.instruction.substring(16, 21),2);
			sign_extend=0;
			if(if_id.instruction=="00000000000000000000000000000000"){
				ControlSignal="000000000";
			}
			else{
				ControlSignal="110000010";
			}
		}
		else{
			rd=0;
			sign_extend=Integer.parseInt(if_id.instruction.substring(16),2);
			if(opcode.equals("100011")){
				ControlSignal="000101011";			//lw
			}
			else if(opcode.equals("101011")){
				ControlSignal="000100100";			//sw
			}
			else if(opcode.equals("000100")){
				ControlSignal="001010000";			//beq
			}
		}
	}
	
	void clear(){
		in=true;
		ReadData1=0;
		ReadData2=0;
		sign_extend=0;
		rs=0;
		rt=0;
		rd=0;
		pc=0;
		ControlSignal="000000000";
	}
	
	void print(){
		System.out.println("ID/EX:");
		System.out.println ( String.format("%-10s\t%-5s", "ReadData1",ReadData1) );
		System.out.println ( String.format("%-10s\t%-5s", "ReadData2",ReadData2) );
		System.out.println ( String.format("%-10s\t%-5s", "sign_ext",sign_extend) );
		System.out.println ( String.format("%-10s\t%-5s", "Rs",rs) );
		System.out.println ( String.format("%-10s\t%-5s", "Rt",rt) );
		System.out.println ( String.format("%-10s\t%-5s", "Rd",rd) );
		System.out.println ( String.format("%-10s\t%-5s", "Control Signals",ControlSignal) );
		System.out.println();
	}
	

}
