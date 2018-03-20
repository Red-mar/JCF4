package com.company;

import java.io.*;
import java.util.*;

public class Huffman {
    public HashMap<Character, Integer> frequency;
    public HashMap<Character, String> codes;

    private String text = "";

    public Huffman(){
        frequency = new HashMap<>();
        codes= new HashMap<>();
    }

    public String readFile(String filename){
        StringBuilder sb = new StringBuilder();

        File file = new File(filename);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text= sb.toString();
        return text;
    }

    public void writeFile(String filename, byte[] bytes){
        File file = new File(filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void frequency(String text){
        char[] characters;

        characters = text.toCharArray(); // O(n)

        for (char c: characters){
            if (!frequency.containsKey(c)){ // O(1)
                frequency.put(c, 1); // O(1)
            } else {
                frequency.replace(c, (frequency.get(c) + 1)); // O(n)
            }
        }
    }

    public Node makeTree(HashMap<Character, Integer> frequency){
       PriorityQueue<Node> queue = new PriorityQueue<Node>();

       for (char c: frequency.keySet()){ // O(n)
           Node newNode = new Node(c, frequency.get(c), null, null);
           queue.offer(newNode);
       }

       while (queue.size() > 1){ // O(n)
           Node leftNode = queue.poll();
           Node rightNode = queue.poll();

           Node newNode = new Node('|', leftNode.frequency + rightNode.frequency, leftNode, rightNode);

           queue.offer(newNode);
       }
       return queue.poll();

    }

    public void printTree(Node root, String code){ // O(log)
        if (root == null){
            return;
        }
        if (root.leftNode == null && root.rightNode == null){
            codes.put(root.character, code);
            System.out.println(root.character + " " + code);
            return;
        }

        String leftCode = code + "0";
        String rightCode = code + "1";
        printTree(root.leftNode, leftCode);
        printTree(root.rightNode, rightCode);

    }

    public String makeMessage(String text){
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char c: chars){
            sb.append(codes.get(c));
        }

        return sb.toString();
    }

    public String decode(Node root, Node current, char[] chars, int i, StringBuilder sb){

        if (current == null){
            current = root;
        }

        char c = chars[i];
        current = (c == '0') ? current.leftNode : current.rightNode; // O(log)

        if (current.character != '|'){
            sb.append(current.character);
            if (i == chars.length - 1){
                return sb.toString();
            }
            current = root;
        }

        return decode(root, current, chars, i + 1, sb);
    }








}
