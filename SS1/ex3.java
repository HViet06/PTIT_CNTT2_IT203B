package SS1;
//Tạo một đối tượng lỗi IllegalArgumentException sau đó ném lỗi đó ra ngoài để chương trình xử lý bằng catch
class User {
    private int age;
    public void setAge(int age) {
        if (age<0){
            //throw new dùng để tự tạo và ném ra một ngoại lệ (exception) trong chương trình
            throw new IllegalArgumentException("Tuổi không thể âm");
        }
        this.age=age;
    }
    public int getAge(){
        return age;
    }
}
public class ex3 {
    public static void main(String[] args){
        User u=new User();

        try{
            u.setAge(-5);//test
            System.out.println("Tuổi: " + u.getAge());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}