import java.util.Scanner;

class findProp {
    long val;
    boolean[] prop = new boolean[12];
    
    public findProp(long val) {
        this.val = val;
        this.prop[0] = val % 2 == 0; //even
        this.prop[1] = !this.prop[0]; //odd
        this.prop[2] = val % 10 == 7 || val % 7 == 0; //buzz
        this.prop[3] = Long.toString(val).trim().contains("0"); //duck
        this.prop[4] = palindromic(val); //palindromic
        this.prop[5] = gapful(val); //gapful
        this.prop[6] = spy(val); //spy
        this.prop[7] = Math.sqrt(val) == Math.floor(Math.sqrt(val)); //square
        this.prop[8] = Math.sqrt(val + 1) == Math.floor(Math.sqrt(val + 1)); //sunny
        this.prop[9] = jumping(val); //jumping
        this.prop[10] = happy(val); //happy
        this.prop[11] = !this.prop[10]; //sad
        
    }
    
    boolean palindromic(long val) {
        long cop = val;
        long inv = 0;
        while (cop != 0) {
            inv = inv * 10 + cop % 10;
            cop /= 10;
        }
        return inv == val;
    }
    
    boolean gapful(long val) {
        long cop = val;
        int nr = 0;
        while (cop / 10 > 0) {
            cop /= 10;
            nr++;
        }
        return (nr + 1) > 2 && val % (cop * 10 + val % 10) == 0;
    }
    
    boolean spy(long val) {
        int sum = 0;
        int prod = 1;
        while(val != 0) {
            sum += val % 10;
            prod *= val % 10;
            val /= 10;
        }
       return sum == prod;
    }
    
    boolean jumping(long val) {
        if (val >= 0 && val < 10) return true;
        boolean status = true;
        int size = Long.toString(val).length();
        long[] v = new long[size];
        int i = 0;
        while (val > 0) {
            v[size - i - 1] = val % 10;
            val /= 10;
            i++;
        }
        for (int j = 0; j < size - 1; j++) {
            if(v[j + 1] != v[j] + 1 && v[j + 1] != v[j] - 1) {
                status = false;                
            }
        }
        return status;
    }
    
    boolean happy(long val) {
        int suma = 0;
        while (val > 0) {
            suma += Math.pow(val % 10, 2);
            val = val / 10;
        }
 
        if (suma == 1) {
            return true;
        } else if (suma > 1 && suma <= 4) {
            return false;
        }
        return happy(suma);
    }
    
    void afis() {
        System.out.println("Properties of " + this.val + "\n" +
                "    even: " + this.prop[0] + "\n" +
                "    odd: " + this.prop[1] + "\n" +
                "    buzz: " + this.prop[2] + "\n" +
                "    duck: " + this.prop[3] + "\n" +
                "    palindromic: " + this.prop[4] + "\n" +
                "    gapful: " + this.prop[5] + "\n" +
                "    spy: " + this.prop[6] + "\n" +
                "    square: " + this.prop[7] + "\n" +
                "    sunny: " + this.prop[8] + "\n" + 
                "    jumping: " + this.prop[9] + "\n" +
                "    happy: " + this.prop[10] + "\n" +
                "    sad: " + this.prop[11] + "\n");
    }
}

class getParam {
    String str;
    int poz;
    boolean sign;
    
    getParam(String str) {
        this.str = str;
        findSign(this.str);
    }
    
    void findSign(String str) {
        if (str.toCharArray()[0] == '-') {
            this.sign = false;
            this.poz = denum.checkValid(str.substring(1, str.length()).toLowerCase());
        } else {
            this.sign = true;
            this.poz = denum.checkValid(str.toLowerCase());
        }
    }
    
    public enum denum {
        EVEN(0, "even"),
        ODD(1, "odd"), 
        BUZZ(2, "buzz"),
        DUCK(3, "duck"),
        PALINDROMIC(4, "palindromic"),
        GAPFUL(5, "gapful"),
        SPY(6, "spy"),
        SQUARE(7, "square"),
        SUNNY(8, "sunny"), 
        JUMPING(9, "jumping"),
        HAPPY(10, "happy"),
        SAD(11, "sad");
        
        int poz;
        String str;
        
        denum(int poz, String str) {
            this.poz = poz;
            this.str = str;
        }
        
        public static int checkValid(String strCheck) {
            for (denum value: values()) {
                if (value.str.equals(strCheck)) {
                    return value.poz;
                } 
            }
            return -1;
        } 
            
        public static String getByPoz(long tmPoz) {
            for (denum value: values()) {
                if (value.poz == tmPoz) {
                    return value.str;
                } 
            }
        return null;
        } 
    }
  
}

