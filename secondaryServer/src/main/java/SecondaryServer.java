import MathCalc.*;
import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;

public class SecondaryServer {
    
    public static void main(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "secondaryServer.config")) {
            System.out.println("Secondary server init");
            
            ObjectAdapter adapter = communicator.createObjectAdapter("services");


            
            
            


            Object object = new CalculatorI();
            adapter.add(object, Util.stringToIdentity("DistributedCalculator"));
            adapter.activate();

            Endpoint[] endpoints=adapter.getEndpoints();
            String inf=endpoints[0]._toString();
            String [] fields=inf.split(" ");
            String ip=fields[2];
            String port=fields[4];
            System.out.println("IP: "+ ip);
            System.out.println("PORT:"+ port);
            
            sendIpAndPortToMainServer(communicator,ip,port);

            communicator.waitForShutdown();
            
        }
    }

    public static void sendIpAndPortToMainServer(Communicator communicator,String ip, String port){
        ObjectPrx base = communicator.propertyToProxy("server.proxy");
        CalculatorPrx calculator = CalculatorPrx.checkedCast(base);
        if(calculator == null) {
            throw new Error("Invalid proxy");
        }
        calculator.initConnection(ip,port);
          
       
   }

}