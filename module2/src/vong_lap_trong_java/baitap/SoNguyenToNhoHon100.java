package vong_lap_trong_java.baitap;

public class SoNguyenToNhoHon100 {
    public static void main(String[] args) {

        int number =2;
        String string = "";

        while (number < 100) {
            boolean check = true;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    check = false;
                    break;
                }
            }
            if (check) {
                string += number + ",";
            }
            number++;
        }
        System.out.println("Các số nguyên tố nhỏ hơn 100 là : "+ string);
    }
}

