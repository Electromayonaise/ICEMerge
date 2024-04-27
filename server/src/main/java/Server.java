import MathCalc.*;
import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;

public class Server {
    
    public static void main(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "server.config")) {
            
            ObjectAdapter adapter = communicator.createObjectAdapter("services");

            Object object = new CalculatorI(communicator);
            
            adapter.add(object, Util.stringToIdentity("DistributedCalculator"));
            adapter.activate();
            communicator.waitForShutdown();
        }
    }
    public static void captureSecondaryServers(String[] args){
         while(true){
            try(Communicator communicator = Util.initialize(args, "server.config")) {
               // ObjectPrx base = communicator.propertyToProxy("secondaryServer.proxy");
                ObjectPrx base = communicator.stringToProxy("DistributedCalculator: tcp -h localhost -p 58581");

                CalculatorPrx calculator = CalculatorPrx.checkedCast(base);
                if(calculator == null) {
                    throw new Error("Invalid proxy");
                }
    
            }catch(com.zeroc.Ice.Exception e){
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (java.lang.Exception e) {
                e.printStackTrace();
            }
            

        }
        
        
        
    }
}