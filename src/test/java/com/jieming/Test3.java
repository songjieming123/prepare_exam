package com.jieming;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class Test3 {
    public static void main(String[] args) {
        CompletableFuture.runAsync(() ->{
            System.out.println("nihao");
            return;
        });

        Student student = null;
//                new Student();

        
    }

    static class Student{
        private String name;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

}
