
### 代码

```java
class Trie {
    private Trie[] root;
    private boolean flag;
    public Trie() {
        root = new Trie[26];
        flag = false;
    }
    
    public void insert(String word) {
         Trie node = this;
         for(int i=0;i<word.length();i++){
            char x = word.charAt(i);
            int index = x-'a';
            if(node.root[index]==null){
                node.root[index] = new Trie();
            }
            node = node.root[index];
         }   
         node.flag = true;
    }
    
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node!=null&&node.flag;
    }
    
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node!=null;
    }
    public Trie searchPrefix(String word){
        Trie node = this;
        for(int i = 0;i<word.length();i++){
            char x = word.charAt(i);
            int index = x-'a';
            if(node.root[index]==null){
                return null;
            }
            node = node.root[index];
        }
        return node;
    }
}
//2.调用库函数
// class Trie {
//     HashSet<String> set;
//     public Trie() {
//         set = new HashSet<>();
//     }
    
//     public void insert(String word) {
//             set.add(word);
//     }
    
//     public boolean search(String word) {
//             if(set.contains(word)){
//                 return true;
//             }
//             return false;
//     }
    
//     public boolean startsWith(String prefix) {
//         for(String s:set){
//             if(s.startsWith(prefix)){
//                 return true;
//             }
//         }
//         return false;
//     }
// }
```