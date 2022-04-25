package com.lymboy.graph.dfs;

import com.lymboy.graph.base.Graph;

/**
 * 有环图检测 
 */
public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if(dfs(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph G = new Graph("g2.txt");
        CycleDetection graphDFS = new CycleDetection(G);
        System.out.println(graphDFS.hasCycle());
    }
}
