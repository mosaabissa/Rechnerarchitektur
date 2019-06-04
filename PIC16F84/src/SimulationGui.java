import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultHighlighter;
import javax.swing.JSpinner;

public class SimulationGui {

	private JFrame frame;
	private JTextArea registerTextArea;
	private JScrollPane scrollRegisterTextArea;
	private JScrollPane lstScroll;
	private JTextArea lst;
	private JScrollPane scrollPane;
	private JPanel panel;
	public JButton btnPin_1;
	public JButton btnPin_2;
	public JButton btnPin_3;
	public JButton btnPin_6;
	public JButton btnPin_7;
	public JButton btnPin_8;
	public JButton btnPin_9;
	public JButton btnPin_10;
	public JButton btnPin_11;
	public JButton btnPin_12;
	public JButton btnPin_13;
	public JButton btnPin_17;
	public JButton btnPin_18;
	private JPanel panel_1;
	private JFileChooser fileChooser;
	private JTabbedPane tabbedPane;
	private JScrollPane SFRScroll;
	private TextArea SFR;
	private JScrollPane GPRScroll;
	private TextArea GPR;
	private JScrollPane StackScroll;
	private TextArea Stack;
	private TextArea EEPROMText;
	private JTabbedPane stuff;
	private JScrollPane scrollPane_1;
	public JSpinner breakPoint;
	private JScrollPane scrollEEPROM;
	public DefaultHighlighter highlighter;
	public DefaultHighlighter.DefaultHighlightPainter painter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulationGui window = new SimulationGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimulationGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//setting up the file(change the disk name accordingly)
				
