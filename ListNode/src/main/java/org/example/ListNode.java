package org.example;

/**
 * Definition for singly-linked list.*/

 public class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {

    ListNode no1 = new ListNode(2);
    ListNode no2 = new ListNode(4);
    ListNode no3 = new ListNode(3);

    ListNode no4 = new ListNode(5);
    ListNode no5 = new ListNode(6);
    ListNode no6 = new ListNode(4);

    public Solution() {
        no1.next = no2;
        no2.next = no3;

        no4.next = no5;
        no5.next = no6;
    }



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode current1 = no1;
        ListNode current2 = no4;

        ListNode invertList = null;
        ListNode invertList2 = null;

        ListNode soma = null;
        while (current1 != null) {
            int val = current1.val;

            ListNode newNode = new ListNode(val);

            newNode.next = invertList;
            invertList = newNode;

            int val2 = current2.val;

            ListNode newNode2 = new ListNode(val2);

            newNode2.next = invertList2;
            invertList2 = newNode2;

            current1 = current1.next;
            current2 = current2.next;
        }

        while (invertList != null && invertList2 != null) {
            int valSoma = invertList.val + invertList2.val;
            ListNode somaList = new ListNode(valSoma);

            somaList.next = soma;
            soma = somaList;

            invertList = invertList.next;
            invertList2 = invertList2.next;
        }
        System.out.println("l1 " + invertList);
        System.out.println("l2 " + invertList2);
        return soma;
    }

    public static void main(String[] args) {

    }
}