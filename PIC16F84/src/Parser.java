import java.io.*;
import java.util.Scanner; 
public class Parser {

	//comments will be added later
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//setting up the file(change the disk name accordingly)
		File file = new File("E:\\\\RechnerArch\\\\PIC16F84\\\\src\\\\TPicSim101.LST"); 
		Scanner sc = new Scanner(file);
		String[] line=new String[1000];
		String empty="         ";
		String test;
		String index;
		int i=0;
		//bit masks
		int six=16128;
		int five=15872;
		int four=15360;
		int three=14336;
		int noOp=16287;
		while (sc.hasNextLine())  
		{
			//System.out.println(sc.nextLine());

			test=sc.nextLine();
			test=test.substring(0, 9);
			if(!test.equals(empty))
			{
				line[i]=test;
				i++;
			}  

		}

		int[] linesinINT=new int[1000];
		for(int j=0;j<i;j++)
		{
			index=line[j].substring(0, 4);
			int indexInInt=Integer.parseInt(index,16);
			linesinINT[indexInInt]=Integer.parseInt(line[j].substring(5, 9),16);
		}

		//int j=0; 
		//while(j<1000)
		//{
		//	if(linesinINT[j]!=0)
		//		System.out.println(linesinINT[j]);
		//	j++;
		//}
		int k=0;
		//adress stack index
		int j=0;
		int[] adressStack= {0,0,0,0,0,0,0,0};
		while(linesinINT[k]!=0)
		{
			//noop code check
			if ((linesinINT[k] & noOp)!=0) {
				//first three digits
				switch (linesinINT[k] & three) {
				//call
				case 8192:
					adressStack[j]=k+1;
					j++;
					k = linesinINT[linesinINT[k]& (~three)] ; break;
				case 10240:
					k=k& (~three);

				default:
					//first four digits
					switch (linesinINT[k] & four) {
					case 12288:
						movlw(linesinINT[k] & (~four));
						k++;
						break;

					default:
						//first five digits
						switch (linesinINT[k] & five) {
						case 15360:
							sublw(linesinINT[k] & (~five));
							k++; 
							break;
						case 15872:
							addlw(linesinINT[k] & (~five));
							k++;
							break;
						default:
							//first six digits
							switch (linesinINT[k] & six) {
							case 14592:
								andlw(linesinINT[k] & (~six));
								k++;
								break;
							case 14336:
								iorlw(linesinINT[k] & (~six));
								k++;
								break;
							case 14848:
								xorlw(linesinINT[k] & (~six));
								k++;
								break;
							default:
								break;

							}
							break;
						}
						break;
					}
					break;
				}
			}else k++;
		}


	}
	private static int xorlw(int i, int w) {
		return i^w;
		
	}
	private static int addlw(int i, int w) {
		i=i& 0b11111111;
		return i+w;
		// TODO Auto-generated method stub

	}
	private static int sublw(int i, int w) {
		i=i& 0b11111111;
		return i-w;
		// TODO Auto-generated method stub
	}
	private static int iorlw(int i, int w) {
		return i|w;		
		// TODO Your code goes here

	}
	public static int andlw(int i, int w) {
		return i&w;
		// TODO Your code goes here

	}
	public static int movlw(int i){
		i=i& 0b11111111;

		
		// TODO Your code goes here
		return i;
	}

}

//mosaabissa