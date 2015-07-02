

import java.io.IOException;

import com.sendsafely.SendSafely;
import com.sendsafely.dto.File;
import com.sendsafely.dto.PackageInformation;
import com.sendsafely.dto.PackageURL;
import com.sendsafely.dto.Recipient;
import com.sendsafely.exceptions.AddRecipientFailedException;
import com.sendsafely.exceptions.ApproverRequiredException;
import com.sendsafely.exceptions.CreatePackageFailedException;
import com.sendsafely.exceptions.FinalizePackageFailedException;
import com.sendsafely.exceptions.InvalidCredentialsException;
import com.sendsafely.exceptions.LimitExceededException;
import com.sendsafely.exceptions.SendFailedException;
import com.sendsafely.exceptions.UploadFileException;

public class SendSafelyRefApp {
	
	public static void main(String[] args) throws SendFailedException, IOException, InvalidCredentialsException, CreatePackageFailedException, LimitExceededException, AddRecipientFailedException, FinalizePackageFailedException, UploadFileException, ApproverRequiredException
	{
		/*
		 * This example will read in the following command line arguments:
		 *
		 * SendSafelyHost: The SendSafely hostname to connect to. Enterprise users should connect to their designated 
		 *	Enterprise host (company-name.sendsafely.com)
		 *
		 * UserApiKey: The API key for the user you want to connect to.  API Keys can be obtained from the Edit 
		 *  Profile screen when logged in to SendSafely
		 *
		 * UserApiSecret: The API Secret associated with the API Key used above.  The API Secret is provided to  
		 *  you when you generate a new API Key.  
		 *
		 * FileToUpload: Local path to the file you want to upload. Can be any file up to 2GB in size. 
		 *
		 * RecipientEmailAddress: The email address of the person you want to send the file to. 
		 */
		
		if (args == null || (args.length != 5))
		{
			// Invalid number of arguments.  Print the usage syntax to the screen and exit. 
			System.out.println("Usage: " + SendSafelyRefApp.class.getName() + " SendSafelyHost UserApiKey UserApiSecret FileToUpload RecipientEmailAddress");
			System.out.println("\nThis program will print out the secure URL to access the package after it has been submitted.");
            return;
		}
		else
		{
			// Valid arguments provided.  Assign each argument to a local variable 
            String sendSafelyHost = args[0];
            String userApiKey = args[1];
            String userApiSecret = args[2];
            String fileToUpload = args[3];
            String recipientEmailAddress = args[4];
            
            System.out.println("Initializing API");
            SendSafely sendSafely = new SendSafely(sendSafelyHost, userApiKey, userApiSecret);
            
            String userEmail = sendSafely.verifyCredentials();
            System.out.println("Connected to SendSafely as user: " + userEmail);
            
            // Create a new empty package
            PackageInformation pkgInfo = sendSafely.createPackage();
            
            String packageId = pkgInfo.getPackageId();
            System.out.println("Created new empty package with PackageID#" + packageId);
            
            Recipient newRecipient = sendSafely.addRecipient(packageId, recipientEmailAddress);
            System.out.println("Adding Recipient (Recipient ID#" + newRecipient.getRecipientId() + ")");
            
            System.out.println("Uploading File");
            File addedFile = sendSafely.encryptAndUploadFile(packageId, pkgInfo.getKeyCode(), new java.io.File(fileToUpload), new ProgressCallback());
            System.out.println("Upload Complete - File Id#" + addedFile.getFileId() + ".  Finalizing Package.");

            // Package is finished, call the finalize method to make the package available for pickup and print the URL for access.
            PackageURL packageLink = sendSafely.finalizePackage(packageId, pkgInfo.getKeyCode());
            System.out.println("Success: " + packageLink.getSecureLink());
            
		}
		
	}
	
}