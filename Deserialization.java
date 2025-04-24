import java.io.*;
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

//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
//        {
//            out.writeObject(user);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

        //Here is how you deserialize back to an object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
        {
            User u = (User) in.readObject();
            System.out.println(u.name[0] + " " + u.name[1] + " " + u.name[2] + " " + u.isBald + "\n");
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
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
