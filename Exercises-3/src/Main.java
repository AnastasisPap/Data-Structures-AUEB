import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        BST bst = new BST();
        
        int selection = printMenu();

        while (selection != 14) {
            if (selection < 0 || selection > 14) {
                System.out.println("Wrong input");
                selection = printMenu();
            } else {
                switch (selection) {
                    case 1:
                        System.out.print("Enter word to insert: ");
                        String wordToInsert = scanner.nextLine();
                        bst.insert(wordToInsert);
                        System.out.println();
                        System.out.println("Successfully inserted");
                        break;
                    case 2:
                        System.out.print("Enter word to search: ");
                        WordFreq word = bst.search(scanner.nextLine());
                        System.out.println();
                        if (word == null) System.out.println("Couldn't find this word");
                        else System.out.println(word);
                        break;
                    case 3:
                        System.out.print("Enter word to remove: ");
                        bst.remove(scanner.nextLine());
                        System.out.println();
                        System.out.println("Deleted word");
                        break;
                    case 4:
                        System.out.print("Enter file path: ");
                        bst.load(scanner.nextLine());
                        System.out.println();
                        break;
                    case 5:
                        System.out.println();
                        System.out.println(bst.getTotalWords() + " total " + (bst.getTotalWords() == 1 ? "word" : "words"));
                        break;
                    case 6:
                        System.out.println();
                        System.out.println(bst.getDistinctWords() + " distinct " + (bst.getDistinctWords() == 1 ? "word" : "words"));
                        break;
                    case 7:
                        System.out.print("Get frequency of word: ");
                        int freq = bst.getFrequency(scanner.nextLine());
                        System.out.println();
                        System.out.println("This word has a frequency of " + freq);
                        break;
                    case 8:
                        System.out.println();
                        System.out.println(bst.getMaximumFrequency());
                        break;
                    case 9:
                        System.out.println();
                        System.out.println("Mean frequency = " + bst.getMeanFrequency());
                        break;
                    case 10:
                        System.out.print("Stop word to add: ");
                        bst.addStopWord(scanner.nextLine());
                        System.out.println();
                        System.out.println("Successfully added stop word");
                        break;
                    case 11:
                        System.out.print("Stop word to remove: ");
                        String wordToRemove = scanner.nextLine();
                        bst.removeStopWord(wordToRemove);
                        System.out.println();
                        System.out.println("Successfully removed stop word");
                        break;
                    case 12:
                        System.out.println();
                        bst.printTreeAlphabetically(System.out);
                        break;
                    case 13:
                        System.out.println();
                        bst.printTreeByFrequency(System.out);
                        break;
                    default:
                        break;
                }

                System.out.println();
                System.out.println();
                selection = printMenu();
            }
        }

        scanner.close();
    }

    private static int printMenu() {
        
        System.out.println("Choose one option:");
        System.out.println("1.  Insert word");
        System.out.println("2.  Search word");
        System.out.println("3.  Remove word");
        System.out.println("4.  Load words from file");
        System.out.println("5.  Get total words");
        System.out.println("6.  Get distinct words");
        System.out.println("7.  Get frequency of word");
        System.out.println("8.  Get word with most occurrences");
        System.out.println("9.  Get mean frequency");
        System.out.println("10. Add stop word");
        System.out.println("11. Remove stop word");
        System.out.println("12. Print tree alphabetically");
        System.out.println("13. Print tree by frequence");
        System.out.println("14. Exit");
        System.out.print("Selection: ");
        int selection = scanner.nextInt();

        scanner = new Scanner(System.in);
        return selection;
    }
}
