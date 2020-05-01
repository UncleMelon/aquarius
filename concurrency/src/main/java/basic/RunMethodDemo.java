package basic;

public class RunMethodDemo {

    public static void main(String[] args) {
        testRun1();
        testRun2();
    }


    public static void testRun2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run of Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Run of Thread");
                super.run();
            }
        }.start();
    }

    public static void testRun1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run of Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Run of Thread");
            }
        }.start();

    }
}


/**
首先，我们来看一下JDK的Thread源码，片段 3 如下：
private Runnable target;
public void run() {
    if (target != null) {
        target.run();
    }
}
在run()方法中，首先会检查target是否为空，如果不是，则执行该target的run()方法。

那么，对于上面两段代码的执行，也就清楚了。
在第一个代码段 1 中，重写了Thread的run()方法，同时传入了一个Runnable对象，该对象也实现了run()方法。
该Thread对象调用start()方法后，会执行该对象重写的run()方法，其输出结果也就是Run of Thread，输出完后，
run()方法返回，该线程对象的生命周期也就结束了。

在第二个代码段 2 中，同样也重写了Thread的run()方法，同时传入了一个Runnable对象，实现了run()方法。
唯一不同的是，在Thread重写的run方法中，在打印输出后，还执行了super.run()，这就有意思了。
首先，该线程启动运行后，执行其重写的run()方法，输出Run of Thread。
接下来调用super.run()，也就是调用超类的run()方法，而该超类的run()方法，也就是JDK定义的Thread类的run()，
其执行如上代码段 3 所示；显然target不为空，这时候会调用该对象的run()方法，会输出Run of Runnable.。

如果，上面的Thread并未重写run()方法，那么，执行的结果还是一样。首先会执行该Thread的run()方法，
因为此时并未重写该方法，所以还是会调用JDK定以的run()方法，也就是上面的代码段 3，
在该代码段中会判断target是否为空，显然不是，所以会调用Runnable对象实现的run()方法。

总结：对于Thread(Runnable target ...)，不管传入的Target是否为空，首先都会执行Thread自己的run()方法。
如果重写了该方法且该方法中没有super.run()，那么是永远不会调用Runnable实现的run()方法；
如果没有重写该方法，则会去判断target是否为空，以此来决定调用target实现的run()方法；
如果重写了该方法，且该方法中有super.run()，在执行完该语句之前的所有代码后，会判断target是否为空，
以此来决定调用target实现的run()方法，执行完后，接着执行该语句之后的代码。
 **/