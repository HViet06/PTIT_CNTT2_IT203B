package SS2;

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User log: " + activity);
    }
}

interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("Admin log: " + activity);
    }
}

class SuperAdmin implements UserActions, AdminActions {

    // Bắt buộc override để giải quyết Diamond Problem
    @Override
    public void logActivity(String activity) {
        AdminActions.super.logActivity(activity);
    }
}

public class ex5 {
    public static void main(String[] args) {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.logActivity("Super admin logged into system");
    }
}