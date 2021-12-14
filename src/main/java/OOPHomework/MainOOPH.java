package OOPHomework;

import java.awt.*;

public class MainOOPH {
    public static void main(String[] args) {

        Phone phone1 = new Phone(PhoneManufacturer.SAMSUNG, "Galaxy S8", 100, Color.green, "glass");
        Phone phone2 = new Phone(PhoneManufacturer.HUAWEI, "Mate 20 Pro", 120, Color.black, "plastic");
        System.out.println(phone1);
        System.out.println(phone2);

        phone1.addContact(1, "0723432009", "Mihai", "Aranghel");
        phone1.addContact(2, "0789234566", "Daniel", "Zarug");

        phone1.sendMessage("0723432009", "Let's play something!");
        phone1.call("0789234566");

        phone1.sendMessage("0723432009", "How about eating a burger?");
        phone1.call("0723432009");

        phone1.listMessages("0723432009");
        phone1.viewHistory();

        System.out.println(phone1);
        System.out.println(phone2);
        phone1.listContacts();






    }
}