				File file = new File("F:\\\\TPicSim1.LST"); 
				file=fileChooser.getSelectedFile();
				Scanner sc = null;
				try {
					sc = new Scanner(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				int three=0x3800;
				int noOp=0b0000;
				//flags
				int z=0;
				int c=0;
				int dc=0;
				//register array
				int Register[]=new int[256];
				int EEPROM[]=new int[64];
				for(i=0;i<64;i++)
					EEPROM[i]=0;
				for(i=0;i<255;i++)
					Register[i]=0;
				//address for f commands
				int address=0;
				
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
				//pins
				//pin1
				
				btnPin_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[5]&0b100)==0)
							bsf(Register[5],2);
						else
							bcf(Register[5],2);
					}
				});
				//end pin1
				//pin2
				
				btnPin_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[5]&0b1000)==0)
							bsf(Register[5],3);
						else
							bcf(Register[5],3);
					}
				});
				//end pin2
				//pin3
				btnPin_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[5]&0b1000)==0)
							bsf(Register[5],3);
						else
							bcf(Register[5],3);
					}
				});
				//end pin3
				//pin6
				btnPin_6.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b1)==0)
							bsf(Register[6],0);
						else
							bcf(Register[6],0);
					}
				});
				//end pin6
				//pin7
				btnPin_7.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b10)==0)
							bsf(Register[6],1);
						else
							bcf(Register[6],1);
					}
				});
				//end pin7
				//pin8
				btnPin_8.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b100)==0)
							bsf(Register[6],2);
						else
							bcf(Register[6],2);
					}
				});
				//end pin8
				//pin9
				btnPin_6.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b1000)==0)
							bsf(Register[6],3);
						else
							bcf(Register[6],3);
					}
				});
				//end pin9
				//pin10
				btnPin_6.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b10000)==0)
							bsf(Register[6],4);
						else
							bcf(Register[6],4);
					}
				});
				//end pin10
				//pin11
				btnPin_11.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b100000)==0)
							bsf(Register[6],5);
						else
							bcf(Register[6],5);
					}
				});
				//end pin11
				//pin12
				btnPin_12.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b1000000)==0)
							bsf(Register[6],6);
						else
							bcf(Register[6],6);
					}
				});
				//end pin12
				//pin13
				btnPin_13.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[6]&0b10000000)==0)
							bsf(Register[6],7);
						else
							bcf(Register[6],7);
					}
				});
				//end pin13
				//pin17
				btnPin_17.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[5]&1)==0)
							bsf(Register[5],0);
						else
							bcf(Register[5],0);
					}
				});
				//end pin17
				//pin18
				btnPin_18.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if((Register[5]&0b10)==0)
							bsf(Register[5],1);
						else
							bcf(Register[5],1);
					}
				});
				//end pin18
				//end pins

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
				//timer variable
				int TMR0=0;
				//RB0 bit number 0 in PORTB (06h)
				int RB0=0;
				//RP0 5 bit in statues register
				int bank=0;
				int PCLATH=0;
				test="";
				for(i=0;i<line.length;i++)
				{
					if(line[i]!=null)
						test=test+line[i]+"\n";	
				}
				lst.setText(test);
				try {
				    breakPoint.commitEdit();
				} catch ( java.text.ParseException e ) {  }
				int value = (Integer) breakPoint.getValue();
				Register[0x81]=0xff;
				while(programCounter<value)
				{
					
					
					//GUI
					//highlight
					try
			        {
						highlighter.removeAllHighlights();
			            int start =  lst.getLineStartOffset(programCounter);
			            int end =    lst.getLineEndOffset(programCounter);
			            highlighter.addHighlight(start, end, painter );
			            
			        }
			        catch(Exception e)
			        {
			            System.out.println(e);
			        }
					//end highlight
					//registerTextArea.setText("test");
					//getRegisterTextArea().append("ur text");
					//registerTextArea.setText(Register.toString());
					String register="";
					for(int aa=0;aa<256;aa++)
						register=register+Integer.toString(aa)+"  "+Integer.toString(Register[aa])+"\n";
					
					registerTextArea.setText(register);
					//SFR
					register="";
					register=register+"0 Indirect add "+Integer.toString(Register[0])+"\n";
					register=register+"1 TMR0         "+Integer.toString(Register[1])+"\n";
					register=register+"2 PCL          "+Integer.toString(Register[2])+"\n";
					register=register+"3 Status       "+Integer.toString(Register[3])+"\n";
					register=register+"4 FSR          "+Integer.toString(Register[4])+"\n";
					register=register+"5 Port A       "+Integer.toString(Register[5])+"\n";
					register=register+"6 Port B       "+Integer.toString(Register[6])+"\n";
					register=register+"8 EEDATA       "+Integer.toString(Register[8])+"\n";
					register=register+"9 EEADR        "+Integer.toString(Register[9])+"\n";
					register=register+"A PCLATH       "+Integer.toString(Register[0xa])+"\n";
					register=register+"B INTCON       "+Integer.toString(Register[0xb])+"\n";
					register=register+"81 Option      "+Integer.toString(Register[0x81])+"\n";
					register=register+"85 TRISA       "+Integer.toString(Register[0x85])+"\n";
					register=register+"86 TRISB       "+Integer.toString(Register[0x86])+"\n";
					register=register+"88 EECON1      "+Integer.toString(Register[0x88])+"\n";
					register=register+"89 EECON2      "+Integer.toString(Register[0x89])+"\n";
					SFR.setText(register);
					//end SFR	
					//GPR
					register="";
					for(int aa=12;aa<80;aa++)
						register=register+Integer.toString(aa)+"  "+Integer.toString(Register[aa])+"\n";
					
					GPR.setText(register);
					//end GPR
					//stack
					register="";
					for(int aa=0;aa<j;aa++)
						register=register+Integer.toString(aa)+"  "+Integer.toString(adressStack[aa])+"\n";
					
					Stack.setText(register);
					//end stack
					//end GUI
					//program counter
					if((currentLine & three)!=8192 && (currentLine & four)!=10240 && (currentLine & four)!=0b11010000000000 &&currentLine!=0x0008)
						programCounter=Register[2]&0xff;
					//PCLATH=Register[0xa]&0b11111;
					//PCLATH=PCLATH<<8;
					//programCounter=programCounter|PCLATH;
					//end program counter
					bank=Register[3]&0b100000;
					//timer interrupt
					if(TMR0==255 && Register[1]==0)
					{
						//T0IF (bit 2 from INTCON) is set
						Register[0x0b]=bsf(Register[0x0b],2);
					}
					if(RB0==0 && (Register[1]&1)==1)
					{
						adressStack[j]=programCounter;
						j++;
						currentLine=Register[4];
					}
					else if(RB0==1 && (Register[6]&1)==0)
					{
						programCounter=adressStack[j-1];
						j--;
					}
					else
						currentLine=programLines[programCounter];
					TMR0=Register[1];
					RB0=Register[0x06]&1;
					//end of timer interrupt
					//EEPROM
					//write
					if((Register[0x88]&0b10)==0b10 && currentLine==0x3055 && programLines[programCounter+1]==0x0089 && programLines[programCounter+2]==0x30AA && programLines[programCounter+3]==0x0089)
					{
						Register[0x89]=0xaa;
						programCounter=programCounter+4;
						EEPROM[Register[0x9]&0b111111]=Register[0x8];
						Register[0x88]=bcf(Register[0x88],1);
						Register[0x88]=bsf(Register[0x88],4);
					}
					//end write
					//read
					if((Register[0x88]&0b1)==0b1)
					{
						Register[0x8]=EEPROM[Register[0x9]&0b111111];
						Register[0x88]=bcf(Register[0x88],0);
					}
					//end read
					//end of EEPROM
					
					//noop code check
					if (currentLine !=0) {
						//first three digits
						switch (currentLine & three) {
						//call
						case 8192:
							adressStack[j]=programCounter+1;
							j++;
							programCounter=currentLine & (~three);
							PCLATH=Register[0xa]&0b11000;
							PCLATH=PCLATH<<8;
							programCounter=programCounter|PCLATH;
							Register[2]=programCounter&0xff;
							System.out.println(w);
							if((Register[0x81]&0b100000)==0)
							{
							if(Register[1]==255)
								{
								Register[1]=0;
								Register[0x0b]=bsf(Register[0x0b],2);
								}
							else
								Register[1]++;
							}
							break;
						case 10240:
							//goto
							programCounter=currentLine & (~three);
							PCLATH=Register[0xa]&0b11000;
							PCLATH=PCLATH<<8;
							programCounter=programCounter|PCLATH;
							Register[2]=programCounter&0xff;
							//System.out.println(w);
							if((Register[0x81]&0b100000)==0)
							{
							if(Register[1]==255)
							{
								Register[1]=0;
								Register[0x0b]=bsf(Register[0x0b],2);
								}
							else
								Register[1]++;
							}
							break;

						default:
							//first four digits
							switch (currentLine & four) {
							case 12288:
								w=movlw(currentLine & (~four));
								Register[2]++;
								System.out.println(w);
								break;
								//returnlw
							case 0b11010000000000:
								w=currentLine & (~six);
								programCounter=adressStack[j-1];
								Register[2]=programCounter&0xff;
								Register[0xa]=programCounter&0x1f00;
								Register[0xa]=Register[0xa]>>8;
								PCLATH=Register[0xa]&0b11111;
								j--;
								System.out.println(w);
								if((Register[0x81]&0b100000)==0)
								{
								if(Register[1]==255)
								{
									Register[1]=0;
									Register[0x0b]=bsf(Register[0x0b],2);
									}
								else
									Register[1]++;
								}
								break;
							case 0x1400:
								//bsf
								int b = currentLine&0x0380;
								b=b>>7;

				address=currentLine&(~seven);
				if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
					address=address|0x80;
				
				Register[address]=bsf(Register[address],b);
				System.out.println(w);
				Register[2]++;
				break;
				case 0x1000:
					//bcf
					testLine = currentLine&0x0380;
					testLine=testLine>>7;
					address=currentLine&(~seven);
					if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
						address=address|0x80;
					Register[address]=bcf(Register[address],testLine);
					System.out.println(w);
					Register[2]++;
					break;

				case 0x1800:
					//btfsc
					//bank check
					address=currentLine&(~seven);
					if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
						address=address|0x80;
					
					testLine = currentLine&0x0380;
					testLine=testLine>>7;
					testLine2= Register[address];
					testLine2=testLine2>>testLine;
					if(testLine2%2==0)
						{
							Register[2]=Register[2]+2;
							if((Register[0x81]&0b100000)==0)
							{
							if(Register[1]==255)
							{
								Register[1]=0;
								Register[0x0b]=bsf(Register[0x0b],2);
								}
							else
								Register[1]++;
							}
						}
					else
						Register[2]++;

					System.out.println(w);

					break;

				case 0x1c00:
					//btfss
					//bank check
					address=currentLine&(~seven);
					if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
						address=address|0x80;
					
					testLine = currentLine&0x0380;
					testLine=testLine>>7;
					testLine2= Register[address];
					testLine2=testLine2>>testLine;
					if(testLine2%2==1)
						{
							Register[2]=Register[2]+2;
							if((Register[0x81]&0b100000)==0)
							{
							if(Register[1]==255)
							{
								Register[1]=0;
								Register[0x0b]=bsf(Register[0x0b],2);
								}
							else
								Register[1]++;
							}
						}
					else
						Register[2]++;

					System.out.println(w);

					break;

				default:
					//first five digits
					switch (currentLine & five) {
					case 15360:
						w=sublw(currentLine & (~five),w);
						//c flag
						if(((currentLine & (~five))-w)<0)
							Register[3]=bsf(Register[3],0);
						else
							Register[3]=bcf(Register[3],0);
						//end c flag
						//z flag
						if(w==0)
							Register[3]=bsf(Register[3],2);
						else
							Register[3]=bcf(Register[3],2);
						//end z flag
						Register[2]++;
						System.out.println(w);
						break;
					case 15872:
						w=addlw(currentLine & (~five),w);
						//c flag
						if(((currentLine & (~five))+w)>255)
							Register[3]=bsf(Register[3],0);
						else
							Register[3]=bcf(Register[3],0);
						//end c flag
						//z flag
						if(w==0)
							Register[3]=bsf(Register[3],2);
						else
							Register[3]=bcf(Register[3],2);
						//end z flag
						Register[2]++;
						System.out.println(w);
						break;
					default:
						//first six digits
						switch (currentLine & six) {
						case 14592:
							w=andlw(currentLine & (~six),w);
							//z flag
							if(w==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end z flag
							Register[2]++;
							System.out.println(w);
							break;
						case 14336:
							w=iorlw(currentLine & (~six),w);
							//z flag
							if(w==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end z flag
							Register[2]++;
							System.out.println(w);
							break;
						case 14848:
							w=xorlw(currentLine & (~six),w);
							//z flag
							if(w==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end z flag
							Register[2]++;
							System.out.println(w);
							break;
						case 1792:
							//addwf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							
							
							testLine=Register[address]+w;
							Register[3]=bcf(Register[3],0);
							if (testLine>255)
							{
								//c flag
								Register[3]=bsf(Register[3],0);
								//end c flag
								testLine=testLine-256;
							}
							//Z flag
							if(testLine==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end Z flag
							
							if((currentLine & (~six))>127)
							{
								Register[address]=testLine;
								if(address==2)
								{
									programCounter=Register[2]&0xff;
									PCLATH=Register[0xa]&0b11111;
									PCLATH=PCLATH<<8;
									programCounter=programCounter|PCLATH;
								}
							}
							else
								w=testLine;
							Register[2]++;

							System.out.println(w);
							break;
						case 1280:
							//andwf
							
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							//Z flag
							if ((Register[address]&w)==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end Z flag
							if((currentLine & (~six))>127)
								Register[address]=Register[address]&w;

							else
								w=Register[address]&w;
							Register[2]++;

							System.out.println(w);
							break;
						case 2304:
							//comf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							
							//Z flag
							if((~Register[address])==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end Z flag
							
							if((currentLine & (~six))>127)
								Register[address]=(~Register[address])&0xff;

							else
								w=(~Register[address])&0xff;
							Register[2]++;

							System.out.println(w);
							break;
						case 768:
							//decf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							//Z flag
							if(Register[address]-1==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end Z flag
							if(Register[address]-1>=0)
							{
								if((currentLine & (~six))>127)
									Register[address]=Register[address]-1;

								else
									w=Register[address]-1;
							}
							else if((currentLine & (~six))<=127)
								w=0xff;
							else
								Register[address]=0xff;
							Register[2]++;

							System.out.println(w);
							break;
						case 2560:
							//incf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							
							if (Register[currentLine
									& (~seven)]+1 <= 0xff) {
								Register[3]=bcf(Register[3],2);
								if ((currentLine & (~six)) > 127)
									Register[address] = Register[address] + 1;

								else
									w = Register[address] + 1;
							}else if((currentLine & (~six))<=127)
								{
								w=0;
								Register[3]=bsf(Register[3],2);
								}
							else
								{
								Register[address]=0;
								Register[3]=bsf(Register[3],2);
								}
							Register[2]++;

							System.out.println(w);
							break;
						case 2048:
							//movf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							//Z flag
							if(Register[address]==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end Z flag
							if((currentLine & (~six))>127)
								Register[address]=Register[address];

							else
								w=Register[address];
							Register[2]++;

							System.out.println(w);
							break;
						case 1024:
							//iorwf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							//Z flag
							if((Register[address] | w)==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//End Z flag
							if((currentLine & (~six))>127)
								Register[address]=Register[address] | w;

							else
								w=Register[address] | w;
							Register[2]++;

							System.out.println(w);
							break;
						case 512:
							 
								//subwf
								//TODO make sure normal subtraction is ok
								//bank check
									address=currentLine&(~seven);
									if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
										address=address|0x80;
									
								 testLine=Register[address] - w;
								 Register[3]=bcf(Register[3],0);
								 if(testLine<0)
								 {
									 Register[3]=bsf(Register[3],0);
									 testLine=testLine+256;
								 }
								 //Z flag
								 if(testLine==0)
									 Register[3]=bsf(Register[3],2);
								 else
									 Register[3]=bcf(Register[3],2);
								 //end Z flag
								if ((currentLine & (~six)) > 127)
									Register[currentLine
											& (~seven)] = testLine;

								else
									w = testLine;
							
							Register[2]++;

							System.out.println(w);
							break;
						case 3584:
							//swapf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;

							if((currentLine & (~six))>127)
								Register[address]=swapf(Register[address]);

							else
								w=swapf(Register[address]);
							Register[2]++;

							System.out.println(w);
							break;
						case 1536:
							//xorwf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							//Z flag
							if((Register[address]^w)==0)
								Register[3]=bsf(Register[3],2);
							else
								Register[3]=bcf(Register[3],2);
							//end Z flag
							if((currentLine & (~six))>127)
								Register[address]=Register[address]^w;

							else
								w=Register[address]^w;
							Register[2]++;

							System.out.println(w);
							break;
						case 3328:
							//rlf
							
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;

							if((currentLine & (~six))>127)
								//Register[address]=rrf(Register[address]);
								
								testLine=Register[3]&1;
							testLine2=Register[address];
							if(testLine2>127)
								Register[3]=bsf(Register[3],0);
								
							testLine2=testLine2<<1;
							testLine2=testLine2&0xff;

							if(testLine==1)
								testLine2=testLine2+1;


							if ((currentLine & (~six)) > 127)
								Register[currentLine
										& (~seven)] = testLine2;

							else
								w = testLine2;
							Register[2]++;

							System.out.println(w);
							break;
						case 3072:
							//rrf
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;

							if((currentLine & (~six))>127)
								//Register[address]=rrf(Register[address]);
								testLine=Register[3]&1;
							testLine2=Register[address];
							if(testLine2%2==1)
								Register[3]=bsf(Register[3],0);
							testLine2=testLine2>>1;

							if(testLine==1)
								testLine2=testLine2+0x80;


							if ((currentLine & (~six)) > 127)
								Register[currentLine
										& (~seven)] = testLine2;

							else
								w = testLine2;
							Register[2]++;

							System.out.println(w);
							break;
						case 2816:
							//decfsz
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							testLine=Register[currentLine
									& (~seven)];
							
							if(testLine-1<0)
								testLine=255;
							else
								testLine--;
							
							if (testLine!=0) 
								Register[2]++;
							else
								{
									Register[2]=Register[2]+2;
									if((Register[0x81]&0b100000)==0)
									{
									if(Register[1]==255)
									{
										Register[1]=0;
										Register[0x0b]=bsf(Register[0x0b],2);
										}
									else
										Register[1]++;
									}
								}
							
							
							
							



							if ((currentLine & (~six)) > 127)
								Register[currentLine
										& (~seven)] = testLine;

							else
								w = testLine;

							

							System.out.println(w);

							break;
						case 3840:
							//incfsz
							//bank check
							address=currentLine&(~seven);
							if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
								address=address|0x80;
							
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
								Register[2]++;
							else
								{
									Register[2]=Register[2]+2;
									if((Register[0x81]&0b100000)==0)
										{
										if(Register[1]==255)
										{
											Register[1]=0;
											Register[0x0b]=bsf(Register[0x0b],2);
											}
										else
											Register[1]++;
										}
								}

							System.out.println(w);

							break;
						default:
							switch (currentLine & seven) {
							//movwf
							case 128:
								//bank check
								address=currentLine&(~seven);
								if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
									address=address|0x80;
								
								Register[address]=w;
								Register[2]++;
								System.out.println(w);
								break;

							case 384:
								//clrf
								//bank check
								address=currentLine&(~seven);
								if(bank!=0 &&(address==1 || address==5 || address==6 || address==8 || address==9))
									address=address|0x80;
								Register[address]=0;
								//Z flag
									Register[3]=bsf(Register[3],2);
								//end Z flag
								Register[2]++;
								System.out.println(w);
								break;
							case 256:
								//clrw
								w=0;
								//Z flag
								Register[3]=bsf(Register[3],2);
								//end Z flag
								Register[2]++;
								System.out.println(w);
								break;
							default:
								switch (currentLine) {
								//return
								case 0x0008:
									programCounter=adressStack[j-1];
									Register[2]=programCounter&0xff;
									Register[0xa]=programCounter&0x1f00;
									Register[0xa]=Register[0xa]>>8;
									PCLATH=Register[0xa]&0b11111;
									j--;
									if((Register[0x81]&0b100000)==0)
									{
									if(Register[1]==255)
									{
										Register[1]=0;
										Register[0x0b]=bsf(Register[0x0b],2);
										}
									else
										Register[1]++;
									}
										
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
						if((Register[0x81]&0b100000)==0)
						{
						if(Register[1]==255)
						{
							Register[1]=0;
							Register[0x0b]=bsf(Register[0x0b],2);
							}
						else
							Register[1]++;
						}
					}else { Register[2]++;
					System.out.println(w);
					if((Register[0x81]&0b100000)==0)
					{
					if(Register[1]==255)
					{
						Register[1]=0;
						Register[0x0b]=bsf(Register[0x0b],2);
						}
					else
						Register[1]++;
					}
					}
				}
			}
		});
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		frame.getContentPane().add(btnPlay);
				
				scrollPane = new JScrollPane();
				frame.getContentPane().add(scrollPane);
				
				panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(new GridLayout(1, 0, 0, 0));
				
				btnPin_1 = new JButton("pin1");
				panel.add(btnPin_1);
				btnPin_2 = new JButton("pin2");
				panel.add(btnPin_2);
				btnPin_3 = new JButton("pin3");
				panel.add(btnPin_3);
				btnPin_6 = new JButton("pin6");
				panel.add(btnPin_6);
				btnPin_7 = new JButton("pin7");
				panel.add(btnPin_7);
				btnPin_8 = new JButton("pin8");
				panel.add(btnPin_8);
				btnPin_9 = new JButton("pin9");
				panel.add(btnPin_9);
				btnPin_10 = new JButton("pin10");
				panel.add(btnPin_10);
				btnPin_11 = new JButton("pin11");
				panel.add(btnPin_11);
				btnPin_12 = new JButton("pin12");
				panel.add(btnPin_12);
				btnPin_13 = new JButton("pin13");
				panel.add(btnPin_13);
				btnPin_17 = new JButton("pin17");
				panel.add(btnPin_17);
				btnPin_18 = new JButton("pin18");
				panel.add(btnPin_18);
				
				
				
				
				panel_1 = new JPanel();
				frame.getContentPane().add(panel_1);
				
				fileChooser = new JFileChooser();
				panel_1.add(fileChooser);
				
				tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				frame.getContentPane().add(tabbedPane);
				
				lstScroll = new JScrollPane();
				tabbedPane.addTab("LST", null, lstScroll, null);
				
				lst = new JTextArea();
				lst.setEditable(false);
				lst.setFont(new Font("Dialog", Font.PLAIN, 22));
				lst.setText("lst");
				lstScroll.setViewportView(lst);
				
				
				
				registerTextArea = new JTextArea();
				registerTextArea.setRows(256);
				registerTextArea.setText("assd");
				registerTextArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
				registerTextArea.setDisabledTextColor(Color.DARK_GRAY);
				registerTextArea.setEditable(false);
				//frame.getContentPane().add(registerTextArea, BorderLayout.WEST);
				registerTextArea.setColumns(10);
				scrollRegisterTextArea= new JScrollPane (registerTextArea, 
						   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				tabbedPane.addTab("Register", null, scrollRegisterTextArea, null);
				
				SFRScroll = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				tabbedPane.addTab("SFR", null, SFRScroll, null);
				
				SFR = new TextArea();
				SFR.setFont(new Font("Dialog", Font.PLAIN, 22));
				SFRScroll.setViewportView(SFR);
				
				GPRScroll = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				tabbedPane.addTab("GPR", null, GPRScroll, null);
				
				GPR = new TextArea();
				GPR.setFont(new Font("Dialog", Font.PLAIN, 22));
				GPRScroll.setViewportView(GPR);
				
				StackScroll = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				tabbedPane.addTab("Stack", null, StackScroll, null);
				
				Stack = new TextArea();
				Stack.setFont(new Font("Dialog", Font.PLAIN, 22));
				StackScroll.setViewportView(Stack);
				
				scrollEEPROM = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				tabbedPane.addTab("EEPROM", null, scrollEEPROM, null);
				
				EEPROMText = new TextArea();
				EEPROMText.setFont(new Font("Dialog", Font.PLAIN, 22));
				scrollEEPROM.setViewportView(EEPROMText);
				
				stuff = new JTabbedPane(JTabbedPane.TOP);
				frame.getContentPane().add(stuff);
				
				scrollPane_1 = new JScrollPane();
				stuff.addTab("Break point", null, scrollPane_1, null);
				
				breakPoint = new JSpinner();
				scrollPane_1.setViewportView(breakPoint);
				frame.setVisible (true);
				highlighter =  (DefaultHighlighter)lst.getHighlighter();
				painter = new DefaultHighlighter.DefaultHighlightPainter( Color.RED );
		        highlighter.setDrawsLayeredHighlights(false);
	}

	public JTextArea getRegisterTextArea()
	{
	     return registerTextArea;
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
		int testLine=i+w;
		if (i+w>255)
			testLine=testLine-256;
		
		return testLine;
		// TODO Auto-generated method stub

	}
	private static int sublw(int i, int w) {
		i=i& 0b11111111;
		 int testLine=i - w;
		 if(testLine<0)
			 testLine=testLine+256;
		return testLine;
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