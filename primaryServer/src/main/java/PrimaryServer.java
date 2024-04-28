import BucketICE.*;
import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;

public class PrimaryServer {
    
    public static void main(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "primaryServer.config")) {
            
            ObjectAdapter adapter = communicator.createObjectAdapter("services");

            Object object = new Bucket(communicator);
            
            adapter.add(object, Util.stringToIdentity("BucketICE"));
            adapter.activate();
            communicator.waitForShutdown();
        }
    }
    
}