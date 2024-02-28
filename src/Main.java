import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        int blockSize = 100;
        Scanner in = new Scanner(System.in);

        /*System.out.println("Please input number of blocks:");
        blockSize = in.nextInt();*/

        String directoryPath = "src/res";
        BSBI_Index index = new BSBI_Index(directoryPath, blockSize);


        System.out.println("""
                0 - Print index in console;
                1 - Open Index.txt;
                2 - Print statistics;
                3 - Open Stats.txt
                -1 - Exit
                """);

        System.out.println("\nYour input here: ");
        int i = in.nextInt();

      while(i != -1) {

          switch(i) {
              case 0:
                  index.printIndex();
                  break;
              case 1:
                  index.openTXT("src/results/Index.txt");
                  break;
              case 2:
                  index.printStatistics();
                  break;
              case 3:
                  index.openTXT("src/results/Stats.txt");
                  break;
              default:
                  System.out.println("Incorrect format! Try again.");
          }
          System.out.println("\n0 - Print index in console;\n" +
                  "1 - Open Index.txt;\n" +
                  "2 - Print statistics;\n" +
                  "3 - Open Stats.txt\n" +
                  "-1 - Exit");
          System.out.println("\nYour input here: ");
          i = in.nextInt();
      }
    }
}

