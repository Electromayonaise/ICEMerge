module MathCalc {
sequence<int> intSeq;

 interface Calculator {
 double add(double x, double y);
 double subtract(double x, double y);
 double multiply(double x, double y);
 double divide(double x, double y);
 intSeq getArr(intSeq clientArray);
 }


}
