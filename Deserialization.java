import java.io.*;
import java.nio.file.*;
import java.security.*;

public class Deserialization
{
    public static void main(String args[])
    {
        String userFile = "user.ser";
        String verifierFile = "verifier.ser";

        User user = new User();
        user.setAge(40);
        user.setBald(true);
        user.setHeight(6.1);
        user.setName("John", "Lee", "Smith");

        Verifier verifier = new Verifier();

        // Serialization -------------------------------------------------------------------------

        try (ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(userFile)))
        {
            out1.writeObject(user);

//            byte[] hash = computeSHA256(userFile);
//            verifier.setUserHash(hash);

            try (ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(verifierFile))) {
                out2.writeObject(verifier);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // Deserialization --------------------------------------------------------------------------

        try (ObjectInputStream inVerifier = new ObjectInputStream(new FileInputStream(verifierFile));
             ObjectInputStream inUser = new ObjectInputStream(new FileInputStream(userFile)))
        {
            verifier = (Verifier) inVerifier.readObject();
            User u = (User) inUser.readObject();

            System.out.println("Verifier UUID: " + verifier.getUUID());

            if (verifier.getUUID() == null) {
                throw new SecurityException("File tampered with. Invalid UUID.");
            }

//            byte[] currentHash = computeSHA256(userFile);
//
//            if (!MessageDigest.isEqual(currentHash, verifier.getUserHash())) {
//                throw new SecurityException("File tampered with. Hash mismatch.");
//            }

           // System.out.println(u.name[0] + " " + u.name[1] + " " + u.name[2] + " " + u.isBald + "\n");
            System.out.println(user.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static byte[] computeSHA256(String filePath) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        return digest.digest(fileBytes);
    }


    static class User implements Serializable
    {
        private static final long serialVersionUID = 1L;
        String[] name;
        String birthday;
        String address;
        int age;
        double height;
        boolean isBald;

        void setName(String first, String middle, String last)
        {
            name = new String[3];
            name[0] = first;
            name[1] = middle;
            name[2] = last;
        }

        void setAge(int x)
        {
            age = x;
        }

        void setHeight(double x)
        {
            height = x;
        }

        void setBald(boolean x)
        {
            isBald = x;
        }
    }
}
