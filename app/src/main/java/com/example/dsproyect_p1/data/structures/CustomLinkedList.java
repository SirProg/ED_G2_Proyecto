package com.example.dsproyect_p1.data.structures;
import java.util.*;
public class CustomLinkedList<E> {
    private CustomNode<E> head;
    private int size;
    public CustomLinkedList(){
        head=null;
        size=0;
    }
    public void add(E info){
        CustomNode<E> newNode= new CustomNode<>(info);
        if(head==null){
            head=newNode;
            head.next=head;
            head.previous=head;
        }
        else{
            CustomNode<E> before=head.previous;
            before.next=newNode;
            head.previous=newNode;
            newNode.previous=before;
            newNode.next=head;
        }
        size++;
    }
    public void addFirst(E info) {
        CustomNode<E> newNode = new CustomNode<>(info);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.previous = head;
        } else {
            CustomNode<E> before = head.previous;
            before.next = newNode;
            head.previous = newNode;
            newNode.previous = before;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }
    public E getFirst(){
        if(head==null){
            throw new IllegalStateException("La lista está vacía");
        }
        return head.getInfo();
    }
    public E getLast(){
        if(head==null){
            throw new IllegalStateException("La lista está vacía");
        }
        return head.previous.getInfo();
    }
    public E removeFirst(){
        if(head==null){
            throw new IllegalStateException("La lista está vacía");
        }
        E infodeleted=head.getInfo();
        if(head.next==head){
            head=null;
        }
        else{
            CustomNode<E> before=head.previous;
            head=head.next;
            head.previous=before;
            before.next=head;
        }
        size--;
        return infodeleted;
    }
    public  E removeLast(){
        if(head==null){
            throw new IllegalStateException("La lista está vacía");
        }
        E infodeleted= head.previous.getInfo();
        if(head.previous==head){
            head=null;
        }
        else{
            CustomNode<E> before=head.previous;
            before.previous.next=head;
            head.previous=before.previous;
        }
        size--;
        return infodeleted;
    }
    public boolean contains(E info){
        if(head==null){
            return false;
        }
        else{
            CustomNode<E> actual=head;
            do {
                if (actual.getInfo().equals(info)) {
                    return true;
                }
                actual = actual.next;
            } while (actual != head);
            return false;
        }
    }
    public int size(){
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void printList(){
        if(head==null){
            System.out.println("Lista vacia");
            throw new IllegalStateException("La lista está vacía");
        }
        else {
            CustomNode<E> actual = head;
            do {
                System.out.print(actual.getInfo() + " <-> ");
                actual = actual.next;
            }
            while (actual != head);
            System.out.println("(cabeza)");
        }
    }
    public void printListReverse() {
        if(head==null){
            System.out.println("Lista vacia");
            throw new IllegalStateException("La lista está vacía");
        }
        else{
            CustomNode<E> actual=head.previous;
            do {
                System.out.print(actual.getInfo() + " <-> ");
                actual = actual.previous;
            }
            while (actual != head.previous);
            System.out.println("(cabeza reverse)");
        }
    }
}