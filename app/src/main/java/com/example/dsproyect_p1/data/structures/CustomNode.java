package com.example.dsproyect_p1.data.structures;

public class CustomNode<E> {
    private E info;
    CustomNode<E> next;
    CustomNode<E> previous;
    public CustomNode(E info){
        this.info=info;
        next=null;
        previous=null;
    }
    public E getInfo(){
        return info;
    }

}