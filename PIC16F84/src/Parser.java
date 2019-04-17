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

		int j=0; 
		while(j<1000)
		{
			if(linesinINT[j]!=0)
				System.out.println(linesinINT[j]);
			j++;
		}
		int k=0;
		while(linesinINT[k]!=0)
		{
			//first four digits
			switch(linesinINT[k] & 15360)
			{
			case 12288:
				movlw(linesinINT[k]&(~15360)); break;

			default:
				//first five digits
				switch (linesinINT[k] & 15872) {
				case 15360:
					sublw(linesinINT[k] & (~15872));
				default:
					//first six digits
					switch (linesinINT[k] & 16128) {
					case 14592:
						andlw(linesinINT[k] & (~16128));
						break;
					case 14336:
						iorlw(linesinINT[k] & (~16128));
						break;

					}
					break;
				}
				break;
			}
		}


	}
	private static int sublw(int i) {
		// TODO Auto-generated method stub
		return i;
		
	}
	private static int iorlw(int i) {
		// TODO Your code goes here
		return i;
	}
	public static int andlw(int i) {
		// TODO Your code goes here
		return i;

	}
	public static int movlw(int i)
	{
		// TODO Your code goes here
		return i;
	}

}

//mosaabissa