
import patchable_router.PatchableRouterDisplayer;
import src.UserInterface;
import src.CLI;

/***
* @author William Macdonald
*/
public class Main{

    public static void main(String[] args){

        UserInterface UI  = new CLI();
      
		if(args.length != 1){
		    System.out.println("Usage: java Main <filepath>" );
        }
		else{
			String csvFilePath = args[0];
        
            PatchableRouterDisplayer PRD = new PatchableRouterDisplayer(UI, csvFilePath);
        }
        
        
    }

}
