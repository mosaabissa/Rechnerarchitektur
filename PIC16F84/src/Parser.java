import java.io.*;
import java.util.Scanner; 
public class Parser {

	//comments will be added later
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//setting up the file(change the disk name accordingly)
		File file = new File("F:\\\\TPicSim4.LST"); 
		Scanner sc = new Scanner(file);
		String[] line=new String[1000];
		String empty="         ";
		String test;
		String index;
		int i=0;
		//bit masks
		int seven=16256;
		int six=16128;
		int five=15872;
		int four=15360;
		int three=14336;
		int noOp=0b0000;
		//flags
		int z=0;
		int c=0;
		int dc=0;
		//register array
		int Register[]=new int[255];
		int EEPROM[]=new int[64];
		for(i=0;i<64;i++)
			EEPROM[i]=0;
		for(i=0;i<255;i++)
			Register[i]=0;
		int registerBank=0;
		i=0;
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

		int[] programLines=new int[1000];
		for(int j=0;j<i;j++)
		{
			index=line[j].substring(0, 4);
			int indexInInt=Integer.parseInt(index,16);
			programLines[indexInInt]=Integer.parseInt(line[j].substring(5, 9),16);
		}

		//int j=0; 
		//while(j<1000)
		//{
		//	if(linesinINT[j]!=0)
		//		System.out.println(linesinINT[j]);
		//	j++;
		//}
		int programCounter=0;
		//address stack index
		int j=0;
		int w=0;
		int[] adressStack= new int[8];
		int testLine=0;
		int testLine2=0;
		//TODO implement flags
		int currentLine=programLines[programCounter];
		while(0==0)
		{
			currentLine=programLines[programCounter];
			//noop code check
			if (currentLine !=0) {
				//first three digits
				switch (currentLine & three) {
				//call
				case 8192:
					adressStack[j]=programCounter+1;
					j++;
					programCounter=currentLine & (~three);
					System.out.println(w);
					break;
				case 10240:
					programCounter=programCounter & (~three);
					//System.out.println(w);
					break;

				default:
					//first four digits
					switch (currentLine & four) {
					case 12288:
						w=movlw(currentLine & (~four));
						programCounter++;
						System.out.println(w);
						break;
						//returnlw
					case 0b11010000000000:
						w=currentLine & (~six);
						programCounter=adressStack[j-1];
						j--;
						System.out.println(w);
						break;
					case 0x1400:
						//bsf
						int b = currentLine&0x0380;
						b=b>>7;
		Register[currentLine&(~seven)]=bsf(Register[currentLine&(~seven)],b);
		System.out.println(w);
		programCounter++;
		break;
		case 0x1000:
			//bcf
			testLine = currentLine&0x0380;
			testLine=testLine>>7;
			Register[currentLine&(~seven)]=bcf(Register[currentLine&(~seven)],testLine);
			System.out.println(w);
			programCounter++;
			break;

		case 0x1800:
			//btfsc
			testLine = currentLine&0x0380;
			testLine=testLine>>7;
			testLine2= Register[currentLine&(~seven)];
			testLine2=testLine2>>testLine;
			if(testLine2%2==0)
				programCounter=programCounter+2;
			else
				programCounter++;

			System.out.println(w);

			break;

		case 0x1c00:
			//btfss
			testLine = currentLine&0x0380;
			testLine=testLine>>7;
			testLine2= Register[currentLine&(~seven)];
			testLine2=testLine2>>testLine;
			if(testLine2%2==1)
				programCounter=programCounter+2;
			else
				programCounter++;

			System.out.println(w);

			break;

		default:
			//first five digits
			switch (currentLine & five) {
			case 15360:
				w=sublw(currentLine & (~five),w);
				programCounter++;
				System.out.println(w);
				break;
			case 15872:
				w=addlw(currentLine & (~five),w);
				programCounter++;
				System.out.println(w);
				break;
			default:
				//first six digits
				switch (currentLine & six) {
				case 14592:
					w=andlw(currentLine & (~six),w);
					programCounter++;
					System.out.println(w);
					break;
				case 14336:
					w=iorlw(currentLine & (~six),w);
					programCounter++;
					System.out.println(w);
					break;
				case 14848:
					w=xorlw(currentLine & (~six),w);
					programCounter++;
					System.out.println(w);
					break;
				case 1792:
					//addwf
					testLine=Register[currentLine & (~seven)]+w;
					if (testLine>255)
						testLine=testLine-256;
					
					if((currentLine & (~six))>127)
						Register[currentLine & (~seven)]=testLine;

					else
						w=testLine;
					programCounter++;

					System.out.println(w);
					break;
				case 1280:
					//andwf
				
					if ((Register[currentLine & (~seven)]&w)==0)
						z=1;
					else
						z=0;
						
					if((currentLine & (~six))>127)
						Register[currentLine & (~seven)]=Register[currentLine & (~seven)]&w;

					else
						w=Register[currentLine & (~seven)]&w;
					programCounter++;

					System.out.println(w);
					break;
				case 2304:
					//comf
					if((currentLine & (~six))>127)
						Register[currentLine & (~seven)]=(~Register[currentLine & (~seven)])&0xff;

					else
						w=(~Register[currentLine & (~seven)])&0xff;
					programCounter++;

					System.out.println(w);
					break;
				case 768:
					//decf
					if(Register[currentLine & (~seven)]-1>=0)
					{
						if((currentLine & (~six))>127)
							Register[currentLine & (~seven)]=Register[currentLine & (~seven)]-1;

						else
							w=Register[currentLine & (~seven)]-1;
					}
					else if((currentLine & (~six))<=127)
						w=0xff;
					else
						Register[currentLine & (~seven)]=0xff;
					programCounter++;

					System.out.println(w);
					break;
				case 2560:
					//incf
					if (Register[currentLine
							& (~seven)]+1 <= 0xff) {

						if ((currentLine & (~six)) > 127)
							Register[currentLine
									& (~seven)] = Register[currentLine & (~seven)] + 1;

						else
							w = Register[currentLine & (~seven)] + 1;
					}else if((currentLine & (~six))<=127)
						w=0;
					else
						Register[currentLine & (~seven)]=0;
					programCounter++;

					System.out.println(w);
					break;
				case 2048:
					//movf
					if((currentLine & (~six))>127)
						Register[currentLine & (~seven)]=Register[currentLine & (~seven)];

					else
						w=Register[currentLine & (~seven)];
					programCounter++;

					System.out.println(w);
					break;
				case 1024:
					//iorwf
					if((currentLine & (~six))>127)
						Register[currentLine & (~seven)]=Register[currentLine & (~seven)] | w;

					else
						w=Register[currentLine & (~seven)] | w;
					programCounter++;

					System.out.println(w);
					break;
				case 512:
					 {
						//subwf
						//TODO make sure normal subtraction is ok
						
						 testLine=Register[currentLine & (~seven)] - w;
						 if(testLine<0)
							 testLine=testLine+256;
						 
						if ((currentLine & (~six)) > 127)
							Register[currentLine
									& (~seven)] = testLine;

						else
							w = testLine;
					}
					programCounter++;

					System.out.println(w);
					break;
				case 3584:
					//swapf

					if((currentLine & (~six))>127)
						Register[currentLine & (~seven)]=swapf(Register[currentLine & (~seven)]);

					else
						w=swapf(Register[currentLine & (~seven)]);
					programCounter++;

					System.out.println(w);
					break;
				case 1536:
					//xorwf

					if((currentLine & (~six))>127)
						Register[currentLine & (~seven)]=Register[currentLine & (~seven)]^w;

					else
						w=Register[currentLine & (~seven)]^w;
					programCounter++;

					System.out.println(w);
					break;
				case 3328:
					//rlf

					if((currentLine & (~six))>127)
						//Register[currentLine & (~seven)]=rrf(Register[currentLine & (~seven)]);
						testLine=c;
					testLine2=Register[currentLine & (~seven)];
					if(testLine2>127)
						c=1;
					testLine2=testLine2<<1;
					testLine2=testLine2&0xff;

					if(testLine==1)
						testLine2=testLine2+1;


					if ((currentLine & (~six)) > 127)
						Register[currentLine
								& (~seven)] = testLine2;

					else
						w = testLine2;
					programCounter++;

					System.out.println(w);
					break;
				case 3072:
					//rrf

					if((currentLine & (~six))>127)
						//Register[currentLine & (~seven)]=rrf(Register[currentLine & (~seven)]);
						testLine=c;
					testLine2=Register[currentLine & (~seven)];
					if(testLine2%2==1)
						c=1;
					testLine2=testLine2>>1;

					if(testLine==1)
						testLine2=testLine2+0x80;


					if ((currentLine & (~six)) > 127)
						Register[currentLine
								& (~seven)] = testLine2;

					else
						w = testLine2;
					programCounter++;

					System.out.println(w);
					break;
				case 2816:
					//decfsz
					testLine=Register[currentLine
							& (~seven)];
					
					if(testLine-1<0)
						testLine=255;
					else
						testLine--;
					
					if (testLine!=0) 
						programCounter++;
					else
						programCounter=programCounter+2;
					
					
					
					



					if ((currentLine & (~six)) > 127)
						Register[currentLine
								& (~seven)] = testLine;

					else
						w = testLine;

					

					System.out.println(w);

					break;
				case 3840:
					//incfsz
					testLine=Register[currentLine
							& (~seven)];
					if(testLine+1>255)
						testLine=0;
					else
						testLine++;




					if ((currentLine & (~six)) > 127)
						Register[currentLine
								& (~seven)] =testLine;

					else
						w = testLine;

					if (testLine!=0) 
						programCounter++;
					else
						programCounter=programCounter+2;

					System.out.println(w);

					break;
				default:
					switch (currentLine & seven) {
					//movwf
					case 128:
						Register[currentLine & (~seven)]=w;
						programCounter++;
						System.out.println(w);
						break;

					case 384:
						//clrf
						Register[currentLine & (~seven)]=0;
						programCounter++;
						System.out.println(w);
						break;
					case 256:
						//clrw
						w=0;
						z=1;
						programCounter++;
						System.out.println(w);
						break;
					default:
						switch (currentLine) {
						//return
						case 0x0008:
							programCounter = adressStack[j - 1];
							j--;
							System.out.println(w);
							break;
						default:
							break;
						}break;
					}
					break;

				}
				break;
			}
			break;
					}
					break;
				}
			}else { programCounter++;
			System.out.println(w);
			}
		}


	}
	//bsf(Register[currentLine&(~seven)],b)
	private static int bcf(int f, int b) {
		// TODO Auto-generated method stub
		f = f & ~(1 << b);
		return f;
	}
	private static int bsf(int f, int b) {
		// TODO Auto-generated method stub
		f = f | (1 << b); 
		return f;
	}

	private static int swapf(int i) {
		// TODO Auto-generated method stub
		int left=i&0xf0;
		int right=i&0x0f;
		right=right<<4;
		left=left>>4;

		return left+right;
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
