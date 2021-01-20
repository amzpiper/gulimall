package com.atguigu.gulimall.product;

public class T{

}

class Father<E>{

}

class Son<E> extends Father<E>{

    private E e;

    public  void setData(E e) {
        this.e = e;
    }

    public E getData() {
        return this.e;
    }

    public <H> H sayString(H h){
        System.out.println(h);
        return h;
    }

    public static void main(String[] args) {
        Son<String> t = new Son<String>();
        t.setData("e");
        System.out.println(t.getData());
        t.sayString("123");
        t.sayString(123+1);
    }
}
