import MergeICE.*;
import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ObjectAdapter;

public class HelperServer {
    
    public static void main(String[] args) {

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "helperServer.config")) {


            System.out.println("Secondary server init");
            
            ObjectAdapter adapter = communicator.createObjectAdapter("services");

            Object object = new MergeSort();
            adapter.add(object, Util.stringToIdentity("MergeICE"));
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
        DistributedSortingPrx sorter = DistributedSortingPrx.checkedCast(base);
        if(sorter == null) {
            throw new Error("Invalid proxy");
        }
        sorter.initConnection(ip,port);
          
       
   }

}