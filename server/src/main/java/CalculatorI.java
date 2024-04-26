import com.zeroc.Ice.Current;

public class CalculatorI implements MathCalc.Calculator{

    @Override
    public double add (double a, double b, Current current) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }

    @Override
    public double subtract (double a, double b, Current current) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a - b;
    }

    @Override
    public double multiply (double a, double b, Current current) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a * b;
    }

    @Override
    public double divide (double a, double b, Current current) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a / b;
    }
    
    
    @Override
    public int[] getArr(Current current) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] arr=new int[10];
        for(int i=0;i<10;i++){
            arr[i]=i;
        }
        return arr;
    }

    
}
