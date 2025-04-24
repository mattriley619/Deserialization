
import java.io.*;


class GoldenGrahams{


    public static void main(String args[]){

        Cereal c = new Cereal();
        c.setAge(50);
        c.setName("Some Dude");

        //Here is how you serialize to store or transfer the object
        String file = "example.ser";
/*
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(c);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Here is how you deserialize back to an object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Cereal d = (Cereal) in.readObject();
            System.out.println(d.getName() + " " + d.getAge() + "\n");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}

class Cereal implements Serializable{

    String name;
    int age;


    void setAge(int x){
        age = x;
    }
    void setName(String x){
        name = x;
    }
    int getAge(){return age;}
    String getName(){return name;}

}