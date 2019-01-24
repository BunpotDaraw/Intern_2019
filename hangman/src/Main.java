import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

		static Scanner sc = new Scanner(System.in);
		Scanner reader;
		static char ans;
		
		int cat;
		int random;
		
		Data[] data = new Data[5];
		
		static int len;
		int randPosit;
		StringBuilder Q = new StringBuilder("");
		
		static int score;
		static int life;
		char[] wrongChar;
		char[] corChar;
		int widx;
		
		String wrongCh;
		static int corlen;
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main m = new Main();
		System.out.println("This is My HangMan! Game");
		System.out.println("_______________S_T_A_R_T_________________");
		System.out.println();
		boolean c = true;
		while(c)
		{
		try {
			m.PrepareData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		m.StartGame();
		
		while(true) {
		System.out.print("ANSWER:");
		ans = sc.next().charAt(0);
		ans = Character.toLowerCase(ans);
		m.Check();
		if(life == -1) {System.out.println("LOSE WITH SCORE: "+score);break;}
		if(corlen == len) {System.out.println("WIN WITH SCORE: "+score);break;}
		}
		System.out.println("___________________________________");
		System.out.println("Do you want to play again(Y or N)");
		System.out.println("ANSWER:");
		ans = sc.next().charAt(0);
		ans = Character.toLowerCase(ans);
		if(ans == 'n') {break;}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("_________________E_N_D_G_A_M_E________________");
		
	}

	public void PrepareData() throws FileNotFoundException {
		
		System.out.println("Select Category:");
		System.out.println("(1) Music");
		System.out.println("(2) Food");
		
		cat = sc.nextInt();
		
		if(cat == 1){reader = new Scanner(new File("cat/music.txt"));}
		if(cat == 2){reader = new Scanner(new File("cat/food.txt"));}
		
		int idx = 0;
		while(reader.hasNextLine())
		{
			data[idx] = new Data();
			data[idx].setWord(reader.next());
			data[idx].setHint(reader.next());
			idx++;
		}
		reader.close();
	}
	
	public void StartGame() {
				
			
				score = 0;
				life = 10;
				int widx = 0;
				wrongCh = "";
				corlen = 1;
				Q.setLength(0);
				
				
				random = (int)(Math.random()*5)+0;  
		
				System.out.println();
				System.out.println("Insert one char for answer(if you insert more than one the first char will be an answer)");
				System.out.println();
				System.out.println("This is a Hint >>> "+data[random].getHint());
				
				
				len = data[random].getWord().length();
				randPosit = (int)(Math.random()*len)+0;
				
				wrongChar = new char[11];
				corChar = new char[len];
				
				corChar[randPosit] = data[random].getWord().charAt(randPosit);
				
				for(int i=0;i<len;i++)
				{
					if(i == randPosit)
					{
						Q.append(corChar[randPosit]);
						continue;
					}
					Q.append("_");
				}
				
				System.out.println(Q);
				
				
			
				
			
		
		
		
	}
	
	public void Check() {
		boolean c = false;
		for(int i = 0;i<len;i++)
		{
		
			if(data[random].getWord().charAt(i) == ans)
			{
				c = true;
				Correct(i);
			}
		}
		if(c == false) {
				Wrong();
			}
	}
	
	public void Correct(int p) {
		
		if(corChar[p] == 0) {
		score+=10;
		corlen++;
		corChar[p] = ans;
		}
		else {life--;System.out.println(); System.out.println("REPEAT!");}
		if(life>-1) {
		System.out.println("score "+score+" ,remaining wrong guess "+life+", wrong guessed: "+wrongCh);
		Q.setCharAt(p, data[random].getWord().charAt(p));
		System.out.println(Q);}
	}
	
	public void Wrong() {
		life--;
		score = score -5 ;
		boolean c = true;
		for(int i=0;i<=widx;i++)
		{
			if(ans == wrongChar[i])
			{
				c = false;
			}
		}
		if(c) 
		{	
			wrongChar[widx] = ans;
			widx++;
			wrongCh += ans+" ";
			}
		
		if(life>-1) {System.out.println("score "+score+" ,remaining wrong guess "+life+", wrong guessed: "+wrongCh);}
		System.out.println(Q);
		
	}
	
}

