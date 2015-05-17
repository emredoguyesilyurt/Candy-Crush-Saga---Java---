package ccs;

import java.util.*;

public class Tahta {
    
    // oyunTahtasinda 1 ile 5 arasında sayılar tutulacak, bu sayılara göre tahtadaki karelerde hangi elemanların olduğu belirlenecek
    // 1='1' 2='*' 3='-' 4='8' 5='a'
    static int[][] oyunTahtasi;
    static int satir;
    static int sutun;
    static int toplamPuan;
    static int maxHamle;
    static int hedefPuan;
    
    
    // tahta class'ı için bir constructor oluştur
    public static void tahtayiOlustur(int sat, int sut){
        
        // tahtanın satır ve sütunlarını verilen değerlere eşitle
        satir = sat;
        sutun = sut;
        
        // oyunTahtasi arrayini verilen satır ve sütun sayısına göre oluştur
        oyunTahtasi = new int [satir][sutun];
        
        // oyunTahtasındaki her kareye 1 ile 5 arasında rastgele bir sayı yerleştir
        // bu sayılar o karede hangi karakterin olduğunu göstermek için kullanılacak
        for(int i=0; i<satir; i++){
            
            for(int j=0; j<sutun; j++){
                
                // rastgeşe bir sayı bul
                Random rand = new Random();
                // rastgele sayı ( 1 <= sayi < 6 ) şeklinde olacak
                int ilk = 1;
                int son = 6;
                // gereken rastgele sayıyı bul
                int sayi = rand.nextInt(son-ilk) + ilk;
                
                
                // oyun matrisindeki kareyi bulduğun sayıya eşitle
                oyunTahtasi[i][j] = sayi;
                
            }
            
        }
        
    }
    
    
    // kullanıcının verdiği hamlenin geçerli olup olmadığını kontrol et
    public static boolean hamleGecerliMi(int x1, int y1, int x2, int y2){
        
        // verilen satır ve sütun koordinatları tahtanın içindeyse kontrole devam et
        if(
            (-1<x1) && (x1<satir) &&
            (-1<y1) && (y1<sutun) &&
            (-1<x2) && (x2<satir) &&
            (-1<y2) && (y2<sutun)
            ){

            // önce verilen hamleyi yap
            hamleYap(x1,y1,x2,y2);

            // hamleyi yaptıktan sonra verilen koordinatlardaki şekerlerin etrafında en az iki tane aynı türden şeker olup olmadığını kontrol et
            if(etrafiKontrolEt(x1,y1) || etrafiKontrolEt(x2,y2)){

                // hamle geçerliyse true dön
                // yaptığın hamleyi geri al (bu fonksiyon doğru dönerse hamleyi main fonksiyonu içinde yapacağız
                hamleYap(x1,y1,x2,y2);
                return true;

            }
            else{

                // hamle geçersizse false dön
                // yaptığın hamleyi geri al (bu fonksiyon doğru dönerse hamleyi main fonksiyonu içinde yapacağız
                hamleYap(x1,y1,x2,y2);
                return false;

            }

        }
        // verilen koordinatlar tahta dışındaysa false dön
        else
            return false;
    }
    
    
    // kullanıcının verdiği koordinatlardaki şekerleri yer değiştir
    public static void hamleYap(int x1, int y1, int x2, int y2){
        
        // yerlerini değiştireceğin ilk ve ikinci şekerleri al
        int ilkSeker = oyunTahtasi[x1][y1];
        int ikinciSeker = oyunTahtasi[x2][y2];
        
        // aldığın şekerleri oyunTahtasındaki yeni yerlerine yerleştir
        oyunTahtasi[x1][y1] = ikinciSeker;
        oyunTahtasi[x2][y2] = ilkSeker;
        
    }
    
    
    // verilen bir koordinattaki şekerin sağında, solunda, üstünde ya da altında aynı türden bir şeker olup olmadığını kontrol et
    public static boolean etrafiKontrolEt(int x, int y){
        
        int flag = 0;

        
        // (x,y)nin solunda en az iki şeker varsa, o iki şekerin (x,y)dekine eşit olup olmadığına bak
        if(y > 1){

            // şekerler (x,y)dekine eşitse true dön
            if(oyunTahtasi[x][y]==oyunTahtasi[x][y-1] && oyunTahtasi[x][y]==oyunTahtasi[x][y-2]){
                //System.out.println(5);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;

        // (x,y)nin sağında en az iki şeker varsa, o iki şekerin (x,y)dekine eşit olup olmadığına bak
        if(sutun - y > 2){

            // şekerler (x,y)dekine eşitse true dön
            if(oyunTahtasi[x][y]==oyunTahtasi[x][y+1] && oyunTahtasi[x][y]==oyunTahtasi[x][y+2]){
                //System.out.println(6);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;

        
        // (x,y)nin üstünde en az iki şeker varsa, o iki şekerin (x,y)dekine eşit olup olmadığına bak
        if(x > 1){

            // şekerler (x,y)dekine eşitse true dön
            if(oyunTahtasi[x][y]==oyunTahtasi[x-1][y] && oyunTahtasi[x][y]==oyunTahtasi[x-2][y]){
                //System.out.println(7);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;

        // (x,y)nin altında en az iki şeker varsa, o iki şekerin (x,y)dekine eşit olup olmadığına bak
        if(satir - x > 2){

            // şekerler (x,y)dekine eşitse true dön
            if(oyunTahtasi[x][y]==oyunTahtasi[x+1][y] && oyunTahtasi[x][y]==oyunTahtasi[x+2][y]){
                //System.out.println(8);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;
        
        
        // (x,y)nin bir solunda bir de sağında aynı şekerden varsa true dön
        if(sutun - y > 1 && y > 0){

            // şekerler (x2,y2)dekine eşitse true dön
            if(oyunTahtasi[x][y]==oyunTahtasi[x][y+1] && oyunTahtasi[x][y]==oyunTahtasi[x][y-1]){
                //System.out.println(8);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;
        
        // (x,y)nin bir üstünde bir de altında aynı şekerden varsa true dön
        if(satir - x > 1 && x > 0){

            // şekerler (x,y)dekine eşitse true dön
            if(oyunTahtasi[x][y]==oyunTahtasi[x+1][y] && oyunTahtasi[x][y]==oyunTahtasi[x-1][y]){
                //System.out.println(8);
                return true;
            }
            else
                flag++;
        }
        else
            flag++;
        
        
        if(flag == 6)
            return false;
        else 
            return true;
        
    }
    
    
    // tahtada en az bir tane silinebilecek şeker grubu olup olmadığını kontrol et
    // sadece 3 tane yan yana ya da üst üste şeker varsa bile true dönecek
    // eğer böyle bir grup varsa tahtada patlatma işlemi yapılacak
    public static boolean patlamaVarMi(){
        
        for(int i=0; i<satir; i++){
            
            for(int j=0; j<sutun; j++){
                
                // bütün koordinatlar için o koordinattaki şekerin etrafını kontrol et
                // herhangi bir şekerin etrafında aynı türden 2 şeker daha varsa, patlama vardır. true dön
                if(etrafiKontrolEt(i,j)){
                    
                    //System.out.println(i + "," + j);
                    return true;
                    
                }
                
            }
            
        }

        return false;
        
    }
    
    
    // T şeklinde dizilmiş şekerler varsa onları sil
    /*
    
    88aa88
    88***8
    aa8*aa
    aa8*aa
    
    tahtasındaki * elemanlarıyla aynı şekilde bir dizilim varsa
    */
    public static void tSil(){
        
        
        /*
        
        ---
         -
         -
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        -
        ---
        -
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=1; j+1<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=2; j<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
    
    
    // toplam yedi elemanlı T şeklinde dizilmiş şekerler varsa onları sil
    /*
    
    88aa88
    8*****
    aa8*aa
    aa8*aa
    
    tahtasındaki * elemanlarıyla aynı şekilde bir dizilim varsa
    */
    public static void buyukTSil(){
        
        
        /*
        
        -----
          -
          -
        
        şeklinde bir dizilim
        */
        if(sutun>=5 && satir>=3){
            for(int i=0; i+2<satir; i++){
                for(int j=0; j+4<sutun; j++){

                    // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        if(satir>=5 && sutun>=5){
            for(int i=0; i+4<satir; i++){
                for(int j=0; j+2<sutun; j++){

                    // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        if(satir>=5 && sutun>=5){
            for(int i=0; i+2<satir; i++){
                for(int j=2; j+2<sutun; j++){

                    // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        if(satir>=5 && sutun>=5){
            for(int i=0; i+4<satir; i++){
                for(int j=2; j<sutun; j++){

                    // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
    
    
    // L şeklinde şeker dizilimi varsa onları sil
    /*
    
    -
    -
    ---
    
    gibi bir dizilim varsa
    */
        public static void LSil(){
        
        
        /*
        
        -
        -
        ---
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=2; j<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
        şeklinde bir dizilim
        */
        for(int i=0; i+2<satir; i++){
            for(int j=0; j+2<sutun; j++){
                
                // eğer T şeklinde bir dizilim varsa, bu dizilimdeki bütün şekerleri sıfıra eşitle (sil)
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
        
    
    // yan yana ya da üst üste 5 tane şeker varsa o şekerleri sil ve puanı ekle
    public static void besSil(){
        
        // (0,0)daki şekerden başlayarak bu şekerlerin sağ taraftaki 4 şekere eşit olup olmadığına bak
        // eğer eşitse bu şekerleri sil
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
        
        
        // (0,0)daki şekerden başlayarak bu şekerlerin alt taraftaki 4 şekere eşit olup olmadığına bak
        // eğer eşitse bu şekerleri sil
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
    
    // yan yana ya da üst üste 4 tane şeker varsa o şekerleri sil ve puanı ekle
    public static void dortSil(){
        
        // (0,0)daki şekerden başlayarak bu şekerlerin sağ taraftaki 3 şekere eşit olup olmadığına bak
        // eğer eşitse bu şekerleri sil
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
        
        
        // (0,0)daki şekerden başlayarak bu şekerlerin alt taraftaki 3 şekere eşit olup olmadığına bak
        // eğer eşitse bu şekerleri sil
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
    
    
    // yan yana ya da üst üste 3 tane şeker varsa o şekerleri sil ve puanı ekle
    public static void ucSil(){
        
        // (0,0)daki şekerden başlayarak bu şekerlerin sağ taraftaki 2 şekere eşit olup olmadığına bak
        // eğer eşitse bu şekerleri sil
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
        
        
        // (0,0)daki şekerden başlayarak bu şekerlerin alt taraftaki 2 şekere eşit olup olmadığına bak
        // eğer eşitse bu şekerleri sil
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
    
    
    public static void bosluklariDoldur(){
        
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
                // eğer (i,j)deki şeker 0'a eşitse (daha önce silinmişse) bu noktaya rastgele bir şeker yerleştir
                if(oyunTahtasi[i][j] == 0){
                    
                    // rastgeşe bir sayı bul
                    Random rand = new Random();
                    // rastgele sayı ( 1 <= sayi < 6 ) şeklinde olacak
                    int ilk = 1;
                    int son = 6;
                    // gereken rastgele sayıyı bul
                    int sayi = rand.nextInt(son-ilk) + ilk;


                    // oyun matrisindeki kareyi bulduğun sayıya eşitle
                    oyunTahtasi[i][j] = sayi;
                    
                }
                
            }
        }
        
    }
    
    
    // oyun tahtasında yukarı tarafında şeker olan herhangi bir boş kare olup olmadığını kontrol et 
    // (değeri 0'a eşit olan karelerden herhangi birinin üzerinde şeker varsa true dön)
    public static boolean boslukVarMi(){
        
        // sıfıra eşit olan bir kare bulduğunda o karenin üzerinde herhangi bir şeker olup olmadığını kontrol et
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
                // içinde şeker olmayan (sıfıra eşit olan) bir kare varsa
                if(oyunTahtasi[i][j]==0){
                    
                    // o karenin yukarsındaki bütün kareleri kontrol et
                    for(int k=i; k>-1; k--){
                        
                        // eğer bu yukardaki karelerden biri bile boş değilse true dön
                        if(oyunTahtasi[k][j] != 0)
                            return true;
                        
                    }
                    
                }

            }
        }
        
        return false;
    }
    
    
    // şekerleri sildikten sonra kalan şekerleri aşağı düşür
    public static void sekerleriDusur(){
        
        // sıfıra eşit olan bütün kareleri yukarı taşıyana kadar aşağıdaki for döngüsünü devam ettir
        // boşlukVarMi fonksiyonu boş karelerin üzerinde herhangi bir şeker olup olmadığını kontrol eder
        while(boslukVarMi()){
            
            // her sütun için en alttaki satırdan başla,
            // eğer satır sıfıra eşitse, o satırın üstündeki bütün satırları birer aşağı indir
            for(int i=0; i<satir-1; i++){
                for(int j=0; j<sutun; j++){

                    // koordinattaki şeker numarası sıfırsa (yani buradaki şeker silinmişse)
                    if(oyunTahtasi[i+1][j] == 0){
                        
                        oyunTahtasi[i+1][j] = oyunTahtasi[i][j];
                        oyunTahtasi[i][j] = 0;
                    }

                }
            }
        
        }
    }
    
    
    // tahtada yapılabilecek geçerli hamle kalıp kalmadığını kontrol et
    /*
    hamleler şu şekillerde olabilir:
    
    *-  -*  -    -  
    -    -  -    -  
    -    -  *-  -*  
    
    *         * 
    -**     **- 
    
    -**     **-
    *         *
    
    */
    // bu şekillere uyan taş dizilimleri varsa true dön, yoksa false dön
    public static boolean gecerliHamleVarMi(){
        
        /*
        
        *-
        -
        -
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
        
        şeklinde dizilim
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
    
    // tahtadaki bütün şekerleri topla ve hepsini farklı koordinatlara yerleştir
    // (tahtayı karıştır)
    public static void tahtaKaristir(){
        
        // elindeki şeker türlerini tutmak için bir array oluştur
        // şeker türü 1 olan şekerlerin sayısı sekerler[1] indexinde tutulacak
        // şeker türü 2 olan şekerlerin sayısı sekerler[2]'de tutulacak
        int[] sekerler = new int[6];
        
        
        // tahtada bütün şekerlerden kaçar tane olduğunu bul
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
                // tahtadaki her şeker için, o şekerin sayısını bir arttır
                // oyunTahtasi[i][j]=1 ise, sekerler[1]'i bir arttır. yani 1 numaralı şekerden bir tane daha var demek
                sekerler[oyunTahtasi[i][j]]++;
                
            }
        }
        
        
        // tahtadaki her kareye, elindeki şekerlerden rastgele bir tanesini yerleştir
        // elinde rastgele seçtiğin şekerlerden kalmadıysa yeni bir şeker seç
        for(int i=0; i<satir; i++){
            for(int j=0; j<sutun; j++){
                
                // rastgeşe bir sayı bul
                Random rand = new Random();
                // rastgele sayı ( 1 <= sayi < 6 ) şeklinde olacak
                int ilk = 1;
                int son = 6;
                // gereken rastgele sayıyı bul
                int sayi = rand.nextInt(son-ilk) + ilk;
                
                
                // rastgele seçtiğin şekerin sayısı sıfırdan büyükse, o şekeri tahtaya yerleştir ve sayısını bir azalt
                if(sekerler[sayi] > 0){
                    
                    oyunTahtasi[i][j] = sayi;
                    sekerler[sayi]--;
                    
                }
                // şeker sayısı sıfırsa (elinde o şekerden kalmadıysa) elinde olan bir şeker bulana kadar yeni bir şeker seç
                else {
                    
                    // sayısı sıfırdan büyük olan yeni bir şeker bulana kadar dene
                    while(sekerler[sayi]==0){
                        
                        // rastgeşe bir sayı bul
                        rand = new Random();
                        // rastgele sayı ( 1 <= sayi < 6 ) şeklinde olacak
                        ilk = 1;
                        son = 6;
                        // gereken rastgele sayıyı bul
                        sayi = rand.nextInt(son-ilk) + ilk;
                        
                    }
                    
                    // yeni şekeri bulunca tahtaya yerleştir ve sayısını bir azalt
                    oyunTahtasi[i][j] = sayi;
                    sekerler[sayi]--;
                    
                }

            }
        }
        
    }
    
    
    // oyunu oynat
    public static void oyunuOynat(){
   
        // kullanıcı inputunu almak için scanner oluştur
        Scanner keyboard = new Scanner(System.in);
        
        
        // tahta büyüklüğünü al
        System.out.print("Tahta büyüklüğü : ");
        String input = keyboard.nextLine();
        // inputu space karakterinden satır ve sütun olarak ayır
        String[] splitted = new String[2];
        splitted = input.split("\\s+");
        satir = Integer.parseInt(splitted[0]);
        sutun = Integer.parseInt(splitted[1]);
        tahtayiOlustur(satir, sutun);
        
        
        //System.out.println(input);
        
        // maksimum hamle sayısını al
        System.out.print("Maksimum hamle sayısı : ");
        input = keyboard.nextLine();
        maxHamle = Integer.parseInt(input);
        int kalanHamle = maxHamle;
        
        
        // ulaşılması gereken puanı al
        System.out.print("Ulaşılması gereken puan : ");
        input = keyboard.nextLine();
        hedefPuan = Integer.parseInt(input);
        
        System.out.println();
        
        // oyun bittiğinde (kalanHamle = 0 olduğunda) stop=1 olacak ve while döngüsü sonlanacak
        int stop = 0;
        
        while(stop != 1){
            
            // geçerli hamle yoksa tahtayı karıştır
            while(!gecerliHamleVarMi()){
                
                tahtaKaristir();
                
            }
            
            // tahtada patlatılacak şeker grupları yok olana kadar patlatmayı yap ve tahtayı yeniden doldur
            while(patlamaVarMi()){

                //System.out.println("BOOOM");

                // T şeklindeki gruplardan başlayarak bütün şeker gruplarını teker teker sil
                buyukTSil();
                tSil();
                LSil();
                besSil();
                dortSil();
                ucSil();

                // şekerleri sildikten sonra kalan şekerleri aşağı düşür
                sekerleriDusur();
                
                //System.out.println(1);
                //tahtayiYazdir();
                
                //System.out.println(1);
                // bütün gruplar silindikten sonra tahtadaki boş kısımları doldur
                bosluklariDoldur();


            }
            
            // tahtayı yazdır
            tahtayiYazdir();
            System.out.println();
            
            System.out.println("Puan : " + toplamPuan);
            System.out.println("Kalan hamle sayısı : " + kalanHamle);
            
            
            // kalan hamle sayısı sıfırsa oyunu bitir
            if(kalanHamle == 0){
                
                
                // kullanıcının topladığı puan hedef puandan büyük ya da ona eşitse oyunu kazanmıştır
                if(toplamPuan >= hedefPuan){
                    
                    System.out.println("Tebrikler, kazandınız.");
                    
                }
                else{
                    
                    System.out.println("Üzgünüm, kaybettiniz.");
                    
                }
                
                
                // while döngüsünü bitirmek için stop'u bire eşitle
                stop = 1;
                
            }
            
            // kalan hamle sayısı sıfırdan büyükse oyuna devam et
            else {
                
                
                // eğer tahtada yapılabilecek bir hamle varsa, kullanıcıdan input al
                if(gecerliHamleVarMi()){
                    
                    // bir sonraki hamleyi al
                    System.out.print("Bir sonraki hamleyi giriniz : ");
                    input = keyboard.nextLine();
                    // inputu space karakterinden birinci ve ikinci koordinat olarak ayır
                    splitted = new String[2];
                    splitted = input.split("\\s+");
                    String birinci = splitted[0];
                    String ikinci = splitted[1];
                    
                    // birinci koordinatı "," karakterinden ayır ve x1 ve y1 koordinatlarına ata
                    splitted = birinci.split(",");
                    int x1 = Integer.parseInt(splitted[0]);
                    int y1 = Integer.parseInt(splitted[1]);
                    // ikinci koordinatı "," karakterinden ayır ve x2 ve y2 koordinatlarına ata
                    splitted = ikinci.split(",");
                    int x2 = Integer.parseInt(splitted[0]);
                    int y2 = Integer.parseInt(splitted[1]);
                    
                    
                    // kullanıcının verdiği hamle geçerliyse işlemi yap
                    if(hamleGecerliMi(x1, y1, x2, y2)){
                    
                        // hamleyi yap
                        hamleYap(x1, y1, x2, y2);
                        
                        
                        // tahtada patlatılacak şeker grupları yok olana kadar patlatmayı yap ve tahtayı yeniden doldur
                        while(patlamaVarMi()){
                            
                            //System.out.println("BOOOM");
                            
                            // T şeklindeki gruplardan başlayarak bütün şeker gruplarını teker teker sil
                            buyukTSil();
                            tSil();
                            LSil();
                            besSil();
                            dortSil();
                            ucSil();

                            // şekerleri sildikten sonra kalan şekerleri aşağı düşür
                            sekerleriDusur();

                            //System.out.println();
                            //tahtayiYazdir();
                            
                            // bütün gruplar silindikten sonra tahtadaki boş kısımları doldur
                            bosluklariDoldur();
                            
                            //System.out.println();
                            //tahtayiYazdir();
                            
                            // her patlatmadan sonra tahtayı yeniden yazdır
                            //tahtayiYazdir();
                            
                        }

                        
                        // hamle yapıldıktan sonra hamle sayısını bir eksilt
                        kalanHamle--;
                        
                    }
                    else {
                        
                        System.out.println("Yaptığınız hamle geçersiz. Başka bir hamle seçin.");
                        
                    }
                    
                }
                // yapılacak hamle yoksa tahtayı karıştır
                else {
                    
                    
                    // tahtada yapılacak herhangi bir hamle oluşana kadar tahtayı karıştır
                    while(!gecerliHamleVarMi())
                        tahtaKaristir();
                    
                }

                

            }
            
            System.out.println();
            
            
        }

    }
    
    
    // tahtanın mevcut durumunu ekrana yazdır
    public static void tahtayiYazdir(){
        
        // tahtadaki bütün kareler için o karedeki karakterleri yazdır
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
