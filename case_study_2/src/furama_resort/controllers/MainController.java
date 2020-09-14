package furama_resort.controllers;

import furama_resort.commons.ReadWriteFile;
import furama_resort.commons.Validate;
import furama_resort.exception.*;
import furama_resort.models.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainController {
    static Scanner scanner = new Scanner(System.in);
    static Matcher matcher;
    static Pattern pattern;


    public static final String FILE_VILLA = "src/furama_resort/data/villa.csv";
    public static final String FILE_HOUSE = "src/furama_resort/data/house.csv";
    public static final String FILE_ROOM = "src/furama_resort/data/room.csv";
    public static final String FILE_BOOKING = "src/furama_resort/data/booking.csv";
    public static final String FILE_CUSTOMER = "src/furama_resort/data/customer.csv";
    public static final String FILE_EMPLOYEE ="src/furama_resort/data/employee.csv" ;

    public static List<Villa> villaList = new ArrayList<>();
    public static List<House> houseList = new ArrayList<>();
    public static List<Room> roomList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();
    public static List<Customer> customerListBooking = new ArrayList<Customer>();
    public static Map<String, Employee> employeeMap = new HashMap<>();

    //////////////////////////////////////////DAT//////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        displayMainMenu();
    }

    public static void displayMainMenu() {
        while (true) {
            int choose;
            System.out.print("1.\tAdd New Services\n" +
                    "2.\tShow Services\n" +
                    "3.\tAdd New Customer\n" +
                    "4.\tShow Information of Customer\n" +
                    "5.\tAdd New Booking\n" +
                    "6.\tShow Information of Employee\n" +
                    "7.\tShow Queue Customer\n" +
                    "8.\tFind find of Employee\n" +
                    "9.\tExit\n");
            System.out.print("Enter choose: ");
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    addNewService();
                    break;
                case 2:
                    showService();
                    break;
                case 3:
                    addNewCustomer();
                    break;
                case 4:
                    showInformCustomer();
                    break;
                case 5:
                    addNewBooking();
                    break;
                case 6:
                    showInformationOfEmployee();
                    break;
                case 7:
                    cinemaTickets();
                    break;
                case 8:
                    CabinetFile.findFileEmployee();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.err.println("The selection is not in the selection list.please reselect");
            }
        }
    }

    private static void addNewService() {
        int choose;
        System.out.print("1.\tAdd New Villa\n" +
                "2.\tAdd New House\n" +
                "3.\tAdd New Room\n" +
                "4.\tBack to menu\n" +
                "5.\tExit\n");
        System.out.print("Enter choose Word want add: ");
        choose = scanner.nextInt();
        switch (choose) {
            case 1:
                addNewVilla();
                break;
            case 2:
                addNewHouse();
                break;
            case 3:
                addNewRoom();
                break;
            case 4:
                displayMainMenu();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choose);
        }
    }
    //////////////////////////////////////REGEX/////////////////////////////////////////
    public static final String REGEX_ID = "SV(VL|HO|RO)-\\d{4}";
    public static final String REGEX_NAME_SERVICE = "[A-Z][a-z]*";
    public static final String REGEX_AREA = "[3-9]\\d(.{0,1})\\d{0,9}|\\d{3,9}(.{0,1})\\d{0,9}";
    public static final String REGEX_PRICE = "[1-9](.{0,1})\\d|[1-9]\\d(.{0,1})\\d{0,9}|[1-9]\\d{1,9}(.{0,1})\\d{0,9}";
    public static final String REGEX_MAX_PEOPLE = "[1-9]|[1]\\d|20";
    public static final String REGEX_SERVICE_EXTRA = "(massage|karaoke|food|drink|car)";
    public static final String REGEX_NUMBER_FLOORS = "\\d|[1-9]\\d";
    public static final String REGEX_STANDARD = "SV(SUP|SUT|STD|DLX)";
    public static final String REGEX_BIRTHDAY = "(([0][1-9]|[1-2]\\d|[3][0-1])/(0[1-9]|1[0-2])/(19[2-9]\\d|(20[0-2]\\d)))";
    public static final String REGEX_NAME_CUSTOMER = "[A-Z][a-z]* ([A-Z][a-z]* )*[A-Z][a-z]*";
    public static final String REGEX_EMAIL = "([a-zA-Z0-9_.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+";
    public static final String REGEX_GENDER = "[Mm][Aa][Ll][Ee]|[Ff][Aa][Mm][Aa][Ll][Ee]|[Uu][Nn][Kk][Nn][Oo][Ww]";
    public static final String REGEX_ID_CUSTOMER = "\\d{9}";


    private static void addNewVilla() {
        System.out.println("================================================================================");
        scanner.nextLine();


        String id;
        boolean checkID = true;
        do {
            System.out.print("Enter ID: ");
            id = scanner.nextLine();
            ReadWriteFile.readerFile(FILE_VILLA);
            if (villaList.size() >=1) {
                for (Villa villa : villaList) {
                    if (id.equals(villa.getId())) {
                        checkID = false;
                    } else {
                        checkID = true;
                    }
                }
            }
        } while (!Validate.check(id,REGEX_ID) || !checkID);

        String name;
        do {
            System.out.print("Enter Name Villa: ");
            name = scanner.nextLine();
        } while (!Validate.check(name,REGEX_NAME_SERVICE));

        String userArea;
        do {
            System.out.print("Enter the area of use: ");
            userArea = scanner.nextLine();
        } while (!Validate.check(userArea, REGEX_AREA));

        String price;
        do {
            System.out.print("Enter price Villa: ");
            price = scanner.nextLine();
        } while (!Validate.check(price,REGEX_PRICE));

        String maxPeople;
        do {
            System.out.print("Enter number People: ");
            maxPeople = scanner.nextLine();
        } while (!Validate.check(maxPeople,REGEX_MAX_PEOPLE));

        System.out.print("Enter Type of rent(Hour/Day/month/year): ");
        String rentType = scanner.nextLine();

        String roomStandard;
        do {
            System.out.print("Enter Standard Room: ");
            roomStandard = scanner.nextLine();
        } while (!Validate.check(roomStandard,REGEX_STANDARD));

        System.out.print("Enter Description Villa: ");
        String description = scanner.nextLine();

        String areaPool;
        do {
            System.out.print("Enter Arena Pool: ");
            areaPool = scanner.nextLine();
        } while (!Validate.check(areaPool, REGEX_AREA));


        String numberFloors;
        do {
            System.out.print("Enter Number Floors :");
            numberFloors = scanner.nextLine();
        } while (!Validate.check(numberFloors,REGEX_NUMBER_FLOORS));

        villaList.add(new Villa(id, name, userArea, price, maxPeople, rentType, roomStandard, description, areaPool, numberFloors));
        for (int i = villaList.size() - 1; i < villaList.size(); i++) {
            ReadWriteFile.writerFile(String.valueOf(villaList.get(i).getId()) + ",", FILE_VILLA);
            ReadWriteFile.writerFile(villaList.get(i).getName() + ",", FILE_VILLA);
            ReadWriteFile.writerFile(String.valueOf(villaList.get(i).getUserArea()) + ",", FILE_VILLA);
            ReadWriteFile.writerFile(String.valueOf(villaList.get(i).getPrice()) + ",", FILE_VILLA);
            ReadWriteFile.writerFile(String.valueOf(villaList.get(i).getMaxPeople()) + ",", FILE_VILLA);
            ReadWriteFile.writerFile(String.valueOf(villaList.get(i).getRentType()) + ",", FILE_VILLA);
            ReadWriteFile.writerFile(villaList.get(i).getRoomStandard() + ",", FILE_VILLA);
            ReadWriteFile.writerFile(villaList.get(i).getDescription() + ",", FILE_VILLA);
            ReadWriteFile.writerFile(String.valueOf(villaList.get(i).getAreaPool()) + ",", FILE_VILLA);
            ReadWriteFile.writerFile(String.valueOf(villaList.get(i).getNumberFloors()), FILE_VILLA);
            ReadWriteFile.writerFile("\n", FILE_VILLA);
        }
        villaList.clear();
        System.out.println("=======================================================================================");
    }

    private static void addNewHouse() {
        scanner.nextLine();

        String id;
        do {
            System.out.print("Enter ID: ");
            id = scanner.nextLine();
        } while (!Validate.check(id,REGEX_ID));

        String name;
        do {
            System.out.print("Enter Name House: ");
            name = scanner.nextLine();
        } while (!Validate.check(name,REGEX_NAME_SERVICE));

        String userArea;
        do {
            System.out.print("Enter the area of use: ");
            userArea = scanner.nextLine();
        } while (!Validate.check(userArea, REGEX_AREA));

        String price;
        do {
            System.out.print("Enter price House: ");
            price = scanner.nextLine();

        } while (!Validate.check(price,REGEX_PRICE));


        String maxPeople;
        do {
            System.out.print("Enter number People: ");
            maxPeople = scanner.nextLine();
        } while (!Validate.check(maxPeople,REGEX_MAX_PEOPLE));

        System.out.print("Enter Type of rent(Hour/Day/month/year): ");
        String rentType = scanner.nextLine();

        String roomStandard;
        do {
            System.out.print("Enter Standard Room: ");
            roomStandard = scanner.nextLine();

        } while (!Validate.check(roomStandard,REGEX_STANDARD));

        System.out.print("Enter Description Villa: ");
        String description = scanner.nextLine();

        String numberFloors;
        do {
            System.out.print("Enter Number Floors :");
            numberFloors = scanner.nextLine();
        } while (!Validate.check(numberFloors,REGEX_NUMBER_FLOORS));

        houseList.add(new House(id, name, userArea, price, maxPeople, rentType, roomStandard, description, numberFloors));
        for (int i = houseList.size() - 1; i < houseList.size(); i++) {
            ReadWriteFile.writerFile(String.valueOf(houseList.get(i).getId()) + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(houseList.get(i).getName() + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(String.valueOf(houseList.get(i).getUserArea()) + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(String.valueOf(houseList.get(i).getPrice()) + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(String.valueOf(houseList.get(i).getMaxPeople()) + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(String.valueOf(houseList.get(i).getRentType()) + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(houseList.get(i).getRoomStandard() + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(houseList.get(i).getDescription() + ",", FILE_HOUSE);
            ReadWriteFile.writerFile(String.valueOf(houseList.get(i).getNumberFloors()), FILE_HOUSE);
            ReadWriteFile.writerFile("\n", FILE_HOUSE);
        }
        houseList.clear();
        System.out.println("-----------------------------------------------------------------------------------------");

    }

    private static void addNewRoom() {
        scanner.nextLine();
        String id;
        do {
            System.out.print("Enter ID: ");
            id = scanner.nextLine();
        } while (!Validate.check(id,REGEX_ID));

        String name;
        do {
            System.out.print("Enter Name Room: ");
            name = scanner.nextLine();

        } while (!Validate.check(name,REGEX_NAME_SERVICE));

        String userArea;
        do {
            System.out.print("Enter the area of use: ");
            userArea = scanner.nextLine();
        } while (!Validate.check(userArea, REGEX_AREA));

        String price;
        do {
            System.out.print("Enter price Room: ");
            price = scanner.nextLine();
        } while (!Validate.check(price,REGEX_PRICE));


        String maxPeople;
        do {
            System.out.print("Enter number People: ");
            maxPeople = scanner.nextLine();
        } while (!Validate.check(maxPeople,REGEX_MAX_PEOPLE));


        System.out.print("Enter Type of rent(Hour/Day/month/year): ");
        String rentType = scanner.nextLine();

        String nameExtraService;
        String unitExtraService;
        double moneyExtraService;
        do {
            System.out.print("Enter Name Service Included: ");
            nameExtraService = scanner.nextLine();
        } while (!Validate.check(nameExtraService,REGEX_SERVICE_EXTRA));
        System.out.print("Enter unit Word included: ");
        unitExtraService = scanner.nextLine();
        System.out.print("Enter money Word included: ");
        moneyExtraService = Double.parseDouble(scanner.nextLine());
        ExtraService extraService = new ExtraService(nameExtraService, unitExtraService, moneyExtraService);
        roomList.add(new Room(id, name, userArea, price, maxPeople, rentType, extraService));
        for (int i = roomList.size() - 1; i < roomList.size(); i++) {
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getId()) + ",", FILE_ROOM);
            ReadWriteFile.writerFile(roomList.get(i).getName() + ",", FILE_ROOM);
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getUserArea()) + ",", FILE_ROOM);
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getPrice()) + ",", FILE_ROOM);
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getMaxPeople()) + ",", FILE_ROOM);
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getRentType()) + ",", FILE_ROOM);
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getExtraService().getExtraServiceName()) + ",", FILE_ROOM);
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getExtraService().getUnit()) + ",", FILE_ROOM);
            ReadWriteFile.writerFile(String.valueOf(roomList.get(i).getExtraService().getMoney()), FILE_ROOM);
            ReadWriteFile.writerFile("\n", FILE_ROOM);
        }
        roomList.clear();
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void showService() {
        int choose;
        System.out.print("1.\tShow all Villa\n" +
                "2.\tShow all House\n" +
                "3.\tShow all Room\n" +
                "4.\tShow All Name Villa Not Duplicate\n" +
                "5.\tShow All Name House Not Duplicate\n" +
                "6.\tShow All Name Name Not Duplicate\n" +
                "7.\tBack to menu\n" +
                "8.\tExit\n");
        System.out.println("Enter choose (1-8)");
        choose = scanner.nextInt();
        switch (choose) {
            case 1:
                showAllVilla();
                break;
            case 2:
                showAllHouse();
                break;
            case 3:
                showAllRoom();
                break;
            case 4:
                showAllNameVillaNotDuplicate();
                break;
            case 5:
                showAllNameHouseNotDuplicate();
                break;
            case 6:
                showAllNameRoomNotDuplicate();
                break;
            case 7:
                displayMainMenu();
                break;
            case 8:
                System.exit(0);
                break;
            default:
                System.err.println("The selection is not in the selection list");
        }
    }

    private static void showAllVilla() {
        villaList.clear();
        ReadWriteFile.readerFile(FILE_VILLA);
        Villa villa = null;
        for (int i = 0; i < villaList.size(); i++) {
            villa = villaList.get(i);
            System.out.print((i + 1) + ". ");
            villa.showInform();
        }

    }

    private static void showAllHouse() {
        houseList.clear();
        ReadWriteFile.readerFile(FILE_HOUSE);
        House house = null;
        for (int i = 0; i < houseList.size(); i++) {
            house = houseList.get(i);
            System.out.print((i + 1) + ". ");
            house.showInform();
        }
    }

    private static void showAllRoom() {
        roomList.clear();
        ReadWriteFile.readerFile(FILE_ROOM);
        Room room = null;
        for (int i = 0; i < roomList.size(); i++) {
            room = roomList.get(i);
            System.out.print((i + 1) + ". ");
            room.showInform();
        }
    }

    private static void showAllNameVillaNotDuplicate() {
        villaList.clear();
        ReadWriteFile.readerFile(FILE_VILLA);
        Set<String> listVillaSet = new TreeSet<>();
        for (Villa villa : villaList) {
            listVillaSet.add(villa.getName());
        }
        for (String name : listVillaSet) {
            System.out.println(name);
        }
    }

    private static void showAllNameHouseNotDuplicate() {
        villaList.clear();
        ReadWriteFile.readerFile(FILE_HOUSE);
        Set<String> listHouseSet = new TreeSet<>();
        for (House house : houseList) {
            listHouseSet.add(house.getName());
        }
        for (String name : listHouseSet) {
            System.out.println(name);
        }
    }

    private static void showAllNameRoomNotDuplicate() {
        villaList.clear();
        ReadWriteFile.readerFile(FILE_ROOM);
        Set<String> listRoomSet = new TreeSet<>();
        for (Room room : roomList) {
            listRoomSet.add(room.getName());
        }
        for (String name : listRoomSet) {
            System.out.println(name);
        }
    }

    private static void addNewCustomer() {
        scanner.nextLine();
        boolean check;
        String name;
        do {
            System.out.println("Enter Name Customer: ");
            name = scanner.nextLine();
            if (!Validate.check(name,REGEX_NAME_CUSTOMER)) {
                check = false;
            } else {
                check = true;
            }
            if (!check) {
                try {
                    throw new NameException("Enter wrong Name of Customer");
                } catch (NameException e) {
                    System.out.println(e.toString());
                }
            }
        } while (!Validate.check(name,REGEX_NAME_CUSTOMER));

        String birthday;
        do {
            System.out.println("Enter Birthday of Customer: ");
            birthday = scanner.nextLine();
            if (!Validate.check(birthday,REGEX_BIRTHDAY)) {
                check = false;
            } else {
                check = true;
            }
            if (!check) {
                try {
                    throw new BirthdayException("Enter wrong BirthDay of Customer");
                } catch (BirthdayException e) {
                    System.out.println(e.toString());
                }
            }
        } while (!Validate.check(birthday,REGEX_BIRTHDAY));

        String gender;
        String genderNew;
        do {
            System.out.println("Enter Gender of Customer: ");
            gender = scanner.nextLine();
            String gender1 = gender.toLowerCase();
            genderNew=String.valueOf(gender1.charAt(0)).toUpperCase()+gender1.substring(1);
            if (!Validate.check(gender,REGEX_GENDER)) {
                check = false;
            } else {
                check = true;
            }
            if (!check) {
                try {
                    throw new GenderException("Enter wrong Gender of Customer");
                } catch (GenderException e) {
                    System.out.println(e.toString());
                }
            }
        } while (!Validate.check(gender,REGEX_GENDER));

        String id;
        boolean checkIDCustomer=true;
        do {
            customerList.clear();
            ReadWriteFile.readerFile(FILE_CUSTOMER);
            System.out.println("Enter ID of Customer: ");
            id = scanner.nextLine();

            if (customerList.size() > 0) {
                for (Customer customer : customerList) {
                    if (id.compareTo(customer.getId())==0){
                        checkIDCustomer=false;
                    }else {
                        checkIDCustomer=true;
                    }
                }
            }
            if (!Validate.check(id,REGEX_ID_CUSTOMER)) {
                check = false;
            } else {
                check = true;
            }
            if (!check) {
                try {
                    throw new IDException("Enter wrong ID of Customer");
                } catch (IDException e) {
                    System.out.println(e.toString());
                }
            }
        } while (!Validate.check(id,REGEX_ID_CUSTOMER) || !checkIDCustomer);

        System.out.print("Enter Number Phone of Customer: ");
        String numberPhone = scanner.nextLine();

        String email;
        do {
            System.out.println("Enter Email of Customer: ");
            email = scanner.nextLine();
            if (!Validate.check(email,REGEX_EMAIL)) {
                check = false;
            } else {
                check = true;
            }
            if (!check) {
                try {
                    throw new EmailException("Enter wrong Email of Customer");
                } catch (EmailException e) {
                    System.out.println(e.toString());
                }
            }
        } while (!Validate.check(email,REGEX_EMAIL));

        System.out.print("Enter Type of Customer: ");
        String typeCustomer = scanner.nextLine();

        System.out.print("Enter Address of Customer: ");
        String address = scanner.nextLine();

        customerList.add(new Customer(name, birthday, id, genderNew, numberPhone, email, typeCustomer, address));
        for (int i = customerList.size() - 1; i < customerList.size(); i++) {
            ReadWriteFile.writerFile(customerList.get(i).getName() + ",", FILE_CUSTOMER);
            ReadWriteFile.writerFile(customerList.get(i).getBirthday() + ",", FILE_CUSTOMER);
            ReadWriteFile.writerFile(customerList.get(i).getGender() + ",", FILE_CUSTOMER);
            ReadWriteFile.writerFile(customerList.get(i).getId() + ",", FILE_CUSTOMER);
            ReadWriteFile.writerFile(customerList.get(i).getNumberPhone() + ",", FILE_CUSTOMER);
            ReadWriteFile.writerFile(customerList.get(i).getEmail() + ",", FILE_CUSTOMER);
            ReadWriteFile.writerFile(customerList.get(i).getTypeCustomer() + ",", FILE_CUSTOMER);
            ReadWriteFile.writerFile(customerList.get(i).getAddress(), FILE_CUSTOMER);
            ReadWriteFile.writerFile("\n", FILE_CUSTOMER);
        }
        Collections.sort(customerList);
    }

    private static void showInformCustomer() {
        customerList.clear();
        ReadWriteFile.readerFile(FILE_CUSTOMER);
        Customer customer = null;
        Collections.sort(customerList);
        for (int i = 0; i < customerList.size(); i++) {
            customer = customerList.get(i);
            System.out.print((i + 1) + ". ");
            customer.showInfor();
        }
    }

    private static void addNewBooking() {
        customerList.clear();
        ReadWriteFile.readerFile(FILE_CUSTOMER);
        System.out.print("1.\tBooking Villa\n" +
                "2.\tBooking House\n" +
                "3.\tBooking Room\n");
        System.out.println("Please Input choose booking: ");
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                bookingVilla();
                break;
            case 2:
                bookingHouse();
                break;
            case 3:
                bookingRoom();
                break;
            default:
                System.err.println("The selection is not in the selection list");
        }
    }

    private static void bookingVilla() {
        showInformCustomer();
        System.out.print("Enter choose customer to booking: ");
        int iCustomer = scanner.nextInt();
        showAllVilla();
        System.out.print("Enter Choose Villa to booking: ");
        int iVilla = scanner.nextInt();
        Customer customer = customerList.get(iCustomer - 1);
        customer.setUserService(villaList.get(iVilla - 1));
        ReadWriteFile.writerFile(customer.toString(), FILE_BOOKING);
        customerListBooking.add(customer);
        for (int i=customerListBooking.size()-1;i<customerListBooking.size();i++){
            ReadWriteFile.writerFile(customerList.get(i).getName() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getBirthday() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getGender() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getId() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getNumberPhone() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getEmail() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getTypeCustomer() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getAddress()+",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getUserService().getId()+",",FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getUserService().getName(), FILE_BOOKING);
            ReadWriteFile.writerFile("\n", FILE_BOOKING);
        }
    }

    private static void bookingHouse() {
        showInformCustomer();
        System.out.print("Enter choose customer to booking: ");
        int iCustomer = scanner.nextInt();
        showAllHouse();
        System.out.print("Enter Choose House to booking: ");
        int iHouse = scanner.nextInt();
        Customer customer = customerList.get(iCustomer - 1);
        customer.setUserService(houseList.get(iHouse - 1));
        customerListBooking.add(customer);
        for (int i=customerListBooking.size()-1;i<customerListBooking.size();i++){
            ReadWriteFile.writerFile(customerList.get(i).getName() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getBirthday() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getGender() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getId() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getNumberPhone() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getEmail() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getTypeCustomer() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getAddress()+",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getUserService().getId()+",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getUserService().getName(), FILE_BOOKING);
            ReadWriteFile.writerFile("\n", FILE_BOOKING);
        }
        customerListBooking.clear();
    }

    private static void bookingRoom() {
        showInformCustomer();
        System.out.print("Enter choose customer to booking: ");
        int iCustomer = scanner.nextInt();
        showAllRoom();
        System.out.print("Enter Choose House to booking: ");
        int iRoom = scanner.nextInt();
        Customer customer = customerList.get(iCustomer - 1);
        customer.setUserService(roomList.get(iRoom - 1));
        customerListBooking.add(customer);
        for (int i=customerListBooking.size()-1;i<customerListBooking.size();i++){
            ReadWriteFile.writerFile(customerList.get(i).getName() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getBirthday() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getGender() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getId() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getNumberPhone() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getEmail() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getTypeCustomer() + ",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getAddress()+",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getUserService().getId()+",", FILE_BOOKING);
            ReadWriteFile.writerFile(customerList.get(i).getUserService().getName(), FILE_BOOKING);
            ReadWriteFile.writerFile("\n", FILE_BOOKING);
        }
    }

    private static void cinemaTickets() {
        Queue<Customer> customerQueue = new LinkedList<>();
        showInformCustomer();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("List of customers watching 4D movies");
        customerQueue.add(customerList.get(3));
        customerQueue.add(customerList.get(1));
        customerQueue.add(customerList.get(5));

        Customer customer = null;
        while (!customerQueue.isEmpty()) {
            customer = customerQueue.poll();
            customer.showInfor();
        }
    }

    public static void showInformationOfEmployee() {
        employeeMap.clear();
        ReadWriteFile.readerFile(FILE_EMPLOYEE);
        for (Map.Entry<String, Employee> employeeEntry : employeeMap.entrySet()) {
            System.out.println(employeeEntry.getKey() + ". " + employeeEntry.getValue().toString());
        }
    }
}
