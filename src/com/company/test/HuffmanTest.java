package com.company.test;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import com.company.Huffman;
import com.company.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kitty
 */
public class HuffmanTest {

    Huffman huffman;
    HashMap<Character, Integer> map1, map2, map3;
    Node b1k1, b1k2, b1k3, b1k4, b1k5, b1k6, b1k7;
    Node b2k1, b2k2, b2k3, b2k4, b2k5, b2k6, b2k7, b2k8, b2k9, b2k10, b2k11, b2k12, b2k13, b2k14, b2k15;
    Node hk1, hk2;
    BitSet bitSetMap1, bitSetMap2, bitSetMap3;
    String bericht1, bericht2;
    String bericht1codeerd, bericht2codeerd;
    Map<Character, Integer> ltf1, ltf2;

    public HuffmanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        huffman = new Huffman();

        // <editor-fold desc="map1">
        map1 = new HashMap<>();
        //Hey!
        map1.put('H', 1);
        map1.put('e', 1);
        map1.put('y', 1);
        map1.put('!', 1);
        // </editor-fold>
        // <editor-fold desc="map2">
        map2 = new HashMap<>();
        //mississippi river
        map2.put('i', 5);
        map2.put('s', 4);
        map2.put('p', 2);
        map2.put('r', 2);
        map2.put('m', 1);
        map2.put('v', 1);
        map2.put('e', 1);
        map2.put(' ', 1);
        // </editor-fold>

        // <editor-fold desc="boom1">
        b1k1 = new Node('H', 1, null, null);
        b1k2 = new Node('e', 1, null, null);
        b1k3 = new Node('y', 1, null, null);
        b1k4 = new Node('!', 1, null, null);
        b1k5 = new Node('\0', 2, b1k1, b1k3);
        b1k6 = new Node('\0', 2, b1k4, b1k2);
        b1k7 = new Node('\0', 4, b1k6, b1k5);
        // </editor-fold>
        // <editor-fold desc="boom2">
        b2k1 = new Node('e', 1, null, null);
        b2k2 = new Node(' ', 1, null, null);
        b2k3 = new Node('v', 1, null, null);
        b2k4 = new Node('m', 1, null, null);
        b2k5 = new Node('\0', 2, b2k2, b2k1);
        b2k6 = new Node('p', 2, null, null);
        b2k7 = new Node('\0', 4, b2k6, b2k5);
        b2k8 = new Node('s', 4, null, null);
        b2k9 = new Node('\0', 8, b2k8, b2k7);
        b2k10 = new Node('\0', 2, b2k4, b2k3);
        b2k11 = new Node('r', 2, null, null);
        b2k12 = new Node('\0', 4, b2k11, b2k10);
        b2k13 = new Node('i', 5, null, null);
        b2k14 = new Node('\0', 9, b2k13, b2k12);
        b2k15 = new Node('\0', 17, b2k14, b2k9);
        // </editor-fold>

        bericht1 = "Hey!";
        bericht1codeerd = "10011100"; // 10110100 ? zo lang de lengte hetzelfde is zou het toch niet uitmaken ?
        bericht2 = "mississippi river";
        bericht2codeerd = "0110001010001010001101100011100100001111111010";
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFrequentie()
    {
        huffman.frequency(bericht1);
        assertEquals("Map niet correct", map1, huffman.frequency);
        huffman = new Huffman();
        huffman.frequency(bericht2);
        assertEquals("Map niet correct", map2, huffman.frequency);
    }
    @Test
    public void testBouwBoom()
    {
        //System.out.println("b1k5: char - " + b1k5.charr + " freq - " + b1k5.frequentie);
        Node KMap1 = huffman.makeTree(map1);
        Node KMap2 = huffman.makeTree(map2);
        //System.out.println("KMap1: char - " + KMap1.charr + " freq - " + KMap1.frequentie);
        assertEquals("Knoop komt niet overeen", b1k5.getClass() , KMap1.getClass());
        assertEquals("Knoop komt niet overeen", b2k15.getClass(), KMap2.getClass());
    }

    @Test
    public void testMaakBericht()
    {
        huffman = new Huffman();
        huffman.frequency(bericht1);
        Node rootNode = huffman.makeTree(huffman.frequency);
        huffman.printTree(rootNode, "");
        String b1 = huffman.makeMessage(bericht1);

        //assertEquals("Bericht komt niet overeen", bericht1codeerd, b1);

        huffman = new Huffman();
        huffman.frequency(bericht2);
        rootNode = huffman.makeTree(huffman.frequency);
        huffman.printTree(rootNode, "");
        String b2 = huffman.makeMessage(bericht2);

        //assertEquals("Bericht komt niet overeen", bericht2codeerd, b2);
    }

    @Test
    public void testDecomprimeren()
    {
        huffman = new Huffman();
        huffman.frequency(bericht1);
        Node rootNode = huffman.makeTree(huffman.frequency);
        huffman.printTree(rootNode, "");
        String b1 = huffman.makeMessage(bericht1);


        //assertEquals("Bericht komt niet overeen", bericht1, huffman.decode(rootNode, null, bericht1codeerd.toCharArray(), 0, new StringBuilder()));
        assertEquals("Bericht komt niet overeen", bericht1, huffman.decode(rootNode, null, b1.toCharArray(), 0, new StringBuilder()));

        huffman = new Huffman();
        huffman.frequency(bericht2);
        rootNode = huffman.makeTree(huffman.frequency);
        huffman.printTree(rootNode, "");
        String b2 = huffman.makeMessage(bericht2);


        //assertEquals("Bericht komt niet overeen", bericht2, huffman.decode(rootNode, null, bericht2codeerd.toCharArray(), 0, new StringBuilder()));
        assertEquals("Bericht komt niet overeen", bericht2, huffman.decode(rootNode, null, b2.toCharArray(), 0, new StringBuilder()));
    }
}

