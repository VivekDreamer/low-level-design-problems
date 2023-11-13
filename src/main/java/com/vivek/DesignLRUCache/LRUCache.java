package com.vivek.DesignLRUCache;

import java.util.HashMap;
import java.util.Map;

//Least Recently Used Cache is implemented...Using HashMap and Doubly LinkedList.
public class LRUCache {

    Map<Integer,Node> cache = new HashMap<>();
    Node head = new Node(0, 0); //dummy head node
    Node tail = new Node(0,0);  //dummy tail node
    int capacity; //capacity of cache

    public LRUCache(int _capacity){
        this.capacity = _capacity;
        head.next = tail;
        tail.prev = head;
    }

    private int get(int key){
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            removeFromCache(node);
            insertInCache(node); //insert just after head
            return node.value;
        }
        else{
            return -1;
        }
    }

    private void put(int _key, int _value){
        if(cache.containsKey(_key)){
            Node node = cache.get(_key);
            removeFromCache(node);
        }
        if(cache.size() == capacity){
            removeFromCache(tail.prev);
        }
        Node newNode = new Node(_key,_value);
        insertInCache(newNode);
    }

    private void removeFromCache(Node node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertInCache(Node node) {
        cache.put(node.key, node);  
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev  = node;
    }

    public static class Node{
        Node prev, next;
        int key , value;
        Node(int _key, int _value){
            this.key = _key;
            this.value = _value;
        }
    }
}
