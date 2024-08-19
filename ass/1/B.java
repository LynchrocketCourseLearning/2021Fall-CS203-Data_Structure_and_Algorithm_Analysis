import java.util.Scanner;

class B {
    public static void main(String[] args) {
        int[] al = new int[100010];
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < a; i++){
            al[sc.nextInt()] = 1;
        }
        int b = sc.nextInt();
        sc.nextLine();
        for(int j = 0; j < b; j++){
            if(al[sc.nextInt()] == 1){
                System.out.println("yes");
            }else {
                System.out.println("no");
            }
        }

    }
}