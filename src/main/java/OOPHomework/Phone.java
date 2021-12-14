package OOPHomework;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Phone {
    private PhoneManufacturer manufacturer;
    private String model;
    private int batteryLife;
    private Color color;
    private String material;
    private String imeiNumber;

    public Phone(PhoneManufacturer manufacturer, String model, int batteryLife, Color color, String material) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.batteryLife = batteryLife;
        this.color = color;
        this.material = material;
        this.imeiNumber = getRandomImeiNumber();
    }

    private String getRandomImeiNumber() {
        Random random = new Random();
        int newGenImei = random.nextInt(999_999_999);
        return String.format("%09d", newGenImei) ;
    }

    public PhoneManufacturer getManufacturer() {
        return manufacturer;
    }


    public String getModel() {
        return model;
    }


    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public Color getColor() {
        return color;
    }


    public String getMaterial() {
        return material;
    }


    public String getImeiNumber() {
        return imeiNumber;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "manufacturer=" + manufacturer +
                ", model='" + model + '\'' +
                ", batteryLife=" + batteryLife +
                ", color=" + color +
                ", material='" + material + '\'' +
                ", imeiNumber=" + imeiNumber +
                '}';
    }

    ArrayList<Agenda> agenda = new ArrayList<>();
    HashMap<String, List<String>> messageHistory = new HashMap<>();
    ArrayList<String> callHistory = new ArrayList<>();

    public void addContact(int id, String phoneNumber, String firstName, String lastName) {
        Agenda newContact = new Agenda(id, phoneNumber,firstName, lastName);
        agenda.add(newContact);
        System.out.println("Contact " + newContact.getPhoneNumber() + " added.");
    }

    public void listContacts() {
        for (Agenda value : agenda) {
            System.out.println(value.toString());
        }
    }

    public void sendMessage(String phoneNumber, String messageText) {
        if (messageText.length() < 500) {
            if (messageHistory.containsKey(phoneNumber)) {
                messageHistory.get(phoneNumber).add(messageText);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(messageText);
                messageHistory.put(phoneNumber, list);
            }
            batteryLife --;
        } else {
            System.out.println("Message length exceeded");
        }
    }

    public void listMessages(String phoneNumber) {
        System.out.println(messageHistory.get(phoneNumber));
    }

    public void call(String phoneNumber) {
        callHistory.add(phoneNumber);
        batteryLife = batteryLife-2;
        System.out.println("Call to " + phoneNumber + " executed.");
    }

    public void viewHistory() {
        System.out.println(callHistory);
    }

}
