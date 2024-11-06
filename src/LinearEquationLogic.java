
import java.util.Scanner;

class LinearEquationLogic {
    private final Scanner input = new Scanner(System.in);
    private int[] cords = {0,0,0,0};// x1 y1 x2 y2
    private int tempX;
    private int tempY;

    private boolean isDouble(String toCheck) {
        try {
            Double.parseDouble(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;

        }
    }

    private boolean isInt(String toCheck) {
        try {
            Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;

        }
    }
    private boolean isFormatted(String coordinatePair){//checks if a string is formatted and if it is sets tempX and tempY to the x and y values of the coordinate pair
        char firstChar=coordinatePair.charAt(0);
        char lastChar=coordinatePair.charAt(coordinatePair.length()-1);

        if(firstChar=='(' && lastChar==')' && coordinatePair.contains(", ")){
            //req 4
            String x=coordinatePair.substring(1,coordinatePair.indexOf(","));
            String y=coordinatePair.substring(coordinatePair.indexOf(" ")+1,coordinatePair.indexOf(")"));
            if(isInt(x) && isInt(y)) {
                tempX = Integer.parseInt(x);
                tempY = Integer.parseInt(y);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    private void getCords(){
        //req 3
        System.out.print("Enter your first point in the form (x, y): ");
        String c1 = input.nextLine();

        while(!isFormatted(c1)){
            System.out.print("Enter your point in the form (x, y): ");
            c1 = input.nextLine();
        }

        cords[0] = tempX;//sets x1 of the coordinate pair to tempX which will always be a valid integer due to calls on isFormatted()
        cords[1] = tempY;//sets y1 of the coordinate pair to tempY which will always be a valid integer due to calls on isFormatted()

        System.out.print("Enter your second point in the form (x, y): ");
        String c2 = input.nextLine();

        while(!isFormatted(c2)){
            System.out.print("Enter your point in the form (x, y): ");
            c2 = input.nextLine();
        }

        cords[2] = tempX;//sets x2 of the coordinate pair to tempX which will always be a valid integer due to calls on isFormatted()
        cords[3] = tempY;//sets y2 of the coordinate pair to tempY which will always be a valid integer due to calls on isFormatted()
    }

    private void yAtX(LinearEquation equation){
        System.out.print("Enter a value for x: ");
        String x = input.nextLine();
        while(!isDouble(x)){
            System.out.print("Not a number enter again: ");
            x = input.nextLine();
        }
        //req 8
        System.out.println("The point on the line is "+equation.coordinateForX(Double.parseDouble(x)));
    }

    private void print(){//prints all info needed
        getCords();
        int y2 = cords[3];
        int y1 = cords[1];
        int x2 = cords[2];
        int x1 = cords[0];

        LinearEquation equation = new LinearEquation(x1,y1,x2,y2);//req 5
        System.out.println(equation.lineInfo());//req 6
        if(!(equation.getDiffX()==0)) {// req 10 pt 2
            yAtX(equation);//req 7
        }
    }

    private boolean runAgain(){// returns true if y or Y is entered and false if n or N does not end until one of these is entered as an input
        System.out.print("Would you like to enter another pair of coordinates(y/n): ");
        String again=input.nextLine();
        while(!again.toLowerCase().matches("[y,n]")){
            System.out.println("Invalid enter y for yes or n for no: ");
            again=input.nextLine();
        }
        return again.equalsIgnoreCase("y");
    }
    public void start(){//req 2
        print();
        while(runAgain()){//req 11
            print();
        }
    }


}