public class Main {
    
    
    public static void main(String[] args) {
        final Scanner read = new Scanner(System.in);
        long[] number = new long[2];
        
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        req();
        
        
        do {
            System.out.println("Enter a request: ");
            String[] input = read.nextLine().split(" ");
            number[0] = Long.parseLong(input[0]);
            
            if (number[0] < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            } else if (number[0] == 0) break;
            
            findProp obj = new findProp(number[0]);
            
            if (input.length > 1) {
                number[1] = Long.parseLong(input[1]);
                if (number[1] < 0) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                
                String propAfis = "";
                
                if (input.length > 2) {
                    getParam[] toCheck = new getParam[input.length - 2];
                    String err = "";
                    
                    for (int i = 0; i < toCheck.length; i++) {
                        toCheck[i] = new getParam(input[i + 2]);
                        if (toCheck[i].poz == -1) {
                            err += toCheck[i].str + " ";
                        }    
                    }
                    
                    arraySort(toCheck);
                    
                    if (!err.equals("")) {
                        if (err.split(" ").length > 1) { 
                            err = err.substring(0, err.length() - 1).replace(" ", ", ");
                            System.out.printf("The properties [%s] are wrong.\n", err);
                        } else {
                            System.out.printf("The property [%s] is wrong.\n", err.substring(0, err.length() - 1));
                        }
                        System.out.println("Available properties:");
                        System.out.println("[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                        continue;
                    }
                    
                    boolean progStat = true;
                    
                    for (int i = 0; i < toCheck.length - 1 && progStat; i++) {
                        for (int j = i + 1; j < toCheck.length; j++) {
                            if (toCheck[i].poz == toCheck[j].poz && toCheck[i].sign != toCheck[j].sign) {
                                System.out.printf("The request contains mutually exclusive properties: [%s, -%s]\n", toCheck[i].str, toCheck[i].str);
                                System.out.println("There are no numbers with these properties.");
                                progStat = false;
                                break;
                            }
                            if (toCheck[i].sign == toCheck[j].sign) {
                                if(toCheck[i].poz == 0 && toCheck[j].poz == 1 || toCheck[i].poz == 3 && toCheck[j].poz == 6 || toCheck[i].poz == 7 && toCheck[j].poz == 8 || toCheck[i].poz == 10 && toCheck[j].poz == 11) {
                                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", toCheck[i].str, toCheck[j].str);
                                    System.out.println("There are no numbers with these properties.");
                                    progStat = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (!progStat) continue;
                    
                    int cnt = 0;
                    
                    while (cnt != number[1]) {
                        boolean status = true;
                        propAfis = "";
                        
                        for(int i = 0; i < toCheck.length; i++) {
                            if (obj.prop[toCheck[i].poz] != toCheck[i].sign)
                                status = false;
                        }
                        
                        if(status == true) {
                            System.out.print(obj.val + " is ");
                            for(int i = 0; i < 12; i++) {
                                if (obj.prop[i]) propAfis += getParam.denum.getByPoz(i) + " ";
                            }
                            
                            cnt++;
                            System.out.println(propAfis.substring(0, propAfis.length() - 1).replace(" ", ", "));
                        }
                        
                        obj = new findProp(obj.val + 1);
                    }
                    continue;
                }
                
                for (long i = number[0] + 1; i < number[0] + number[1] + 1; i++) {
                    propAfis = "";
                    System.out.print(obj.val + " is ");
                    for (int j = 0; j < 12; j++) {
                        if (obj.prop[j]) {
                            propAfis += getParam.denum.getByPoz(j) + " ";
                        }
                    }
                    System.out.println(propAfis.substring(0, propAfis.length() - 1).replace(" ", ", "));
                    obj = new findProp(i);
                }
                
            } else {
                obj.afis();
            }
            
        } while(number[0] != 0);
    }
    
    static void req(){
        System.out.println("Supported requests:\n" + 
            "- enter a natural number to know its properties;\n" +
            "- enter two natural numbers to obtain the properties of the list:\n" +
            "* the first parameter represents a starting number;\n" +
            "* the second parameters show how many consecutive numbers are to be processed;\n" +
            "- two natural numbers and properties to search for;\n" +
            "- a property preceded by minus must not be present in numbers;" +
            "- separate the parameters with one space;\n" +
            "- enter 0 to exit.\n");
    }
    
    static void arraySort(getParam[] toCheck) {
        for (int i = 0; i < toCheck.length - 1; i++) {
            for (int j = i + 1; j < toCheck.length; j++) {
                if (toCheck[i].poz > toCheck[j].poz){ 
                    getParam tmpObj = toCheck[j];
                    toCheck[j] = toCheck[i];
                    toCheck[i] = tmpObj;
                }
            }
        }
    }
}


