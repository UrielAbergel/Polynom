public class aa {

    public static boolean prime(int x){
        boolean flag = false;
        for(int i = 2; i <= x/2; ++i)
        {
            // condition for nonprime number
            if(x % i == 0)
            {
                flag = true;
                break;
            }
        }
        if (!flag)
           return true;
        else
            return false;
    }


    public static void xxx(int k, int n){
        int x = k*(2^n) +1;
        prime(x);

    }
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i < 50 ; i++) {
           if(canPri(i))
               count++;
        }
        System.out.println(count);

    }

    static boolean canPri(int i){
        for (int j = 1; j < 50 ; j++) {
            if(prime(i*(2^j) +1))
                return true;
        }
        return false;
    }
}
