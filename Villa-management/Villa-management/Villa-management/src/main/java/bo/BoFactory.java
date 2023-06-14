package bo;

import bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory(){
        return (boFactory == null)? boFactory =new BoFactory() : boFactory;
    }

    public enum BOTypes{
        ADMIN_BO, BILL_BO, BOOKING_BO, CUSTOMER_BO, DRIVER_BO, EMPLOYEE_BO, HOME_BO, LOGIN_BO, ROOM_BO, SAFARY_BO, SIGNIN_BO,
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case ADMIN_BO:
                return (T) new AdminDashBoardBOImpl();
            case BILL_BO:
                return (T) new BillBOImpl();
            case BOOKING_BO:
                return (T) new BookingRoomBOImpl();
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case DRIVER_BO:
                return (T) new DriverBOImpl();
            case EMPLOYEE_BO:
                return (T) new EmployeeBOImpl();
            case HOME_BO:
                return (T) new HomeBOImpl();
            case LOGIN_BO:
                return (T) new LoginBOImpl();
            case ROOM_BO:
                return (T) new RoomBOImpl();
            case SAFARY_BO:
                return (T) new SafaryBOImpl();
            case SIGNIN_BO:
                return (T) new SignInBOImpl();
            default:
                return null;
        }
    }
}
