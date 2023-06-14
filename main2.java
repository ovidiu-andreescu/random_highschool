//package converter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Scanner read = new Scanner(System.in);
        long num;
        int base;
        String[] req;
        
        do {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) >");
            req = read.nextLine().split(" ");
            
            if (req.length == 1 && req.equals("/exit") break;
            
            
                num = read.nextLong();
                base = read.nextInt();
                System.out.printf("Conversion result: %s\n", convertFrom(num, base).reverse());
            } else if (req.equals("/to")) {
                System.out.print("Enter source number: ");
                String strNum = read.next();
                System.out.print("Enter source base: ");
                base = read.nextInt();
                System.out.printf("Conversion to decimal result: %d\n", convertTo(strNum.toUpperCase(), base));
            }
            
        } while(!req.equals("/exit"));
        
    }
    
    static StringBuilder convertFrom(String strNum, int base) {
        StringBuilder convNum = new StringBuilder("");
        int nr = 0;
        long rem;
        
        while (Math.pow(base, nr) < num) nr++;
        for (int i = 0; i < nr; i++) {
            rem = num % base;
            if (rem > 9) {
                convNum.append((char) (55 + rem));
            } else {
                convNum.append(rem);
            }
            strum /= base;
        }
        return convNum;
    }
    
    static long convertTo(String strNum, int base) {
        char[] numCh = strNum.toCharArray();
        long convNum = 0;
        
        for (int i = 0; i < numCh.length; i++) {
            int tmpNm = 0;
            if ((int) numCh[i] > 64) {
                tmpNm = (int) numCh[i] - 55;  
            } else {
                tmpNm = (int) numCh[i] - 48;
            }
            convNum += tmpNm * Math.pow(base, numCh.length - i - 1);
        }      
        return convNum;
    }
}
