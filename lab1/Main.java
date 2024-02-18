import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the dimension of num");
        int dimension = Integer.parseInt(bufferedReader.readLine());

        while (dimension < 0 || dimension > 10) {
            System.out.println("Enter dimension in [1,9]");
            dimension = Integer.parseInt(bufferedReader.readLine());
        }

        String secret = createSecretNum(dimension);
        System.out.println(secret); // for testing

        int countAttempts = 0;

        System.out.println("Enter your guess");

        while (true) {
            int cowCount = 0;
            int bullCount = 0;

            String guess = bufferedReader.readLine();

            if (guess.length() != dimension) {
                System.out.println("Enter a " + dimension + " digit number");
                continue;
            }

            for (int i = 0; i < dimension; i++) {
                if (secret.charAt(i) == guess.charAt(i)) bullCount++;
                else {
                    for (int j = 0; j < dimension; j++) {
                        if (secret.charAt(i) == guess.charAt(j)) {
                            cowCount++;
                            break;
                        }
                    }
                }
            }
            if (bullCount == dimension) {
                System.out.println("You are win! You have spent " + countAttempts + " attempts");
                break;
            }
            else System.out.println("Your guess have a " + bullCount + " bulls and " + cowCount + " cows");

            countAttempts++;
        }

    }

    static String createSecretNum(int dimension) {
        while (true) {
            int random = (int) (Math.random() * Math.pow(10, dimension));
            if (String.valueOf(random).length() != dimension) continue;
            int num = random;
            int[] tmp = new int[10];
            boolean flag = true;
            while (random != 0) {
                if (tmp[random % 10] == 0) {
                    tmp[random % 10] = 1;
                    random /= 10;
                }
                else  {
                    flag = false;
                    break;
                }
            }
            if (flag) return String.valueOf(num);
        }
    }
}