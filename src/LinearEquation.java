import java.math.BigInteger;

public class LinearEquation {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final int diffX;
    private final int diffY;
    public LinearEquation(int x1, int y1, int x2, int y2){//req 12
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        diffX = x2 - x1;
        diffY = y2 - y1;
    }

    public int getDiffX(){
        return diffX;
    }

    private int greatestCommonFactor(int num1, int num2){
        BigInteger n1= new BigInteger(String.valueOf(num1));
        BigInteger n2= new BigInteger(String.valueOf(num2));
        return Integer.parseInt(String.valueOf(n1.gcd(n2)));
    }

    private double  roundedToHundredth(double toRound){// req 20
        return Math.round(toRound*100)/100.0;
    }



    double distance(){// req 13
        return roundedToHundredth(Math.sqrt((diffY*diffY)+(diffX*diffX)));//returns square root of x^2 + y^2(pythagorean theorem)
    }


    double slope(){// req 15
        return roundedToHundredth((double)diffY/diffX);//slope formula
    }


    double yIntercept(){// req 14
        return y1-slope()*x1;
    }


    String equation(){// req 16
        if(x1==x2){// req 10 pt 1
            return "x = "+x1;
        }else if(y1==y2){// req 17
            return "y = "+yIntercept();
        }else {
            int gcf=greatestCommonFactor(diffX, diffY);
            String xSign="";
            if(slope()<0) xSign="-";

            String ySign;
            if(yIntercept()>0) ySign="+ " + Math.abs(yIntercept());
            else if(yIntercept()<0) ySign="- " + Math.abs(yIntercept());
            else ySign="";
            if(Math.abs(diffY)==Math.abs(diffX)){
                return "y = " + xSign + "x " + ySign;
            }
            else if((double)diffY/diffX==diffY/diffX){
                return "y = " + xSign + Math.abs(diffY/diffX) + "x " + ySign;
            }else {
                return "y = " + xSign + Math.abs(diffY/gcf) + "/" + Math.abs(diffX/gcf) + "x " + ySign;
            }
        }
    }


    String coordinateForX(double xCord){// req 19
        double yCord=slope()*xCord+yIntercept();
        return "("+roundedToHundredth(xCord)+", "+roundedToHundredth(yCord)+")";
    }


    String lineInfo(){// req 18
        if(diffX==0){
            return "These points are on a vertical line "+equation();
        }
        return "The two points are ("+x1+", "+y1+") and ("+x2+", "+y2+")"
                +"\nThe equation of the line between these points is: "+equation()
                +"\nThe y-intercept of this line is: "+yIntercept()
                +"\nThe slope of this line is: "+slope()
                +"\nThe distance between these points is: "+distance();
    }


}
