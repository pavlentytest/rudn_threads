package ru.samsung.itschool.mdev;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /* !!!Thread - поток!!!
        Stream API - "стримы"
        Процесс (Программа)
        Поток
        */
        //   new MyThread("+").start(); // вызывается run в отдельном потоке
        ///new MyThread("-").start();
        // [+][-][+][-]
        MyThread thread1 = new MyThread("+");
        thread1.start();
        MyThread thread2 = new MyThread("-");
        thread2.start();

        Thread.sleep(3000);
        thread1.flag = false;
        thread1.join(); // ждет завершение потока
        test("1-Stopped");

    }
    public static Object key = new Object();
    public static void test(String message) {
       // synchronized (key) {
            try {
                System.out.print("[");
                Thread.sleep(10);
                System.out.print(message);
                Thread.sleep(10);
                System.out.print("]");
              //  key.notify(); // возобновляем поток, наход. в режиме ожидания
              //  key.wait(); // кладем поток в режим ожидания
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       // }
    }
}
class MyThread extends Thread {
    private String mess;
    public boolean flag;
    MyThread(String m) {
        this.mess = m;
        this.flag = true;
    }
    @Override
    public void run() {
       // тяжелая по времени логика
        while(this.flag){
            Main.test(this.mess);
        }
    }
}

/*
1) Класс наследник от Thread
2) Имплементация от интерф. Runnable
3) wait() - кладет поток в режим ожид.
4) notify() - возобновл. поток который был в режиме ожид.
5) join() - ждет завершения потока
 */