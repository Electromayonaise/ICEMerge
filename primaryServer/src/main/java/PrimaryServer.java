import ICEBucket.*;
import com.zeroc.Ice.*;

public class PrimaryServer {

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args, "primaryServer.config")) {
            ObjectAdapter adapter = communicator.createObjectAdapter("services");

            // Create an instance of the PrimaryServer implementation
            PrimaryServerImpl serverImpl = new PrimaryServerImpl();

            // Add the implementation object to the adapter
            adapter.add(serverImpl, Util.stringToIdentity("PrimaryServer"));

            // Activate the object adapter
            adapter.activate();

            System.out.println("PrimaryServer is running...");


            communicator.waitForShutdown();
        } catch (Ice.LocalException e) {
            e.printStackTrace();
        }
    }

    public void SortFileMiddleMan(List<T> data, Comparator<T> comparator) {
        try (Communicator communicator = Util.initialize(args, "primaryServer.config")) {
            ObjectPrx base = communicator.propertyToProxy("helperServer.proxy");
            HelperServerPrx helperServer = HelperServerPrx.checkedCast(base);
            if (helperServer == null) {
                throw new Error("Invalid proxy");
            } else {
                SortingQuery.sortFileAsync(data, comparator, helperServer);
            }
        } catch (Ice.LocalException e) {
            e.printStackTrace();
        }

        
    }

    
}
