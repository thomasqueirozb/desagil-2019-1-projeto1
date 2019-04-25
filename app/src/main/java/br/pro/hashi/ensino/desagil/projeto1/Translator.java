package br.pro.hashi.ensino.desagil.projeto1;

import java.util.HashMap;
import java.util.LinkedList;

// Não é permitido mudar nada nessa classe
// exceto o recheio dos três métodos.

public class Translator {
    private final Node root;
    private final HashMap<Character, Node> map;


    // Você deve mudar o recheio deste construtor,
    // de acordo com os requisitos não-funcionais.
    public Translator() {
        root = new Node(' ');
        map = new HashMap();


        Node e = new Node('e');
        map.put('e',e);
        Node t = new Node('t');
        map.put('t',t);

        root.setLeft(e);
        root.setRight(t);


        Node i = new Node('i');
        map.put('i',i);
        Node a = new Node('a');
        map.put('a',a);

        Node n = new Node('n');
        map.put('n',n);
        Node m = new Node('m');
        map.put('m',m);


        e.setLeft(i);
        e.setRight(a);

        t.setLeft(n);
        t.setRight(m);

        Node s = new Node('s');
        map.put('s',s);
        Node u = new Node('u');
        map.put('u',u);

        Node r = new Node('r');
        map.put('r',r);
        Node w = new Node('w');
        map.put('w',w);

        Node d = new Node('d');
        map.put('d',d);
        Node k = new Node('k');
        map.put('k',k);

        Node g = new Node('g');
        map.put('g',g);
        Node o = new Node('o');
        map.put('o',o);

        i.setLeft(s);
        i.setRight(u);

        a.setLeft(r);
        a.setRight(w);

        n.setLeft(d);
        n.setRight(k);

        m.setLeft(g);
        m.setRight(o);

        Node h = new Node('h');
        map.put('h',h);
        Node v = new Node('v');
        map.put('v',v);

        Node f = new Node('f');
        map.put('f',f);
        Node ur = new Node(' ');

        Node l = new Node('l');
        map.put('l',l);
        Node rr = new Node(' ');

        Node p = new Node('p');
        map.put('p',p);
        Node j = new Node('j');
        map.put('j',j);

        Node b = new Node('b');
        map.put('b',b);
        Node x = new Node('x');
        map.put('x',x);

        Node c = new Node('c');
        map.put('c',c);
        Node y = new Node('y');
        map.put('y',y);

        Node z = new Node('z');
        map.put('z',z);
        Node q = new Node('q');
        map.put('q',q);

        Node ol = new Node(' ');
        Node or = new Node(' ');

        s.setLeft(h);
        s.setRight(v);

        u.setLeft(f);
        u.setRight(ur);

        r.setLeft(l);
        r.setRight(rr);

        w.setLeft(p);
        w.setRight(j);

        d.setLeft(b);
        d.setRight(x);

        k.setLeft(c);
        k.setRight(y);

        g.setLeft(z);
        g.setRight(q);

        o.setLeft(ol);
        o.setRight(or);


        Node _5 = new Node('5');
        map.put('5', _5);
        Node _4 = new Node('4');
        map.put('4', _4);

        Node _3 = new Node('3');
        map.put('3', _3);

        Node _2 = new Node('2');
        map.put('2', _2);

        Node plus = new Node('+');
        map.put('+', plus);


        Node _1 = new Node('1');
        map.put('1', _1);

        Node _6 = new Node('6');
        map.put('6', _6);
        Node eq = new Node('=');
        map.put('=', eq);

        Node back = new Node('/');
        map.put('/', back);

        Node _7 = new Node('7');
        map.put('7', _7);

        Node _8 = new Node('8');
        map.put('8', _8);

        Node _9 = new Node('9');
        map.put('9', _9);
        Node _0 = new Node('0');
        map.put('0', _0);

        h.setLeft(_5);
        h.setRight(_4);

        v.setRight(_3);

        ur.setRight(_2);

        rr.setLeft(plus);

        j.setRight(_1);

        b.setLeft(_6);
        b.setRight(eq);

        x.setLeft(back);

        z.setLeft(_7);

        ol.setLeft(_8);

        or.setLeft(_9);
        or.setRight(_0);
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public char morseToChar(String code) {
        Node cur_node;
        cur_node = this.root;

        for (int i = 0; i < code.length(); i++){
            char c = code.charAt(i);
            if (c == '.') {
                cur_node = cur_node.getLeft();
            } else {
                cur_node = cur_node.getRight();
            }
        }

        return cur_node.getValue();
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public String charToMorse(char c) {
        return " ";
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public LinkedList<String> getCodes() {
        return new LinkedList<>();
    }

}
