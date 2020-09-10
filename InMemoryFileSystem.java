package leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/*
*
*
*588. Design In-Memory File System
*
* Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
* If it is a directory path, return the list of file and directory names in this directory.
* Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
*  If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format.
* If the file doesn't exist, you need to create that file containing given content.
* If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.
*
*
*
 * */
public class InMemoryFileSystem {

    public class File{
        TreeMap<String,File> file = new TreeMap<>();
        boolean isFile = false;
        String content = "";
    }

    File root ;
    public  InMemoryFileSystem(){
        root =  new File();
    }

    public static void main(String[] args) {
        InMemoryFileSystem inMemoryFileSystem = new InMemoryFileSystem();
        List<String> list = inMemoryFileSystem.ls("/");
        System.out.println(Arrays.toString(list.toArray()));
        inMemoryFileSystem.mkdir("/a/b/c");
        list = inMemoryFileSystem.ls("/");
        System.out.println(Arrays.toString(list.toArray()));
        inMemoryFileSystem.addContent("/a/b/c/d","Hello Boss");
        String str = inMemoryFileSystem.getContent("/a/b/c/d");
        System.out.println("Content of file "+str);

    }

    public List<String> ls(String path){
        File current = root;
        List<String> result = new ArrayList<>();
        if(!path.equals("/")){
            String[] pls = path.split("/");
            for(int i=1;i<pls.length;i++){
                current = current.file.get(pls[i]);
            }
            if(current.isFile){
                result.add(pls[pls.length - 1]);
                return result;
            }
        }
        return new ArrayList<>(current.file.keySet());
    }

    public void mkdir(String path){
        File curr = root;
        String[] dirs = path.split("/");
        for(int i=1;i<dirs.length;i++){
            if(!curr.file.containsKey(dirs[i])){
                curr.file.put(dirs[i],new File());
            }
            curr = curr.file.get(dirs[i]);
        }
    }

    public void addContent(String path, String content){

        File curr = root;
        String[] dirs = path.split("/");
        for(int i=1;i<dirs.length-1;i++){
            curr = curr.file.get(dirs[i]);
        }
        if(!curr.file.containsKey(dirs[dirs.length - 1]))
            curr.file.put(dirs[dirs.length - 1],new File());
        curr.file.get(dirs[dirs.length - 1]).isFile = true;
        curr.file.get(dirs[dirs.length - 1]).content += content;
    }

    public String getContent(String path){
        File curr = root;
        String[] dir = path.split("/");
        for(int i=1;i<dir.length - 1;i++){
            curr = curr.file.get(dir[i]);
        }
        return curr.file.get(dir[dir.length - 1]).content;
    }
}
