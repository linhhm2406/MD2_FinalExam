import java.util.Scanner;

public class Main {

    public static final int SHOW_LIST = 1;
    public static final int ADD_CONTACT = 2;
    public static final int UPDATE = 3;
    public static final int DELETE = 4;
    public static final int FIND_CONTACT = 5;
    public static final int READ_FROM_FILE = 6;
    public static final int WRITE_TO_FILE = 7;
    public static final int EXIT = 8;

    public static void main(String[] args) {
        ContactManagement contactManagement = new ContactManagement();
        contactManagement.initBeginningList();
        contactManagement.writeMainListIntoMainFile();
        int userChoice;
        do {
            userChoice = setMenu();

            switch (userChoice) {
                case SHOW_LIST: {
                    contactManagement.readMainAndPrintOut();
                    break;
                }
                case ADD_CONTACT: {
                    contactManagement.addNewContact();
                    contactManagement.readSearchAndPrintOut();
                    break;
                }
                case UPDATE: {
                    contactManagement.updateInformation();
                    contactManagement.readSearchAndPrintOut();
                    break;
                }
                case DELETE: {
                    contactManagement.delete();
                    contactManagement.readSearchAndPrintOut();
                    break;
                }
                case FIND_CONTACT: {
                    contactManagement.findContact();
                    break;
                }
                case READ_FROM_FILE: {
                    contactManagement.readMainAndPrintOut();
                    break;
                }
                case WRITE_TO_FILE: {
                    contactManagement.writeMainListIntoMainFile();
                    break;
                }
            }
        }while (userChoice != 8);
    }

    public static int setMenu() {
        System.out.print("Menu : \n" +
                "1. Xem danh sach\n" +
                "2. Them moi\n" +
                "3. Cap Nhat\n" +
                "4. Xoa\n" +
                "5. Tim Kiem\n" +
                "6. Doc Tu File\n" +
                "7. Ghi vao File\n" +
                "8. Thoat\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please select your choice : ");
        return scanner.nextInt();
    }
}
