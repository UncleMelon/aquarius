package basic;


/**
 * 面向对象的思维解决逢三缺一的问题
 */
public class Count3Quit2 {

    public static void main(String[] args) {
        KidCircle kc = new KidCircle(500);
        int countNum = 0;
        Kid k = kc.first;
        while (kc.count > 1) {
            countNum++;
            if (countNum == 3) {
                kc.delete(k);
                countNum=0;
            }
            k = k.right;
        }

        System.out.println(kc.first.id);

    }
}

class Kid {
    int id;
    Kid left;
    Kid right;
}

class KidCircle {
    int count = 0;
    Kid first, last;

    public KidCircle(int n) {
        for (int i = 0; i < n; i++) {
            add();
        }
    }

    public void add() {
        Kid k = new Kid();
        k.id = count;
        if (count <= 0) {
            first = k;
            last = k;
            k.left = k;
            k.right = k;
        } else {
            last.right = k;
            k.left = last;
            k.right = first;
            first.left = k;
            last = k;
        }
        count ++;
    }

    public void delete(Kid kid) {
        if (count <= 0) {
            return;
        } else if (count == 1) {
            first = last = null;
        } else {
            kid.left.right = kid.right;
            kid.right.left = kid.left;

            if (kid == first) {
                first = kid.right;
            } else if (kid == last) {
                last = kid.left;
            }
            count--;
        }
    }


}
