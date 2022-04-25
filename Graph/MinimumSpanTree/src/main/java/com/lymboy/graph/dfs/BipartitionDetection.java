package com.lymboy.graph.dfs;

import java.util.ArrayList;
import java.util.List;

import com.lymboy.graph.base.Graph;

/**
 * 深度优先遍历
 */
public class BipartitionDetection {

    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = -1;
        }
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if(!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if(!dfs(w, 1-color)) {
                    return false;
                }
            } else if (colors[v] == colors[w]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph G = new Graph("g2.txt");
        BipartitionDetection graphDFS = new BipartitionDetection(G);
        System.out.println(graphDFS.isBipartite());
    }
}
