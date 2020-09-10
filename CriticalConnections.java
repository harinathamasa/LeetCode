package leetcode.amazon;


import java.util.*;

/*
*
* Tajan's algorithm - Modified version of DFS
* */
public class CriticalConnections {
    Map<Integer, List<Integer>> graph;
    List<List<Integer>> criticalConnections;
    boolean[] visited;
    int[] visitedTime;
    int[] lowTime;
    int time = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new HashMap<>();
        criticalConnections = new ArrayList<>();
        visitedTime = new int[n];
        lowTime = new int[n];
        visited = new boolean[n];
        buildGraph(n,connections);
        dfs(connections,0,-1);
        return criticalConnections;
    }

    private void dfs(List<List<Integer>> connections,int currentNode, int parentNode){
        visited[currentNode] = true;
        visitedTime[currentNode] = lowTime[currentNode] = ++time;
        for(int neibour:graph.get(currentNode)){
            if(neibour == parentNode) continue;
            if(!visited[neibour]){
                dfs(connections,neibour,currentNode);
                lowTime[currentNode] = Math.min(lowTime[currentNode] ,lowTime[neibour]);
                if(visitedTime[currentNode] < lowTime[neibour]){
                    criticalConnections.add(new ArrayList(Arrays.asList(currentNode,neibour)));
                }
            }else{
                lowTime[currentNode] = Math.min(lowTime[currentNode],visitedTime[neibour]);
            }
        }
    }

    private void buildGraph(int n, List<List<Integer>> connections){

        for(int i=0;i<n;i++){
            graph.put(i,new ArrayList());
        }

        for(List<Integer> list:connections){
            int x = list.get(0);
            int y = list.get(1);
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
    }
}
