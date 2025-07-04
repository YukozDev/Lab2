import java.io.*;
import java.net.*;
import java.util.ArrayList;


class Client {
	public static void main(String[] args) {
         
	Socket MyClient;
	BufferedInputStream input;
	BufferedOutputStream output;
    //int[][] board = new int[8][8];
	Board board = new Board();
	
	try {
		MyClient = new Socket("localhost", 8888);

	   	input    = new BufferedInputStream(MyClient.getInputStream());
		output   = new BufferedOutputStream(MyClient.getOutputStream());
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	   	while(1 == 1) {
			char cmd = 0;
		   	
            cmd = (char)input.read();
            System.out.println(cmd);

            // Debut de la partie en joueur blanc
            if(cmd == '1') {
                byte[] aBuffer = new byte[1024];
				
				int size = input.available();
				//System.out.println("size " + size);
				input.read(aBuffer,0,size);

				String s = new String(aBuffer).trim();
				System.out.println("VALEUR DE S - ROUGE : " + s);

				String[] boardValues;
				boardValues = s.split(" ");

                int x=0,y=0;
                for(int i=0; i<boardValues.length;i++) {
                    board.getBoard()[x][y] = Integer.parseInt(boardValues[i]);
                    x++;
                    if(x == 8){
                        x = 0;
                        y++;
                    }
                }

                System.out.println("Nouvelle partie! Vous jouer blanc, entrez votre premier coup : ");

                String move = null;
                move = console.readLine();

				board.updateBoard(move);

				output.write(move.getBytes(),0,move.length());
				output.flush();
            }

            // Debut de la partie en joueur Noir
            if(cmd == '2') {
                System.out.println("Nouvelle partie! Vous jouer noir, attendez le coup des blancs");
                byte[] aBuffer = new byte[1024];
				
				int size = input.available();
				//System.out.println("size " + size);
				input.read(aBuffer,0,size);
				
                String s = new String(aBuffer).trim();
                System.out.println("VALEUR DE S - NOIR : " + s);

                String[] boardValues;
                boardValues = s.split(" ");

                int x=0,y=0;

                for(int i=0; i<boardValues.length;i++){
                    board.getBoard()[x][y] = Integer.parseInt(boardValues[i]);
                    x++;
                    if(x == 8){
                        x = 0;
                        y++;
                    }
                }
            }


			// Le serveur demande le prochain coup
			// Le message contient aussi le dernier coup joue.
			if(cmd == '3') {
				CPUPlayer cpuPlayer = new CPUPlayer(PawnColor.BLACK);

				byte[] aBuffer = new byte[16];
						
				int size = input.available();
				System.out.println("size :" + size);

				input.read(aBuffer,0,size);
						
				String s = new String(aBuffer);
				System.out.println("Dernier coup :" + s);

				board.updateBoard(s);
				//board.getMove().getAllMoves(PawnColor.RED);

				System.out.println("Entrez votre coup : ");
				String move = cpuPlayer.getBestMove(board, 3);
				//move = console.readLine();

				board.updateBoard(move);

				output.write(move.getBytes(),0,move.length());
				output.flush();
			}

			// Le dernier coup est invalide
			if(cmd == '4') {
				System.out.println("Coup invalide, entrez un nouveau coup : ");

		       	String move = null;
				move = console.readLine();

				board.updateBoard(move);

				output.write(move.getBytes(),0,move.length());
				output.flush();
				
			}

            // La partie est terminée
			if(cmd == '5') {
				byte[] aBuffer = new byte[16];

				int size = input.available();
				input.read(aBuffer,0,size);

				String s = new String(aBuffer);
				System.out.println("Partie Terminé. Le dernier coup joué est: " + s);

				String move = null;
				move = console.readLine();

				board.updateBoard(move);

				output.write(move.getBytes(),0,move.length());
				output.flush();
					
			}
        }
	}
	catch (IOException e) {
   		System.out.println(e);
	}
	
    }
}
