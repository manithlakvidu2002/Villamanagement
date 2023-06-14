package dto;

public class CustomEntityDTO {
    private  String safaryId;
    private  String date;
    private  String time;
    private  String driverId;
    private  String driverName;
    private  String driverContact;
    private String cusId;
    private String Name;

    private String contact;
    private String safaryType;
    private  String roomId;
    private String roomType;
    private String roomPrice;
    private double payment;
    private String paymentType;
    private String bill;

    private String cash;
    private String balance;

    public CustomEntityDTO(String cusId, String name, String contact, String safaryType, String roomId, String roomType, String roomPrice, double payment, String bill, String cash, String balance) {
        this.cusId = cusId;
        this.Name = name;
        this.contact = contact;
        this.safaryType = safaryType;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.payment = payment;
        this.bill = bill;
        this.cash = cash;
        this.balance = balance;
    }
    public CustomEntityDTO(String safaryId, String date, String time, String driverId, String driverName, String driverContact, String safaryType) {
        this.safaryId = safaryId;
        this.date = date;
        this.time = time;
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverContact = driverContact;
        this.safaryType = safaryType;
    }

    public CustomEntityDTO(String name, String contact, String roomId, String roomType, String roomPrice) {
        this.Name = name;
        this.contact = contact;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public CustomEntityDTO(String cusId, String name, String contact, String roomId, String roomType, String roomPrice,String paymentType, double payment, String cash) {
        this.cusId = cusId;
        this.Name = name;
        this.contact = contact;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.paymentType = paymentType;
        this.payment = payment;
        this.cash = cash;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSafaryType() {
        return safaryType;
    }

    public void setSafaryType(String safaryType) {
        this.safaryType = safaryType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSafaryId() {
        return safaryId;
    }

    public void setSafaryId(String safaryId) {
        this.safaryId = safaryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

}
