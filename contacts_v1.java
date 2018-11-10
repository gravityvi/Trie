import java.util.*;



import java.io.*;

/* given a string add that string*/
/* given a string calculate how many contacts start with that string*/

public class contacts_v1{

   public static  class  Node{
        Node children[] = new Node[26];
        boolean isComplete;
        int count=0;

        private  int getCharacterIndex(char c)
        {   
            return c-'a';
        }
        public Node getNode(char c)
        {
            return children[getCharacterIndex(c)];
        }
        public void setNode(char c, Node node)
        {
            children[getCharacterIndex(c)]= node;
        }

        private void add(char c,boolean isComplete)
        {
            count++;
            Node child = getNode(c);
            if(child==null)
            {
                child = new Node();
                setNode(c, child);
            }
            else
            {
                setNode(c,child);
            }
            child.isComplete=isComplete;
        }





    }

    public static class Trie{
        private Node root;
        
        void Trie()
        {
            // System.out.println("coonstructor called");
            this.root = new Node();
            
        }

        private void addString(String s)
        {
            s=s.toLowerCase();
            if(exists(s))
            {
                System.out.println("Contact already exists");
                return;
            }
            char ch [] = s.toCharArray();
            Node child;
            if(s.length()==1)
            {
                root.add(ch[0],true);
            }
            else
            {
                root.add(ch[0], false);
            }
            child = root.getNode(ch[0]);
            for(int i=1;i<ch.length;i++)
            {
                boolean isComplete;
                if(i==ch.length-1)
                    isComplete=true;
                else
                    isComplete=false;

                child.add(ch[i],isComplete);
                child = child.getNode(ch[i]);
            }


        }
        public int findCount(String s)
        {
            char ch[] = s.toCharArray();
            Node child;
            child=root.getNode(ch[0]);
            if(child==null)
                return 0;
            for(int i=1;i<s.length();i++)
            {
                child = child.getNode(ch[i]);
                if(child==null)
                    return 0;
            }
            return child.count;
        }
        public boolean exists(String s)
        {
            char ch[] = s.toCharArray();
            Node child;
            child=root.getNode(ch[0]);
            if(child==null)
                return false;
            for(int i=1;i<ch.length;i++)
            {
               child = child.getNode(ch[i]);
               if(child==null)
                    return false;
                
            }
            return true;


        }
       
    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.Trie();
        trie.addString("Ravi");
        trie.addString("ravi");
        trie.addString("RaviSawlani");
        System.out.println(trie.findCount("rav"));

        
        
    }
}