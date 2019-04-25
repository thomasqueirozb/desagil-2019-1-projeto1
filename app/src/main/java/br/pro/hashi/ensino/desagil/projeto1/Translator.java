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
        e.setParent(root);
        root.setRight(t);
        t.setParent(root);


        Node i = new Node('i');
        map.put('i',i);
        Node a = new Node('a');
        map.put('a',a);

        Node n = new Node('n');
        map.put('n',n);
        Node m = new Node('m');
        map.put('m',m);


        e.setLeft(i);
        i.setParent(e);
        e.setRight(a);
        a.setParent(e);

        t.setLeft(n);
        n.setParent(t);
        t.setRight(m);
        m.setParent(t);

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
        s.setParent(i);
        i.setRight(u);
        u.setParent(i);

        a.setLeft(r);
        r.setParent(a);
        a.setRight(w);
        w.setParent(a);

        n.setLeft(d);
        d.setParent(n);
        n.setRight(k);
        k.setParent(n);

        m.setLeft(g);
        g.setParent(m);
        m.setRight(o);
        o.setParent(m);

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
        h.setParent(s);
        s.setRight(v);
        v.setParent(s);

        u.setLeft(f);
        f.setParent(u);
        u.setRight(ur);
        ur.setParent(u);

        r.setLeft(l);
        l.setParent(r);
        r.setRight(rr);
        rr.setParent(r);

        w.setLeft(p);
        p.setParent(w);
        w.setRight(j);
        j.setParent(w);

        d.setLeft(b);
        b.setParent(d);
        d.setRight(x);
        x.setParent(d);

        k.setLeft(c);
        c.setParent(k);
        k.setRight(y);
        y.setParent(k);

        g.setLeft(z);
        z.setParent(g);
        g.setRight(q);
        q.setParent(g);

        o.setLeft(ol);
        ol.setParent(o);
        o.setRight(or);
        or.setParent(o);


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
        _5.setParent(h);
        h.setRight(_4);
        _4.setParent(h);

        v.setRight(_3);
        _3.setParent(v);

        ur.setRight(_2);
        _2.setParent(ur);

        rr.setLeft(plus);
        plus.setParent(rr);

        j.setRight(_1);
        _1.setParent(j);

        b.setLeft(_6);
        _6.setParent(b);
        b.setRight(eq);
        eq.setParent(b);

        x.setLeft(back);
        back.setParent(x);

        z.setLeft(_7);
        _7.setParent(z);

        ol.setLeft(_8);
        _8.setParent(ol);

        or.setLeft(_9);
        _9.setParent(or);
        or.setRight(_0);
        _0.setParent(or);
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
        Node cur_node;
        cur_node = this.map.get(c);
        StringBuilder sb = new StringBuilder();


        while (cur_node!=this.root){
            char cur_val = cur_node.getValue();
            Node new_node = cur_node.getParent();

            if (new_node.getLeft() != null){
                if (new_node.getLeft() == cur_node) {
                    sb.append('.');
                } else {
                    sb.append('-');
                }

            } else {
                sb.append('-');
            }
            cur_node = new_node;
        }
        return sb.reverse().toString();
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public LinkedList<String> getCodes() {
        return new LinkedList<>();
    }

}
