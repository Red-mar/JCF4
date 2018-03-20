package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Huffman huff = new Huffman();

        while (scanner.hasNext()){
            String command = scanner.next();

            switch (command){
                case "test":
                    System.out.println("SYSTEM: test");
                    break;
                case "huff":
                    String text = huff.readFile(scanner.next());
                    huff.frequency(text);
                    Node firstNode = huff.makeTree(huff.frequency);
                    huff.printTree(firstNode, "");
                    String encoded = huff.makeMessage(text);
                    System.out.println(encoded);
                    StringBuilder sb = new StringBuilder();
                    System.out.println(huff.decode(firstNode, null, encoded.toCharArray(), 0, sb));
                    break;
            }
        }

    }
}
