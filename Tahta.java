package ccs;

import java.util.*;

public class Tahta {
    
	// numbers between 1 and 5 will be kept in oyunTahtasi (gameBoard), according to these numbers, we will understand which characters are on a given tile
    // 1='1' 2='*' 3='-' 4='8' 5='a'
    static int[][] oyunTahtasi;
    static int satir;
    static int sutun;
    static int toplamPuan;
    static int maxHamle;
    static int hedefPuan;
    
    
	// constructor for tahta class
    public static void tahtayiOlustur(int sat, int sut){
        
		// set row and columns of the board equal to given values
        satir = sat;
        sutun = sut;
        
		// create oyunTahtasi array according to the row and column numbers
        oyunTahtasi = new int [satir][sutun];
        
		// put random numbers between 1 and 5 into each tile on oyunTahtasi
		// these numbers will show which character is on a given tile
        for(int i=0; i<satir; i++){
            
            for(int j=0; j<sutun; j++){
                
				// find a random number
                Random rand = new Random();
                // rastgele sayı ( 1 <= sayi < 6 ) şeklinde olacak
				// ( 1 <= random number < 6)
                int ilk = 1;
                int son = 6;
				// generate the random number that is needed
                int sayi = rand.nextInt(son-ilk) + ilk;
                
                
				// set the tile on game board to the random number
                oyunTahtasi[i][j] = sayi;
                
            }
            
        }
        
    }
    
    
	// function to check if user's move is valid or not
    public static boolean hamleGecerliMi(int x1, int y1, int x2, int y2){
        
		// if the given row and colum coordinates are in the game board, continue with further checking
        if(
            (-1<x1) && (x1<satir) &&
            (-1<y1) && (y1<sutun) &&
            (-1<x2) && (x2<satir) &&
            (-1<y2) && (y2<sutun)
            ){

			// first, make the given move
            hamleYap(x1,y1,x2,y2);

			// after the move, check if the candies on given coordinates have at least two neighboring candies that are the same kind as them
            if(etrafiKontrolEt(x1,y1) || etrafiKontrolEt(x2,y2)){

				// if the move is valid, return true
				// take back the move you just made (if this function returns true, we will make the actual move in main function)
                hamleYap(x1,y1,x2,y2);
                return true;

            }
            else{

				// if the move is invalid, return false
				// take back the move you just made (if this function returns true, we will make the actual move in main function)
                hamleYap(x1,y1,x2,y2);
                return false;

            }

        }

		// if the given coordinates are outside the game board, return false
        else
            return false;
    }
    
    
	// function to switch places of two candies
    public static void hamleYap(int x1, int y1, int x2, int y2){
        
		// take the first and second candies that you will switch places
        int ilkSeker = oyunTahtasi[x1][y1];
        int ikinciSeker = oyunTahtasi[x2][y2];
        
		// put these candies on their new places on oyunTahtasi
        oyunTahtasi[x1][y1] = ikinciSeker;
        oyunTahtasi[x2][y2] = ilkSeker;
        
    }
    
    
	
