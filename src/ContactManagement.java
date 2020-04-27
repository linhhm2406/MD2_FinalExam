import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManagement {
    public static final int FIND_BY_PHONE_NUMBER = 1;
    public static final int FIND_BY_NAME = 2;

    ArrayList<Contact> contactList = new ArrayList<>();
    ArrayList<Contact> findingList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    File allListTargetFile = new File("data/contact.csv");
    File findTargetFile = new File("data/find_contact.csv");

    public void initBeginningList() {
        contactList.add(new Contact("0997888888", "Family", "Hoang Manh Linh", "Male", "Ha Noi", "24/06/1991", "linh@gmail.com"));
        contactList.add(new Contact("0993214568", "Friend", "Truong Gia Han", "Male", "Ha Giang", "08/12/6548", "a@gmail.com"));
        contactList.add(new Contact("0998755168", "Friend", "Chau Du Dan", "Female", "Bac Ninh", "05/11/3216", "n@gmail.com"));
        contactList.add(new Contact("0548641588", "Work", "Tran Con", "Male", "HCM", "00/00/0000", "c@gmail.com"));
        contactList.add(new Contact("0658468165", "Family", "Nguyen Anh9", "Female", "An Giang", "11/10/3288", "d@gmail.com"));
    }

    public void writeContactToFile(File target, ArrayList<Contact> list) {
        OutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            os = new FileOutputStream(target);
            oos = new ObjectOutputStream(os);

            for (Contact contact : list) {
                oos.writeObject(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readContactFromFile(File target) {
        InputStream is = null;
        ObjectInputStream ois = null;

        try {
            is = new FileInputStream(target);
            ois = new ObjectInputStream(is);

            Contact contact;
            while ((contact = (Contact) ois.readObject()) != null) {
                System.out.println(contact);
            }
        } catch (Exception ignored) {
        } finally {
            try {
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeMainListIntoMainFile() {
        writeContactToFile(allListTargetFile, contactList);
    }

    public void readMainAndPrintOut() {
        readContactFromFile(allListTargetFile);
    }

    public void writeIntoSearchFile() {
        writeContactToFile(findTargetFile, findingList);
    }

    public void readSearchAndPrintOut() {
        readContactFromFile(findTargetFile);
    }

    public String inputPhoneNumber() {
        System.out.println("Input Phone Number : ");
        return scanner.nextLine();
    }

    public String inputGroup() {
        System.out.println("Input Group : ");
        return scanner.nextLine();
    }

    public String inputName() {
        System.out.println("Input Name : ");
        return scanner.nextLine();
    }

    public String inputGender() {
        System.out.println("Input Gender : ");
        return scanner.nextLine();
    }

    public String inputAddress() {
        System.out.println("Input Address : ");
        return scanner.nextLine();
    }

    public String inputDOB() {
        String dob;
        do {
            System.out.println("Input Date Of Birth (dd/mm/yyyy): ");
            dob = scanner.nextLine();
            if (matchValidateDOB(dob)) {
                return dob;
            } else {
                System.out.println("Invalid format (dd/mm/yyyy)");
            }
        } while (!matchValidateDOB(dob));
        return dob;
    }

    private boolean matchValidateDOB(String dob) {
        String dobRegex = "([0-2][0-9]|[3][0-1])/([0][1-9]|[1][0-2])/[0-9]{4}";
        return dob.matches(dobRegex);
    }

    public String inputEmail() {
        String email;
        do {
            System.out.println("Input Email (aaa@ss.cc): ");
            email = scanner.nextLine();
            if (matchValidateEmail(email)) {
                return email;
            } else {
                System.out.println("Invalid format (aaa@ss.cc)");
            }
        } while (!matchValidateDOB(email));
        return email;
    }

    private boolean matchValidateEmail(String email) {
        String emailRegex = "\\w+@\\w+.\\w+";
        return email.matches(emailRegex);
    }

    public void addNewContact() {
        Contact newContact = new Contact(inputPhoneNumber(), inputGroup(),
                inputName(), inputGender(), inputAddress(), inputDOB(), inputEmail());
        contactList.add(newContact);
    }

    public int getFindByFromUser() {
        System.out.println("Select Item Number: ");
        System.out.println("1. Phone Number" +
                "\n2. Name");
        System.out.println("Find by : ");
        return scanner.nextInt();
    }

    public String getFindContentFromUser() {
        scanner.nextLine();
        System.out.println("Find Content : ");
        return scanner.nextLine();
    }

    public void addFindOutContactToFindingList(int findBy, String content) {
        switch (findBy) {
            case FIND_BY_PHONE_NUMBER: {
                toFindByPhoneNumber(content);
                break;
            }
            case FIND_BY_NAME: {
                toFindByName(content);
                break;
            }
        }
    }

    private void toFindByPhoneNumber(String content) {
        for (int i = 0; i < contactList.size(); i++) {
            if (content.contains(contactList.get(i).getPhoneNumber())) {
                findingList.add(contactList.get(i));
            }
        }
    }

    private void toFindByName(String content) {
        for (int i = 0; i < contactList.size(); i++) {
            if (content.contains(contactList.get(i).getName())) {
                findingList.add(contactList.get(i));
            }
        }
    }

    public void findContact() {
        addFindOutContactToFindingList(getFindByFromUser(), getFindContentFromUser());
        writeIntoSearchFile();
        readSearchAndPrintOut();
    }

    public String getPhoneNumberFromUser() {
        System.out.println("Input Phone Number to Update : ");
        return scanner.nextLine();
    }

    public Contact havePhoneNumberInList(String phoneNumber) {
        Contact contact = null;
        for (int i = 0; i < contactList.size(); i++) {
            if (phoneNumber.equals(contactList.get(i).getPhoneNumber())) {
                System.out.println("FindOut Phone Number");
                contact = contactList.get(i);
            }
        }
        return contact;
    }

    public void updateInformation() {
        Contact contactToUpdate = null;
        do {
            contactToUpdate = havePhoneNumberInList(getPhoneNumberFromUser());
            if (contactToUpdate != null) {
                System.out.println("Input New Information :");
                contactToUpdate.setGroup(inputGroup());
                contactToUpdate.setName(inputName());
                contactToUpdate.setGender(inputGender());
                contactToUpdate.setAddress(inputAddress());
                contactToUpdate.setDateOfBirth(inputDOB());
                contactToUpdate.setEmail(inputEmail());
            } else {
                System.out.println("Can not find Phone Number, please input again");
            }
        }
        while (contactToUpdate == null);
    }

    public void delete() {
        Contact contactToDelete = null;
        do {
            contactToDelete = havePhoneNumberInList(getPhoneNumberFromUser());
            if (contactToDelete != null) {
                contactList.remove(contactToDelete);
            } else {
                System.out.println("Can not find Phone Number, please input again");
            }
        }
        while (contactToDelete == null);
    }
}
