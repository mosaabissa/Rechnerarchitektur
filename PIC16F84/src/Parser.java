import java.io.*;
import java.util.Scanner; 
public class Parser {

	//comments will be added later
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//setting up the file(change the disk name accordingly)
		File file = new File("F:\\\\TPicSim2.LST"); 
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
		int Register[]=new int[128];
		for(i=0;i<128;i++)
			Register[i]=0;
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
		//TODO implement flags
		while(0==0)
		{
			//noop code check
			if (programLines[programCounter] !=0) {
				//first three digits
				switch (programLines[programCounter] & three) {
				//call
				case 8192:
					adressStack[j]=programCounter+1;
					j++;
					programCounter=programLines[programCounter] & (~three);
					System.out.println(w);
					break;
				case 10240:
					programCounter=programCounter & (~three);
					//System.out.println(w);
					break;

				default:
					//first four digits
					switch (programLines[programCounter] & four) {
					case 12288:
						w=movlw(programLines[programCounter] & (~four));
						programCounter++;
						System.out.println(w);
						break;
						//returnlw
					case 0b11010000000000:
						w=programLines[programCounter] & (~six);
						programCounter=adressStack[j-1];
						j--;
						System.out.println(w);
						break;

					default:
						//first five digits
						switch (programLines[programCounter] & five) {
						case 15360:
							w=sublw(programLines[programCounter] & (~five),w);
							programCounter++;
							System.out.println(w);
							break;
						case 15872:
							w=addlw(programLines[programCounter] & (~five),w);
							programCounter++;
							System.out.println(w);
							break;
						default:
							//first six digits
							switch (programLines[programCounter] & six) {
							case 14592:
								w=andlw(programLines[programCounter] & (~six),w);
								programCounter++;
								System.out.println(w);
								break;
							case 14336:
								w=iorlw(programLines[programCounter] & (~six),w);
								programCounter++;
								System.out.println(w);
								break;
							case 14848:
								w=xorlw(programLines[programCounter] & (~six),w);
								programCounter++;
								System.out.println(w);
								break;
							case 1792:
								//addwf
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)]+w;

								else
									w=Register[programLines[programCounter] & (~seven)]+w;
								programCounter++;
								break;
							case 1280:
								//andwf
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)]&w;

								else
									w=Register[programLines[programCounter] & (~seven)]&w;
								programCounter++;
								break;
							case 2304:
								//comf
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=~Register[programLines[programCounter] & (~seven)];

								else
									w=~Register[programLines[programCounter] & (~seven)];
								programCounter++;
								break;
							case 768:
								//decf
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)]-1;

								else
									w=Register[programLines[programCounter] & (~seven)]-1;
								programCounter++;
								break;
							case 2560:
								//incf
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)]+1;

								else
									w=Register[programLines[programCounter] & (~seven)]+1;
								programCounter++;
								break;
							case 2048:
								//movf
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)];

								else
									w=Register[programLines[programCounter] & (~seven)];
								programCounter++;
								break;
							case 1024:
								//iorwf
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)] | w;

								else
									w=Register[programLines[programCounter] & (~seven)] | w;
								programCounter++;
								break;
							case 512:
								//subwf
								//TODO make sure normal subtraction is ok
								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)] - w;

								else
									w=Register[programLines[programCounter] & (~seven)] - w;
								programCounter++;
								break;
							case 3584:
								//swapf

								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=swapf(Register[programLines[programCounter] & (~seven)]);

								else
									w=swapf(Register[programLines[programCounter] & (~seven)]);
								programCounter++;
								break;
							case 1536:
								//xorwf

								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=Register[programLines[programCounter] & (~seven)]^w;

								else
									w=Register[programLines[programCounter] & (~seven)]^w;
								programCounter++;
								break;
							case 3328:
								//rlf

								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=rlf(Register[programLines[programCounter] & (~seven)]);

								else
									w=rlf(Register[programLines[programCounter] & (~seven)]);
								programCounter++;
								break;
							case 3072:
								//rrf

								if((programLines[programCounter] & (~six))>127)
									Register[programLines[programCounter] & (~seven)]=rrf(Register[programLines[programCounter] & (~seven)]);

								else
									w=rrf(Register[programLines[programCounter] & (~seven)]);
								programCounter++;
								break;
							case 2816:
								//decfsz
								
								if (Register[programLines[programCounter]
										& (~seven)]-1!=0) 
								programCounter++;
								else
									programCounter=programCounter+2;

								
									if ((programLines[programCounter] & (~six)) > 127)
										Register[programLines[programCounter]
												& (~seven)] = Register[programLines[programCounter] & (~seven)] - 1;

									else
										w = Register[programLines[programCounter] & (~seven)] - 1;
									
								break;
							case 3840:
								//incfsz
								
								if (Register[programLines[programCounter]
										& (~seven)]+1!=0) 
								programCounter++;
								else
									programCounter=programCounter+2;

								
									if ((programLines[programCounter] & (~six)) > 127)
										Register[programLines[programCounter]
												& (~seven)] = Register[programLines[programCounter] & (~seven)] + 1;

									else
										w = Register[programLines[programCounter] & (~seven)] + 1;
									
								break;
							default:
								switch (programLines[programCounter] & seven) {
								//movwf
								case 128:
									Register[programLines[programCounter] & (~seven)]=w;
									programCounter++;
									break;

								case 384:
									//clrf
									Register[programLines[programCounter] & (~seven)]=0;
									programCounter++;
									break;
								case 256:
									//clrw
									w=0;
									z=1;
									programCounter++;
									break;
								default:
									switch (programLines[programCounter]) {
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
	private static int rrf(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static int rlf(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static int swapf(int i) {
		// TODO Auto-generated method stub
		return 0;
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
