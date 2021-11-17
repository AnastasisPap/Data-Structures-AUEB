import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class NetBenefit {

    // Pair class where two values are stored, the amount and the price
    public static class Pair {
        // amount of stock we want to buy / sell
        private int amount;
        private int price;

        public Pair() {
            this.amount = 0;
            this.price = 0;
        }

        // set amount and price if given as arguments
        public Pair(int amount, int price) {
            this.amount = amount;
            this.price = price;
        }

        // getters
        public int getPrice() {
            return this.price;
        }

        public int getAmount() {
            return this.amount;
        }

        public void subtractAmount(int val) { this.amount = this.amount - val; }

        public void setPrice(int val) { this.price = val; }

        public void setAmount(int val) { this.amount = val; }
    }

    // returns a pair which has the amount bought/sold and the price, type is buy or sell
    private static Pair getPair(String content, String type) {
        // idx is the index where the number starts after the amount
        int idx = content.indexOf(type) + type.length() + 1;
        String num = "";

        // read the amount
        while (content.charAt(idx) != ' ') {
            num += content.charAt(idx);
            idx++;
        }
        // convert to int
        int amount = Integer.parseInt(num);
        num = "";

        // idx is the index where the number start after the price
        idx = content.indexOf("price") + "price".length() + 1;
        // while a number
        while (idx < content.length() && content.charAt(idx) != ' ') {
            num += content.charAt(idx);
            idx++;
        }
        // convert to int
        int price = Integer.parseInt(num);

        // return the pair
        return new Pair(amount, price);

    }

    // returns the total profit
    private static String getData(String filePath) {
        // queue where the pair of buy amount and prices are stored
        IntQueueImpl<Pair> buyPrices = new IntQueueImpl<>();
        int totalProfit = 0;

        // if the file exists
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String line;
            // store the pair of the surplus
            Pair savedPair = new Pair(-1, -1);

            while ((line = in.readLine()) != null) {
                // if a stock is bought
                if (line.contains("buy")) {
                    // get the pair and store it to the queue
                    Pair pair = getPair(line, "buy");
                    buyPrices.put(pair);
                // if a stock is sold
                } else {
                    // get the pair that is sold
                    Pair pair = getPair(line, "sell");
                    int sellAmount = pair.getAmount();
                    int sellPrice = pair.getPrice();

                    // while we want to sell stocks
                    while (sellAmount > 0) {
                        // if there are no stocks to sell and saved amount of stocks is not enough then there is an error
                        if (buyPrices.isEmpty() && sellAmount > savedPair.getAmount()) return "Not sufficient amount to sell";

                        // if there is surplus and it greater than the amount we want to sell
                        if (savedPair.getAmount() > 0 && savedPair.getAmount() >= sellAmount) {
                            // sell all the stocks we want to sell at the price of surplus
                            totalProfit += sellAmount * (sellPrice - savedPair.getPrice());
                            // subtract the surplus with the amount sold
                            savedPair.subtractAmount(sellAmount);
                            // sold all stocks
                            sellAmount = 0;
                        // if there isn't a surplus or there is but it's smaller than the amount we want to sell
                        } else {
                            // if there is a surplus sell all of it
                            if (savedPair.getAmount() > 0) {
                                // sell all surplus at the price that got saved
                                totalProfit += savedPair.getAmount() * (sellPrice - savedPair.getPrice());
                                // subtract the amount of surplus we just sold with the total we want to be sold
                                sellAmount -= savedPair.getAmount();
                                // set surplus to 0
                                savedPair.setAmount(0);
                            }

                            // get from the queue the oldest stock buy transaction
                            Pair currPair = buyPrices.get();
                            int currAmount = currPair.getAmount();
                            int currPrice = currPair.getPrice();

                            // if we want to sell less than we have bought at the oldest transaction
                            if (currAmount > sellAmount) {
                                // sell all of the amount we want to sell and save surplus
                                totalProfit += sellAmount * (sellPrice - currPrice);
                                savedPair.setAmount(currAmount - sellAmount);
                                // amount sold set to 0
                                sellAmount = 0;
                                savedPair.setPrice(currPrice);
                            // if we want to sell more than the oldest transaction then we have to sell all of those and continue with the remaining
                            } else {
                                // sell all of bought stocks of the oldest transaction
                                totalProfit += currAmount * (sellPrice - currPrice);
                                // continue with the rest of the amount we want to sell
                                sellAmount -= currAmount;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) { System.out.println(e.getMessage()); }

        return "Total Profit: " + totalProfit;
    }

    public static void main(String[] args) {
        System.out.println(getData(args[0]));
    }
}