	// check if a given candy have a neighboring candy (that is the same kind) on its right, up, down or left side
    public static boolean etrafiKontrolEt(int x, int y){
        
		// flag will be increased by one each time the candy fails to have two neighbors of same kind on its sides
		// if the flag reaches 6, the function will return false
        int flag = 0;

        
		// if (x,y) has at least two candies on its left side, check if those two candies are equal to the one at (x,y)
        if(y > 1){

			// if the candies are equal, return true
            if(oyunTahtasi[x][y]==oyunTahtasi[x][y-1] && oyunTahtasi[x][y]==oyunTahtasi[x][y-2]){
                //System.out.println(5);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;


		// if (x,y) has at least two candies on its right side, check if those are equal to (x,y)
        if(sutun - y > 2){

			// if the candies are equal, return true
            if(oyunTahtasi[x][y]==oyunTahtasi[x][y+1] && oyunTahtasi[x][y]==oyunTahtasi[x][y+2]){
                //System.out.println(6);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;

        
		// if (x,y) has at least two candies on its up side, check if those are equal to (x,y)
        if(x > 1){

			// if equal, return true
            if(oyunTahtasi[x][y]==oyunTahtasi[x-1][y] && oyunTahtasi[x][y]==oyunTahtasi[x-2][y]){
                //System.out.println(7);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;

			
		// if (x,y) has at least two candies on its down side, check if those are equal to (x,y)
        if(satir - x > 2){

			// if equal, return true
            if(oyunTahtasi[x][y]==oyunTahtasi[x+1][y] && oyunTahtasi[x][y]==oyunTahtasi[x+2][y]){
                //System.out.println(8);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;
        
        
		// if (x,y) has one candy on its left side and one on its right side, check if they are equal to (x,y)
        if(sutun - y > 1 && y > 0){

			// if those candies are equal to (x,y), return true
            if(oyunTahtasi[x][y]==oyunTahtasi[x][y+1] && oyunTahtasi[x][y]==oyunTahtasi[x][y-1]){
                //System.out.println(8);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;
        

		// if (x,y) has one candy on its up side and one on its down side, check if they are equal to (x,y)
        if(satir - x > 1 && x > 0){

			// if equal, return true
            if(oyunTahtasi[x][y]==oyunTahtasi[x+1][y] && oyunTahtasi[x][y]==oyunTahtasi[x-1][y]){
                //System.out.println(8);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;
        
        
		// if flag reached 6, return false
        if(flag == 6)
            return false;
        else 
            return true;
        
    }
    
	
	
	// check if the board has at least one group of candies that can be removed
	// even if there are only 3 neighboring candies of the same kind, the funciton will return true
	// if there is this kind of a group, the candies will be blown up
    public static boolean patlamaVarMi(){
        
        for(int i=0; i<satir; i++){
            
            for(int j=0; j<sutun; j++){

				// for each coordinate, check around that candy
				// if any candy have 2 neighboring candies of the same kind, we will blow them up. return true
				if(etrafiKontrolEt(i,j)){
                    
                    //System.out.println(i + "," + j);
                    return true;
                    
                }
                
            }
            
        }

        return false;
        
    }
    
    
	/////////////// FUNCTIONS TO REMOVE DIFFERENT CANDY GROUPS ////////////////////////
	
	
	// if there are candy groups shaped like T, remove them
    /*
    
    88aa88
    88***8
    aa8*aa
    aa8*aa
    
    same shape as  * element above
    */
    public static void tSil(){
        
        
        /*
        a grouping like
		
        ---
         -
         -
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
				// set all candies on that group to 0 (remove them)
                if( oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+2] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j+1]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i][j+1] = 0;
                    oyunTahtasi[i][j+2] = 0;
                    oyunTahtasi[i+1][j+1] = 0;
                    oyunTahtasi[i+2][j+1] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
        /*
        a grouping like
		
        -
        ---
        -
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
				// set all candies on that group to 0 (remove them)
                if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+2]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                    oyunTahtasi[i+1][j+1] = 0;
                    oyunTahtasi[i+1][j+2] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
        
        /*
        
         -
         -
        ---
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=1; j+1<sutun; j++){
                
				// set all candies on that group to 0 (remove them)
                if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j-1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j+1]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                    oyunTahtasi[i+2][j-1] = 0;
                    oyunTahtasi[i+2][j+1] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
        
        /*
        
          -
        ---
          -
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=2; j<sutun; j++){
                
				// set all candies on that group to 0 (remove them)
                if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-2] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+1][j-1] = 0;
                    oyunTahtasi[i+1][j-2] = 0;
                    oyunTahtasi[i+2][j] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
    }
    
    
    // T like grouping with 7 candies
    /*
    
    88aa88
    8*****
    aa8*aa
    aa8*aa
    
    */
    public static void buyukTSil(){
        
        
        /*
        
        -----
          -
          -
        
        */
        if(sutun>=5 && satir>=3){
            for(int i=0; i+2<satir; i++){
                for(int j=0; j+4<sutun; j++){

				// set all candies on that group to 0 (remove them)
                    if( oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i][j+2] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i][j+3] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i][j+4] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+1][j+2] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j+2]
                            ){

                        oyunTahtasi[i][j] = 0;
                        oyunTahtasi[i][j+1] = 0;
                        oyunTahtasi[i][j+2] = 0;
                        oyunTahtasi[i][j+3] = 0;
                        oyunTahtasi[i][j+4] = 0;
                        oyunTahtasi[i+1][j+2] = 0;
                        oyunTahtasi[i+2][j+2] = 0;
                            
                        toplamPuan += 49;
                    
                    }

                }
            }
        }
        /*
        
        -
        -
        ---
        -
        -
        
        */
        if(satir>=5 && sutun>=5){
            for(int i=0; i+4<satir; i++){
                for(int j=0; j+2<sutun; j++){

				// set all candies on that group to 0 (remove them)
                    if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+3][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+4][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j+1] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j+2]
                            ){

                        oyunTahtasi[i][j] = 0;
                        oyunTahtasi[i+1][j] = 0;
                        oyunTahtasi[i+2][j] = 0;
                        oyunTahtasi[i+3][j] = 0;
                        oyunTahtasi[i+4][j] = 0;
                        oyunTahtasi[i+2][j+1] = 0;
                        oyunTahtasi[i+2][j+2] = 0;
                            
                        toplamPuan += 49;
                    
                    }

                }
            }
        }
        
        
        /*
        
          -
          -
        -----
        

        */
        if(satir>=5 && sutun>=5){
            for(int i=0; i+2<satir; i++){
                for(int j=2; j+2<sutun; j++){

				// set all candies on that group to 0 (remove them)
                    if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j-2] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j-1] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j+1] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j+2]
                            ){

                        oyunTahtasi[i][j] = 0;
                        oyunTahtasi[i+1][j] = 0;
                        oyunTahtasi[i+2][j-2] = 0;
                        oyunTahtasi[i+2][j-1] = 0;
                        oyunTahtasi[i+2][j] = 0;
                        oyunTahtasi[i+2][j+1] = 0;
                        oyunTahtasi[i+2][j+2] = 0;

                        toplamPuan += 49;

                    }

                }
            }
        }
        
        /*
        
          -
          -
        ---
          -
          -
        
        */
        if(satir>=5 && sutun>=5){
            for(int i=0; i+4<satir; i++){
                for(int j=2; j<sutun; j++){

				// set all candies on that group to 0 (remove them)
                    if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j-1] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+2][j-2] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+3][j] &&
                        oyunTahtasi[i][j]==oyunTahtasi[i+4][j]
                            ){

                        oyunTahtasi[i][j] = 0;
                        oyunTahtasi[i+1][j] = 0;
                        oyunTahtasi[i+1][j-1] = 0;
                        oyunTahtasi[i+1][j-2] = 0;
                        oyunTahtasi[i+2][j] = 0;
                            
                        toplamPuan += 49;
                    
                    }

                }
            }
        }
        
    }
    
    
    // a grouping like L
    /*
    
    -
    -
    ---
    
    */
        public static void LSil(){
        
        
        /*
        
        -
        -
        ---
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
				// set all candies on that group to 0 (remove them)
                if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j+2]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                    oyunTahtasi[i+2][j+1] = 0;
                    oyunTahtasi[i+2][j+2] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
        /*
        
        ---
        -
        -
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                if( oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+2] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i][j+1] = 0;
                    oyunTahtasi[i][j+2] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
        
        /*
        
          -
          -
        ---
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=2; j<sutun; j++){
                
                if( oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j-1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j-2]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                    oyunTahtasi[i+2][j-1] = 0;
                    oyunTahtasi[i+2][j-2] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
        
        /*
        
        ---
          -
          -
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                if( oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+2] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+2] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j+2]
                        ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i][j+1] = 0;
                    oyunTahtasi[i][j+2] = 0;
                    oyunTahtasi[i+1][j+2] = 0;
                    oyunTahtasi[i+2][j+2] = 0;
                            
                    toplamPuan += 25;
                    
                }
                
            }
        }
        
    }
        
    
	// if there are 5 candies side by side or from top to bottom, remove them and add the points
    public static void besSil(){
        
		// starting from (0,0), check if the next 4 candies are equal to this one
		// if equal, remove these candies
        for(int i=0; i<satir; i++){
            for(int j=0; j+4<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+2] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+3] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+4]
                    ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i][j+1] = 0;
                    oyunTahtasi[i][j+2] = 0;
                    oyunTahtasi[i][j+3] = 0;
                    oyunTahtasi[i][j+4] = 0;
                            
                    toplamPuan += 25;
                    
                }
                    
            }
        }
        
        
		// starting from (0,0), check if the next 4 candies under it are the same as that
		// if equal, remove these candies
        for(int i=0; i+4<satir; i++){
            for(int j=0; j<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+3][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+4][j]
                    ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                    oyunTahtasi[i+3][j] = 0;
                    oyunTahtasi[i+4][j] = 0;
                            
                    toplamPuan += 25;
                    
                }
                    
            }
        }
        
    }
    
	// if there are 4 candies side by side or from top to bottom, remove them and add the points
    public static void dortSil(){
        
		// starting from (0,0), check if the next 3 candies are equal to this one
		// if equal, remove these candies
        for(int i=0; i<satir; i++){
            for(int j=0; j+3<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+2] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+3]
                    ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i][j+1] = 0;
                    oyunTahtasi[i][j+2] = 0;
                    oyunTahtasi[i][j+3] = 0;
                            
                    toplamPuan += 16;
                    
                }
                    
            }
        }
        
        
		// starting from (0,0), check if the next 3 candies under it are the same as that
		// if equal, remove these candies
        for(int i=0; i+3<satir; i++){
            for(int j=0; j<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+3][j]
                    ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                    oyunTahtasi[i+3][j] = 0;
                            
                    toplamPuan += 16;
                    
                }
                    
            }
        }
        
    }
    
    
	// if there are 3 candies side by side or from top to bottom, remove them and add the points
    public static void ucSil(){
        
		// starting from (0,0), check if the next 2 candies are equal to this one
		// if equal, remove these candies
        for(int i=0; i<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+2]
                    ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i][j+1] = 0;
                    oyunTahtasi[i][j+2] = 0;
                            
                    toplamPuan += 9;
                    
                }
                    
            }
        }
        
        
		// starting from (0,0), check if the next 2 candies under it are the same as that
		// if equal, remove these candies
        for(int i=0; i+2<satir; i++){
            for(int j=0; j<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j]
                    ){
                    
                    oyunTahtasi[i][j] = 0;
                    oyunTahtasi[i+1][j] = 0;
                    oyunTahtasi[i+2][j] = 0;
                            
                    toplamPuan += 9;
                    
                }
                    
            }
        }
        
    }
    
    
	// fill in the blank tiles
    public static void bosluklariDoldur(){
        
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
				// if the candy at (i,j) is equal to 0, put a random candy in that tile
                if(oyunTahtasi[i][j] == 0){
                    
                    // find a random number
                    Random rand = new Random();
                    // ( 1 <= rand < 6 )
                    int ilk = 1;
                    int son = 6;
                    // generate the number
                    int sayi = rand.nextInt(son-ilk) + ilk;


                    // set the given tile to that number
                    oyunTahtasi[i][j] = sayi;
                    
                }
                
            }
        }
        
    }
    
	
	
	// check if any of the empty tiles has candies above it
	// (return true if any tile that is equal to 0, has a candy above it)
    public static boolean boslukVarMi(){
        
		// when you find a tile equal to 0, check if it has any candies above
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
				// if there is an empty tile (tile that is equal to 0)
                if(oyunTahtasi[i][j]==0){
                    
					// check all tiles above it
                    for(int k=i; k>-1; k--){
                        
						// if at least one of these tiles is not empty, return true
                        if(oyunTahtasi[k][j] != 0)
                            return true;
                        
                    }
                    
                }

            }
        }
        
        return false;
    }
    
    
	
	// after the removal (blowing up the candies), drop down the remaining candies
    public static void sekerleriDusur(){
        
		// continue the while loop until you get all empty tiles to top
		// boslukVarMi function checks if empty tiles have any candies above them
        while(boslukVarMi()){

			// start from the lowest row for each column
            for(int i=0; i<satir-1; i++){
                for(int j=0; j<sutun; j++){

					// if the row is equal to 0, move all rows above it down by one
                    if(oyunTahtasi[i+1][j] == 0){
                        
                        oyunTahtasi[i+1][j] = oyunTahtasi[i][j];
                        oyunTahtasi[i][j] = 0;
                    }

                }
            }
        
        }
    }
    
    
    // check if any valid moves are left on the game board
    /*
    the moves can be like:
    
    *-  -*  -    -  
    -    -  -    -  
    -    -  *-  -*  
    
    *         * 
    -**     **- 
    
    -**     **-
    *         *
    
    */
    // if there are groupings of these kinds, return true
    public static boolean gecerliHamleVarMi(){
        
        /*
        
        *-
        -
        -
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=1; j<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j-1]
                ){
                    
                    //System.out.println(12);
                    return true;

                }
            }
        }
        
        /*
        
        -*
         -
         -
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+1<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j+1]
                ){
                    
                    //System.out.println(11);
                    return true;

                }
            }
        }
        
        /*
        
        -
        -
        *-
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+1<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j+1]
                ){
                    
                    //System.out.println(10);
                    return true;

                }
            }
        }
        
        /*
        
         -
         -
        -*
        
        */
        for(int i=0; i+2<satir; i++){
            for(int j=1; j<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j-1]
                ){
                    
                    //System.out.println(9);
                    return true;

                }
            }
        }
        
        
        /*
        
        --*
          -
        
        */
        for(int i=0; i+1<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+2]
                ){
                    
                    //System.out.println(1);
                    return true;

                }
            }
        }
        
        /*
        
          -
        --*
        
        */
        for(int i=0; i+1<satir; i++){
            for(int j=2; j<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-2]
                ){
                    
                    //System.out.println(2);
                    return true;

                }
            }
        }
        
        /*
        
        *--
        -  

        */
        for(int i=0; i+1<satir; i++){
            for(int j=1; j+1<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-1]
                ){
                    
                    //System.out.println(3);
                    return true;

                }
            }
        }
        
        /*
        
        -
        *--  

        */
        for(int i=0; i+1<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+2]
                ){
                    
                    //System.out.println(4);
                    return true;

                }
            }
        }
        
        
        /*
        
        *-*
        -a- 

        */
        for(int i=0; i+1<satir; i++){
            for(int j=1; j+1<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+1]
                ){
                    
                    //System.out.println(5);
                    return true;

                }
            }
        }
        
        /*
        
        -a-
        *-* 

        */
        for(int i=0; i+1<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i][j+2]
                ){
                    
                    //System.out.println(6);
                    return true;

                }
            }
        }
        
        /*
        
        -
        *- 
        -

        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+1<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j+1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j]
                ){
                    
                    //System.out.println(7);
                    return true;

                }
            }
        }
        
        /*
        
        *-
        -a 
        *-

        */
        for(int i=0; i+2<satir; i++){
            for(int j=1; j<sutun; j++){
                
                if(
                    oyunTahtasi[i][j]==oyunTahtasi[i+1][j-1] &&
                    oyunTahtasi[i][j]==oyunTahtasi[i+2][j]
                ){
                    
                    //System.out.println(8);
                    return true;

                }
            }
        }
        
        return false;
        
    }
    

	// function to shuffle the board (when there are no valid moves left)
	// collect all candies on the board, and put them on different coordinates
    public static void tahtaKaristir(){
        
		// create an array to keep your candies
		// candies of type 1 will be kept at sekerler[1], 2 will be kept at sekerler[2] etc..
        int[] sekerler = new int[6];
        
        
		// find how many of each cany there is on the board
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
				// for each candy, increase the number of that candy by one
                sekerler[oyunTahtasi[i][j]]++;
                
            }
        }
        
        
		// choose one candy randomly and put that on the next tile
		// if you don't have any remaining candies of that kind, choose a new one
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
                // find a random number
                Random rand = new Random();
                // ( 1 <= rand < 6 )
                int ilk = 1;
                int son = 6;
                // generate it
                int sayi = rand.nextInt(son-ilk) + ilk;
                
                
				// if you have at least one of that kind of candy, put it on the tile and decrease its number by one
                if(sekerler[sayi] > 0){
                    
                    oyunTahtasi[i][j] = sayi;
                    sekerler[sayi]--;
                    
                }
				// if you don't have that kind of candy, choose a new candy until you find one that you have
                else {
                    
					// try until you select a candy of which you have at least one
                    while(sekerler[sayi]==0){
                        
                        // find random number
                        rand = new Random();
                        //  ( 1 <= rand < 6 ) 
                        ilk = 1;
                        son = 6;
                        // generate number
                        sayi = rand.nextInt(son-ilk) + ilk;
                        
                    }
                    
                    // put the candy on the tile and decrease its number by one
                    oyunTahtasi[i][j] = sayi;
                    sekerler[sayi]--;
                    
                }

            }
        }
        
    }
    
    
	// play the game
    public static void oyunuOynat(){

		// create scanner to take user input
        Scanner keyboard = new Scanner(System.in);
        
        
        // take the board's size
        System.out.print("Tahta büyüklüğü : ");
        String input = keyboard.nextLine();
        // split the input from the space character
        String[] splitted = new String[2];
        splitted = input.split("\\s+");
        satir = Integer.parseInt(splitted[0]);
        sutun = Integer.parseInt(splitted[1]);
        tahtayiOlustur(satir, sutun);
        
        
        //System.out.println(input);
        
        // take the maximum number of moves
        System.out.print("Maksimum hamle sayısı : ");
        input = keyboard.nextLine();
        maxHamle = Integer.parseInt(input);
        int kalanHamle = maxHamle;
        
        
        // take the goal score
        System.out.print("Ulaşılması gereken puan : ");
        input = keyboard.nextLine();
        hedefPuan = Integer.parseInt(input);
        
        System.out.println();
        

		// when the game is over (when kalanHamle = 0), stop will be set to 1 and while loop will end
        int stop = 0;
        
        while(stop != 1){
            
            // if no valid moves, shuffle the board
            while(!gecerliHamleVarMi()){
                
                tahtaKaristir();
                
            }
            

			// until there are no more blowable candy groups, blow them up and fill the board again
            while(patlamaVarMi()){

                //System.out.println("BOOOM");

				// remove all candy groups starting from the ones shaped like T
                buyukTSil();
                tSil();
                LSil();
                besSil();
                dortSil();
                ucSil();

                // drop down the remaining candies
                sekerleriDusur();
                
                //System.out.println(1);
                //tahtayiYazdir();
                
                //System.out.println(1);
                // fill in the blank tiles after all candies are blown up
                bosluklariDoldur();


            }
            
            // print the board
            tahtayiYazdir();
            System.out.println();
            
            System.out.println("Puan : " + toplamPuan);
            System.out.println("Kalan hamle sayısı : " + kalanHamle);
            
            
            // if number of remaining moves (kalanHamle) is equal to 0, end the game
            if(kalanHamle == 0){
                
                
                // kullanıcının topladığı puan hedef puandan büyük ya da ona eşitse oyunu kazanmıştır
				// if the user score is greater than or equal to the goal, print "You won"
                if(toplamPuan >= hedefPuan){
                    
                    System.out.println("Tebrikler, kazandınız.");
                    
                }
                else{
                    
                    System.out.println("Üzgünüm, kaybettiniz.");
                    
                }
                
                
                // to stop the while loop, set stop to 1
                stop = 1;
                
            }
            
			// if number of remaining moves is more than 1, continue with the game 
            else {
                
                
				// if there is a valid move on the board, take user input
                if(gecerliHamleVarMi()){
                    
                    // take the next move
                    System.out.print("Bir sonraki hamleyi giriniz : ");
                    input = keyboard.nextLine();
                    // split the first and second coordinates
                    splitted = new String[2];
                    splitted = input.split("\\s+");
                    String birinci = splitted[0];
                    String ikinci = splitted[1];
                    
                    
					// split the first coordinate from "," character and set x1,y1 coordinates
                    splitted = birinci.split(",");
                    int x1 = Integer.parseInt(splitted[0]);
                    int y1 = Integer.parseInt(splitted[1]);
					// split the second coordinate from "," character and set x2,y2 coordinates
                    splitted = ikinci.split(",");
                    int x2 = Integer.parseInt(splitted[0]);
                    int y2 = Integer.parseInt(splitted[1]);
                    
                    
                    // if the move given by the user is valid,
                    if(hamleGecerliMi(x1, y1, x2, y2)){
                    
                        // make the move
                        hamleYap(x1, y1, x2, y2);
                        
                        
						// until the blowable candy groups are gone, blow them up and fill up the board again
                        while(patlamaVarMi()){
                            
                            //System.out.println("BOOOM");
                            
                            // remove all candy groups starting from the ones shaped like T
                            buyukTSil();
                            tSil();
                            LSil();
                            besSil();
                            dortSil();
                            ucSil();

                            // drop down remaining candies
                            sekerleriDusur();
                            
                            // fill in the blanks
                            bosluklariDoldur();
                            
 
                        }

                        
                        // decrease remaining number of moves by one
                        kalanHamle--;
                        
                    }
                    else {
                        
                        System.out.println("Yaptığınız hamle geçersiz. Başka bir hamle seçin.");
                        
                    }
                    
                }
                // if no more valid moves, shuffle the game board
                else {
                    
                    
                    // shuffle the board until there is at least one valid move
                    while(!gecerliHamleVarMi())
                        tahtaKaristir();
                    
                }

                

            }
            
            System.out.println();
            
            
        }

    }
    
    
    // print the current state of the game board
    public static void tahtayiYazdir(){
        
        // print the given character for each tile on the board
        // 1='1' 2='*' 3='-' 4='8' 5='a'
        for(int i=0; i<satir; i++){
            
            for(int j=0; j<sutun; j++){
                
                if(oyunTahtasi[i][j] == 1)
                    System.out.print("1 ");
                else if(oyunTahtasi[i][j] == 2)
                    System.out.print("* ");
                else if(oyunTahtasi[i][j] == 3)
                    System.out.print("- ");
                else if(oyunTahtasi[i][j] == 4)
                    System.out.print("8 ");
                else if(oyunTahtasi[i][j] == 5)
                    System.out.print("a ");
                else
                    System.out.print("x ");
            }
            
            System.out.println();
            
        }
        

    }
    
    public static void main(String[] args) {
        
        oyunuOynat();
        
    }
    
    
}
