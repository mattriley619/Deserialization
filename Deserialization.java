import java.io.*;
import java.nio.file.*;
import java.security.*;

public class Deserialization
{
    public static void main(String args[])
    {
        User user = new User();
        user.setAge(40);
        user.setBald(true);
        user.setHeight(6.1);
        user.setName("John", "Lee", "Smith");

        //Here is how you serialize to store or transfer the object
        String file = "example.ser";


        // write to file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
        {
            out.writeObject(user);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        // create and store hash
        try
        {
            byte[] hash = computeSHA256(file);
            Files.write(Paths.get("example.sha256"), hash);
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            byte[] currentHash = computeSHA256(file);
            byte[] originalHash = Files.readAllBytes(Paths.get("example.sha256"));

            if (!MessageDigest.isEqual(currentHash, originalHash))
            {
                throw new SecurityException("Hash mismatch. File has been tampered with.");
            }

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
            {
                User u = (User) in.readObject();
                System.out.println(u.name[0] + " " + u.name[1] + " " + u.name[2] + " " + u.isBald + "\n");
            }

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    public static byte[] computeSHA256(String filePath) throws IOException, NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        return digest.digest(fileBytes);
    }

}

class User implements Serializable
{
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
